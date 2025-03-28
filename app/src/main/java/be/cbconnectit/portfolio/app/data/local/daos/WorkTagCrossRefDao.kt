package be.cbconnectit.portfolio.app.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import be.cbconnectit.portfolio.app.data.local.entities.WorkTagCrossRefEntity
import be.cbconnectit.portfolio.app.data.local.entities.WorkWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkTagCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(crossRefList: List<WorkTagCrossRefEntity>)
}