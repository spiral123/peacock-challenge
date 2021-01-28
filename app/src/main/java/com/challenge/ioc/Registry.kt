package com.challenge.ioc

import com.challenge.ioc.errors.BindingException
import kotlin.reflect.KClass

class Registry {

    private var bindings: MutableMap<KClass<*>, Binding> = mutableMapOf()

    fun putBinding(type: KClass<*>, implementation: KClass<*>) {
        bindings[type] = Binding(type, implementation)
    }

    fun getBinding(type: KClass<*>): Binding {
        if (!bindings.containsKey(type)) {
            throw (BindingException(type))
        }
        return bindings[type]!!
    }

}