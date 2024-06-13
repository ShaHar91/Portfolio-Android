package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = [WorkLinkCrossRefEntity.COLUMN_ID_LINK, WorkLinkCrossRefEntity.COLUMN_ID_WORK],
    indices = [Index(unique = true, value = [WorkLinkCrossRefEntity.COLUMN_ID_WORK, WorkLinkCrossRefEntity.COLUMN_ID_LINK])],
    tableName = WorkLinkCrossRefEntity.ENTITY_NAME
)
data class WorkLinkCrossRefEntity(
    val workId: String,
    val linkId: String
) {
    companion object {
        const val COLUMN_ID_LINK = "linkId"
        const val COLUMN_ID_WORK = "workId"

        const val ENTITY_NAME = "workLinkCrossRef"
    }
}
