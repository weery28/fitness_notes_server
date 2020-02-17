package me.coweery.app.config

import org.springframework.context.annotation.Configuration

@Configuration
open class SecurityConfiguration {

    companion object {
        const val AUTH_HEADER = "Authorization"
    }
}