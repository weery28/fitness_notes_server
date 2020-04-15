package me.coweery.app.rest.controllers.training

import me.coweery.app.models.AuthenticatedUser
import me.coweery.app.models.training.FullTraining
import me.coweery.app.rest.controllers.training.models.ExerciseClientModel
import me.coweery.app.rest.controllers.training.models.SetClientModel
import me.coweery.app.rest.controllers.training.models.TrainingClientModel
import me.coweery.app.services.training.ExerciseSaveModel
import me.coweery.app.services.training.SetSaveModel
import me.coweery.app.services.training.TrainingSaveModel
import me.coweery.app.services.training.TrainingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/training")
class TrainingController @Autowired constructor(
    private val trainingService: TrainingService
) {

    @PostMapping
    fun save(
        @RequestBody clientModel: TrainingClientModel,
        user: AuthenticatedUser
    ): TrainingClientModel {

        return clientModel.validate()
            .let {
                trainingService.save(mapToIntervalModel(clientModel), user.id)
            }
            .let {
                mapToClientModel(it)
            }
    }

    private fun mapToIntervalModel(clientModel: TrainingClientModel): TrainingSaveModel {

        return TrainingSaveModel(
            clientModel.id,
            clientModel.name!!,
            clientModel.creationDate!!,
            clientModel.exercises.mapIndexed { exerciseIndex, exerciseClientModel ->
                ExerciseSaveModel(
                    exerciseClientModel.id,
                    exerciseClientModel.name!!,
                    exerciseClientModel.setsCount!!,
                    exerciseClientModel.weight!!,
                    exerciseClientModel.repsCount!!,
                    exerciseIndex,
                    exerciseClientModel.sets.mapIndexed { i, setClientModel ->
                        SetSaveModel(
                            setClientModel.id,
                            setClientModel.repsCount!!,
                            setClientModel.weight!!,
                            i
                        )
                    }
                )
            },
            clientModel.isComplete!!,
            clientModel.date!!
        )
    }

    private fun mapToClientModel(training: FullTraining): TrainingClientModel {

        return TrainingClientModel(
            training.id,
            training.name,
            training.creationDate,
            training.isComplete,
            training.date,
            training.exercises
                .asSequence()
                .sortedBy { it.index }
                .map { exercise ->
                    ExerciseClientModel(
                        exercise.id,
                        exercise.exerciseDescription.name,
                        exercise.setsCount,
                        exercise.weight,
                        exercise.repsCount,
                        exercise.sets
                            .asSequence()
                            .sortedBy { it.index }
                            .map {
                                SetClientModel(
                                    it.id,
                                    it.repsCount,
                                    it.weight
                                )
                            }
                            .toList()
                    )
                }
                .toList()
        )
    }
}