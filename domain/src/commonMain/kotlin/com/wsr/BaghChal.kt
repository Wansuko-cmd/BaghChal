package com.wsr

import com.wsr.board.Board
import com.wsr.phase.GoatPhase
import com.wsr.phase.Movement
import com.wsr.phase.Phase
import com.wsr.phase.TigerPhase

class BaghChal private constructor(
    val board: Board,
    val sumOfPlacedGoat: Int,
    val sumOfKilledGoat: Int,
    private val phase: Phase<Movement>,
) {
    fun process(block: (Phase<Movement>) -> Movement): BaghChal {
        val coordinate = block(phase)
        val result = phase.process(coordinate)
        return BaghChal(
            board = result.board,
            sumOfPlacedGoat = sumOfPlacedGoat + result.placedGoat,
            sumOfKilledGoat = sumOfKilledGoat,
            phase = when (phase) {
                is GoatPhase -> TigerPhase(result.board)
                is TigerPhase -> GoatPhase.create(result.board, sumOfPlacedGoat + result.placedGoat)
            },
        )
    }

    companion object {
        fun create(): BaghChal {
            val board = Board.create()
            val sumOfPlacedGoat = 0
            return BaghChal(
                board = Board.create(),
                sumOfKilledGoat = 0,
                sumOfPlacedGoat = sumOfPlacedGoat,
                phase = GoatPhase.create(board, sumOfPlacedGoat),
            )
        }
    }
}
