package com.example.plugins

import com.example.routes.testRoutes
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Empleado Departamento")
        }
    }

    // Definimos el resto de rutas dentro de Routes
    testRoutes()
}
