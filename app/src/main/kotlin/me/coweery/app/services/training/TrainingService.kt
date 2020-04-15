package me.coweery.app.services.training

import me.coweery.app.models.training.FullTraining

interface TrainingService {

    fun save(trainingSaveModel: TrainingSaveModel, userId: Long): FullTraining
}