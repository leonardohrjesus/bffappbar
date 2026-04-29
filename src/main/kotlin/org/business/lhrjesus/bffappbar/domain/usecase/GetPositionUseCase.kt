package org.business.lhrjesus.bffappbar.domain.usecase

import org.business.lhrjesus.bffappbar.dataprovider.postgree.EventRepository
import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.EventEntity
import org.springframework.stereotype.Component

@Component
class GetPositionUseCase(
    private val eventRepository: EventRepository,
    ) {
    fun getNearbyEvents(lat: Double, lon: Double): List<EventEntity> {
        val radiusInMeters = 20000.0 // 20km
        return eventRepository.findEventsWithinRadius(lat, lon, radiusInMeters)
    }


}