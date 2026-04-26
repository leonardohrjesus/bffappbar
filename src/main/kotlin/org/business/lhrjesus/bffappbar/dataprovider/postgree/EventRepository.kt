package org.business.lhrjesus.bffappbar.dataprovider.postgree

import org.business.lhrjesus.bffappbar.dataprovider.postgree.data.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {
    // Você pode adicionar métodos de busca customizados aqui, por exemplo:
    fun findByCategory(category: String): List<Event>
}