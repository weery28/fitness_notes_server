package me.coweery.app.services.training

import me.coweery.app.exceptions.NotFoundException
import me.coweery.app.models.training.Exercise
import me.coweery.app.models.training.ExerciseDescription
import me.coweery.app.models.training.Training
import me.coweery.app.repositories.ExerciseDescriptionRepository
import me.coweery.app.repositories.TrainingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class TrainingServiceImpl @Autowired constructor(
    private val trainingRepository: TrainingRepository,
    private val exerciseDescriptionRepository: ExerciseDescriptionRepository
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
    override fun save(trainingParams: SavingTrainingParams, userId: Long): Training {

        val existingTraining = trainingRepository.findByUserIdAndCreationTime(userId, trainingParams.creationTime)
        val exerciseNames = trainingParams.exercises.map { it.name }
        val existingDescriptions = exerciseDescriptionRepository.getAllByNameIn(exerciseNames)
            .associateBy { it.name }

        val exercises = trainingParams.exercises.map {
            Exercise(
                null,
                existingDescriptions[it.name] ?: exerciseDescriptionRepository.save(
                    ExerciseDescription(
                        null,
                        it.name
                    )
                ),
                null,
                it.approachesCount,
                it.weight,
                it.repetitionsCount
            )
        }

        return trainingRepository.save(
            Training(
                existingTraining?.id,
                userId,
                trainingParams.name,
                false,
                trainingParams.creationTime,
                exercises
            )
        )
    }
}