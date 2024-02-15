package com.wsr.phase

import com.wsr.board.Board

sealed class Phase<out T : Movement> {
    abstract val movements: List<T>

    internal abstract fun process(movement: @UnsafeVariance T): PhaseResult
}

data class PhaseResult(
    val board: Board,
    val placedGoat: Int = 0,
    val killedGoat: Int = 0,
)
