package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(TagEntity.ENTITY_NAME)
data class TagEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val slug: String,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
) {
    companion object {
        const val ENTITY_NAME = "tag"
    }
}