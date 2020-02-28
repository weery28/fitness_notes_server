package me.coweery.app.rest.controllers.training

import me.coweery.app.models.AuthenticatedUser
import me.coweery.app.models.training.Training
import me.coweery.app.rest.controllers.training.models.CreateTrainingRequest
import me.coweery.app.rest.controllers.training.models.TrainingClientModel
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
        @RequestBody request: CreateTrainingRequest,
        user: AuthenticatedUser
    ): TrainingClientModel {

        return request.validate()
            .let {
                trainingService.create(
                    Training(

                    )
                )
            }
    }

    private fun mapToClientModel
}