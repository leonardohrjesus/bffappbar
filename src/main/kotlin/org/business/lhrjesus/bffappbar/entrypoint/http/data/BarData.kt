package org.business.lhrjesus.bffappbar.entrypoint.http.data

import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.Event
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class BarData (
        val name: String,
        val address: String,
        val latitude: Double,
        val date: LocalDateTime,
        val photoUrl: String,
        val category: String,
        val longitude: Double,
        val descDate: String,
        val rating : String,
        val typeEstablishment : String,
        val videoUrl : String,
        val linkInstagram : String
)



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



