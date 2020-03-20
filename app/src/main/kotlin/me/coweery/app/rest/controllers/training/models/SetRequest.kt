package me.coweery.app.rest.controllers.training.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.InvalidInputException

class SetRequest(
    @JsonProperty("repsCount")
    val repsCount: Int?,

    @JsonProperty("weight")
    val weight: Float?
) {

    fun validate() {

        if (repsCount == null) {
            throw InvalidInputException("repsCount should be not null")
        }
        if (weight == null) {
            throw InvalidInputException("weight should be not null")
        }
    }
}