package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board

sealed class GoatPhase<T : Movement> private constructor() : Phase<T>() {
    class Place internal constructor(private val board: Board) : GoatPhase<Movement.Place>() {
        override val movements: List<Movement.Place> = Movement.Place.createMovements(board)

        override fun process(movement: Movement.Place): PhaseResult {
            if (movement !in movements) throw PhaseException.InvalidCoordinateException()
            return PhaseResult(
                board = board.place(Peace.Goat, movement.coordinate),
                placedGoat = 1,
            )
        }
    }

    class Move internal constructor(private val board: Board) : GoatPhase<Movement.Move>() {
        override val movements: List<Movement.Move> = Movement.Move.createMovements(board)

        override fun process(movement: Movement.Move): PhaseResult {
            if (movement !in movements) throw PhaseException.InvalidCoordinateException()
            return PhaseResult(
                board = board
                    .remove(movement.from)
                    .place(Peace.Goat, movement.to),
            )
        }
    }

    companion object {
        fun create(board: Board, sumOfPlacedGoat: Int): Phase<Movement> =
            if (sumOfPlacedGoat < 20) Place(board) else Move(board)
    }
}
