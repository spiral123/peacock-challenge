package com.challenge.ioc

import com.challenge.ioc.errors.BindingException
import kotlin.reflect.KClass

class Container() {

    var bindings: MutableMap<KClass<*>, KClass<*>> = mutableMapOf()

    /***
     * usage: register<class, interface>()
     */
    inline fun <reified T1: Any, reified T2: Any> register() {
        if (!T2::class.java.isAssignableFrom(T1::class.java)) {
            throw (BindingException(T1::class, T2::class))
        }
        bindings[T1::class] = T2::class
    }

//    inline fun <reified T : Any> resolve(): T {
//        return resolve(T::class) as T
//    }

//    fun resolve(requestedType: KClass<*>): Any {
//        val bindings = registrations.retrieveBindingFor(requestedType)
//        val activationContext = ActivationContext(requestedType)
//
//        try {
//            return creator.create(bindings, activationContext)
//        } catch (ex: StackOverflowError) {
//            throw CircularDependencyException(ex)
//        }
//    }
//
//    private fun create(target: KClass<*>): Any {
//
//    }

}