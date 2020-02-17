package me.coweery.app.rest.security

import me.coweery.app.config.SecurityConfiguration.Companion.AUTH_HEADER
import me.coweery.app.services.auth.jwt.app.AppJwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter @Autowired constructor(
    private val appJwtService: AppJwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val jwt = getJwtFromRequest(request)

        if (!jwt.isNullOrEmpty() && appJwtService.validateToken(jwt)) {
            val jwtPayload = appJwtService.extractPayload(jwt)
            val authentication = UsernamePasswordAuthenticationToken(jwtPayload, null, emptyList())
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {

        return try {
            request.getHeader(AUTH_HEADER)
        } catch (ex: NullPointerException) {
            null
        }
    }
}