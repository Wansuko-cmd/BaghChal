package com.wsr.board.tile

import com.wsr.Peace
import com.wsr.board.Coordinate

internal class EightDirectionTile private constructor(peace: Peace?) : Tile(peace) {

    override fun neighborCoordinates(standard: Coordinate): List<Coordinate> = listOf(
        standard.up,
        standard.down,
        standard.left,
        standard.right,
        standard.up.left,
        standard.up.right,
        standard.down.left,
        standard.down.right,
    )

    override fun put(peace: Peace): Tile = EightDirectionTile(peace)

    override fun remove(): Tile = EightDirectionTile(null)

    companion object {
        fun create() = EightDirectionTile(null)
    }
}
