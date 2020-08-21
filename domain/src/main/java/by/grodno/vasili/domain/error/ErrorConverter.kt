package by.grodno.vasili.domain.error

/**
 * Converter for [Throwable]s.
 */
interface ErrorConverter {

    /**
     * Convert one [Throwable] to application error.
     */
    fun convert(error: Throwable): RijksThrowable
}
