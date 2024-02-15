package com.wsr

import com.wsr.phase.GoatPhase
import com.wsr.phase.TigerPhase

fun main() {
    val baghChal = BaghChal.create()
    while (true) {
        baghChal.process { phase ->
            when (phase) {
                is GoatPhase.Place -> phase.movements.random()
                is GoatPhase.Move -> phase.movements.random()
                is TigerPhase -> phase.movements.random()
            }
        }
    }
}
