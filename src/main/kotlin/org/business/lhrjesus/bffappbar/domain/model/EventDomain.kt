package org.business.lhrjesus.bffappbar.domain.model

import java.time.LocalDateTime

data class EventDomain (
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