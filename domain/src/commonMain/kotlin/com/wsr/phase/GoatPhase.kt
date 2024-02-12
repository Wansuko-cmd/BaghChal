package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board
import com.wsr.board.Coordinate
import com.wsr.result.ApiResult

sealed class GoatPhase private constructor(protected val board: Board) {
    class Place internal constructor(board: Board) : GoatPhase(board) {
        private val placeableCoordinate: List<Coordinate> = board
            .coordinates
            .filter { board[it].peace == null }

        fun place(coordinate: Coordinate): ApiResult<TigerPhase, PhaseException> {
            if (coordinate !in placeableCoordinate) return ApiResult.Failure(PhaseException.InvalidCoordinateException())
            return board
                .place(Peace.Goat, coordinate)
                .let { TigerPhase(it) }
                .let { ApiResult.Success(it) }
        }
    }

//    class Move internal constructor(board: Board) : GoatPhase(board) {
//        val movableCoordinate: List<Pair<Coordinate, List<Coordinate>>> = board
//            .filterCoordinate { tile -> tile.peace == Peace.Goat }
//            .map { coordinate ->
//                val emptyCoordinate = board.filterCoordinate { tile -> tile.peace == null }
//                board
//                    .getNeighborCoordinates(coordinate)
//                    .map { neighborCoordinates ->
//                        coordinate to neighborCoordinates.filter { it in emptyCoordinate }
//                    }
//            }
//            .filterIsInstance<ApiResult.Success<Pair<Coordinate, List<Coordinate>>>>()
//            .map { it.value }
//            .filter { it.second.isNotEmpty() }
//
//        fun move(from: Coordinate, to: Coordinate) =
//            board
//    }

    companion object {
        fun create(board: Board) = Place(board)
    }
}
