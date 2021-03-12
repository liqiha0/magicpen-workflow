package io.github.liqiha0.magicpen.workflow.jvm.converter

import io.github.liqiha0.magicpen.workflow.data.Boolean
import io.github.liqiha0.magicpen.workflow.jvm.Converter

class BooleanInputConverter : Converter<Boolean, kotlin.Boolean> {
    override fun convert(value: Boolean): kotlin.Boolean = value.value
}

class BooleanOutputConverter : Converter<kotlin.Boolean, Boolean> {
    override fun convert(value: kotlin.Boolean): Boolean = Boolean(value)
}