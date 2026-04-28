package org.business.lhrjesus.bffappbar.dataprovider.postgree.data

import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import java.time.Instant
import java.time.LocalDateTime


@Entity
@Table(name = "events", schema = "public")
data class EventEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    val name: String? = null,
    val address: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,

    @Column(name = "date_event")
    val dateEvent: LocalDateTime? = null,

    @Column(name = "photo_url")
    val photoUrl: String? = null,

    @Column(name = "categories", columnDefinition = "text[]")
    val categories: List<String>? = null,

    val rating: Double? = null,

    @Column(name = "type_establishment")
    val typeEstablishment: String? = null,

    @Column(name = "video_url")
    val videoUrl: String? = null,

    @Column(name = "link_instagram")
    val linkInstagram: String? = null
)
