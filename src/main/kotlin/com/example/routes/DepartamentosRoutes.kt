package com.example.routes

import com.example.models.Departamento
import com.example.repositories.departamentoRepository.DepartamentoRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val END_POINT = "api/departamentos"

fun Application.departamentosRoutes() {
    val departamentoRepository = DepartamentoRepository()

    routing {
        route("/$END_POINT"){
            get {
                val result = mutableListOf<Departamento>()

                // Procesamos el flujo
                departamentoRepository.findAll().collect {
                    result.add(it)
                }
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }
}