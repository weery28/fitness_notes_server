package me.coweery.app.rest.controllers.training.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.InvalidInputException
import java.util.Date

class TrainingRequest(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("creationTime")
    val creationTime: Date? = null,

    @JsonProperty("exercises")
    val exercises: List<ExerciseRequest>
) {

    fun validate(): TrainingRequest {

        if (name.isNullOrBlank()) {
            throw InvalidInputException("name should be not null or blank")
        }
        if (creationTime == null) {
            throw InvalidInputException("creationTime should be not null")
        }
        if (exercises.isEmpty()) {
            throw InvalidInputException("exercises should be not empty")
        }
        exercises.forEach { it.validate() }
        return this
    }
}