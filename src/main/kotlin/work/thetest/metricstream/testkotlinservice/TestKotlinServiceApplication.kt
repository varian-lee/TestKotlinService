package work.thetest.metricstream.testkotlinservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestKotlinServiceApplication

fun main(args: Array<String>) {
	runApplication<TestKotlinServiceApplication>(*args)
}
