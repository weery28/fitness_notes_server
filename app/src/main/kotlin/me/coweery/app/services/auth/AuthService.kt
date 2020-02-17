package me.coweery.app.services.auth

interface AuthService {

    fun authenticate(username: String, password: String): String

    fun authenticateWithGoogle(googleToken: String): String
}