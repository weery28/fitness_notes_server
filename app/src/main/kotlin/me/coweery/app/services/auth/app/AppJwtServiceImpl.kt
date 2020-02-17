package me.coweery.app.services.auth.app

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import me.coweery.app.exceptions.AuthenticationException
import me.coweery.app.models.JwtPayload
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class AppJwtServiceImpl : AppJwtService {

    @Value("\${app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${app.jwtExpirationInMs}")
    private val jwtExpirationInMs: Int = 0

    override fun generateToken(payload: JwtPayload): String {

        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)

        return Jwts.builder()
            .setSubject(payload.id.toString())
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    override fun validateToken(token: String): Boolean {

        return try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    override fun extractPayload(token: String): JwtPayload? {

        return try {
            val claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body

            JwtPayload(
                claims.subject.toLong()
            )
        } catch (ex: Exception) {
            throw AuthenticationException(ex.message!!)
        }
    }
}