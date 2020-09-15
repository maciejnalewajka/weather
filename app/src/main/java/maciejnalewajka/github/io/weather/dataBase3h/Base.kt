package maciejnalewajka.github.io.weather.dataBase3h

data class Base(val cod: String, val message: String, val cnt: Int, val list: List<List<Any?>>, val city: City)