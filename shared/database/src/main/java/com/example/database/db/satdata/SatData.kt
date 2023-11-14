package com.example.database.db.satdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.security.encrypt

@Entity(tableName = "sat_data")
data class SatData(
    @PrimaryKey @ColumnInfo(name = "dbn") val dbn: String = "",
    @ColumnInfo(name = "schoolName") val schoolName: String,
    @ColumnInfo(
        name = "satCriticalReadingAvgScore", typeAffinity = ColumnInfo.BLOB
    ) val satCriticalReadingAvgScore: ByteArray,
    @ColumnInfo(
        name = "satMathAvgScore", typeAffinity = ColumnInfo.BLOB
    ) val satMathAvgScore: ByteArray,
    @ColumnInfo(
        name = "satWritingAvgScore", typeAffinity = ColumnInfo.BLOB
    ) val satWritingAvgScore: ByteArray
) {
    companion object {
        fun getFromNetworkModel(satData: com.example.data.network.SatData): SatData {
            return SatData(
                dbn = satData.dbn,
                schoolName = satData.schoolName,
                satCriticalReadingAvgScore = satData.satCriticalReadingAvgScore.encrypt(),
                satMathAvgScore = satData.satMathAvgScore.encrypt(),
                satWritingAvgScore = satData.satWritingAvgScore.encrypt(),
            )
        }
    }
}