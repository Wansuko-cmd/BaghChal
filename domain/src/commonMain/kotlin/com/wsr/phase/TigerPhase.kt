package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board

class TigerPhase(private val board: Board) : Phase<Movement.TigerMove>() {
    private val moveMovements = board
        .coordinates
        .filter { coordinate -> board[coordinate].peace == Peace.Tiger }
        .flatMap { coordinate ->
            board[coordinate]
                .movableDirection
                .mapNotNull { direction -> board.getNext(coordinate, direction) }
                .filter { nextCoordinate -> board[nextCoordinate].peace == null }
                .map { Movement.TigerMove(from = coordinate, to = it) }
        }

    private val killMovement = board
        .coordinates
        .filter { coordinate -> board[coordinate].peace == Peace.Tiger }
        .flatMap { coordinate ->
            board[coordinate]
                .movableDirection
                .asSequence()
                .mapNotNull { direction ->
                    board.getNext(coordinate, direction)?.let { direction to it }
                }
                .filter { (_, killCoordinate) -> board[killCoordinate].peace == Peace.Goat }
                .mapNotNull { (direction, killCoordinate) ->
                    board.getNext(killCoordinate, direction)?.let { killCoordinate to it }
                }
                .filter { (_, nextCoordinate) -> board[nextCoordinate].peace == null }
                .map { (killCoordinate, nextCoordinate) ->
                    Movement.TigerMove(from = coordinate, to = nextCoordinate, kill = killCoordinate)
                }
                .toList()
        }

    override val movements: List<Movement.TigerMove> = moveMovements + killMovement
    override fun process(movement: Movement.TigerMove): PhaseResult {
        return PhaseResult(
            board = board
                .remove(movement.from)
                .place(Peace.Tiger, movement.to)
                .let { if (movement.kill != null) it.remove(movement.kill) else it },
            killedGoat = if (movement.kill != null) 1 else 0,
        )
    }
}
