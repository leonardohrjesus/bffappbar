package org.business.lhrjesus.bffappbar.entrypoint.http

import com.sun.jdi.request.EventRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.business.lhrjesus.bffappbar.dataprovider.postgree.EventRepository
import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.Event
import org.business.lhrjesus.bffappbar.entrypoint.http.data.BarData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RestController
@RequestMapping("/api/bars")
@Tag(name = "Bares", description = "Endpoints para informações sobre bares")
class BarController(
    private val eventRepository: EventRepository
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

    fun formatDateTimeToPortuguese(dateTime: LocalDateTime): String {
        // 1. Define o padrão de formatação e a localidade (Português do Brasil)
        val pattern = "EEE, dd 'de' MMMM - HH:mm"
        val locale = Locale("pt", "BR")
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)

        // 2. Formata a data. O resultado será algo como "sáb., 16 de abril - 18:30"
        val formattedString = dateTime.format(formatter)

        // 3. Ajusta a capitalização para o formato desejado ("Sab, 16 de Abril - 18:30")
        return formattedString
            .replace(".", "") // Remove o ponto de "sáb."
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() } // Capitaliza a primeira letra ("Sab")
            .replace(" de ", " de ") // Garante o espaçamento
            .let {
                // Encontra e capitaliza o nome do mês
                val monthPart = it.substringAfter("de ").substringBefore(" -")
                it.replace(monthPart, monthPart.replaceFirstChar { char -> char.uppercase() })
            }
    }

    @GetMapping("/list")
    @Operation(summary = "Listar todos os bares", description = "Retorna todos os dados dos bares disponíveis.")
    fun list(): List<BarData?> {
        val response = eventRepository.findAll()
        return toResponse(response)
    }


    @PostMapping("/create")
    fun create(@RequestBody request: BarData): ResponseEntity<Event> {
        val event = Event(
            name = request.name,
            address = request.address,
            latitude = request.latitude,
            longitude = request.longitude,
            dateEvent = LocalDateTime.now(),
            photoUrl = request.photoUrl,
            category = request.category,
            rating = request.rating.toDouble(),
            typeEstablishment = request.typeEstablishment,
            videoUrl = request.videoUrl,
            linkInstagram = request.linkInstagram
        )

        val response = eventRepository.save<Event>(event)
        return ResponseEntity.ok(response)
    }

    private fun toResponse(list : List<Event>) : List<BarData?> {
        return list.map { event ->
            BarData(
                name = event.name!!,
                address = event.address!!,
                latitude = event.latitude!!,
                date = event.dateEvent!!,
                photoUrl = event.photoUrl!!,
                category = event.category!!,
                longitude = event.longitude!!,
                descDate = formatDateTimeToPortuguese(event.dateEvent!!),
                rating = event.rating.toString(),
                typeEstablishment = event.typeEstablishment!!,
                videoUrl = event.videoUrl!!,
                linkInstagram = event.linkInstagram!!

            )
        }
    }


}
