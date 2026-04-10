package org.business.lhrjesus.bffappbar.entrypoint.http

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.business.lhrjesus.bffappbar.entrypoint.http.data.BarData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RestController
@RequestMapping("/api/bars")
@Tag(name = "Bares", description = "Endpoints para informações sobre bares")
class BarController {

    @GetMapping("/list")
    @Operation(summary = "Listar todos os bares", description = "Retorna todos os dados dos bares disponíveis.")
    fun listBar(): List<BarData> {
        return listOf(
            BarData(    
                 "Vila 567 ",
                 "R. Aspicuelta, 567 - Vila Madalena",
                   -23.558163612967796,
                 LocalDateTime.now(),
                 "https://lh3.googleusercontent.com/p/AF1QipOdntTx9QXDxQtZ5tXJVOC4lCEPT5LSsV24aaE2=s1360-w1360-h1020",
                  "Sertanejo",
                -46.69052073254774,
                  formatDateTimeToPortuguese(LocalDateTime.now()),
                "3.6",
                "Balada",
                "https://www.youtube.com/shorts/_-UClyNhMRk",
                "https://www.instagram.com/vila567bar/?hl=pt-br"


            ),
            BarData(
                 "Patriarca Bar",
                "R. Mourato Coelho, 1059 - Vila Madalena",
                -23.558501013687366,
                LocalDateTime.now(),
                "https://www.baressp.com.br/bares/fotos2/cervejaria-patriarca-baressp-1-min.jpg",
                "Pagode",
                -46.69120701252684,
                 formatDateTimeToPortuguese(LocalDateTime.now()),
                "4.0",
                "Bar",
                "https://www.youtube.com/shorts/htcZ5R_IgKE",
                "https://www.instagram.com/patriarcabar/?hl=pt-br"
            )  ,
            BarData(
                 "Baile do Helipa",
                "Rua Adriana, n°63",
                -23.616090860967553,
                LocalDateTime.now(),
                "https://cdn.agenciamural.org.br/2024/02/05100906/helipa_baile1.png",
                "Funk",
                -46.589443926015846,
                 formatDateTimeToPortuguese(LocalDateTime.now()),
                "2.5",
            "Rua",
                "https://www.youtube.com/watch?v=ERBY_tpOIsU",
                "https://www.instagram.com/bailedohelipa.ofc/"
            ),
            BarData(
                 "Festival Novabrasil 25 Anos com Seu Jorge, Alceu Valença, Nando Reis e mais",
                "Avenida Queiroz Filho, 1365 - Vila Hamburguesa",
                 -23.54466982557449,
                        LocalDateTime.now(),
                "https://images.ticket360.com.br/images.ticket360/eventos/interna/31387-20250919175214.webp",
                "MPB",
                -46.732441371785626,
                 formatDateTimeToPortuguese(LocalDateTime.now()),
                "4.3",
                "Festival",
                "https://www.youtube.com/watch?v=2ldBu2CHAaE",
                "https://www.instagram.com/festivalnovabrasil/?hl=pt-br"
            ),
            BarData(
                 "The Blue Pub",
                "Alameda Ribeirão Preto, 384 - Bela Vista, ",
                -23.560919227429586,
                        LocalDateTime.now(),
                "https://www.baressp.com.br/bares/fotos2/the-blue-pub-baressp-7.jpg",
                "Rock",
                -46.65040182559529,
                 formatDateTimeToPortuguese(LocalDateTime.now()),
                "4.2",
                "PUB",
                "https://www.youtube.com/watch?v=XpWl9qsnotY",
                "https://www.instagram.com/thebluepub/"
            ),
            BarData(
                 "D-Edge",
                " Av. Mário de Andrade, 141 - Barra Funda",
                -23.52806216527986,
                        LocalDateTime.now(),
                "https://static.wixstatic.com/media/050e55_21a51abf7e4946258af5680168a0aaa4~mv2.jpg/v1/fit/w_2500,h_1330,al_c/050e55_21a51abf7e4946258af5680168a0aaa4~mv2.jpg",
                "Eletrônica",
                -46.66210926160029,
                 formatDateTimeToPortuguese(LocalDateTime.now()),
                "3.8",
                "Balada",
                "youtube.com/watch?v=HZsumh9DIXY&pp=ygUGRC1FZGdl",
                "https://www.instagram.com/dedgesp/"
            ),
            BarData(
                "Villa Country",
                " Av. Francisco Matarazzo, 774 - Água Branca",
                -23.527432992534035,
                LocalDateTime.now(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqV9om9Tm_OpTlxLjReMWDrwLmVBwKUoOCqg&s",
                "Sertanejo",
                -46.67041066834576,
                formatDateTimeToPortuguese(LocalDateTime.now()),
                "4.5",
                "Balada",
                "https://www.youtube.com/watch?v=UNk885LHUj0",
                "https://www.instagram.com/villacountrybar/?hl=pt-br"
            ),
        )
    }

    @GetMapping("/category")
    @Operation(summary = "Listar todos os bares", description = "Retorna todos os dados dos bares disponíveis.")
    fun listCategoty(): List<String> {
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


}
