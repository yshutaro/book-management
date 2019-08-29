package book.management

import io.micronaut.context.annotation.Requires
import javax.inject.Singleton
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper


@Singleton
class JdbcBookRepository(private val jdbcTemplate: JdbcTemplate) : BookRepository {

    private val books: MutableList<Book> = mutableListOf()

    private val rowMapper = RowMapper<Book> { rs, _ ->
        Book(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getString("publisher"))
    }

    private val maxId: Long
        get() = books.map(Book::id).max() ?:0

    override fun create(name: String, author: String, publisher: String): Book {
        val id = maxId + 1
        val book = Book(id, name, author, publisher)
        books += book
        return book
    }

    override fun update(book: Book) {
        books.replaceAll { t ->
            if (t.id == book.id) book
            else t
        }
    }

    override fun delete(id: Long) {
        books.toList().forEach {
            if (it.id == id) books.remove(it)
        }
    }

    override fun findAll(): List<Book> =
        jdbcTemplate.query("SELECT id, name, author, publisher FROM books", rowMapper)

    override fun findById(id: Long): Book? = books.find { it.id == id }
}