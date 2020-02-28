package me.coweery.app.config

import me.coweery.app.rest.AuthenticatedUserArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
open class WebMvcConfiguration : WebMvcConfigurerAdapter() {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(AuthenticatedUserArgumentResolver())
    }
}