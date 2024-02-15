package com.wsr.phase

import com.wsr.Peace
import com.wsr.board.Board

class TigerPhase(private val board: Board) : Phase<Movement.TigerMove>() {

    override val movements: List<Movement.TigerMove> = Movement.TigerMove.createMovements(board)
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
