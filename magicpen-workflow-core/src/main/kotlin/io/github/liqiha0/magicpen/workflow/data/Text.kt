package io.github.liqiha0.magicpen.workflow.data

import io.github.liqiha0.magicpen.workflow.Data
import kotlin.Boolean

class Text(val value: String) : Data {
    override val type: String = "text"
    override fun toBoolean(): Boolean = this.value.isNotEmpty()

    override fun toString(): String = this.value
    override fun equals(other: Any?): Boolean = this === other || other is Text && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}