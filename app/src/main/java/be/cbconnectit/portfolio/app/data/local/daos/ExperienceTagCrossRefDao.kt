package be.cbconnectit.portfolio.app.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import be.cbconnectit.portfolio.app.data.local.entities.ExperienceTagCrossRefEntity

@Dao
interface ExperienceTagCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(crossRefList: List<ExperienceTagCrossRefEntity>)
}