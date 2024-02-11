package com.wsr.tile

import com.wsr.peace.Peace

class EightDirectionTile private constructor(override val peace: Peace? = null) : Tile {
    override fun place(peace: Peace): Tile = EightDirectionTile(peace)

    override fun remove(): Tile = EightDirectionTile()

    override fun direction(): List<Direction> = listOf(
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
        fun create(peace: Peace?) = EightDirectionTile(peace)
    }
}
