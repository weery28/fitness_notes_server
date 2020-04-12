package me.coweery.app.services.training

import me.coweery.app.models.training.ExerciseDescription
import me.coweery.app.models.training.FullExercise
import me.coweery.app.models.training.FullTraining
import me.coweery.app.models.training.Set
import me.coweery.app.repositories.ExerciseDescriptionRepository
import me.coweery.app.repositories.ExerciseRepository
import me.coweery.app.repositories.FullTrainingRepository
import me.coweery.app.repositories.TrainingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class TrainingServiceImpl @Autowired constructor(
    private val trainingRepository: TrainingRepository,
    private val exerciseDescriptionRepository: ExerciseDescriptionRepository,
    private val exerciseRepository: ExerciseRepository,
    private val fullTrainingRepository: FullTrainingRepository
) : TrainingService {

    @Transactional
    override fun save(trainingSaveModel: TrainingSaveModel, userId: Long): FullTraining {

        val exitingTraining = fullTrainingRepository.findByUserIdAndCreationDate(userId, trainingSaveModel.creationTime)
        return if (exitingTraining == null) {
            createTraining(trainingSaveModel, userId)
        } else {
            updateTraining(exitingTraining, trainingSaveModel)
        }
    }

    private fun createTraining(trainingSaveModel: TrainingSaveModel, userId: Long): FullTraining {

        val descriptions = getExerciseDescriptions(trainingSaveModel)

        return FullTraining(
            null,
            userId,
            trainingSaveModel.name,
            trainingSaveModel.isComplete,
            trainingSaveModel.creationTime,
            trainingSaveModel.date
        ).apply {
            exercises = trainingSaveModel.exercises.map {
                FullExercise(
                    null,
                    descriptions[it.name]!!,
                    null,
                    it.setsCount,
                    it.weight,
                    it.repsCount,
                    it.index
                ).apply {
                    sets = it.sets.map {
                        Set(
                            null,
                            it.index,
                            it.repsCount,
                            it.weight,
                            null
                        )
                    }
                }
            }
        }
            .let {
                fullTrainingRepository.save(it)
            }
    }

    private fun updateTraining(existingTraining: FullTraining, trainingSaveModel: TrainingSaveModel): FullTraining {

        val descriptions = getExerciseDescriptions(trainingSaveModel)

        return FullTraining(
            existingTraining.id,
            existingTraining.userId,
            trainingSaveModel.name,
            trainingSaveModel.isComplete,
            trainingSaveModel.creationTime,
            trainingSaveModel.date
        ).apply {
            //            val exercisesIdsForDelete = existingTraining.exercises
//                .asSequence()
//                .filter { existingExercise ->
//                    trainingSaveModel.exercises.firstOrNull {
//                        it.id == existingExercise.id
//                    } == null
//                }
//                .map { it.id!! }
//                .toList()
//
//            exerciseRepository.deleteByIdIn(exercisesIdsForDelete)

            val exercisesWithExistingExercises = trainingSaveModel.exercises.map { exercise ->
                Pair(
                    exercise,
                    existingTraining.exercises.firstOrNull { exercise.id == it.id }
                )
            }
            exercises = exercisesWithExistingExercises.map { (exerciseSaveModel, existingExercise) ->
                FullExercise(
                    existingExercise?.id,
                    descriptions[exerciseSaveModel.name]!!,
                    null,
                    exerciseSaveModel.setsCount,
                    exerciseSaveModel.weight,
                    exerciseSaveModel.repsCount,
                    exerciseSaveModel.index
                ).apply {
                    val setsWithExistingSets = exerciseSaveModel.sets.map { set ->
                        Pair(
                            set,
                            existingExercise?.sets?.firstOrNull { set.id == it.id }
                        )
                    }

                    sets = setsWithExistingSets.map { (setSaveModel, existingSet) ->
                        Set(
                            existingSet?.id,
                            setSaveModel.index,
                            setSaveModel.repsCount,
                            setSaveModel.weight,
                            null
                        )
                    }
                }
            }
        }
            .let {
                fullTrainingRepository.save(it)
            }
    }

    private fun getExerciseDescriptions(saveModel: TrainingSaveModel): Map<String, ExerciseDescription> {

        val names = saveModel.exercises.map { it.name }
        val existingDescriptions = exerciseDescriptionRepository.getAllByNameIn(names)
            .associateBy { it.name }
        val notExisting = names
            .asSequence()
            .filter { existingDescriptions[it] == null }
            .map { ExerciseDescription(null, it) }
            .toList()

        return existingDescriptions.toMutableMap()
            .apply {
                putAll(
                    exerciseDescriptionRepository.saveAll(notExisting).associateBy { it.name }
                )
            }
    }
}