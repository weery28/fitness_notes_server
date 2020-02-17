package me.coweery.app.rest.controllers.auth.models

import com.fasterxml.jackson.annotation.JsonProperty

class AuthRequest(
    @JsonProperty("login")
    val login: String,

    @JsonProperty("password")
    val password: String
)