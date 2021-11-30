package com.marcos.projects.http

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.marcos.projects.EntityNotAvailableException
import com.marcos.projects.EntityNotFoundException
import com.marcos.projects.RatingOutOfBoundException
import com.marcos.projects.UnprocessedEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.reflect.UndeclaredThrowableException

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: Exception): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("Requested resource does not found: ${getMessage(e)}")
    }

    @ExceptionHandler(EntityNotAvailableException::class)
    fun handleEntityNotAvailableException(e: Exception): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body("Requested resource is not available for the requested action: ${getMessage(e)}")
    }

    @ExceptionHandler(
        UnprocessedEntity::class,
    )
    fun handleFailedExternalDependency(e: Exception): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.FAILED_DEPENDENCY)
            .body(getMessage(e))
    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMissingParameter(e: MissingKotlinParameterException): ResponseEntity<Any> {
        return badRequestResponse("invalid parameter: ${e.parameter.name}")
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentExceptionParameter(e: IllegalArgumentException): ResponseEntity<Any> {
        return badRequestResponse(e.message)
    }

    @ExceptionHandler(RatingOutOfBoundException::class)
    fun handleRatingOutOfBoundParameter(e: RatingOutOfBoundException): ResponseEntity<Any> {
        return badRequestResponse(e.message)
    }

    private fun badRequestResponse(message: String?): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                mapOf(
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "error" to "Bad Request",
                    "message" to message
                )
            )
    }

    companion object {

        /**
         * This is a workaround, we could use RuntimeException instead.
         * */
        fun getMessage(e: Exception): String? {
            return when (e) {
                is UndeclaredThrowableException -> {
                    if (e.message == null && e.undeclaredThrowable?.message != null) {
                        return e.undeclaredThrowable?.message
                    }
                    return e.message
                }

                else -> e.message
            }
        }

        /**
         * This is a workaround, we could use RuntimeException instead.
         * */
        fun getMessage(e: Throwable): String? {
            return when (e) {
                is UndeclaredThrowableException -> {
                    if (e.message == null && e.undeclaredThrowable?.message != null) {
                        return e.undeclaredThrowable?.message
                    }
                    return e.message
                }

                else -> e.message
            }
        }
    }
}

