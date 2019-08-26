package book.management

import javax.inject.Singleton
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.Size

@Singleton
class BookCreateForm {

    @NotBlank
    @Size(max = 50)
    var name: String? = null
    var author: String? = null
    var publisher: String? = null
}
