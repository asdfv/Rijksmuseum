package by.grodno.vasili.domain.error

/**
 * Base class for application errors.
 */
open class RijksThrowable(
        error: Throwable? = null, message: String? = null
) : Throwable(message, error)

/**
 * API key is wring or missing.
 */
class WrongApiKey(
        error: Throwable? = null, message: String? = null
) : RijksThrowable(error, message)

/**
 * Receiving data takes to long.
 */
class Timeout(
        error: Throwable? = null, message: String? = null
) : RijksThrowable(error, message)

/**
 * Data not found.
 */
class NotFound(
        error: Throwable? = null, message: String? = null
) : RijksThrowable(error, message)

/**
 * Error occurs when retrieving data.
 */
class RetrievingDataError(
        error: Throwable? = null, message: String? = null
) : RijksThrowable(error, message)

/**
 * Unknown error.
 */
class UnknownError(
        error: Throwable? = null, message: String? = null
) : RijksThrowable(error, message)

