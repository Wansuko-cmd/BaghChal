package com.wsr.phase

import com.wsr.board.Board

interface Phase<T> {
    fun process(value: T): PhaseResult
}

data class PhaseResult(
    val board: Board,
    val placedGoat: Int = 0,
    val killedGoat: Int = 0,
)
