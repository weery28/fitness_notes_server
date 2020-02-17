package me.coweery.app.rest.controllers.auth

import me.coweery.app.config.SecurityConfiguration.Companion.AUTH_HEADER
import me.coweery.app.rest.controllers.auth.models.AuthRequest
import me.coweery.app.rest.controllers.auth.models.GoogleAuthRequest
import me.coweery.app.services.auth.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController @Autowired constructor(
    private val authService: AuthService
) {

    @PostMapping("/login-google")
    fun loginWithGoogle(
        @RequestBody googleAuthRequest: GoogleAuthRequest
    ): ResponseEntity<Unit> {

        return authService.authenticateWithGoogle(
            googleAuthRequest.googleToken
        )
            .let {
                mapToAuthResponse(it)
            }
    }

    @PostMapping("/login")
    fun login(
        @RequestBody authRequest: AuthRequest
    ): ResponseEntity<Unit> {

        return authService.authenticate(
            authRequest.login,
            authRequest.password
        )
            .let {
                mapToAuthResponse(it)
            }
    }

    private fun mapToAuthResponse(authToken: String): ResponseEntity<Unit> {

        return ResponseEntity.ok()
            .headers(
                HttpHeaders().apply {
                    set(AUTH_HEADER, authToken)
                }
            )
            .body(Unit)
    }
}