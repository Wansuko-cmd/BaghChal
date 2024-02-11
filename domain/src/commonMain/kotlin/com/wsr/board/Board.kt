package com.wsr.board

import com.wsr.Peace
import com.wsr.board.tile.Tile
import com.wsr.replace
import com.wsr.result.ApiResult

class Board private constructor(private val tiles: List<List<Tile>>) {
    fun getTile(row: Int, column: Int): ApiResult<Tile, BoardException> =
        tiles
            .getOrNull(row)
            ?.getOrNull(column)
            ?.let { tile -> ApiResult.Success(tile) }
            ?: ApiResult.Failure(BoardException.NotInRangeException)

    fun place(peace: Peace, row: Int, column: Int): Board = Board(tiles.replace(row, column) { it.place(peace) })

    fun remove(row: Int, column: Int) = Board(tiles.replace(row, column) { it.remove() })
}
