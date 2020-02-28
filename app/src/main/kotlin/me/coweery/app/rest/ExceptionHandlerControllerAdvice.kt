package me.coweery.app.rest

import com.fasterxml.jackson.annotation.JsonProperty
import me.coweery.app.exceptions.AuthenticationException
import me.coweery.app.exceptions.InvalidInputException
import me.coweery.app.exceptions.NotFoundException
import me.coweery.app.exceptions.UserException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Date

@ControllerAdvice
class ExceptionHandlerControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(UserException::class)
    fun handleUserException(ex: UserException, request: WebRequest): ResponseEntity<Any> {

        val status = when (ex) {
            is InvalidInputException -> HttpStatus.BAD_REQUEST
            is AuthenticationException -> HttpStatus.UNAUTHORIZED
            is NotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        val response = ExceptionResponse(
            Date(),
            status.value(),
            status.reasonPhrase,
            ex.message!!,
            (request as ServletWebRequest).request.servletPath
        )

        return handleExceptionInternal(
            ex,
            response,
            HttpHeaders(),
            status,
            request
        )
    }

    class ExceptionResponse(
        @field:JsonProperty("timestamp")
        val timestamp: Date,

        @field:JsonProperty("status")
        val status: Int,

        @field:JsonProperty("error")
        val error: String,

        @field:JsonProperty("message")
        val message: String,

        @field:JsonProperty("path")
        val path: String
    )
}