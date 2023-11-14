package com.example.nycschool.listener

import com.example.data.network.SchoolData

interface SchoolListItemClickListener {
    fun onItemClickListener(schoolData: SchoolData)
}