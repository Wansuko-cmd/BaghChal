package com.wsr

import com.wsr.board.Board
import com.wsr.phase.GoatPhase
import com.wsr.phase.TigerPhase

fun main() {
    var baghChal = BaghChal.create()
    while (baghChal.winner == null) {
        baghChal = baghChal.process { phase ->
            when (phase) {
                is GoatPhase.Place -> phase.movements.random()
                is GoatPhase.Move -> phase.movements.random()
                is TigerPhase -> phase.movements.random()
            }
        }
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
