package com.wsr.board.tile

import com.wsr.Peace
import com.wsr.board.Direction

internal class EightDirectionTile private constructor(peace: Peace?) : Tile(peace) {

    override val movableDirection: List<Direction> = listOf(
        Direction.Up,
        Direction.Down,
        Direction.Left,
        Direction.Right,
        Direction.UpLeft,
        Direction.UpRight,
        Direction.DownLeft,
        Direction.DownRight,
    )

    override fun place(peace: Peace): Tile = EightDirectionTile(peace)

    override fun remove(): Tile = EightDirectionTile(null)

    companion object {
        fun create() = EightDirectionTile(null)
    }
}
