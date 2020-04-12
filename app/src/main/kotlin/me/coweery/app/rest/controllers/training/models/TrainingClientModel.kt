package me.coweery.app.rest.controllers.training.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.InvalidInputException
import java.util.Date

class TrainingClientModel(
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("creationDate")
    val creationDate: Date? = null,

    @JsonProperty("isComplete")
    val isComplete: Boolean? = null,

    @JsonProperty("date")
    val date: Date? = null,

    @JsonProperty("exercises")
    val exercises: List<ExerciseClientModel> = emptyList()
) {

    fun validate(): TrainingClientModel {

        if (name.isNullOrBlank()) {
            throw InvalidInputException("name should be not null or blank")
        }
        if (creationDate == null) {
            throw InvalidInputException("creationTime should be not null")
        }
        exercises.forEach { it.validate() }
        return this
    }
}