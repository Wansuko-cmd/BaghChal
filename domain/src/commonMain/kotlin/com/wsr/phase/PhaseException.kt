package com.wsr.phase

sealed class PhaseException : Exception() {
    class InvalidCoordinateException : PhaseException()
    class NotInRangeException : PhaseException()
}
