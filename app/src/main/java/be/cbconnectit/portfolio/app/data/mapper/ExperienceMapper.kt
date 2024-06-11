package be.cbconnectit.portfolio.app.data.mapper

import be.cbconnectit.portfolio.app.data.local.entities.ExperienceEntity
import be.cbconnectit.portfolio.app.data.remote.dto.ExperienceDto
import be.cbconnectit.portfolio.app.domain.model.Company
import be.cbconnectit.portfolio.app.domain.model.Experience
import be.cbconnectit.portfolio.app.domain.model.JobPosition

fun ExperienceEntity.toExperience() = Experience(
    id,
    shortDescription,
    description,
    from,
    to,
    emptyList(),
    asFreelance,
    JobPosition("", "", "", ""),
    Company("", "", emptyList(), "", ""),
    createdAt,
    updatedAt
)

fun List<ExperienceEntity>.toExperiences() = this.map { it.toExperience() }

fun ExperienceDto.toExperience() = Experience(
    id,
    shortDescription,
    description,
    from,
    to,
    tags.map { it.toTag() },
    asFreelance,
    jobPosition.toJobPosition(),
    company.toCompany(),
    createdAt,
    updatedAt
)

fun ExperienceDto.toExperienceEntity() = ExperienceEntity(
    id,
    shortDescription,
    description,
    from,
    to,
    asFreelance,
    jobPosition.id,
    company.id,
    createdAt,
    updatedAt
)

fun List<ExperienceDto>.toEntities() = this.map { it.toExperienceEntity() }

@JvmName("dtoToExperiences")
fun List<ExperienceDto>.toExperiences() = this.map { it.toExperience() }