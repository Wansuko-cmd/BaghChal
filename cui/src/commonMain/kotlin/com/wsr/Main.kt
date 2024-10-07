package com.wsr

import com.wsr.board.Board

fun main() {
    var baghChal = BaghChal.create()
    while (baghChal.winner == null) {
        baghChal = baghChal.process(
            onGoatPlace = { movements -> movements.random() },
            onGoatMove = { movements -> movements.random() },
            onTigerMove = { movements -> movements.random() },
        )
        printLine()
        println(baghChal.toDisplayString())
        printLine()
    }
    println(baghChal.winner)
}

private fun BaghChal.toDisplayString() =
    """
Placed Goat: $sumOfPlacedGoat
Killed Goat: $sumOfKilledGoat
        
${board.toDisplayString()}
    """.trimIndent()

private fun Board.toDisplayString() = this
    .coordinates
    .groupBy { it.row }
    .map { (_, columns) ->
        columns
            .sortedBy { it.column }
            .joinToString(", ") { this[it].peace.toDisplayString() }
    }
    .joinToString("\n")

private fun Peace?.toDisplayString() = when (this) {
    null -> "　"
    Peace.Goat -> "羊"
    Peace.Tiger -> "虎"
}

private fun printLine() = println("-----------------")
