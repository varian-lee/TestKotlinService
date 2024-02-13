package work.thetest.metricstream.testkotlinservice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    protected fun handleBaseException(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.status(499).body("임의의 서버 에러 발생!! from First Service")
    }
}
