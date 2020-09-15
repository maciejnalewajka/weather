package maciejnalewajka.github.io.weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import maciejnalewajka.github.io.weather.R
import maciejnalewajka.github.io.weather.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel
    private lateinit var viewModelFactory: SettingsViewModelFactory
    private lateinit var networkState: NetworkState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory =  SettingsViewModelFactory(inflater.context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)
        val binding: SettingsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false)
        binding.settingsViewModel = viewModel
        binding.lifecycleOwner = this
        networkState = NetworkState(inflater.context)

        binding.newCity.hint = viewModel.getCity()
        val units = viewModel.getUnits()
        when(units){
            "" -> binding.kelvin.isChecked = true
            "metric" -> binding.celsjusz.isChecked = true
            "imperial" -> binding.fahrenheit.isChecked = true
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToStartFragment())
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.celsjusz.id -> viewModel.set("metric", "°C", "m/s")
                binding.fahrenheit.id -> viewModel.set("imperial", "°F", "m/s")
                binding.kelvin.id -> viewModel.set("", "K", "m/h")
                }
        }

        binding.okButton.setOnClickListener {
            if(networkState.isActive()) {
                val city = binding.newCity.text.toString()
                if (city != "") {
                    viewModel.changeCity(city)
                }
                viewModel.changeUnits()
                findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToStartFragment())
            }
            else{
                Toast.makeText(inflater.context, "Disconnected", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}