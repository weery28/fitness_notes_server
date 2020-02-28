package me.coweery.app.services.training

import me.coweery.app.exceptions.NotFoundException
import me.coweery.app.models.training.Training
import me.coweery.app.repositories.TrainingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TrainingServiceImpl @Autowired constructor(
    private val trainingRepository: TrainingRepository
) : TrainingService {

    override fun create(training: Training): Training {

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
}