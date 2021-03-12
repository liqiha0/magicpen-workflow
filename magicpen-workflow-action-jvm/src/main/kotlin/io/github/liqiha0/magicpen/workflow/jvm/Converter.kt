package io.github.liqiha0.magicpen.workflow.jvm

interface Converter<in I, out O> {
    fun convert(value: I): O
}