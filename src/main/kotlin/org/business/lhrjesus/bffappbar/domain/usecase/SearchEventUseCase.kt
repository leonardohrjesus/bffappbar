package org.business.lhrjesus.bffappbar.domain.usecase

import org.apache.logging.log4j.LogManager
import org.business.lhrjesus.bffappbar.dataprovider.postgree.EventRepository
import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.EventEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.jvm.java

@Component
class SearchEventUseCase(
    private val eventRepository: EventRepository,
    ) {

    private val logger = LogManager.getLogger(SearchEventUseCase::class.java)


    fun get(
        categories: Array<String>?,
        startDate: String?,
        endDate: String?,
        minRating: Double?
    ): List<EventEntity> {
        logger.info ("C=SearchEventUseCase m=get , stage=init  categories=$categories , startDate=$startDate , endDate=$endDate , minRating=$minRating ")
        val start = parseDate(startDate)
        val end = parseDate(endDate)
        val categoriesNormalized = normalizeCategories(categories)
        val response = eventRepository.search(categoriesNormalized, minRating ,start, end )
            logger.info ("C=SearchEventUseCase m=get , stage=finished response=$response")
        return response

    }

    private fun parseDate(date: String?): LocalDateTime? {
        return date?.let {
            LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }


    private fun normalizeCategories(categories: Array<String>?): Array<String>? {
        return categories?.map { category ->
            when (category.lowercase().trim()) {
                "sertanejo" -> "Sertanejo"
                "pagode" -> "Pagode"
                "funk" -> "Funk"
                "mpb" -> "MPB"
                "rock" -> "Rock"
                "eletrônica", "eletronica", "electronic" -> "Eletrônica"
                "reggae" -> "Reggae"
                "samba" -> "Samba"
                "jazz" -> "Jazz"
                "hip hop", "hip-hop", "rap" -> "RAP"
                else -> category
            }
        }?.toTypedArray()
    }

}