package com.challenge.testclasses

interface INested

class Nested(val nestedDep1: INoDependency1, val nestedDep2: ITwoDependencies) : INested