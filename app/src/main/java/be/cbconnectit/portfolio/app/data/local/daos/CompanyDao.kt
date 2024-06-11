package be.cbconnectit.portfolio.app.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import be.cbconnectit.portfolio.app.data.local.entities.CompanyEntity
import be.cbconnectit.portfolio.app.data.local.entities.LinkEntity

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(list: List<CompanyEntity>)
}