package me.coweery.app.rest

import me.coweery.app.exceptions.AuthenticationException
import me.coweery.app.models.AuthenticatedUser
import me.coweery.app.models.JwtPayload
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class AuthenticatedUserArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == AuthenticatedUser::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val jwtPayload = SecurityContextHolder.getContext().authentication.principal
        return if (jwtPayload == null || jwtPayload !is JwtPayload) {
            throw AuthenticationException("Invalid access token")
        } else {
            AuthenticatedUser(jwtPayload.id)
        }
    }
}