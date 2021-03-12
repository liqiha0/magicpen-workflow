package io.github.liqiha0.magicpen.workflow.data

import io.github.liqiha0.magicpen.workflow.Data
import kotlin.Boolean
import kotlin.collections.List

class List(val value: List<Data?>) : Data {
    override val type: String get() = "list"
    override fun toBoolean(): Boolean = value.isNotEmpty()
}