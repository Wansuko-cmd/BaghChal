package com.wsr.board

import com.wsr.Peace
import com.wsr.board.tile.Tile
import com.wsr.result.ApiResult
import com.wsr.result.map

class Board private constructor(private val tiles: List<List<Tile>>) {
    fun getTile(row: Int, column: Int): ApiResult<Tile, BoardException> =
        tiles
            .getOrNull(row)
            ?.getOrNull(column)
            ?.let { tile -> ApiResult.Success(tile) }
            ?: ApiResult.Failure(BoardException.NotInRangeException)

    fun place(peace: Peace, row: Int, column: Int): ApiResult<Board, BoardException> =
        updateTile(row, column) { it.place(peace) }

    fun remove(row: Int, column: Int): ApiResult<Board, BoardException> =
        updateTile(row, column) { it.remove() }

    private fun updateTile(row: Int, column: Int, block: (Tile) -> Tile) =
        tiles.update(row) { col ->
            col.update(column) { tile -> ApiResult.Success(block(tile)) }
        }
            .map { Board(it) }

    private fun <T> List<T>.update(
        index: Int,
        block: (T) -> ApiResult<T, BoardException>,
    ): ApiResult<List<T>, BoardException> =
        if (index !in 0..size) ApiResult.Failure(BoardException.NotInRangeException)
        else block(get(index))
            .map { subList(0, index) + it + subList(index, size) }
}
