package io.github.liqiha0.magicpen.workflow

class UnexpectedDataTypeException(val dataType: String = "null") : Exception(dataType)