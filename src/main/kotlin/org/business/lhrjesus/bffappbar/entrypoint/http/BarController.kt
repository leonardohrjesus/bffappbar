package org.business.lhrjesus.bffappbar.entrypoint.http

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.business.lhrjesus.bffappbar.dataprovider.postgree.EventRepository
import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.EventEntity
import org.business.lhrjesus.bffappbar.domain.usecase.CreateEventUseCase
import org.business.lhrjesus.bffappbar.domain.usecase.SearchEventUseCase
import org.business.lhrjesus.bffappbar.entrypoint.http.data.BarData
import org.business.lhrjesus.bffappbar.entrypoint.http.data.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/bars")
@Tag(name = "Bares", description = "Endpoints para informações sobre bares")
class BarController(
    private val searchEventUseCase : SearchEventUseCase,
    private val createEventUseCase : CreateEventUseCase
) {

    @GetMapping("/category")
    @Operation(summary = "Listar todos os bares", description = "Retorna todos os dados dos bares disponíveis.")
    fun listCategory(): List<String> {
        return listOf(
            "Rock",
            "Sertanejo",
            "Pagode",
            "Funk",
            "MPB",
            "Eletrônica",
            "PUB",
            "Rua",
        )
    }

    @PostMapping("/create")
    fun create(@RequestBody request: BarData): ResponseEntity<EventEntity> {
        val eventEntity = EventEntity(
            name = request.name,
            address = request.address,
            latitude = request.latitude,
            longitude = request.longitude,
            dateEvent = LocalDateTime.now(),
            photoUrl = request.photoUrl,
            categories = request.categories,
            rating = request.rating.toDouble(),
            typeEstablishment = request.typeEstablishment,
            videoUrl = request.videoUrl,
            linkInstagram = request.linkInstagram
        )
        val response =createEventUseCase.createEvent(eventEntity)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar bares com filtros", description = "Busca bares por categoria, período de data e/ou rating mínimo. Todos os filtros são opcionais e podem ser combinados.")
    fun search(
        @RequestParam(required = false) categories: Array<String>?,
        @RequestParam(required = false) startDate: String?,
        @RequestParam(required = false) endDate: String?,
        @RequestParam(required = false) minRating: Double?
    ): List<BarData?> {
        val events = searchEventUseCase.get(categories,startDate,endDate,minRating)
        return events.toResponse()
    }
}
