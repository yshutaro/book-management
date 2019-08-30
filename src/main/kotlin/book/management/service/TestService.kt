package book.management.service

import io.micronaut.context.annotation.Requires
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.Transactional

import javax.inject.Singleton

@Singleton
@Requires(beans = [JdbcTemplate::class])
open class TestService(private val jdbcTemplate: JdbcTemplate) {
    @Transactional
    open fun printSampleTable() {
        jdbcTemplate.query("select * from books") { rs ->
            println(rs.getString("id"))
            println(rs.getString("name"))
        }
    }
}

