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

    init {
        city = shared.getString("city", "")!!
        units = shared.getString("units", "")!!
        symbol = shared.getString("symbol", "K")!!
    }

    fun changeCity(newCity: String){
        shared.edit().remove("city").apply()
        shared.edit().putString("city", newCity).apply()
    }

    fun changeUnits(newUnits: String, newSymbol: String, newSpeed: String){
        shared.edit().remove("units").apply()
        shared.edit().remove("symbol").apply()
        shared.edit().remove("speed").apply()
        shared.edit().putString("units", newUnits).apply()
        shared.edit().putString("symbol", newSymbol).apply()
        shared.edit().putString("speed", newSpeed).apply()
    }

    fun getCity(): String{
        return city
    }

    fun getUnits(): String{
        return units
    }
}