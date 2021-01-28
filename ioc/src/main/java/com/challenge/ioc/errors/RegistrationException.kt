package com.challenge.ioc.errors

import kotlin.reflect.KClass

class RegistrationException(type: KClass<*>, implementation: KClass<*>) :
    Exception("${implementation.simpleName} must implement interface of ${type.simpleName}")