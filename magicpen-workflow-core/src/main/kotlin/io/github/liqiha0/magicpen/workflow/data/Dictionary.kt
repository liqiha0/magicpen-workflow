package io.github.liqiha0.magicpen.workflow.data

import io.github.liqiha0.magicpen.workflow.Data
import kotlin.Boolean

class Dictionary(val value: Map<String, Data?>) : Data {
    override val type: String get() = "dictionary"
    override fun toBoolean(): Boolean = value.isNotEmpty()
}