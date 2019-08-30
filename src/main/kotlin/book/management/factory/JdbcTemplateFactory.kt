package book.management.factory

import io.micronaut.context.annotation.Factory
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import javax.annotation.Resource

import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Factory
class JdbcTemplateFactory {
    @Inject
    private var dataSource: DataSource? = null

    @Bean
    @Singleton
    internal fun jdbcTemplate(): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}

