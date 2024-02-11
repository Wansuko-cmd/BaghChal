package com.wsr.peace

sealed interface Peace {
    data object Goat : Peace
    data object Tiger : Peace
}
