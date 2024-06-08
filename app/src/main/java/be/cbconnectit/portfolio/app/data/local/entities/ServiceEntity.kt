package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(ServiceEntity.ENTITY_NAME)
data class ServiceEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("image_url")
    val imageUrl: String,
    val title: String,
    @ColumnInfo("short_description")
    val shortDescription: String? = null,
    val description: String,
    @ColumnInfo("banner_description")
    val bannerDescription: String? = null,
    @ColumnInfo("parent_service_id")
    val parentServiceId: String? = null,
    @ColumnInfo("extra_info")
    val extraInfo: String? = null,
    val tagId: String? = null,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
) {
    companion object {
        const val ENTITY_NAME = "service"
    }
}
