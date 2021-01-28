package com.challenge.testclasses

interface ICircularDependency1

class CircularDependency1(val dependency: ICircularDependency2) : ICircularDependency1