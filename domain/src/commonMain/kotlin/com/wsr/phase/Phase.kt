package com.wsr.phase

import com.wsr.board.Board

sealed interface Phase<out T : Movement> {
    val movements: List<T>

    fun process(movement: @UnsafeVariance T): PhaseResult
}

data class PhaseResult(
    val board: Board,
    val placedGoat: Int = 0,
    val killedGoat: Int = 0,
)
