package maciejnalewajka.github.io.weather

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("https://api.openweathermap.org/data/2.5/weather?")
    suspend fun currentWeatherJson(@Query("q") city: String?, @Query("appid") appid: String?, @Query("units") units: String?): Response<JsonObject>

}