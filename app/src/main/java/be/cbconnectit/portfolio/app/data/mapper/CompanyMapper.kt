package be.cbconnectit.portfolio.app.data.mapper

import be.cbconnectit.portfolio.app.data.local.entities.CompanyEntity
import be.cbconnectit.portfolio.app.data.remote.dto.CompanyDto
import be.cbconnectit.portfolio.app.domain.model.Company

fun CompanyEntity.toCompany() = Company(
    id,
    name,
    emptyList(), // TODO!!
    createdAt,
    updatedAt
)

fun CompanyDto.toCompany() = Company(
    id,
    name,
    links.toLinks(),
    createdAt,
    updatedAt
)

fun CompanyDto.toCompanyEntity() = CompanyEntity(
    id,
    name,
    createdAt,
    updatedAt
)