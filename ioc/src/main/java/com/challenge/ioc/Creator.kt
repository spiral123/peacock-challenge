package com.challenge.ioc

import com.challenge.ioc.errors.InvalidConstructorException
import kotlin.reflect.KClass

class Creator(val registry: Registry) {

    fun createType(type: KClass<*>): Any {
        return create(registry.getBinding(type))
    }

    private fun create(binding: Binding): Any {
        val targetConstructor = binding.implementation.constructors.first()
        val targetParams = targetConstructor.parameters.toList()

        val dependencies = mutableListOf<Any>()
        targetParams.forEach { param ->
            val paramType = param.type.classifier as KClass<*>
            if (!paramType.java.isInterface) {
                throw (InvalidConstructorException(binding.implementation, param))
            }
            val instance = create(registry.getBinding(paramType))
            dependencies.add(instance)
        }

        return targetConstructor.call(*dependencies.toTypedArray())
    }
}