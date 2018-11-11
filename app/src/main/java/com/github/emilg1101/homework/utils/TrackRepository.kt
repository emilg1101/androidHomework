package com.github.emilg1101.homework.utils

import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.model.Track

object TrackRepository {

    fun getList() = arrayListOf(
            Track("Container", R.raw.container),
            Track("Funk the Floor", R.raw.funk_the_floor),
            Track("Martina and the plan", R.raw.martina_and_the_air_plan),
            Track("Orbiting the Earth", R.raw.orbiting_the_earth),
            Track("Space love attack", R.raw.space_love_attack)
    )
}
