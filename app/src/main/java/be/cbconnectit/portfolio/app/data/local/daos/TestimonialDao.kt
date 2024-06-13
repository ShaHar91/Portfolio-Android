package be.cbconnectit.portfolio.app.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import be.cbconnectit.portfolio.app.data.local.entities.TestimonialEntity
import be.cbconnectit.portfolio.app.data.local.entities.TestimonialWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface TestimonialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(testimonials: List<TestimonialEntity>)

    @Transaction
    @Query("SELECT * FROM testimonial")
    fun findAllFlow(): Flow<List<TestimonialWithRelations>>
}