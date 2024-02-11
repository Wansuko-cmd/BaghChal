package com.wsr

fun <T> List<T>.replace(index: Int, block: (T) -> T) =
    subList(0, index) + block(get(index)) + subList(index, size)

fun <T> List<List<T>>.replace(row: Int, column: Int, block: (T) -> T) =
    replace(row) { col -> col.replace(column, block) }