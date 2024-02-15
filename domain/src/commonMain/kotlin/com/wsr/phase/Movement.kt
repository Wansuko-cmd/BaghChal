package com.wsr.phase

import com.wsr.board.Coordinate

sealed interface Movement {
    data class Place(val coordinate: Coordinate) : Movement

    data class Move(val from: Coordinate, val to: Coordinate) : Movement

    data class TigerMove(
        val from: Coordinate,
        val to: Coordinate,
        val kill: Coordinate? = null,
    ) : Movement
}
