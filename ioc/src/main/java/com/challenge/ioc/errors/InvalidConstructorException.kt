package com.challenge.ioc.errors

import kotlin.reflect.KClass
import kotlin.reflect.KParameter

class InvalidConstructorException(implementation: KClass<*>, param: KParameter) :
    Exception("class: \"${implementation.simpleName}\" construction parameter: \"${param.name}\" must be a type/interface")