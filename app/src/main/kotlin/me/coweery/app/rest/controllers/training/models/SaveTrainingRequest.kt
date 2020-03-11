package me.coweery.app.rest.controllers.training.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.InvalidInputException
import java.util.Date

class SaveTrainingRequest(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("creationTime")
    val creationTime: Date? = null,

    @JsonProperty("exercises")
    val exercises: List<Exercise>
) {

    class Exercise(
        @JsonProperty("name")
        val name: String? = null,

        @JsonProperty("setsCount")
        val setsCount: Int? = null,

        @JsonProperty("weight")
        val weight: Float? = null,

        @JsonProperty("repsCount")
        val repsCount: Int? = null
    ) {
        fun validate() {

            if (name.isNullOrBlank()) {
                throw InvalidInputException("name should be not null or blank")
            }
            if (setsCount == null) {
                throw InvalidInputException("setsCount should be not null")
            }
            if (weight == null) {
                throw InvalidInputException("weight should be not null")
            }
            if (repsCount == null) {
                throw InvalidInputException("repsCount should be not null")
            }
        }
    }


    fun validate(): SaveTrainingRequest {

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