package com.challenge.testclasses

interface IOneDependency

class OneDependency(val dependency1: INoDependency1) : IOneDependency