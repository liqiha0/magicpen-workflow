package io.github.liqiha0.magicpen.workflow.jvm.converter

import io.github.liqiha0.magicpen.workflow.jvm.Converter
import io.github.liqiha0.magicpen.workflow.jvm.data.JvmObject

class JvmObjectUnboxingConverter : Converter<JvmObject, Any> {
    override fun convert(value: JvmObject): Any {
        return value.value
    }

}

class JvmObjectBoxingConverter : Converter<Any, JvmObject> {
    override fun convert(value: Any): JvmObject {
        return JvmObject(value)
    }
}