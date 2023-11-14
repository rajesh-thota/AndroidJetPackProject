package com.example.nycschool.ui.projectselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.nycschool.R
import com.example.nycschool.databinding.FragmentProjectSelectorBinding
import com.example.nycschool.ui.BaseFragment
import com.example.nycschool.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProjectSelectorFragment : BaseFragment() {

    private var binding: FragmentProjectSelectorBinding? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    private var pickVisualMediaActivityResultLauncher: ActivityResultLauncher<PickVisualMediaRequest>? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickVisualMediaActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(5),
            {

            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentProjectSelectorBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.hideActionBar()
        mainViewModel.hideActionBarBack()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initListeners() {
        binding?.apply {
            btnRegularUI.setOnClickListener {
                navigationManager?.navigateToSchoolList()
            }
            btnComposeUI.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.if_more_time_is_allowed_i_can_implement_the_same_in_compose_ui_too_thanks),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}