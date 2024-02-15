package com.wsr.phase

import com.wsr.board.Board

class TigerPhase(private val board: Board) : Phase<Movement.Move> {
    override val movements: List<Movement.Move> = listOf()
    override fun process(movement: Movement.Move): PhaseResult {
        TODO("Not yet implemented")
    }
}
