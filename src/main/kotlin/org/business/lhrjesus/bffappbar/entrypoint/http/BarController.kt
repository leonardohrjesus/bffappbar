package org.business.lhrjesus.bffappbar.entrypoint.http

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.business.lhrjesus.bffappbar.entrypoint.http.data.BarData
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/bars")
@Tag(name = "Bares", description = "Endpoints para informações sobre bares")
class BarController {

    @GetMapping
    @Operation(summary = "Listar todos os bares", description = "Retorna todos os dados dos bares disponíveis.")
    fun listBar(): List<BarData> {
        return listOf(
            BarData(
                 "Vila 567 ",
                 "R. Aspicuelta, 567 - Vila Madalena",
                  -23.5657261,
                 LocalDateTime.now(),
                 "https://lh3.googleusercontent.com/p/AF1QipOdntTx9QXDxQtZ5tXJVOC4lCEPT5LSsV24aaE2=s1360-w1360-h1020",
                  "Sertanejo",
                -46.6512292

            ),
            BarData(
                 "Patriarca Bar",
                "R. Mourato Coelho, 1059 - Vila Madalena",
                -23.558703,
                LocalDateTime.now(),
                "https://www.baressp.com.br/bares/fotos2/cervejaria-patriarca-baressp-1-min.jpg",
                "Pagode",
                -46.69057794171285
            )
        )
    }
}
