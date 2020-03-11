package me.coweery.app.repositories

import me.coweery.app.models.training.Training
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface TrainingRepository : JpaRepository<Training, Long> {

    fun findByUserIdAndCreationTime(userId: Long, creationTime: Date): Training?

    fun findAllByUserIdAndCreationTime(userId: Long, creationTime: Date): Training?

    fun existsByIdAndUserId(id: Long, userId: Long): Boolean
}