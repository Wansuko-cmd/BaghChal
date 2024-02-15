package com.wsr

import com.wsr.board.Board
import com.wsr.phase.Phase

class BaghChal<T>(
    val board: Board,
    val sumOfPlacedGoat: Int,
    val sumOfKilledGoat: Int,
    private val phase: Phase<T>,
) {
    fun process(block: (Phase<T>) -> T): BaghChal<*> {
        val coordinate = block(phase)
        val result = phase.process(coordinate)
        return BaghChal(
            board = result.board,
            sumOfPlacedGoat = sumOfPlacedGoat + result.placedGoat,
            sumOfKilledGoat = sumOfKilledGoat,
            phase = phase,
        )
    }
}
