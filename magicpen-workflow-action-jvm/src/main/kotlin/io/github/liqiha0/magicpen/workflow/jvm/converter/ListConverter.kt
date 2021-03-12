package io.github.liqiha0.magicpen.workflow.jvm.converter

import io.github.liqiha0.magicpen.workflow.jvm.ConverterFactory

private val converterFactory = ConverterFactory()

//class ListToNativeListConverter : Converter<List, kotlin.collections.List<*>> {
//    override fun convert(value: List): kotlin.collections.List<*> {
//        return value.value.map {
//            converterFactory.getInputConverter(it.type)?.convert(it)
//                ?: throw UnexpectedDataTypeException(it.type)
//        }
//    }
//}
//
//class NativeListToListConverter : Converter<kotlin.collections.List<*>, List> {
//    override fun convert(value: kotlin.collections.List<*>): List {
//        return List(value.map { converterFactory.getOutputConverter(it::class.java).convert(it) })
//    }
//}