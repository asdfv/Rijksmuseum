package by.grodno.vasili.domain.error

/**
 * Default converter for errors, return error as it is.
 */
class DefaultErrorConverter : ErrorConverter {
    override fun convert(error: Throwable) = UnknownError(error)
}
