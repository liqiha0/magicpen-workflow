package io.github.liqiha0.magicpen.workflow

data class PackageManifest(val actions: List<ActionDescription>)

data class ActionDescription(
    val displayName: String,
    val name: String,
    val parameters: List<ActionParameterDescription> = emptyList(),
    val results: List<ActionResultDescription> = emptyList()
)

data class ActionParameterDescription(val displayName: String, val name: String, val type: List<String> = emptyList())

data class ActionResultDescription(val displayName: String, val type: String, val name: String)