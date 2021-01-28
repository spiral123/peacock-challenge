package com.challenge.ioc

import com.challenge.ioc.errors.RegistrationException
import com.challenge.ioc.errors.ResolutionException
import com.challenge.testclasses.Class1
import com.challenge.testclasses.Class1a
import com.challenge.testclasses.IClass1
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

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
    fun register_typeThenImplementationIsGood() {
        sut.register<IClass1, Class1>()
    }

    @Test
    fun register_implementationMustImplementType() {
        assertThrows(RegistrationException::class.java) {
            sut.register<Class1a, IClass1>()
        }
    }

    @Test
    fun resolve_nonRegisteredTypeIsNotGood() {
        assertThrows(ResolutionException::class.java) {
            sut.resolve<Class1a>()
        }
    }
}