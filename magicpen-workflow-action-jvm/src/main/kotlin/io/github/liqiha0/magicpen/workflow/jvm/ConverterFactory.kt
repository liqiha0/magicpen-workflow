package io.github.liqiha0.magicpen.workflow.jvm

import io.github.liqiha0.magicpen.workflow.Data
import io.github.liqiha0.magicpen.workflow.jvm.converter.DefaultConverterRegistry
import io.github.liqiha0.magicpen.workflow.jvm.converter.JvmObjectBoxingConverter
import java.util.*

private val defaultConverterRegistry = DefaultConverterRegistry()
private val defaultOutputConverter = JvmObjectBoxingConverter()

class ConverterFactory {
    private val registries: Collection<ConverterRegistry> = ServiceLoader.load(ConverterRegistry::class.java).toList()

    fun getInputConverter(source: String): Converter<Data, Any>? {
        registries.forEach {
            it.getInputConverter(source)?.let { return it }
        }
        return defaultConverterRegistry.getInputConverter(source)
    }

    fun getOutputConverter(source: Class<*>): Converter<Any, Data> {
        registries.forEach {
            it.getOutputConverter(source)?.let { return it }
        }
        return defaultConverterRegistry.getOutputConverter(source) ?: defaultOutputConverter
    }
}