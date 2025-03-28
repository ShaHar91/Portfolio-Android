package be.cbconnectit.portfolio.app.domain.repository

import be.cbconnectit.portfolio.app.domain.model.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    suspend fun fetchAllServices(): Result<List<Service>>

    fun findAllServices(parentServiceId: String? = null): Flow<List<Service>>

    fun findParentServiceName(parentServiceId: String): Flow<Service?>
}