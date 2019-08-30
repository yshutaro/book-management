package book.management

import book.management.service.TestService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.inject.Inject

@Controller("/hello")
class HelloController {

    @Inject
    private val testService: TestService? = null

    @Get("/")
    fun index(): String {
        testService?.printSampleTable() ?: "Hello"
        return "Goodmorning"
    }
}

