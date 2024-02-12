package com.wsr.board

internal sealed interface Direction {
    val coordinate: Coordinate

    data object Up : Direction {
        override val coordinate = Coordinate(-1, 0)
    }
    data object Down : Direction {
        override val coordinate = Coordinate(1, 0)
    }
    data object Left : Direction {
        override val coordinate = Coordinate(0, -1)
    }
    data object Right : Direction {
        override val coordinate = Coordinate(0, 1)
    }
    data object UpLeft : Direction {
        override val coordinate = Coordinate(-1, -1)
    }
    data object UpRight : Direction {
        override val coordinate = Coordinate(-1, 1)
    }
    data object DownLeft : Direction {
        override val coordinate = Coordinate(1, -1)
    }
    data object DownRight : Direction {
        override val coordinate = Coordinate(1, 1)
    }
}

internal fun Coordinate.moveTo(direction: Direction) = this + direction.coordinate
