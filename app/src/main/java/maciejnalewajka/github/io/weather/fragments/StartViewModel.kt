package maciejnalewajka.github.io.weather.fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import maciejnalewajka.github.io.weather.Service
import maciejnalewajka.github.io.weather.dataBase.Base
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class StartViewModel(context: Context) : ViewModel() {

    private val shared: SharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE)
    private val networkState = NetworkState(context)
    private var city: String
    private val appid: String
    val units: String
    val symbol: String
    val speed: String

    private val _base =  MutableLiveData<Base>()
    val base: LiveData<Base>
        get() = _base

    init {
        appid = shared.getString("appid", "")!!
        if(!shared.getString("city", "").isNullOrEmpty()){
            city = shared.getString("city", "warszawa")!!
        }
        else{
            city = "warszawa"
            shared.edit().putString("city", city).apply()
        }
        units = shared.getString("units", "")!!
        symbol = shared.getString("symbol", "K")!!
        speed = shared.getString("speed", "m/h")!!

        if(networkState.isActive()) {
            getWeatherForLoc(city)
        }
        else{
            val json = shared.getString("currentWeather", "empty")
            if (json != "empty"){
                _base.value = Gson().fromJson(json, Base::class.java)
            }
        }
    }

    private fun getWeatherForLoc(city: String){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)

        CoroutineScope(Dispatchers.IO).launch{
            val responseJson = retrofit.currentWeatherJson(city, appid, units)
            if (responseJson.isSuccessful){
                responseJson.body()?.let {
                    val json = it
                    shared.edit().putString("currentWeather", json.toString()).apply()
                    withContext(Main) {
                        setBase(Gson().fromJson(json, Base::class.java))
                    }
                }
            }
        }
    }

    private fun setBase(base: Base){
        _base.value = base
    }

    fun get(){
        val city = shared.getString("city", city)!!
        if(networkState.isActive()) {
            getWeatherForLoc(city)
        }
    }

    fun getImgWeatherId(context: Context): Int{
        val string = "i" + (base.value?.weather?.get(0)?.icon ?: "")
        return context.resources
            .getIdentifier(string, "drawable", context.packageName)
    }

    fun longToTime(long: Long): String? {
        return try {
            val newDate = Date(long*1000)
            val format = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            format.format(newDate)
        } catch (e: Exception) {
            ""
        }
    }
}