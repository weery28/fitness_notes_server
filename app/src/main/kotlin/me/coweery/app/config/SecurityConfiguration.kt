package me.coweery.app.config

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SecurityConfiguration {

    companion object {
        const val AUTH_HEADER = "Authorization"
    }

    @Bean
    open fun googleIdTokenVerifier(): GoogleIdTokenVerifier {

        return GoogleIdTokenVerifier(
            NetHttpTransport(),
            JacksonFactory()
        )
    }
}