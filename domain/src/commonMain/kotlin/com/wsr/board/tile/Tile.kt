package com.wsr.board.tile

import com.wsr.Direction
import com.wsr.Peace

interface Tile {
    val peace: Peace?
    fun place(peace: Peace): Tile
    fun remove(): Tile
    fun direction(): List<Direction>
}
