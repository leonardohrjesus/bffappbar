package org.business.lhrjesus.bffappbar.entrypoint.http.data

import java.time.LocalDateTime

data class BarData (
    val name: String,
    val address: String,
    val latitude: Double,
    val date: LocalDateTime,
    val photoUrl: String,
    val category: String,
    val longitude: Double,
    val descDate: String
)