package book.management

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("application")
class ApplicationConfigurationProperties : ApplicationConfiguration {

    /*
    protected val DEFAULT_MAX: Int = 10

    override var max: Int? = DEFAULT_MAX
        set(max) {
            if (max != null) {
                field = max
            }
        }
        */
}