package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import be.cbconnectit.portfolio.app.domain.enums.LinkType

@Entity(tableName = LinkEntity.ENTITY_NAME)
data class LinkEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val type: LinkType,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
) {
    companion object {
        const val ENTITY_NAME = "link"
    }
}
