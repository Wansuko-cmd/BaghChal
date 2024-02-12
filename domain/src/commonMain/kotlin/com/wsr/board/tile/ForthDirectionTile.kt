package com.wsr.board.tile

import com.wsr.Peace
import com.wsr.board.Direction

internal class ForthDirectionTile private constructor(peace: Peace?) : Tile(peace) {

    override val movableDirection: List<Direction> = listOf(
        Direction.Up,
        Direction.Down,
        Direction.Left,
        Direction.Right,
    )

    override fun place(peace: Peace): Tile = ForthDirectionTile(peace)

    override fun remove(): Tile = ForthDirectionTile(null)

    companion object {
        fun create() = ForthDirectionTile(null)
    }
}
