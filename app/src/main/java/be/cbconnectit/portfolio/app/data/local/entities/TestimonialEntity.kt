package be.cbconnectit.portfolio.app.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.SerialName

@Entity(TestimonialEntity.ENTITY_NAME)
data class TestimonialEntity(
    @PrimaryKey
    val id: String = "",
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("full_name")
    val fullName: String = "",
    @SerialName("company_id")
    val companyId: String? = null,
    @SerialName("job_position_id")
    val jobPositionId: String? = null,
    val review: String = "",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String = ""
) {
    companion object {
        const val ENTITY_NAME = "testimonial"
    }
}

data class TestimonialWithRelations(
    @Embedded
    val testimonial: TestimonialEntity,
    @Relation(parentColumn = "companyId", entityColumn = "id")
    val company: CompanyEntity,
    @Relation(parentColumn = "jobPositionId", entityColumn = "id")
    val jobPosition: JobPositionEntity,
)