package com.challenge.ioc

import com.challenge.ioc.errors.BindingException
import com.challenge.ioc.errors.CircularDependencyException
import com.challenge.ioc.errors.InvalidConstructorException
import com.challenge.ioc.errors.RegistrationException
import com.challenge.testclasses.*
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
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
    fun verifyCanRegisterGoodTypeAndImplementation() {
        sut.register<INoDependency1, NoDependency1>()
    }

    @Test
    fun verifyCannotRegisterTypeWithBadImplementation() {
        assertThrows(RegistrationException::class.java) {
            sut.register<INoDependency1, NoInterface>()
        }
    }

    @Test
    fun attemptToResolveNonRegisteredTypeThrowsError() {
        assertThrows(BindingException::class.java) {
            sut.resolve<INoDependency1>()
        }
    }

    @Test
    fun verifyResolutionOfSimpleType() {
        sut.register<INoDependency1, NoDependency1>()

        val instance = sut.resolve<INoDependency1>()
        assertTrue(instance is NoDependency1)
    }

    @Test
    fun verifyResolutionOfTypeWhereImplementationHasOneDependency() {
        sut.register<IOneDependency, OneDependency>()
        sut.register<INoDependency1, NoDependency1>()

        val instance = sut.resolve<IOneDependency>()
        assertTrue(instance is OneDependency)
    }

    @Test
    fun verifyResolutionOfTypeWhereImplementationHasTwoDependencies() {
        sut.register<INoDependency1, NoDependency1>()
        sut.register<INoDependency2, NoDependency2>()
        sut.register<ITwoDependencies, TwoDependencies>()

        val instance = sut.resolve<ITwoDependencies>()
        assertTrue(instance is TwoDependencies)
    }

    @Test
    fun verifyResolutionOfComplexGraph() {
        sut.register<INoDependency1, NoDependency1>()
        sut.register<INoDependency2, NoDependency2>()
        sut.register<ITwoDependencies, TwoDependencies>()
        sut.register<INested, Nested>()

        val instance = sut.resolve<INested>()
        assertTrue(instance is Nested)
    }

    @Test
    fun verifyResolutionOfInvalidImplementationParameterFails() {
        sut.register<IHasBadConstructor, HasBadConstructor>()

        assertThrows(InvalidConstructorException::class.java) {
            sut.resolve<IHasBadConstructor>()
        }
    }

    @Test
    fun verifyResolutionOfCoDependantImplementationFails() {
        sut.register<ICircularDependency1, CircularDependency1>()
        sut.register<ICircularDependency2, CircularDependency2>()

        assertThrows(CircularDependencyException::class.java) {
            sut.resolve<ICircularDependency1>()
        }
    }
}