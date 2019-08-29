package book.management.factory

import io.micronaut.context.annotation.Factory
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Factory
class JdbcTemplateFactory {
    @Inject
    lateinit var dataSource: DataSource

    @Bean
    @Singleton
    fun jdbcTemplate():JdbcTemplate {
        return JdbcTemplate(dataSource);
    }
}