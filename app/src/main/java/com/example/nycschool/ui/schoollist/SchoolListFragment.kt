package com.example.nycschool.ui.schoollist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.data.network.SchoolData
import com.example.nycschool.R
import com.example.nycschool.adapter.SchoolListRecyclerViewAdapter
import com.example.nycschool.databinding.FragmentSchoolListBinding
import com.example.nycschool.listener.SchoolListItemClickListener
import com.example.nycschool.ui.BaseFragment
import com.example.nycschool.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolListFragment : BaseFragment() {
    private var binding: FragmentSchoolListBinding? = null
    private val viewModel: SchoolListViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var adapter: SchoolListRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        if (viewModel.schoolList.value.isNullOrEmpty()) {
            mainViewModel.showCurrentStatus(getString(R.string.fetching_data))
            viewModel.fetchSchoolData()
        } else {
            adapter?.setData(viewModel.schoolList.value.orEmpty())
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.showActionBar()
        mainViewModel.setTitle(getString(R.string.schools_list))
        mainViewModel.showActionBarBack()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initViews() {
        adapter = SchoolListRecyclerViewAdapter(object : SchoolListItemClickListener {
            override fun onItemClickListener(schoolData: SchoolData) {
                navigationManager?.navigateToSatData(schoolData.dbn)
            }
        }).apply {
            binding?.rvSchoolList?.adapter = this
        }
    }

    private fun initObservers() {
        viewModel.schoolDataReceived.observe(viewLifecycleOwner) {
            mainViewModel.showCurrentStatus(getString(R.string.fetching_sat_data))
            viewModel.fetchSatData()
        }

        viewModel.satDataReceived.observe(viewLifecycleOwner) {
            mainViewModel.showCurrentStatus(getString(R.string.reading_and_decrypting_records_from_database))
            viewModel.readSchoolDataFromDatabase()
        }

        viewModel.schoolList.observe(viewLifecycleOwner) {
            mainViewModel.showCurrentStatus(null)
            adapter?.setData(it)
        }

        viewModel.currentStatus.observe(viewLifecycleOwner) {
            mainViewModel.showCurrentStatus(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = it
            binding?.rvSchoolList?.isVisible = !it
        }
    }
}