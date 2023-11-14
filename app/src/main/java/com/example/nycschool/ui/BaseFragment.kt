package com.example.nycschool.ui

import androidx.fragment.app.Fragment
import com.example.nycschool.NavigationManager
import javax.inject.Inject

open class BaseFragment : Fragment() {
    var navigationManager: NavigationManager? = null
        @Inject set
}