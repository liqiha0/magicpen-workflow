package io.github.liqiha0.magicpen.workflow.data

import io.github.liqiha0.magicpen.workflow.Data
import kotlin.Boolean

class Boolean(val value: Boolean) : Data {
    override val type: String get() = "boolean"
    override fun toBoolean(): Boolean = value

    override fun toString(): String = value.toString()
    override fun equals(other: Any?): Boolean =
        (this === other) || other is io.github.liqiha0.magicpen.workflow.data.Boolean && value == other.value

    override fun hashCode(): Int = value.hashCode()
}