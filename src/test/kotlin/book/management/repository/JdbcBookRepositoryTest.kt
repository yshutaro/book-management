package book.management.repository

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import javax.inject.Inject
import org.assertj.core.api.Assertions.assertThat
import book.management.Book

class JdbcBookRepositoryTest(@Inject var sut: JdbcBookRepository) : Spek({
    /*
        describe("Bookリポジトリのテスト") {

            beforeEachTest {
            }

            afterEachTest {
            }
            it("何も登録していない状態のときfindAllは空のリストを返す") {
                val got = sut.findAll()
                assertThat(got).isEmpty()
            }
            it("createで登録したデータをfindByで取得する") {
                val book = sut.create("testbook", "testauthor", "testpublisher")
                val got = sut.findById(book.id)
                assertThat(got).isEqualTo(book)
            }
            it("updateで更新したデータをfindByで取得する") {
                //TODO 正しくテスト出来ていない？
                val book = sut.create("testbook", "testauthor", "testpublisher")
                val bookModify = Book(book.id, "testbook2", "testauthor2", "testpublisher2")
                sut.update(bookModify)
                val got = sut.findById(bookModify.id)
                assertThat(got).isEqualTo(bookModify)
            }
    }
     */
})
