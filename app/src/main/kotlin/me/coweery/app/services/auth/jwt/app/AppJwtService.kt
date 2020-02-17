package me.coweery.app.services.auth.jwt.app

import me.coweery.app.models.JwtPayload

interface AppJwtService {

    fun generateToken(payload: JwtPayload): String

    fun validateToken(token: String): Boolean

    fun extractPayload(token: String): JwtPayload?
}