package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.SerialName

@Entity(WorkEntity.ENTITY_NAME)
data class WorkEntity(
    @PrimaryKey
    val id: String,
    @SerialName("banner_image_url")
    val bannerImageUrl: String,
    @SerialName("image_url")
    val imageUrl: String,
    val title: String,
    @SerialName("short_description")
    val shortDescription: String,
    val description: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
) {
    companion object {
        const val ENTITY_NAME = "work"
    }
}

data class WorkWithTags(
    @Embedded
    val work: WorkEntity,
    @Relation(
        parentColumn = "id",
        entity = LinkEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = WorkLinkCrossRefEntity::class,
            parentColumn = WorkLinkCrossRefEntity.COLUMN_ID_WORK,
            entityColumn = WorkLinkCrossRefEntity.COLUMN_ID_LINK
        )
    )
    val links: List<LinkEntity>,
    @Relation(
        parentColumn = "id",
        entity = TagEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = WorkTagCrossRefEntity::class,
            parentColumn = WorkTagCrossRefEntity.COLUMN_ID_WORK,
            entityColumn = WorkTagCrossRefEntity.COLUMN_ID_TAG
        )
    )
    val tags: List<TagEntity>
)