package org.business.lhrjesus.bffappbar.domain.usecase

import org.business.lhrjesus.bffappbar.dataprovider.postgree.EventRepository
import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.EventEntity
import org.springframework.stereotype.Component

@Component
class CreateEventUseCase(
    private val eventRepository : EventRepository
) {

    fun createEvent(
        eventEntity : EventEntity
    ) : EventEntity{
        val response = eventRepository.save<EventEntity>(eventEntity)
        return response
    }
}