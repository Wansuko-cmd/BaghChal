package com.wsr

sealed interface Peace {
    data object Goat : Peace
    data object Tiger : Peace
}
