package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board
import com.wsr.board.Coordinate
import com.wsr.result.ApiResult
import com.wsr.result.flatMap
import com.wsr.result.map

sealed class GoatPhase private constructor(protected val board: Board) {
    class Place internal constructor(board: Board) : GoatPhase(board) {
        val placeableCoordinate = board
            .coordinates
            .map { coordinate -> board.getPeace(coordinate) }
            .filter { peace -> peace is ApiResult.Success && peace.value == null }

        fun place(coordinate: Coordinate) =
            board
                .getPeace(coordinate)
                .map { peace -> peace == null }
                .flatMap { board.place(Peace.Goat, coordinate) }
                .map { placedBoard -> TigerPhase(placedBoard) }
    }

    companion object {
        fun create(board: Board) = Place(board)
    }
}
