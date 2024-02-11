package com.wsr.tile

import com.wsr.peace.Peace

class ForthDirectionTile private constructor(override val peace: Peace? = null) : Tile {
    override fun place(peace: Peace): Tile = ForthDirectionTile(peace)

    override fun remove(): Tile = ForthDirectionTile()

    override fun direction(): List<Direction> = listOf(
        Direction.Up,
        Direction.Down,
        Direction.Left,
        Direction.Right,
    )

    companion object {
        fun create(peace: Peace?) = ForthDirectionTile(peace)
    }
}
