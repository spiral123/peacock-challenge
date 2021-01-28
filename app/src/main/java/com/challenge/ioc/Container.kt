package com.challenge.ioc

import com.challenge.ioc.errors.RegistrationException
import com.challenge.ioc.errors.ResolutionException
import kotlin.reflect.KClass

class Container() {

    private var bindings: MutableMap<KClass<*>, KClass<*>> = mutableMapOf()

    /***
     * usage: register<interface, implementation>()
     */
    inline fun <reified Type : Any, reified Implementation : Any> register() {
        register(Type::class, Implementation::class)
    }

    /***
     * usage: register(interface, implementation)
     */
    fun register(type: KClass<*>, implementation: KClass<*>) {
        if (!type.java.isAssignableFrom(implementation.java)) {
            throw (RegistrationException(type, implementation))
        }
        bindings[type] = implementation
    }

    inline fun <reified Type : Any> resolve(): Any {
        return resolve(Type::class)
    }

    fun resolve(type: KClass<*>): Any {
        return create(getBinding(type))

//        try {
//            return creator.create(bindings, activationContext)
//        } catch (ex: StackOverflowError) {
//            throw CircularDependencyException(ex)
//        }
    }

    private fun create(type: KClass<*>): Any {
        val targetConstructor = type.constructors.first()
        val targetParams = targetConstructor.parameters.toList()

        val dependencies = mutableListOf<Any>()
        targetParams.forEach { param ->
            val paramType = param.type.classifier as KClass<*>
            val instance = create(getBinding(paramType))
            dependencies.add(instance)
        }

        return targetConstructor.call(*dependencies.toTypedArray())
    }

    private fun getBinding(type: KClass<*>): KClass<*> {
        if (!bindings.containsKey(type)) {
            throw (ResolutionException(type))
        }
        return bindings[type]!!
    }

}