package be.cbconnectit.portfolio.app.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import be.cbconnectit.portfolio.app.data.local.entities.ServiceEntity
import be.cbconnectit.portfolio.app.data.local.entities.ServiceWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(services: List<ServiceEntity>)

    @Transaction
    @Query("SELECT * FROM service WHERE parent_service_id IS :parentServiceId")
    fun findAllFlow(parentServiceId: String? = null): Flow<List<ServiceWithTags>>
}
