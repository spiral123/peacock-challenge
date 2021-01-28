package com.challenge.testclasses

interface IHasBadConstructor

class HasBadConstructor(val badParam: OneDependency) : IHasBadConstructor