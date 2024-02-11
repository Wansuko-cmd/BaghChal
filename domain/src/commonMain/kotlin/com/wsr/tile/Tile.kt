package com.wsr.tile

import com.wsr.peace.Peace

interface Tile {
    val peace: Peace?
    fun place(peace: Peace): Tile
    fun remove(): Tile
    fun direction(): List<Direction>
}
