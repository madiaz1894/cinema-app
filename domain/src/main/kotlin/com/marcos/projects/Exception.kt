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

open class RatingOutOfBoundException(
    val rating: String
) : Exception("The rating $rating is out of bound. It must be between 0 and 5 ")
