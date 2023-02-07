package com.example.routes

import com.example.models.Empleado
import com.example.services.empleado.EmpleadoServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

private const val END_POINT = "api/empleados"

fun Application.empleadosRoutes() {

    val empleadoService: EmpleadoServiceImpl by inject()

    routing {
        route("/$END_POINT") {
            // Get All
            get {
                val result = mutableListOf<Empleado>()

                // Procesamos el flujo
                empleadoService.findAll().collect {
                    result.add(it)
                }

                println(Json.encodeToString<List<Empleado>>(result))

                call.respond(HttpStatusCode.OK, result)
            }

            // Get By Id
            get("{id}") {
                try {
                    val id = call.parameters["id"]!!.toInt()
                    val empleado = empleadoService.findById(id)

                    call.respond(HttpStatusCode.OK, empleado.toString())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            // Post /endpoint
            post {
                try {
                    val empleadoReceive = call.receive<Empleado>()
                    val empleadoSave = empleadoService.save(empleadoReceive)
                    call.respond(HttpStatusCode.Created, empleadoService.findById(empleadoSave.id).toString())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            put("{id}") {
                try {
                    val id = call.parameters["id"]?.toInt()!!
                    val request = call.receive<Empleado>()
                    val empleado = empleadoService.update(id, request)
                    call.respond(HttpStatusCode.OK, empleado.toString())
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message.toString())
                }
            }

            delete("{id}") {
                try {
                    val id = call.parameters["id"]?.toInt()!!

                    empleadoService.delete(id)
                    call.respond(HttpStatusCode.NoContent)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.NotFound, e.message.toString())
                }
            }
        }
    }
}