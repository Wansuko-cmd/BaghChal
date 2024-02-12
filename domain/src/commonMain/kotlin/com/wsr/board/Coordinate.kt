package com.wsr.board

class Coordinate internal constructor(val row: Int, val column: Int) {
    operator fun plus(other: Coordinate) = Coordinate(
        row = this.row + other.row,
        column = this.column + other.column,
    )

    override fun toString(): String = "Coordinate(row: $row, column: $column)"
    override fun equals(other: Any?): Boolean =
        other is Coordinate && row == other.row && column == other.column
    override fun hashCode(): Int = 31 * row + column
}
