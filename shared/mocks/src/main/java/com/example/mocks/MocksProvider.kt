package com.example.mocks

import android.content.Context
import com.example.data.network.SatData
import com.example.data.network.SchoolData
import com.google.gson.Gson

object MocksProvider {

    private const val SCHOOL_LIST_JSON_FILENAME = "school_list.json"
    private const val SAT_DATA_JSON_FILENAME = "sat_data.json"

    fun getSchoolData(context: Context): List<SchoolData> =
        readJson(context, SCHOOL_LIST_JSON_FILENAME)?.let {
            Gson().fromJson(
                it, Array<SchoolData>::class.java
            ).toList()
        }.orEmpty()

    fun getSatData(context: Context): List<SatData> =
        readJson(context, SAT_DATA_JSON_FILENAME)?.let {
            Gson().fromJson(
                it, Array<SatData>::class.java
            ).toList()
        }.orEmpty()

    private fun readJson(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }
}