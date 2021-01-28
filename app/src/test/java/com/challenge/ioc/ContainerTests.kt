package com.challenge.ioc

import com.challenge.ioc.errors.BindingException
import com.challenge.testclasses.Class1
import com.challenge.testclasses.Class1a
import com.challenge.testclasses.IClass1
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ContainerTests {

    private lateinit var sut: Container

    @Before
    fun setUp() {
        sut = Container()
    }

    @Test
    fun validBindingThrowsNoError() {
        sut.register<Class1, IClass1>()
    }

    @Test
    fun bindingImplementationMustImplementInterface() {
        assertThrows(BindingException::class.java) {
            sut.register<Class1a, IClass1>()
        }
    }
}