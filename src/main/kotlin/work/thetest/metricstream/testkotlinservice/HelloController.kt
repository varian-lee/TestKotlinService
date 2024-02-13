package work.thetest.metricstream.testkotlinservice

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.ResultSet
import java.sql.Statement
import javax.sql.DataSource


@RestController
@RequestMapping("/")
class HelloController {
    @Value("\${server.port}")
    private val port: String? = null

    private val logger = LoggerFactory.getLogger(this.javaClass.simpleName)


    @Autowired
    var dataSource: DataSource? = null

    @Autowired
    var jdbcTemplate: JdbcTemplate? = null

    @GetMapping("/")
    fun hello(): String {
        // appender 를 아예 설정하지 않았지만 뉴렐릭에 로그 전달됨
        logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");

        return "Hello from Spring3.1 with Kotlin First Service!!"
    }

    @GetMapping("/check")
    fun check(): String? {
        return java.lang.String.format("First Service on PORT %s", port)
    }

    @GetMapping("/connect-db")
    fun connectDB(): String? {
        dataSource!!.connection.use {connection ->
            println(dataSource!!.javaClass)
            println(connection.metaData.url)
            println(connection.metaData.userName)
            val statement: Statement = connection.createStatement()
            val sql = "SELECT * FROM cars"
            val rs: ResultSet = statement.executeQuery(sql);


            while (rs.next()) {
                val year: Int = rs.getInt("year")
                val brand: String = rs.getString("brand")
                val model: String = rs.getString("model")
                println("$year,$brand,$model")
            }
        }

        return "connect db done!";
    }

    @GetMapping("/error-with-handler")
    fun errorWithHandler(): String {
        throw IllegalArgumentException(); // RestControllerAdvice의 핸들러가 처리
        return "Hello from Spring3.1 with Kotlin First Service!!"
    }

    @GetMapping("/error-without-handler")
    fun errorWithoutHandler(): String {
        throw IllegalAccessException(); // RestControllerAdvice의 핸들러 등록 안됨
        return "Hello from Spring3.1 with Kotlin First Service!!"
    }
}
