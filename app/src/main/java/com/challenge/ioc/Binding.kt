package com.challenge.ioc

import kotlin.reflect.KClass

class Binding(val type: KClass<*>, val implementation: KClass<*>)