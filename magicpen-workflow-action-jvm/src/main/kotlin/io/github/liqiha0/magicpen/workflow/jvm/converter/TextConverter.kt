package io.github.liqiha0.magicpen.workflow.jvm.converter

import io.github.liqiha0.magicpen.workflow.data.Text
import io.github.liqiha0.magicpen.workflow.jvm.Converter

class TextToStringConverter : Converter<Text, String> {
    override fun convert(value: Text): String = value.value
}

class StringToTextConverter : Converter<String, Text> {
    override fun convert(value: String): Text = Text(value)
}