package book.management

import io.micronaut.context.annotation.Requires
import javax.inject.Singleton
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.transaction.annotation.Transactional

@Singleton
@Requires(beans = [JdbcTemplate::class])
class JdbcBookRepository(private val jdbcTemplate: JdbcTemplate) : BookRepository {

    private val rowMapper = RowMapper<Book> { rs, _ ->
        Book(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getString("publisher")
                )
    }

    @Transactional
    override fun create(name: String, author: String, publisher: String): Book {
        jdbcTemplate.update("INSERT INTO books(name, author, publisher) VALUES(?, ?, ?)",
                name, author, publisher)
        val id = jdbcTemplate.queryForObject("SELECT last_insert_rowid()", Integer::class.java).toLong()
        return Book(id, name, author, publisher)
    }

    @Transactional
    override fun update(book: Book) {
        jdbcTemplate.update("UPDATE books SET name = ?, author = ?, publisher = ? WHERE id = ?",
                book.name, book.author, book.publisher, book.id)
    }

    @Transactional
    override fun delete(id: Long) {
        jdbcTemplate.update("DELETE from books WHERE id = ?", id)
    }

    override fun findAll(): List<Book> =
            jdbcTemplate.query("SELECT id, name, author, publisher FROM books", rowMapper)

    override fun findById(id: Long): Book? =
            jdbcTemplate.query("SELECT id, name, author, publisher FROM books WHERE id = ?",
                    rowMapper, id).firstOrNull()

}