package me.coweery.app.services.auth.jwt.google

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import me.coweery.app.exceptions.AuthenticationException
import me.coweery.app.models.GoogleUserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GoogleJwtServiceImpl @Autowired constructor(
    private val googleIdTokenVerifier: GoogleIdTokenVerifier
) : GoogleJwtService {

    override fun extractUserInfo(token: String): GoogleUserInfo {

        return googleIdTokenVerifier.verify(token)?.let {
            val payload = it.payload
            GoogleUserInfo(
                payload.subject,
                payload.email
            )
        } ?: throw AuthenticationException("Invalid google token")
    }
}