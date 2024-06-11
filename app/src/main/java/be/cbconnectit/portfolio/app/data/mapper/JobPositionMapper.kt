package be.cbconnectit.portfolio.app.data.mapper

import be.cbconnectit.portfolio.app.data.local.entities.JobPositionEntity
import be.cbconnectit.portfolio.app.data.remote.dto.JobPositionDto
import be.cbconnectit.portfolio.app.domain.model.JobPosition

fun JobPositionEntity.toJobPosition() = JobPosition(
    id,
    name,
    createdAt,
    updatedAt
)

fun JobPositionDto.toJobPosition() = JobPosition(
    id,
    name,
    createdAt,
    updatedAt
)

fun JobPositionDto.toJobPositionEntity() = JobPositionEntity(
    id,
    name,
    createdAt,
    updatedAt
)