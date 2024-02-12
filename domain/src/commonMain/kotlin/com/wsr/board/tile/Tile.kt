package com.wsr.board.tile

import com.wsr.Peace
import com.wsr.board.Direction

abstract class Tile(val peace: Peace?) {
    internal abstract val movableDirection: List<Direction>
    internal abstract fun place(peace: Peace): Tile
    internal abstract fun remove(): Tile
}
