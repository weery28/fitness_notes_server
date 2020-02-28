package me.coweery.app.rest.controllers.training.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.InvalidInputException
import java.util.Date

class CreateTrainingRequest(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("creationTime")
    val creationTime: Date? = null,

    @JsonProperty("exercises")
    val exercises: List<Exercise>
) {

    class Exercise(
        @JsonProperty("name")
        val name: String,

        @JsonProperty("approachesCount")
        val approachesCount: Int,

        @JsonProperty("weight")
        val weight: Float,

        @JsonProperty("repetitionsCount")
        val repetitionsCount: Int
    )


    fun validate(): CreateTrainingRequest {

        if (name.isNullOrBlank()) {
            throw InvalidInputException("name should be not null or blank")
        }

        if (completed == null) {
            throw InvalidInputException("completed should be not null")
        }

        if (creationTime == null) {
            throw InvalidInputException("creationTime should be not null")
        }
        return this
    }
}