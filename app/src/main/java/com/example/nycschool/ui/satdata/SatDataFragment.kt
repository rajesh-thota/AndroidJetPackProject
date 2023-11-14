package com.example.nycschool.ui.satdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.nycschool.databinding.FragmentSatDataBinding
import com.example.nycschool.ui.BaseFragment
import com.example.nycschool.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatDataFragment : BaseFragment() {
    private var binding: FragmentSatDataBinding? = null
    private val viewModel: SatDataViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val navArgs: SatDataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatDataBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel.readSatDataFromDatabase(navArgs.dbn)
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.showActionBar()
        mainViewModel.showActionBarBack()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initObservers() {
        viewModel.satData.observe(viewLifecycleOwner) {
            binding?.apply {
                tvMathValue.text = it.satMathAvgScore
                tvReadingValue.text = it.satCriticalReadingAvgScore
                tvWritingValue.text = it.satWritingAvgScore
                mainViewModel.setTitle(it.schoolName)
            }
        }
    }
}