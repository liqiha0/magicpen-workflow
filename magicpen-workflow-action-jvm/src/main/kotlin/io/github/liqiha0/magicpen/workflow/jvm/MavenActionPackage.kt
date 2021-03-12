package io.github.liqiha0.magicpen.workflow.jvm

import io.github.liqiha0.magicpen.workflow.Action
import io.github.liqiha0.magicpen.workflow.ActionPackage
import io.github.liqiha0.magicpen.workflow.PackageManifest
import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.repository.RemoteRepository
import org.eclipse.aether.resolution.ArtifactRequest
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.http.HttpTransporterFactory
import java.net.URLClassLoader
import java.nio.file.Path
import java.util.*

private val serviceLocator = MavenRepositorySystemUtils.newServiceLocator().apply {
    this.addService(RepositoryConnectorFactory::class.java, BasicRepositoryConnectorFactory::class.java)
    this.addService(TransporterFactory::class.java, HttpTransporterFactory::class.java)
}
private val system = serviceLocator.getService(RepositorySystem::class.java)
private val localRepo = LocalRepository(Path.of(System.getProperty("user.home"), ".m2", "repository").toString())
private val defaultRemoteRepositories =
    listOf(RemoteRepository.Builder(UUID.randomUUID().toString(), "default", "https://repo1.maven.org/maven2/").build())
private val session = MavenRepositorySystemUtils.newSession().apply {
    this.localRepositoryManager = system.newLocalRepositoryManager(this, localRepo)
}

class MavenActionPackage(artifactId: String, remoteRepositoryUrl: String? = null) : ActionPackage {
    private val remoteRepository = remoteRepositoryUrl?.let {
        listOf(
            RemoteRepository.Builder(UUID.randomUUID().toString(), "default", remoteRepositoryUrl).build()
        )
    }
    private val artifactRequest = ArtifactRequest(
        DefaultArtifact(artifactId),
        remoteRepository ?: defaultRemoteRepositories,
        null
    )
    private val file = system.resolveArtifact(session, artifactRequest).artifact.file

    override val manifest: PackageManifest
        get() = TODO("Not yet implemented")

    override fun getAction(name: String): Action {
        val classLoader = URLClassLoader(arrayOf(this.file.toURI().toURL()))
        classLoader.use {
            val instance = classLoader.loadClass(name).getConstructor().newInstance()
            return JvmAction(instance)
        }
    }
}