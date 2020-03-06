package me.coweery.app.repositories

import me.coweery.app.models.training.ExerciseDescription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExerciseDescriptionRepository : JpaRepository<ExerciseDescription, Long> {

    fun getAllByNameIn(names: List<String>): List<ExerciseDescription>
}