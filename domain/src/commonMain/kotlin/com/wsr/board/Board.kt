package com.wsr.board

import com.wsr.Peace
import com.wsr.board.tile.EightDirectionTile
import com.wsr.board.tile.ForthDirectionTile
import com.wsr.board.tile.Tile
import com.wsr.result.ApiResult
import com.wsr.result.map

class Board private constructor(private val tiles: List<List<Tile>>) {
    fun getPeace(coordinate: Coordinate): ApiResult<Peace?, BoardException> =
        getTile(coordinate).map { tile -> tile.peace }

    fun getNeighborCoordinates(coordinate: Coordinate): ApiResult<List<Coordinate>, BoardException> =
        getTile(coordinate)
            .map { tile -> tile.neighborCoordinates(coordinate) }
            .map { coordinates -> coordinates.filter { true } }

    private fun getTile(coordinate: Coordinate): ApiResult<Tile, BoardException> =
        tiles
            .getOrNull(coordinate.row)
            ?.getOrNull(coordinate.column)
            ?.let { tile -> ApiResult.Success(tile) }
            ?: ApiResult.Failure(BoardException.NotInRangeException)

    internal fun place(peace: Peace, coordinate: Coordinate): ApiResult<Board, BoardException> =
        updateTile(coordinate) { it.place(peace) }

    internal fun remove(coordinate: Coordinate): ApiResult<Board, BoardException> =
        updateTile(coordinate) { it.remove() }

    private fun updateTile(coordinate: Coordinate, block: (Tile) -> Tile) =
        tiles.update(coordinate.row) { col ->
            col.update(coordinate.column) { tile -> ApiResult.Success(block(tile)) }
        }
            .map { Board(it) }

    private fun <T> List<T>.update(
        index: Int,
        block: (T) -> ApiResult<T, BoardException>,
    ): ApiResult<List<T>, BoardException> =
        if (index !in 0..size) ApiResult.Failure(BoardException.NotInRangeException)
        else block(get(index))
            .map { subList(0, index) + it + subList(index, size) }

    companion object {
        fun create() = MutableList(5) { rowIndex ->
            MutableList(5) { columIndex ->
                if ((rowIndex + columIndex) % 2 == 0) EightDirectionTile.create()
                else ForthDirectionTile.create()
            }
        }
            .also { tiles ->
                tiles[0][0] = tiles[0][0].place(Peace.Tiger)
                tiles[0][tiles.size - 1] = tiles[0][tiles.size - 1].place(Peace.Tiger)
                tiles[tiles.size - 1][0] = tiles[tiles.size - 1][0].place(Peace.Tiger)
                tiles[tiles.size - 1][tiles.size - 1] = tiles[tiles.size - 1][tiles.size - 1].place(Peace.Tiger)
            }
            .let { Board(it) }
    }
}
