package me.coweery.app.repositories

import me.coweery.app.models.training.Exercise
import me.coweery.app.models.training.FullExercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExerciseRepository : JpaRepository<Exercise, Long> {

    fun deleteByIdIn(ids: List<Long>)
}