package com.example.nycschool

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import javax.inject.Inject

class NavigationManager @Inject constructor(
    private val activity: Activity
) {
    fun navigateToSchoolList() {
        activity.findNavController(R.id.navHostFragment)
            .navigate(R.id.action_project_selector_fragment_to_school_list_fragment)
    }

    fun navigateToSatData(dbn: String) {
        activity.findNavController(R.id.navHostFragment).navigate(
            R.id.action_school_list_fragment_to_sat_data_fragment, bundleOf(
                "dbn" to dbn
            )
        )
    }
}