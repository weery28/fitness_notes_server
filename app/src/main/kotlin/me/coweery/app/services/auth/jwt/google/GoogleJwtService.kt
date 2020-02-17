package me.coweery.app.services.auth.jwt.google

import me.coweery.app.models.GoogleUserInfo

interface GoogleJwtService {

    fun extractUserInfo(token: String): GoogleUserInfo
}