package be.cbconnectit.portfolio.app.data.mapper

import be.cbconnectit.portfolio.app.data.local.entities.LinkEntity
import be.cbconnectit.portfolio.app.data.remote.dto.LinkDto
import be.cbconnectit.portfolio.app.domain.model.Link

fun LinkEntity.toLink() = Link(
    id,
    url,
    type,
    createdAt,
    updatedAt
)

fun List<LinkEntity>.toLinks() = this.map { it.toLink() }

fun LinkDto.toLink() = Link(
    id,
    url,
    type,
    createdAt,
    updatedAt
)

fun LinkDto.toLinkEntity() = LinkEntity(
    id,
    url,
    type,
    createdAt,
    updatedAt
)

fun List<LinkDto>.toEntities() = this.map { it.toLinkEntity() }

@JvmName("dtoToLinks")
fun List<LinkDto>.toLinks() = this.map { it.toLink() }
