package book.management

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "book")
class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotNull
    @Column(name = "name", nullable = false)
    var name: String? = null

    @NotNull
    @Column(name = "author", nullable = false)
    var author: String? = null

    @NotNull
    @Column(name = "publisher", nullable = false)
    var publisher: String? = null

    constructor() {}

    constructor(@NotNull author: String, @NotNull name: String, @NotNull publisher: String) {
        this.author = author
        this.name = name
        this.publisher = publisher
    }

//    @Override
//    fun toString(): String {
//        val sb = StringBuilder()
//        sb.append("Book{")
//        sb.append("id=")
//        sb.append(id)
//        sb.append(", name='")
//        sb.append(name)
//        sb.append("', author='")
//        sb.append(author)
//        sb.append("', publisher='")
//        sb.append(publisher)
//        sb.append("'}")
//        return sb.toString()
//    }
}