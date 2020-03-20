package me.coweery.app.services.training

import me.coweery.app.exceptions.NotFoundException
import me.coweery.app.models.training.Exercise
import me.coweery.app.models.training.ExerciseDescription
import me.coweery.app.models.training.Set
import me.coweery.app.models.training.Training
import me.coweery.app.repositories.ExerciseDescriptionRepository
import me.coweery.app.repositories.ExerciseRepository
import me.coweery.app.repositories.TrainingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class TrainingServiceImpl @Autowired constructor(
    private val trainingRepository: TrainingRepository,
    private val exerciseDescriptionRepository: ExerciseDescriptionRepository,
    private val exerciseRepository: ExerciseRepository
) : TrainingService {

    override fun save(training: Training): Training {

        return trainingRepository.findByUserIdAndCreationTime(training.userId, training.creationTime)
            ?: trainingRepository.save(training)
    }

    override fun update(training: Training): Training {

        return if (trainingRepository.existsByIdAndUserId(training.id!!, training.userId)) {
            throw NotFoundException("Training with id = ${training.id} not found for user")
        } else {
            trainingRepository.save(training)
        }
    }

    @Transactional
    override fun save(saveTrainingParams: SaveTrainingParams, userId: Long): Training {

        val existingTraining = trainingRepository.findByUserIdAndCreationTime(userId, saveTrainingParams.creationTime)

        if (existingTraining != null) {
            val exercisesForSaving = saveTrainingParams.saveExerciseParams
                .filter { saveExerciseParams ->
                    existingTraining.exercises.firstOrNull { equalExercises(it, saveExerciseParams) } == null
                }

            val exercisesForDeleting = existingTraining.exercises
                .filter { existingExercise ->
                    saveTrainingParams.saveExerciseParams.firstOrNull { equalExercises(existingExercise, it) } == null
                }
        } else {

        }



        val t = existingTraining?.exercises?.firstOrNull()?.id

        val existingDescriptions = exerciseDescriptionRepository.getAllByNameIn(
            saveTrainingParams.saveExerciseParams.map { it.name }
        ).associateBy { it.name }

        val exercises = saveTrainingParams.saveExerciseParams.map {
            Exercise(
                null,
                existingDescriptions[it.name] ?: exerciseDescriptionRepository.save(
                    ExerciseDescription(
                        null,
                        it.name
                    )
                ),
                null,
                it.setsCount,
                it.weight,
                it.repsCount
            )
        }

        existingTraining?.exercises
            ?.map { it.id!! }
            ?.let { exerciseRepository.deleteByIdIn(it) }

        return trainingRepository.save(
            Training(
                existingTraining?.id,
                userId,
                saveTrainingParams.name,
                false,
                saveTrainingParams.creationTime,
                exercises
            ).apply {
                this.exercises.forEach { it.training = this }
            }
        )
    }

    private fun equalExercises(exercise: Exercise, saveParams: SaveExerciseParams): Boolean {

        return exercise.exerciseDescription.name == saveParams.name &&
                exercise.repsCount == saveParams.repsCount &&
                exercise.setsCount == saveParams.setsCount &&
                exercise.weight == saveParams.weight
    }

    private fun equalSets(set: Set, saveParams: SaveSetParams, number: Int): Boolean {

        return set.number == number &&
                set.repsCount == saveParams.repsCount &&
                set.weight == saveParams.weight
    }
}