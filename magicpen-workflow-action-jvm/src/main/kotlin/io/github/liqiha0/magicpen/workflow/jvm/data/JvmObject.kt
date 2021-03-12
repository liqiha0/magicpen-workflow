package io.github.liqiha0.magicpen.workflow.jvm.data

import io.github.liqiha0.magicpen.workflow.Data

class JvmObject(val value: Any) : Data {
    override val type: String get() = "jvm-object"
    override fun toBoolean(): Boolean {
        return true
    }
}