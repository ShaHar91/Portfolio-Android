package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = [ExperienceTagCrossRefEntity.COLUMN_ID_TAG, ExperienceTagCrossRefEntity.COLUMN_ID_EXPERIENCE],
    indices = [Index(unique = true, value = [ExperienceTagCrossRefEntity.COLUMN_ID_EXPERIENCE, ExperienceTagCrossRefEntity.COLUMN_ID_TAG])],
    tableName = ExperienceTagCrossRefEntity.ENTITY_NAME
)
data class ExperienceTagCrossRefEntity(
    val experienceId: String,
    val tagId: String
) {
    companion object {
        const val COLUMN_ID_TAG = "tagId"
        const val COLUMN_ID_EXPERIENCE = "experienceId"

        const val ENTITY_NAME = "experienceTagCrossRef"
    }
}
