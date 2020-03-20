package me.coweery.app.repositories

import me.coweery.app.models.training.Set
import org.springframework.data.jpa.repository.JpaRepository

interface SetRepository : JpaRepository<Set, Long> {

    fun findBy
}