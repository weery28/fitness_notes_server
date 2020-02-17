package me.coweery.app.rest.controllers.user.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.InvalidInputException

class CreateUserRequest(
    @JsonProperty("login")
    val login: String? = null,

    @JsonProperty("password")
    val password: String? = null
) {

    fun validate(): CreateUserRequest {

        if (login.isNullOrBlank()) {
            throw InvalidInputException("login should be not null or blank")
        }
        if (password.isNullOrBlank()) {
            throw InvalidInputException("password should be not null or blank")
        }
        return this
    }
}