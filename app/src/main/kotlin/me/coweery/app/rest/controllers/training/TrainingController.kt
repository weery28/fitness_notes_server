package me.coweery.app.rest.controllers.training

import me.coweery.app.models.AuthenticatedUser
import me.coweery.app.rest.controllers.training.models.TrainingRequest
import me.coweery.app.rest.controllers.training.models.TrainingClientModel
import me.coweery.app.services.training.SaveTrainingParams
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
    fun create(
        @RequestBody request: TrainingRequest,
        user: AuthenticatedUser
    ): TrainingClientModel {

        return request.validate()
            .let {
                trainingService.save(
                    SaveTrainingParams(
                        request.name!!,
                        request.creationTime!!,
                        request.exercises.map {
                            SaveTrainingParams.Exercise(
                                it.name!!,
                                it.setsCount!!,
                                it.weight!!,
                                it.repsCount!!
                            )
                        }
                    ),
                    user.id
                )
            }
            .let {
                TrainingClientModel(

                )
            }
    }
}