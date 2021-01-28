package com.challenge.ioc.errors

class CircularDependencyException() :
    Exception("There is a circular dependency in the graph")