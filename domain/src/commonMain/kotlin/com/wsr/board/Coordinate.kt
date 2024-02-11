package com.wsr.board

class Coordinate(val row: Int, val column: Int) {
    val left get() = Coordinate(row, column - 1)
    val up get() = Coordinate(row - 1, column)
    val right get() = Coordinate(row, column + 1)
    val down get() = Coordinate(row + 1, column)
    val around get() = listOfNotNull(left.up, up, right.up, left, right, left.down, down, right.down)
    override fun toString(): String = "Coordinate(row: $row, column: $column)"
    override fun equals(other: Any?): Boolean =
        other is Coordinate && row == other.row && column == other.column
    override fun hashCode(): Int = 31 * row + column
}
