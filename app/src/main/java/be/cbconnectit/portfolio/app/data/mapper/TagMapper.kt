package be.cbconnectit.portfolio.app.data.mapper

import be.cbconnectit.portfolio.app.data.local.entities.TagEntity
import be.cbconnectit.portfolio.app.data.remote.dto.TagDto
import be.cbconnectit.portfolio.app.domain.model.Tag

fun TagEntity.toTag() = Tag(
    id,
    name,
    slug,
    createdAt,
    updatedAt
)

fun List<TagEntity>.toTags() = this.map { it.toTag() }

fun TagDto.toTag() = Tag(
    id,
    name,
    slug,
    createdAt,
    updatedAt
)

fun TagDto.toTagEntity() = TagEntity(
    id,
    name,
    slug,
    createdAt,
    updatedAt
)

fun List<TagDto>.toEntities() = this.map { it.toTagEntity() }

@JvmName("dtoToTags")

fun List<TagDto>.toTags() = this.map { it.toTag() }