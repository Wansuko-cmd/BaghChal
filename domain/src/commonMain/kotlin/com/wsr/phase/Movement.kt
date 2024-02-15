package com.wsr.phase

import com.wsr.board.Coordinate

sealed interface Movement {
    data class Place(val coordinate: Coordinate) : Movement

    data class Move(val from: Coordinate, val to: Coordinate) : Movement
}
