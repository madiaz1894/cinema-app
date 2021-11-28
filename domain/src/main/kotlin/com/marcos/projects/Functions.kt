package com.marcos.projects

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T : Any> loggerFor(): Logger = LoggerFactory.getLogger(T::class.java)

typealias Result<A> = Either<Throwable, A>

fun <A> resultOf(f: () -> A): Result<A> =
    try {
        Right(f())
    } catch (e: Throwable) {
        Left(e)
    }
