package org.business.lhrjesus.bffappbar.dataprovider.postgree

import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.EventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface EventRepository : JpaRepository<EventEntity, Long> {

    @Query(
        value = """
            SELECT *
            FROM events e
            WHERE (CAST(:categories AS text[]) IS NULL OR e.categories && CAST(:categories AS text[]))
              AND (:rating IS NULL OR e.rating >= :rating)
              AND e.date_event >= COALESCE(:startDate, e.date_event)
              AND e.date_event <= COALESCE(:endDate, e.date_event)
        """,
        nativeQuery = true
    )
    fun search(
        @Param("categories") categories: Array<String>?, // Mudamos de List para Array aqui
        @Param("rating") rating: Double?,
        @Param("startDate") startDate: LocalDateTime?,
        @Param("endDate") endDate: LocalDateTime?
    ): List<EventEntity>
}