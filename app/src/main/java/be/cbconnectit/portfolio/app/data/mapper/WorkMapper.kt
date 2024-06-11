package be.cbconnectit.portfolio.app.data.mapper

import be.cbconnectit.portfolio.app.data.local.entities.WorkEntity
import be.cbconnectit.portfolio.app.data.remote.dto.WorkDto
import be.cbconnectit.portfolio.app.domain.model.Link
import be.cbconnectit.portfolio.app.domain.model.Tag
import be.cbconnectit.portfolio.app.domain.model.Work

fun WorkEntity.toWork(links: List<Link> = emptyList(), tags: List<Tag> = emptyList()) = Work(
    id,
    bannerImageUrl,
    imageUrl,
    title,
    shortDescription,
    description,
    links,
    tags,
    createdAt,
    updatedAt
)

fun List<WorkEntity>.toWorks() = this.map { it.toWork() }

fun WorkDto.toWork() = Work(
    id,
    bannerImageUrl,
    imageUrl,
    title,
    shortDescription,
    description,
    links.toLinks(),
    tags.toTags(),
    createdAt,
    updatedAt
)

fun WorkDto.toWorkEntity() = WorkEntity(
    id,
    bannerImageUrl,
    imageUrl,
    title,
    shortDescription,
    description,
    createdAt,
    updatedAt
)

fun List<WorkDto>.toEntities() = this.map { it.toWorkEntity() }

@JvmName("dtoToWorks")
fun List<WorkDto>.toWorks() = this.map { it.toWork() }

