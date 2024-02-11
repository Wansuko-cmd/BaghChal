package com.wsr.board.tile

import com.wsr.Peace
import com.wsr.board.Coordinate

internal class ForthDirectionTile private constructor(peace: Peace?) : Tile(peace) {

    override fun neighborCoordinates(standard: Coordinate): List<Coordinate> = listOf(
        standard.up,
        standard.down,
        standard.left,
        standard.right,
    )

    override fun place(peace: Peace): Tile = ForthDirectionTile(peace)

    override fun remove(): Tile = ForthDirectionTile(null)

    companion object {
        fun create() = ForthDirectionTile(null)
    }
}
