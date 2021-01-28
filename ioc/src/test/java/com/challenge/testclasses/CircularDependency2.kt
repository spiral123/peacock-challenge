package com.challenge.testclasses

interface ICircularDependency2

class CircularDependency2(val dependency: ICircularDependency1) : ICircularDependency2