package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = [WorkTagCrossRefEntity.COLUMN_ID_TAG, WorkTagCrossRefEntity.COLUMN_ID_WORK],
    indices = [Index(unique = true, value = [WorkTagCrossRefEntity.COLUMN_ID_WORK, WorkTagCrossRefEntity.COLUMN_ID_TAG])],
    tableName = WorkTagCrossRefEntity.ENTITY_NAME
)
data class WorkTagCrossRefEntity(
    val workId: String,
    val tagId: String
) {
    companion object {
        const val COLUMN_ID_TAG = "tagId"
        const val COLUMN_ID_WORK = "workId"

        const val ENTITY_NAME = "workTagCrossRef"
    }
}
