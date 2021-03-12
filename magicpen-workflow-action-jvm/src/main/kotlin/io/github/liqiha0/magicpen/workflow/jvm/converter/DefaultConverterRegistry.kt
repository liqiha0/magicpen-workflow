package io.github.liqiha0.magicpen.workflow.jvm.converter

import io.github.liqiha0.magicpen.workflow.Data
import io.github.liqiha0.magicpen.workflow.jvm.Converter
import io.github.liqiha0.magicpen.workflow.jvm.ConverterRegistry
import java.math.BigDecimal

private val defaultInputConverter: Map<String, Converter<*, *>> = mapOf(
    "text" to TextToStringConverter(),
    "number" to NumberToBigDecimalConverter(),
    "boolean" to BooleanInputConverter(),
    "dictionary" to DictionaryToMapConverter(),
    "jvm-object" to JvmObjectUnboxingConverter()
)

private val defaultOutputConverter: Map<Class<*>, Converter<*, *>> = mapOf(
    String::class.java to StringToTextConverter(),
    BigDecimal::class.java to BigDecimalToNumberConverter(),
    Boolean::class.java to BooleanOutputConverter(),
    java.lang.Boolean::class.java to BooleanOutputConverter(),
    Map::class.java to MapToDictionaryConverter()
)

class DefaultConverterRegistry : ConverterRegistry {
    override fun getInputConverter(sourceType: String): Converter<Data, Any>? {
        @Suppress("unchecked_cast")
        return defaultInputConverter[sourceType] as? Converter<Data, Any>
    }

    override fun getOutputConverter(sourceType: Class<*>): Converter<Any, Data>? {
        @Suppress("unchecked_cast")
        return defaultOutputConverter[sourceType] as? Converter<Any, Data>
    }
}