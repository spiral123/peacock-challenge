package com.challenge.ioc.errors

import kotlin.reflect.KClass

class BindingException(implementation: KClass<*>, protocol: KClass<*>)
    : Exception("Binding Exception: ${implementation.simpleName} must implement ${protocol.simpleName}")