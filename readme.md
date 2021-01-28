AnimalOnDemand 
==============

# Demonstration

The AnimalOnDemand app should build as an ordinary Android project.

All that should happen is that the MainActivity should show a screen with the word "Dog" on it

# Usage

The IoC container should be initialized as soon as possible during app startup: in this case it's done in
the custom AnimalOnDemand application class.

For the example I created an extension function for the container (in ``Ioc.kt``) that registers the types 
used in the sample:

```kotlin
    fun Container.build(): Container {
        this.register<INetwork, NetworkHandler>()
        this.register<IAnalytics, AnalyticsHandler>()
        this.register<IMainViewModel, MainViewModel>()
    
        return this
    }
```

``Types.kt`` contains the interfaces used in the examples and the concrete classes are in the **handlers** and
**viewmodels** packages.

All activity classes should inherit from a ``BaseActivity`` class which fetches a reference to the 
singleton container created in the application class.

within the activity, the implementations can be fetched as follows:

```kotlin
    lateinit var analytics: IAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            analytics = container.resolve()
        }
```

# ioc local library module

The only methods exposed by the library to consumer applications are ``register<type, implementation>()``
and ``resolve<type>()``.

I've separated out the Registry and Creator functionality into separate classes as a step towards making
them more extensible.

The tests try to cover all the error conditions I could think of and hopefully are fairly comprehensive.

# Improvements

I've not written anything like this before so it's been an interesting exercise.  Looking at the code 
there are a number of areas I can immediately see where the library could be improved:

* There is no concept of Scoping: at least providing support for a Singleton scope would be good.
* Improved validation.  It should be possible to add a ``checkGraph()`` function that could be called 
after all the types have been registered that would create the dependency graph and check it for 
cyclic dependencies and also to ensure that all the required dependencies were present.
