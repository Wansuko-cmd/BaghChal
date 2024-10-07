package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board
import com.wsr.board.Coordinate

sealed interface Movement {
    data class GoatPlace(val coordinate: Coordinate) : Movement {
        companion object {
            fun createMovements(board: Board): List<GoatPlace> = board
                .coordinates
                .filter { board[it].peace == null }
                .map { GoatPlace(coordinate = it) }
        }
    }

    data class GoatMove(val from: Coordinate, val to: Coordinate) : Movement {
        companion object {
            fun createMovements(board: Board): List<GoatMove> = board
                .coordinates
                .filter { currentCoordinate -> board[currentCoordinate].peace == Peace.Goat }
                .flatMap { currentCoordinate ->
                    board.getNext(currentCoordinate)
                        .filter { nextCoordinate -> board[nextCoordinate].peace == null }
                        .map { nextCoordinate -> GoatMove(from = currentCoordinate, to = nextCoordinate) }
                }
        }
    }

    data class TigerMove(
        val from: Coordinate,
        val to: Coordinate,
        val kill: Coordinate? = null,
    ) : Movement {
        companion object {
            fun createMovements(board: Board): List<TigerMove> {
                val tigerCoordinates = board
                    .coordinates
                    .filter { coordinate -> board[coordinate].peace == Peace.Tiger }

                val moveMovements = tigerCoordinates
                    .flatMap { currentCoordinate ->
                        board.getNext(currentCoordinate)
                            .filter { nextCoordinate -> board[nextCoordinate].peace == null }
                            .map { nextCoordinate -> TigerMove(from = currentCoordinate, to = nextCoordinate) }
                    }

                val killMovement = tigerCoordinates
                    .flatMap { currentCoordinate ->
                        board[currentCoordinate]
                            .movableDirections
                            .mapNotNull { direction ->
                                // 隣が羊である
                                val killCoordinate =
                                    board.getNext(currentCoordinate, direction) ?: return@mapNotNull null
                                if (board[killCoordinate].peace != Peace.Goat) return@mapNotNull null

                                // その奥が空である
                                val nextCoordinate = board.getNext(killCoordinate, direction) ?: return@mapNotNull null
                                if (board[nextCoordinate].peace != null) return@mapNotNull null

                                TigerMove(from = currentCoordinate, to = nextCoordinate, kill = killCoordinate)
                            }
                    }

                return moveMovements + killMovement
            }
        }
    }
}
