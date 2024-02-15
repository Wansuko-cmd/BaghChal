package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board

class TigerPhase(private val board: Board) : Phase<Movement.Move>() {
    private val moveMovements = board
        .coordinates
        .filter { coordinate -> board[coordinate].peace == Peace.Tiger }
        .flatMap { coordinate ->
            board[coordinate]
                .movableDirection
                .mapNotNull { direction -> board.getNext(coordinate, direction) }
                .filter { nextCoordinate -> board[nextCoordinate].peace == null }
                .map { Movement.Move(from = coordinate, to = it) }
        }

    private val killMovement = board
        .coordinates
        .filter { coordinate -> board[coordinate].peace == Peace.Tiger }
        .flatMap { coordinate ->
            board[coordinate]
                .movableDirection
                .asSequence()
                .mapNotNull { direction -> board.getNext(coordinate, direction)?.let { direction to it } }
                .filter { (_, nextCoordinate) -> board[nextCoordinate].peace == Peace.Goat }
                .mapNotNull { (direction, nextCoordinate) -> board.getNext(nextCoordinate, direction) }
                .filter { nextCoordinate -> board[nextCoordinate].peace == null }
                .map { Movement.Move(from = coordinate, to = it) }
                .toList()
        }

    override val movements: List<Movement.Move> = moveMovements + killMovement
    override fun process(movement: Movement.Move): PhaseResult {
        return PhaseResult(board)
    }
}
