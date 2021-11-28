package com.marcos.projects

data class EntityNotFoundException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause)

data class EntityNotAvailableException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause)

open class UnprocessedEntity(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)
