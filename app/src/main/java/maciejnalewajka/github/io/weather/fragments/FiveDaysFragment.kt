package maciejnalewajka.github.io.weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import maciejnalewajka.github.io.weather.R
import maciejnalewajka.github.io.weather.databinding.FiveDaysFragmentBinding

class FiveDaysFragment : Fragment() {

    private lateinit var viewModel: FiveDaysViewModel
    private lateinit var viewModelFactory: FiveDaysViewModelFactory

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory =  FiveDaysViewModelFactory(inflater.context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FiveDaysViewModel::class.java)
        val binding : FiveDaysFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.five_days_fragment, container, false)

        return binding.root
    }
}