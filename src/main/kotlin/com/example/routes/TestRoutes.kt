package com.example.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val END_POINT = "api/test"

fun Application.testRoutes() {

    routing {
        route("/$END_POINT") {
            // getAll
            get {
                println("getAll")
                // Respond
                call.respond(HttpStatusCode.OK, "Get test")
            }
        }
    }
}