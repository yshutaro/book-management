package book.management

import javax.inject.Singleton
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.Size

@Singleton
class BookForm {

    @NotBlank
    @Size(max = 50)
    var name: String? = null

    @NotBlank
    @Size(max = 50)
    var author: String? = null

    @NotBlank
    @Size(max = 50)
    var publisher: String? = null
}
