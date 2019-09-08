package book.management

import javax.inject.Singleton
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Singleton
class BookForm {

    @NotBlank(message = "書籍名は必須です。")
    @Size(max = 50, message = "書籍名は50文字以内で入力してください。")
    var name: String? = null

    @NotBlank(message = "著者名は必須です。")
    @Size(max = 50, message = "著者名は50文字以内で入力してください。")
    var author: String? = null

    @NotBlank(message = "出版社名は必須です。")
    @Size(max = 50, message = "出版社名は50文字以内で入力してください。")
    var publisher: String? = null
}
