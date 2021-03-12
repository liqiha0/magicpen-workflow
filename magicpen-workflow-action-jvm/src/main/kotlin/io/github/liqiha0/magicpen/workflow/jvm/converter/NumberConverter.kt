package io.github.liqiha0.magicpen.workflow.jvm.converter

import io.github.liqiha0.magicpen.workflow.data.Number
import io.github.liqiha0.magicpen.workflow.jvm.Converter
import java.math.BigDecimal

class NumberToBigDecimalConverter : Converter<Number, BigDecimal> {
    override fun convert(value: Number): BigDecimal = value.value
}

class BigDecimalToNumberConverter : Converter<BigDecimal, Number> {
    override fun convert(value: BigDecimal): Number = Number(value)
}