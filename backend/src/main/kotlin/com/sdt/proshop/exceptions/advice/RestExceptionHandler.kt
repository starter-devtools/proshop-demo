package com.sdt.proshop.exceptions.advice

import com.sdt.proshop.exceptions.DuplicateResourceException
import com.sdt.proshop.exceptions.MissingTokenException
import com.sdt.proshop.exceptions.RequestValidationException
import com.sdt.proshop.exceptions.ResourceNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.invoke.MethodHandles

@RestControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    private val log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders,
                                              status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        log.error(ex.message, ex);
        val fieldErrors = ex.fieldErrors
            .asSequence()
            .map { ApiError(it.field, it.defaultMessage!!) }
            .toList()

        val errorResponse = ErrorResponse(
            code = HttpStatus.BAD_REQUEST.value(),
            messages = fieldErrors,
        )
        val badResponse: ResponseEntity<Any> = ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)

        log.error("handleMethodArgumentNotValidException: {}", badResponse);
        return badResponse
    }

    @ExceptionHandler(value = [RequestValidationException::class])
    fun handleRequestValidationException(e: Exception): ResponseEntity<ApiResponse> {
        log.error(e.message, e);
        val responseEntity = buildError(e.message!!, HttpStatus.BAD_REQUEST);

        log.error("handleRequestValidationException: {}", responseEntity);
        return responseEntity;
    }

    @ExceptionHandler(value = [ResourceNotFoundException::class])
    fun handleResourceNotFoundException(e: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        log.error(e.message, e);
        val responseEntity = buildError(e.message, HttpStatus.NOT_FOUND);

        log.error("handleResourceNotFoundException: {}", responseEntity);
        return responseEntity;
    }

    @ExceptionHandler(value = [DuplicateResourceException::class])
    fun handleDuplicateResourceException(e: DuplicateResourceException): ResponseEntity<ApiResponse> {
        log.error(e.message, e);
        val responseEntity = buildError(e.message, HttpStatus.CONFLICT);

        log.error("handleDuplicateResourceException: {}", responseEntity);
        return responseEntity;
    }

    //Security Exceptions - translate security exceptions to 401
    @ExceptionHandler(value = [AccessDeniedException::class, MissingTokenException::class])
    fun handleAccessDeniedException(ex: Exception): ResponseEntity<ApiResponse> {
        log.error(ex.message, ex);
        val responseEntity = buildError(ex.message!!, HttpStatus.UNAUTHORIZED);

        log.error("handleAccessDeniedException: {}", responseEntity);
        return responseEntity;
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleCatchAllException(e: Exception): ResponseEntity<ApiResponse> {
        log.error(e.message, e);
        val responseEntity = buildError(e.message!!, HttpStatus.INTERNAL_SERVER_ERROR);

        log.error("handleCatchAllException: {}", responseEntity);
        return responseEntity;
    }

    private fun buildError(message: String, status: HttpStatus): ResponseEntity<ApiResponse> {
        val response = ApiResponse(code = status.value(), message)
        return ResponseEntity(response, status)
    }

}