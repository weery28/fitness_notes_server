package me.coweery.app.services.training

import me.coweery.app.models.training.Training

interface TrainingService {

    fun save(training: Training): Training

    fun update(training: Training): Training

    fun save(saveTrainingParams: SaveTrainingParams, userId: Long): Training
}