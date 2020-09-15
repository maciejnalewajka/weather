package maciejnalewajka.github.io.weather.dataBase3h

import maciejnalewajka.github.io.weather.dataBase.Weather
import maciejnalewajka.github.io.weather.dataBase.Wind
import kotlin.collections.List

data class List<T>(val dt: Long, val main: Main, val weather: List<Weather> = listOf(Weather()), val clouds: Clouds, val wind: Wind, val rain: Rain, val snow: Snow, val sys: Sys, val dt_txt: String)