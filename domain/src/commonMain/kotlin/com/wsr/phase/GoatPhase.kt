package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board
import com.wsr.board.Coordinate

sealed class GoatPhase<T> private constructor() : Phase<T> {
    class Place internal constructor(private val board: Board) : GoatPhase<Coordinate>() {
        override val coordinates: List<Coordinate> = board
            .coordinates
            .filter { board[it].peace == null }

        override fun process(coordinate: Coordinate): PhaseResult {
            if (coordinate !in coordinates) throw PhaseException.InvalidCoordinateException()
            return PhaseResult(
                board = board.place(Peace.Goat, coordinate),
                placedGoat = 1,
            )
        }
    }

    class Move internal constructor(private val board: Board) : GoatPhase<Pair<Coordinate, Coordinate>>() {
        override val coordinates: List<Pair<Coordinate, Coordinate>> = board
            .coordinates
            .filter { coordinate -> board[coordinate].peace == Peace.Goat }
            .flatMap { coordinate ->
                board[coordinate]
                    .movableDirection
                    .mapNotNull { nextCoordinate -> board.getNext(coordinate, nextCoordinate) }
                    .filter { board[it].peace == null }
                    .map { coordinate to it }
            }

        override fun process(coordinate: Pair<Coordinate, Coordinate>): PhaseResult {
            if (coordinate !in coordinates) throw PhaseException.InvalidCoordinateException()
            return PhaseResult(
                board = board
                    .remove(coordinate.first)
                    .place(Peace.Goat, coordinate.second),
            )
        }
    }

    companion object {
        fun create(board: Board, sumOfPlacedGoat: Int) =
            if(sumOfPlacedGoat < 20) Place(board) else Move(board)
    }
}
