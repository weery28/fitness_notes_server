package me.coweery.app.rest.controllers.auth.models

import com.fasterxml.jackson.annotation.JsonProperty

class GoogleAuthRequest(
    @JsonProperty("googleToken")
    val googleToken: String
)