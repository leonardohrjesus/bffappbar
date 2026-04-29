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


    @Query(value = """
        SELECT * FROM events e 
        WHERE ST_DistanceSphere(
            ST_MakePoint(e.longitude, e.latitude), 
            ST_MakePoint(:userLon, :userLat)
        ) <= :radiusInMeters
    """, nativeQuery = true)
    fun findEventsWithinRadius(
        @Param("userLat") userLat: Double,
        @Param("userLon") userLon: Double,
        @Param("radiusInMeters") radiusInMeters: Double
    ): List<EventEntity>


    @Query(
        value = """
        SELECT *
        FROM events e
        WHERE 
          -- Filtro de Localização (Raio de Distância)
          (CAST(:userLat AS double precision) IS NULL OR CAST(:userLon AS double precision) IS NULL OR ST_DistanceSphere(
              ST_MakePoint(e.longitude, e.latitude), 
              ST_MakePoint(CAST(:userLon AS double precision), CAST(:userLat AS double precision))
          ) <= CAST(:radiusInMeters AS double precision))
          
          -- Filtros de Endereço
          AND (CAST(:state AS text) IS NULL OR e.state ILIKE CAST(:state AS text))
          AND (CAST(:city AS text) IS NULL OR e.city ILIKE CAST(:city AS text))
          
          -- Filtro de Categorias (Array overlap)
          AND (CAST(:categories AS text[]) IS NULL OR e.categories && CAST(:categories AS text[]))
          
          -- Filtro de Avaliação (Rating)
          AND (CAST(:rating AS double precision) IS NULL OR e.rating >= CAST(:rating AS double precision))
          
          -- Filtros de Data (Importante o cast para timestamp)
          AND (e.date_event >= COALESCE(CAST(:startDate AS timestamp), e.date_event))
          AND (e.date_event <= COALESCE(CAST(:endDate AS timestamp), e.date_event))
    """,
        nativeQuery = true
    )
    fun searchEvents(
        @Param("categories") categories: Array<String>?,
        @Param("rating") rating: Double?,
        @Param("startDate") startDate: LocalDateTime?,
        @Param("endDate") endDate: LocalDateTime?,
        @Param("userLat") userLat: Double?,
        @Param("userLon") userLon: Double?,
        @Param("radiusInMeters") radiusInMeters: Double?,
        @Param("state") state: String?,
        @Param("city") city: String?
    ): List<EventEntity>

}