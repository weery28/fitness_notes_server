package me.coweery.app.services.training

import me.coweery.app.models.training.Training

interface TrainingService {

    fun create(training: Training): Training

    fun update(training: Training): Training
}