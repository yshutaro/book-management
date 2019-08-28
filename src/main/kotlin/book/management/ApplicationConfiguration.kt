package book.management

import javax.validation.constraints.NotNull

interface ApplicationConfiguration {

    @get:NotNull
    val max: Integer
}