package com.wsr.board.tile

import com.wsr.Direction
import com.wsr.Peace

abstract class Tile(val peace: Peace?) {
    abstract fun movableDirections(): List<Direction>
    internal abstract fun place(peace: Peace): Tile
    internal abstract fun remove(): Tile
}
