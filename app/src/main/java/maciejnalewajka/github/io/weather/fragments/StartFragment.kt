package maciejnalewajka.github.io.weather.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import maciejnalewajka.github.io.weather.R
import maciejnalewajka.github.io.weather.databinding.StartFragmentBinding

class StartFragment : Fragment() {

    private lateinit var viewModel: StartViewModel
    private lateinit var viewModelFactory: StartViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory =  StartViewModelFactory(inflater.context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StartViewModel::class.java)
        val binding: StartFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.start_fragment,
            container,
            false
        )
        binding.startViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.base.observe(viewLifecycleOwner, Observer {
            @SuppressLint("SetTextI18n")
            binding.temperature.text = "${it.main.temp}"+viewModel.symbol
            binding.cityName.text = it.name
            val context = binding.imgWeather.context

            binding.imgWeather.setImageResource(viewModel.getImgWeatherId(context))
        })

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToSettingsFragment())
        }

        binding.refresh.setOnRefreshListener({
            viewModel.get()
            binding.refresh.isRefreshing = false
        })

        return binding.root
    }
}