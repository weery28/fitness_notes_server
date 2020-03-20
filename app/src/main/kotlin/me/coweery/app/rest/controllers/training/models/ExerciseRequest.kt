package me.coweery.app.rest.controllers.training.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.InvalidInputException

class ExerciseRequest(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("setsCount")
    val setsCount: Int? = null,

    @JsonProperty("weight")
    val weight: Float? = null,

    @JsonProperty("repsCount")
    val repsCount: Int? = null,

    @JsonProperty("sets")
    val sets: List<SetRequest>? = emptyList()
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