package org.business.lhrjesus.bffappbar.dataprovider.postgree

import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.EventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface EventRepository : JpaRepository<EventEntity, Long> {
    // Você pode adicionar métodos de busca customizados aqui, por exemplo:
    fun findByCategory(category: String): List<EventEntity>
    fun findByDateEventBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<EventEntity>
    fun findByRatingGreaterThanEqual(rating: Double): List<EventEntity>
    fun findByCategoryAndDateEventBetween(category: String, startDate: LocalDateTime, endDate: LocalDateTime): List<EventEntity>
    fun findByCategoryAndRatingGreaterThanEqual(category: String, rating: Double): List<EventEntity>
    fun findByDateEventBetweenAndRatingGreaterThanEqual(startDate: LocalDateTime, endDate: LocalDateTime, rating: Double): List<EventEntity>
    fun findByCategoryAndDateEventBetweenAndRatingGreaterThanEqual(category: String, startDate: LocalDateTime, endDate: LocalDateTime, rating: Double): List<EventEntity>

    @Query("""
    SELECT e FROM EventEntity e
    WHERE (:category IS NULL OR e.category = :category)
      AND (:minRating IS NULL OR e.rating >= :minRating)
      AND (
            (CAST(:startDate AS timestamp) IS NULL OR e.dateEvent >= :startDate)
        AND (CAST(:endDate AS timestamp) IS NULL OR e.dateEvent <= :endDate)
      )
""")
    fun search(
        @Param("category") category: String?,
        @Param("startDate") startDate: LocalDateTime?,
        @Param("endDate") endDate: LocalDateTime?,
        @Param("minRating") minRating: Double?
    ): List<EventEntity>
}