package com.wsr

import com.wsr.board.Board
import com.wsr.phase.GoatPhase
import com.wsr.phase.Movement
import com.wsr.phase.Phase
import com.wsr.phase.PhaseException
import com.wsr.phase.TigerPhase

class BaghChal private constructor(
    val board: Board,
    val sumOfPlacedGoat: Int,
    val sumOfKilledGoat: Int,
    private val phase: Phase<Movement>,
) {
    val winner: Peace? = when {
        sumOfKilledGoat >= 5 -> Peace.Tiger
        Movement.TigerMove.createMovements(board).isEmpty() -> Peace.Goat
        else -> null
    }

    fun process(
        onGoatPlace: (movements: List<Movement.GoatPlace>) -> Movement.GoatPlace,
        onGoatMove: (movements: List<Movement.GoatMove>) -> Movement.GoatMove,
        onTigerMove: (movements: List<Movement.TigerMove>) -> Movement.TigerMove,
    ): BaghChal {
        if (winner != null) throw PhaseException.AlreadyCompleteException()
        val coordinate = when (phase) {
            is GoatPhase.Place -> onGoatPlace(phase.movements)
            is GoatPhase.Move -> onGoatMove(phase.movements)
            is TigerPhase -> onTigerMove(phase.movements)
        }
        val result = phase.process(coordinate)
        return BaghChal(
            board = result.board,
            sumOfPlacedGoat = sumOfPlacedGoat + result.placedGoat,
            sumOfKilledGoat = sumOfKilledGoat + result.killedGoat,
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
