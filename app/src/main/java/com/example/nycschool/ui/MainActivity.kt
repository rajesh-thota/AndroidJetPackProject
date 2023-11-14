package com.example.nycschool.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.nycschool.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.toolbar?.let {
            setSupportActionBar(it)
        }

        initObservers()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            viewModel.showCurrentStatus(null)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initObservers() {
        viewModel.toolbarVisibility.observe(this) {
            if (it) {
                supportActionBar?.show()
            } else {
                supportActionBar?.hide()
            }
        }

        viewModel.toolbarText.observe(this) {
            supportActionBar?.title = it
        }

        viewModel.toolbarBackEnabled.observe(this) {
            supportActionBar?.setDisplayHomeAsUpEnabled(it)
        }

        viewModel.currentStatus.observe(this) {
            binding?.tvStatus?.isVisible = it.isNotBlank()
            binding?.tvStatus?.text = it
        }
    }
}