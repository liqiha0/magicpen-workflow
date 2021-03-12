package io.github.liqiha0.magicpen.workflow.jvm.converter

import io.github.liqiha0.magicpen.workflow.data.Dictionary
import io.github.liqiha0.magicpen.workflow.jvm.Converter
import io.github.liqiha0.magicpen.workflow.jvm.ConverterFactory

private val converterFactory = ConverterFactory()

class DictionaryToMapConverter : Converter<Dictionary, Map<String, *>> {
    override fun convert(value: Dictionary): Map<String, *> {
        return value.value.mapValues {
            return@mapValues it.value?.let { converterFactory.getInputConverter(it.type)?.convert(it) }
        }
    }
}

class MapToDictionaryConverter : Converter<Map<String, *>, Dictionary> {
    override fun convert(value: Map<String, *>): Dictionary {
        return Dictionary(value.mapValues {
            return@mapValues it.value?.let { converterFactory.getOutputConverter(it::class.java).convert(it) }
        })
    }
}
