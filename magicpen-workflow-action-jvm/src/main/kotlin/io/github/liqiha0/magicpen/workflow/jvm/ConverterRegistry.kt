package io.github.liqiha0.magicpen.workflow.jvm

import io.github.liqiha0.magicpen.workflow.Data

interface ConverterRegistry {
    fun getInputConverter(sourceType: String): Converter<Data, Any>?
    fun getOutputConverter(sourceType: Class<*>): Converter<Any, Data>?
}