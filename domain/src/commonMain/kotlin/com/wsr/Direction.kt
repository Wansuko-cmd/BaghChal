package com.wsr

sealed interface Direction {
    data object Up : Direction
    data object Down : Direction
    data object Left : Direction
    data object Right : Direction
    data object UpLeft : Direction
    data object UpRight : Direction
    data object DownLeft : Direction
    data object DownRight : Direction
}
