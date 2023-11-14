package com.example.database.db.schooldata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.security.encrypt

@Entity(tableName = "school_data")
data class SchoolData(
    @PrimaryKey @ColumnInfo(name = "dbn") val dbn: String = "",
    @ColumnInfo(name = "schoolName") val schoolName: String,
    @ColumnInfo(
        name = "city", typeAffinity = ColumnInfo.BLOB
    ) val city: ByteArray
) {
    companion object {
        fun getFromNetworkModel(schoolData: com.example.data.network.SchoolData): SchoolData {
            return SchoolData(
                dbn = schoolData.dbn,
                schoolName = schoolData.schoolName,
                city = schoolData.city.encrypt()
            )
        }
    }
}