package io.github.liqiha0.magicpen.workflow.data

import io.github.liqiha0.magicpen.workflow.Data
import java.math.BigDecimal
import kotlin.Boolean

class Number(val value: BigDecimal) : Data {
    override val type: String get() = "number"
    override fun toBoolean(): Boolean = value != BigDecimal.ZERO

    override fun toString(): String = value.toString()
    override fun equals(other: Any?): Boolean = this === other || other is Number && value == other.value
    override fun hashCode(): Int = value.hashCode()
}