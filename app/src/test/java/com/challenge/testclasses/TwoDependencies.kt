package com.challenge.testclasses

interface ITwoDependencies

class TwoDependencies(val twoDependency1: INoDependency1, val twoDependency2: INoDependency2) :
    ITwoDependencies