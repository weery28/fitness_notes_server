package me.coweery.app.rest.controllers.user.models

import com.fasterxml.jackson.annotation.JsonProperty

class UserClientModel(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("email")
    val login: String
)