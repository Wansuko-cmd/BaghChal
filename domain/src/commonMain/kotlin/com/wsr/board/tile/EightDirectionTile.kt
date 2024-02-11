package com.wsr.board.tile

import com.wsr.Direction
import com.wsr.Peace

internal class EightDirectionTile private constructor(peace: Peace?) : Tile(peace) {
    override fun place(peace: Peace): Tile = EightDirectionTile(peace)

    override fun remove(): Tile = EightDirectionTile(null)

    override fun movableDirections(): List<Direction> = listOf(
        Direction.Up,
        Direction.Down,
        Direction.Left,
        Direction.Right,
        Direction.UpLeft,
        Direction.UpRight,
        Direction.DownLeft,
        Direction.DownRight,
    )

    companion object {
        fun create() = EightDirectionTile(null)
    }
}
