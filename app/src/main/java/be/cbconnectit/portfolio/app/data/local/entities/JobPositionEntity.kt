package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = JobPositionEntity.ENTITY_NAME)
data class JobPositionEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
) {
    companion object{
        const val ENTITY_NAME = "job_position"
    }
}