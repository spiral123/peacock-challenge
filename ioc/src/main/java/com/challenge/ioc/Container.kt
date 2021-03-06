package com.challenge.ioc

import com.challenge.ioc.errors.CircularDependencyException
import com.challenge.ioc.errors.RegistrationException
import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf

class Container() {

    internal var registry = Registry()
    private val creator = Creator(registry)

    /***
     * usage: register<interface, implementation>()
     */
    inline fun <reified Type : Any, reified Implementation : Any> register() {
        register(Type::class, Implementation::class)
    }

    @PublishedApi
    internal fun register(type: KClass<*>, implementation: KClass<*>) {
        if (!type.isSuperclassOf(implementation)) {
            throw (RegistrationException(type, implementation))
        }
        registry.putBinding(type, implementation)
    }

    /***
     * usage: resolve<interface>()
     */
    inline fun <reified Type : Any> resolve(): Type {
        return resolve(Type::class) as Type
    }

    @PublishedApi
    internal fun resolve(type: KClass<*>): Any {
        try {
            return creator.createType(type)
        } catch (ex: StackOverflowError) {
            throw CircularDependencyException()
        }
    }

}