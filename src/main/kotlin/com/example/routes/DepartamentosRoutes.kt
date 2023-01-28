package com.example.routes

import com.example.models.Departamento
import com.example.repositories.departamentoRepository.DepartamentoRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val END_POINT = "api/departamentos"

fun Application.departamentosRoutes() {
    val departamentoRepository = DepartamentoRepository()

    routing {
        route("/$END_POINT") {
            // Get All
            get {
                val result = mutableListOf<Departamento>()

                // Procesamos el flujo
                departamentoRepository.findAll().collect {
                    result.add(it)
                }
                call.respond(HttpStatusCode.OK, result)
            }

            // Get by Id /endpoint/id
            /*
             * EN THUNDERCLIENT, HEADERS -> Content-Type __ application/json
             * Luego en BODY -> JSON
             */
            get("{id}") {
                try {
                    val id = call.parameters["id"]!!.toInt()
                    val departamento = departamentoRepository.findById(id)
                    if (departamento != null) {
                        call.respond(
                            HttpStatusCode.OK, departamento.toString()
                        )
                    } else call.respond(HttpStatusCode.NotFound)

                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            // Post /endpoint
            post {
                try {
                    val departamentoReceive = call.receive<Departamento>()
                    val departamentoSave = departamentoRepository.save(departamentoReceive)
                    call.respond(
                        HttpStatusCode.Created, departamentoRepository.findById(departamentoSave.id).toString()
                    )
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }
        }
    }
}