package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board
import com.wsr.board.Coordinate

sealed interface Movement {
    data class Place(val coordinate: Coordinate) : Movement {
        companion object {
            fun createMovements(board: Board): List<Place> = board
                .coordinates
                .filter { board[it].peace == null }
                .map { Place(coordinate = it) }
        }
    }

    data class Move(val from: Coordinate, val to: Coordinate) : Movement {
        companion object {
            fun createMovements(board: Board): List<Move> = board
                .coordinates
                .filter { coordinate -> board[coordinate].peace == Peace.Goat }
                .flatMap { coordinate ->
                    board[coordinate]
                        .movableDirection
                        .mapNotNull { direction -> board.getNext(coordinate, direction) }
                        .filter { nextCoordinate -> board[nextCoordinate].peace == null }
                        .map { Move(from = coordinate, to = it) }
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
                    .flatMap { coordinate ->
                        board[coordinate]
                            .movableDirection
                            .mapNotNull { direction -> board.getNext(coordinate, direction) }
                            .filter { nextCoordinate -> board[nextCoordinate].peace == null }
                            .map { TigerMove(from = coordinate, to = it) }
                    }

                val killMovement = tigerCoordinates
                    .flatMap { coordinate ->
                        board[coordinate]
                            .movableDirection
                            .asSequence()
                            // 隣が羊である
                            .mapNotNull { direction ->
                                board.getNext(coordinate, direction)?.let { direction to it }
                            }
                            .filter { (_, killCoordinate) -> board[killCoordinate].peace == Peace.Goat }
                            // その奥が空である
                            .mapNotNull { (direction, killCoordinate) ->
                                board.getNext(killCoordinate, direction)?.let { killCoordinate to it }
                            }
                            .filter { (_, nextCoordinate) -> board[nextCoordinate].peace == null }
                            .map { (killCoordinate, nextCoordinate) ->
                                TigerMove(from = coordinate, to = nextCoordinate, kill = killCoordinate)
                            }
                            .toList()
                    }

                return moveMovements + killMovement
            }
        }
    }
}
