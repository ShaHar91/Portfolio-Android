package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(ExperienceEntity.ENTITY_NAME)
data class ExperienceEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("short_description")
    val shortDescription: String,
    val description: String,
    val from: String,
    val to: String,
    @ColumnInfo("as_freelance")
    val asFreelance: Boolean,
    @ColumnInfo("job_position_id")
    val jobPositionId: String,
    @ColumnInfo("company_id")
    val companyId: String,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
) {
    companion object {
        const val ENTITY_NAME = "experience"
    }
}

data class ExperienceWithRelations(
    @Embedded
    val experience: ExperienceEntity,
    @Relation(parentColumn = "company_id", entityColumn = "id")
    val company: CompanyEntity,
    @Relation(parentColumn = "job_position_id", entityColumn = "id")
    val jobPosition: JobPositionEntity,
    @Relation(
        parentColumn = "id",
        entity = TagEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = ExperienceTagCrossRefEntity::class,
            parentColumn = ExperienceTagCrossRefEntity.COLUMN_ID_EXPERIENCE,
            entityColumn = ExperienceTagCrossRefEntity.COLUMN_ID_TAG
        )
    )
    val tags: List<TagEntity>
)