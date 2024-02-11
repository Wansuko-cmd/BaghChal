package com.wsr.board.tile

import com.wsr.Peace
import com.wsr.board.Coordinate

abstract class Tile(val peace: Peace?) {
    abstract fun neighborCoordinates(standard: Coordinate): List<Coordinate>
    internal abstract fun put(peace: Peace): Tile
    internal abstract fun remove(): Tile
}
