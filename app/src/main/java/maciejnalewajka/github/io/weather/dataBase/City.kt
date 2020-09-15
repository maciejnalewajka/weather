package maciejnalewajka.github.io.weather.dataBase

data class City (
    val id : Int = 0,
    val name : String = "",
    val state : String = "",
    val country : String = "",
    val coord : Coord = Coord()
)