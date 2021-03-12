package io.github.liqiha0.magicpen.workflow.jvm

import io.github.liqiha0.magicpen.workflow.Action
import io.github.liqiha0.magicpen.workflow.ActionContext
import io.github.liqiha0.magicpen.workflow.Data
import io.github.liqiha0.magicpen.workflow.UnexpectedDataTypeException
import kotlin.reflect.KCallable
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.valueParameters

open class JvmAction(
    private val action: Any,
    private val methodName: String = "execute"
) : Action {
    private val method: KCallable<*> = action::class.functions.find { it.name == methodName }
        ?: throw IllegalArgumentException("Action object must contain a method named $methodName")

    private val parameterList = this.method.valueParameters
    private val converterFactory = ConverterFactory()

    override fun execute(context: ActionContext): Map<String, Data>? {
        val args = buildMethodArgs(context.arguments)
        val returnValue = method.call(action, *args)
        return if (returnValue != null && returnValue != Unit) {
            return if (returnValue is Result) {
                returnValue.data.mapValues {
                    converterFactory.getOutputConverter(it::class.java).convert(it)
                }
            } else {
                val converter = converterFactory.getOutputConverter(returnValue::class.java)
                mapOf("result" to converter.convert(returnValue))
            }
        } else {
            null
        }
    }

    open fun buildMethodArgs(arguments: Map<String, Data?>): Array<Any?> {
        return this.parameterList.map {
            val annotation = it.findAnnotation<Param>()
            val parameterName = annotation?.value ?: it.name
            return@map if (parameterName != null) {
                arguments[parameterName]?.run {
                    val converter = converterFactory.getInputConverter(this.type)
                        ?: throw UnexpectedDataTypeException(this.type)
                    converter.convert(this)
                }
            } else {
                null
            }
        }.toTypedArray()
    }
}