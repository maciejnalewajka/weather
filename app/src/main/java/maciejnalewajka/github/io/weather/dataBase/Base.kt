package maciejnalewajka.github.io.weather.dataBase

data class Base(val coord: Coord = Coord(), val weather: List<Weather> = listOf(Weather()),
                val base: String = "", val main: Main = Main(), val visibility: Int = 0,
                val wind: Wind = Wind(), val clouds: Clouds = Clouds(),
                val dt: Long = 0, val sys: Sys = Sys(), val id: Int = 0, val name: String = "",
                val cod: Int = 0)