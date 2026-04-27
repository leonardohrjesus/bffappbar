package org.business.lhrjesus.bffappbar.domain.usecase

import org.business.lhrjesus.bffappbar.dataprovider.postgree.EventRepository
import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.EventEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class SearchEventUseCase(
    private val eventRepository: EventRepository,

    ) {

    fun get(
        category: String?,
        startDate: String?,
        endDate: String?,
        minRating: Double?
    ): List<EventEntity> {

        val start = parseDate(startDate)
        val end = parseDate(endDate)
        return eventRepository.search(category, start, end, minRating)
    }

    private fun parseDate(date: String?): LocalDateTime? {
        return date?.let {
            LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }

}