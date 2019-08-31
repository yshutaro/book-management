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

    override fun search(name: String, author: String, publisher: String): List<Book> {

        // TODO もっとシンプルに書ける方法があれば書き直す。
        var query = "SELECT id, name, author, publisher FROM books"
        if( name.isBlank() && author.isBlank() && publisher.isBlank() ){
            return jdbcTemplate.query(query, rowMapper)
        } else if (name.isNotBlank() && author.isBlank() && publisher.isBlank()) {
            return jdbcTemplate.query("$query WHERE name like ?", rowMapper,
                    "%$name%")
        } else if (name.isBlank() && author.isNotBlank() && publisher.isBlank()) {
            return jdbcTemplate.query("$query WHERE author like ?", rowMapper,
                    "%$author%")
        } else if (name.isBlank() && author.isBlank() && publisher.isNotBlank()) {
            return jdbcTemplate.query("$query WHERE publisher like ?", rowMapper,
                    "%$publisher%")
        } else if (name.isNotBlank() && author.isNotBlank() && publisher.isBlank()) {
            return jdbcTemplate.query("$query WHERE name like ? AND author like ?",
                    rowMapper, "%$name%", "%$author%")
        } else if (name.isNotBlank() && author.isBlank() && publisher.isNotBlank()) {
            return jdbcTemplate.query("$query WHERE name like ? AND publisher like ?",
                    rowMapper, "%$name%", "%$publisher%")
        } else if (name.isBlank() && author.isNotBlank() && publisher.isNotBlank()) {
            return jdbcTemplate.query("$query WHERE author like ? AND publisher like ?",
                rowMapper, "%$author%", "%$publisher%")
        } else if (name.isNotBlank() && author.isNotBlank() && publisher.isNotBlank()) {
            return jdbcTemplate.query("$query WHERE name like ? AND author like ? AND publisher like ?",
                    rowMapper, "%$name%", "%$author%", "%$publisher%")
        } else {
            return jdbcTemplate.query(query, rowMapper)
        }
    }

}