package maciejnalewajka.github.io.weather.fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class SettingsViewModel(context: Context) : ViewModel() {

    private val shared: SharedPreferences =
        context.getSharedPreferences("app", Context.MODE_PRIVATE)
    private var city: String = ""
    private var units: String = ""
    private var symbol: String = ""
    private var speed: String = ""

    init {
        city = shared.getString("city", "")!!
        units = shared.getString("units", "")!!
        symbol = shared.getString("symbol", "K")!!
        speed = shared.getString("speed", "m/h")!!
    }

    fun changeCity(newCity: String){
        shared.edit().remove("city").apply()
        shared.edit().putString("city", newCity).apply()
    }

    fun changeUnits(){
        shared.edit().remove("units").apply()
        shared.edit().remove("symbol").apply()
        shared.edit().remove("speed").apply()
        shared.edit().putString("units", units).apply()
        shared.edit().putString("symbol", symbol).apply()
        shared.edit().putString("speed", speed).apply()
    }

    fun getCity(): String{
        return city
    }

    fun getUnits(): String{
        return units
    }

    fun set(newUnits: String, newSymbol: String, newSpeed: String){
        units = newUnits
        symbol = newSymbol
        speed = newSpeed
    }
}