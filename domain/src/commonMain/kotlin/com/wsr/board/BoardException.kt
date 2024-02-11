package com.wsr.board

sealed class BoardException : Exception() {
    data object NotInRangeException : BoardException()
}
