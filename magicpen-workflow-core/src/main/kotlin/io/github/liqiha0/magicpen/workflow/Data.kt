package io.github.liqiha0.magicpen.workflow

interface Data {
    val type: String
    fun toBoolean(): Boolean
}