package com.challenge.ioc.errors

import kotlin.reflect.KClass

class ResolutionException(type: KClass<*>) :
    Exception("Resolution Exception: ${type.simpleName} has not been registered")