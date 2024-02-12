package com.wsr.board.tile

import com.wsr.Peace
import com.wsr.board.Coordinate
import com.wsr.board.Direction

abstract class Tile(val peace: Peace?) {
    internal abstract fun movableDirection(standard: Coordinate): List<Direction>
    internal abstract fun place(peace: Peace): Tile
    internal abstract fun remove(): Tile
}
