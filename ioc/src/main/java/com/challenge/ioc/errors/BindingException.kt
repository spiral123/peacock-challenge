package com.challenge.ioc.errors

import kotlin.reflect.KClass

class BindingException(type: KClass<*>) :
    Exception("${type.simpleName} has not been registered")