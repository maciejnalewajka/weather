package maciejnalewajka.github.io.weather.dataBase3h

import maciejnalewajka.github.io.weather.dataBase.Coord

data class City (
    val id : Int = 0,
    val name : String = "",
    val country : String = "",
    val coord : Coord = Coord()
)
