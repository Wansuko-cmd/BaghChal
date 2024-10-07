package com.wsr.board

import com.wsr.Peace
import com.wsr.board.tile.EightDirectionTile
import com.wsr.board.tile.ForthDirectionTile
import com.wsr.board.tile.Tile

class Board private constructor(private val tiles: List<List<Tile>>) {
    val coordinates = tiles.flatMapIndexed { rowIndex, columns ->
        List(columns.size) { columnIndex -> Coordinate(rowIndex, columnIndex) }
    }

    operator fun get(coordinate: Coordinate): Tile =
        tiles
            .getOrNull(coordinate.row)
            ?.getOrNull(coordinate.column)
            ?: throw BoardException.CoordinateOutOfRangeException

    internal fun getNext(coordinate: Coordinate): List<Coordinate> =
        get(coordinate).movableDirections.mapNotNull { getNext(coordinate, it) }

    internal fun getNext(coordinate: Coordinate, direction: Direction): Coordinate? {
        val nextCoordinate = coordinate.moveTo(direction)
        if (nextCoordinate !in coordinates) return null
        return nextCoordinate
    }

    /**
     * 更新処理
     */
    internal fun place(peace: Peace, coordinate: Coordinate): Board =
        updateTile(coordinate) { it.place(peace) }

    internal fun remove(coordinate: Coordinate): Board =
        updateTile(coordinate) { it.remove() }

    private fun updateTile(coordinate: Coordinate, block: (Tile) -> Tile) =
        tiles.update(coordinate.row) { columnTiles ->
            columnTiles.update(coordinate.column) { tile -> block(tile) }
        }
            .let { Board(it) }

    companion object {
        internal fun create() = List(5) { rowIndex ->
            List(5) { columnIndex ->
                if ((rowIndex + columnIndex) % 2 == 0) {
                    EightDirectionTile.create()
                } else {
                    ForthDirectionTile.create()
                }
            }
        }
            .let { Board(it) }
            .place(Peace.Tiger, Coordinate(0, 0))
            .place(Peace.Tiger, Coordinate(0, 4))
            .place(Peace.Tiger, Coordinate(4, 0))
            .place(Peace.Tiger, Coordinate(4, 4))
    }
}

private fun <T> List<T>.update(
    index: Int,
    block: (T) -> T,
): List<T> =
    if (index !in 0..size) {
        throw BoardException.CoordinateOutOfRangeException
    } else {
        subList(0, index) + block(get(index)) + subList(index + 1, size)
    }
