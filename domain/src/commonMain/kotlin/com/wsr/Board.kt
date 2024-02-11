package com.wsr

import com.wsr.peace.Peace
import com.wsr.tile.Tile

class Board private constructor(private val tiles: List<List<Tile>>) {
    fun place(peace: Peace, row: Int, column: Int): Board = Board(tiles.replace(row, column) { it.place(peace) })

    fun remove(row: Int, column: Int) = Board(tiles.replace(row, column) { it.remove() })
}
