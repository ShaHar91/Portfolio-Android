package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = [WorkLinkCrossRefEntity.COLUMN_ID_LINK, WorkLinkCrossRefEntity.COLUMN_ID_WORK], tableName = WorkLinkCrossRefEntity.ENTITY_NAME)
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
