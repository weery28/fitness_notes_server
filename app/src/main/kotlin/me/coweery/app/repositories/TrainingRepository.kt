package me.coweery.app.repositories

import me.coweery.app.models.training.Training
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Date

interface TrainingRepository : JpaRepository<Training, Long> {

    fun findByUserIdAndCreationTime(userId: Long, creationTime: Date): Training?

    fun existsByIdAndUserId(id: Long, userId: Long): Boolean
}