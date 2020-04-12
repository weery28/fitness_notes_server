package me.coweery.app.repositories

import me.coweery.app.models.training.FullTraining
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface FullTrainingRepository : JpaRepository<FullTraining, Long> {

    fun findByUserIdAndCreationDate(userId: Long, creationDate: Date): FullTraining?
}