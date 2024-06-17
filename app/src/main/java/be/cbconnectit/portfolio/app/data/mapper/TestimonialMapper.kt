package be.cbconnectit.portfolio.app.data.mapper

import be.cbconnectit.portfolio.app.data.local.entities.TestimonialEntity
import be.cbconnectit.portfolio.app.data.remote.dto.TestimonialDto
import be.cbconnectit.portfolio.app.domain.model.Company
import be.cbconnectit.portfolio.app.domain.model.JobPosition
import be.cbconnectit.portfolio.app.domain.model.Testimonial

fun TestimonialEntity.toTestimonial() = Testimonial(
    id,
    imageUrl,
    fullName,
    null,
    JobPosition("", "", "", ""),
    review,
    createdAt,
    updatedAt
)

fun List<TestimonialEntity>.toTestimonials() = this.map { it.toTestimonial() }

fun TestimonialDto.toTestimonial() = Testimonial(
    id,
    imageUrl,
    fullName,
    company?.toCompany(),
    jobPosition.toJobPosition(),
    review,
    createdAt,
    updatedAt
)

fun TestimonialDto.toTestimonialEntity() = TestimonialEntity(
    id,
    imageUrl,
    fullName,
    company?.id,
    jobPosition.id,
    review,
    createdAt,
    updatedAt
)

fun List<TestimonialDto>.toEntities() = this.map { it.toTestimonialEntity() }

@JvmName("dtoToTestimonials")
fun List<TestimonialDto>.toTestimonials() = this.map { it.toTestimonial() }