package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board
import com.wsr.board.Coordinate

sealed class GoatPhase<T> private constructor(protected val board: Board) : Phase<T> {
    class Place internal constructor(board: Board) : GoatPhase<Coordinate>(board) {
        private val placeableCoordinate: List<Coordinate> = board
            .coordinates
            .filter { board[it].peace == null }

        override fun process(value: Coordinate): PhaseResult {
            if (value !in placeableCoordinate) throw PhaseException.InvalidCoordinateException()
            return PhaseResult(
                board = board.place(Peace.Goat, value),
                placedGoat = 1,
            )
        }
    }

//    class Move internal constructor(board: Board) : GoatPhase(board) {
//        val movableCoordinate: List<Pair<Coordinate, List<Coordinate>>> = board
//            .coordinates
//            .filter { coordinate -> board[coordinate].peace == Peace.Goat }
//            .map { coordinate ->
//                coordinate to board[coordinate]
//                    .movableDirection
//                    .mapNotNull { nextCoordinate -> board.getNext(coordinate, nextCoordinate) }
//                    .filter { board[it].peace == null }
//            }
//            .filter { (_, nextCoordinates) -> nextCoordinates.isNotEmpty() }
//
//        fun move(from: Coordinate, to: Coordinate): ApiResult<TigerPhase, PhaseException> {
//            val isInvalidCoordinates = movableCoordinate
//                .none { (coordinate, nextCoordinates) ->
//                    coordinate == from && nextCoordinates.contains(to)
//                }
//            if (isInvalidCoordinates) return ApiResult.Failure(PhaseException.InvalidCoordinateException())
//            return board
//                .remove(from)
//                .place(Peace.Goat, to)
//                .let { TigerPhase(it) }
//                .let { ApiResult.Success(it) }
//        }
//    }

    companion object {
        fun create(board: Board) = Place(board)
    }
}
