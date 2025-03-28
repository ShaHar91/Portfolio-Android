package be.cbconnectit.portfolio.app.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import be.cbconnectit.portfolio.app.data.local.entities.ExperienceEntity
import be.cbconnectit.portfolio.app.data.local.entities.ExperienceWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface ExperienceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(experiences: List<ExperienceEntity>)

    @Transaction
    @Query("SELECT * FROM experience")
    fun findAllFlow(): Flow<List<ExperienceWithRelations>>
}