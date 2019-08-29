package book.management

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional

import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import java.util.Arrays
import java.util.Optional

@Singleton
class JdbcBookRepository(@param:CurrentSession @field:PersistenceContext
                          private val entityManager: EntityManager,
                         private val applicationConfiguration: ApplicationConfiguration) : BookRepository {

    /*
//    @Override
//    @Transactional
     override fun create(@NotBlank name: String, @NotBlank author: String, @NotBlank publisher: String): Book {
        entityManager.createQuery("INSERT INTO book(:name, :author, :publisher")
                .setParameter("name", name)
                .setParameter("author", author)
                .setParameter("publisher", publisher)
                .getResultList()

        val id = entityManager.createQuery("SELECT last_insert_id() FROM book")
                .getResultList()
        return Book(id, name, author, publisher)
    }

//    @Override
//    @Transactional
    override fun update(book: Book) {
        entityManager.createQuery("UPDATE Book b SET name = :name, author = :author, publisher = :publisher where id = :id")
                .setParameter("name", book.name)
                .setParameter("author", book.author)
                .setParameter("publisher", book.publisher)
                .setParameter("id", book.id)
                .executeUpdate()
    }

//    @Override
//    @Transactional
    override fun delete(@NotNull id: Long) {
        findById(id).ifPresent({ book -> entityManager.remove(book) })
    }

//    @Transactional(readOnly = true)
//    fun findAll(@NotNull args: SortingAndOrderArguments): List<Book> {
    override fun findAll(): List<Book> {
        var qlString = "SELECT id, name, author, publisher FROM Book"
//        var qlString = "SELECT b FROM Book as b"
//        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
//            qlString += " ORDER BY b." + args.getSort().get() + " " + args.getOrder().get().toLowerCase()
//        }
        val query = entityManager.createQuery(qlString, Book::class.java)
//        query.setMaxResults(args.getMax().orElseGet(???({ applicationConfiguration.getMax() })))
//        args.getOffset().ifPresent(???({ query.setFirstResult() }))

        return query.getResultList()
    }

    //    @Override
//    @Transactional(readOnly = true)
    override fun findById(@NotNull id: Long): Optional<Book> {
        return Optional.ofNullable(entityManager.find(Book::class.java, id))
    }
    */



//    companion object {
//
//        private val VALID_PROPERTY_NAMES = Arrays.asList("id", "name")
//    }
    private val books: MutableList<Book> = mutableListOf()

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

    override fun findAll(): List<Book> = books.toList()

    override fun findById(id: Long): Book? = books.find { it.id == id }
}