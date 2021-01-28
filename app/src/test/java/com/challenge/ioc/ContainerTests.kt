package com.challenge.ioc

import com.challenge.ioc.errors.CircularDependencyException
import com.challenge.ioc.errors.RegistrationException
import com.challenge.ioc.errors.ResolutionException
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
    fun register_typeThenImplementationIsGood() {
        sut.register<INoDependency1, NoDependency1>()
    }

    @Test
    fun register_implementationMustImplementType() {
        assertThrows(RegistrationException::class.java) {
            sut.register<NoInterface, INoDependency1>()
        }
    }

    @Test
    fun attemptToResolveNonRegisteredTypeThrowsError() {
        assertThrows(ResolutionException::class.java) {
            sut.resolve<NoInterface>()
        }
    }

    @Test
    fun resolve_typeWithNoDependencies() {
        sut.register<INoDependency1, NoDependency1>()

        val instance = sut.resolve<INoDependency1>()
        assertTrue(instance is NoDependency1)
    }

    @Test
    fun resolve_typeWithOneDependency() {
        sut.register<INoDependency1, NoDependency1>()

        val instance = sut.resolve<INoDependency1>()
        assertTrue(instance is NoDependency1)
    }

    @Test
    fun resolve_typeWithTwoDependencies() {
        sut.register<INoDependency1, NoDependency1>()
        sut.register<INoDependency2, NoDependency2>()
        sut.register<ITwoDependencies, TwoDependencies>()

        val instance = sut.resolve<ITwoDependencies>()
        assertTrue(instance is TwoDependencies)
    }

    @Test
    fun resolve_complexGraph() {
        sut.register<INoDependency1, NoDependency1>()
        sut.register<INoDependency2, NoDependency2>()
        sut.register<ITwoDependencies, TwoDependencies>()
        sut.register<INested, Nested>()

        val instance = sut.resolve<INested>()
        assertTrue(instance is Nested)
    }

    @Test
    fun resolve_circularDependencyFails() {
        sut.register<ICircularDependency1, CircularDependency1>()
        sut.register<ICircularDependency2, CircularDependency2>()

        assertThrows(CircularDependencyException::class.java) {
            sut.resolve<ICircularDependency1>()
        }
    }
}