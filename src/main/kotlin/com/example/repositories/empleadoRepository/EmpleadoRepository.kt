package com.example.repositories.empleadoRepository

import com.example.db.getEmpleados
import com.example.models.Empleado
import com.example.repositories.CrudRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.koin.core.annotation.Single

@Single
class EmpleadoRepository : CrudRepository<Empleado, Int> {

    // Fuente de datos
    private val empleados: MutableMap<Int, Empleado> = mutableMapOf()

    init {
        println("Iniciando empleados")

        getEmpleados().forEach {
            empleados[it.id] = it
        }
    }

    override suspend fun findAll(): Flow<Empleado> {
        println("Mostrando flujo de departamentos")

        return empleados.values.toList().asFlow()
    }

    override suspend fun delete(id: Int): Empleado? {
        println("Eliminando empleado: $id")

        return empleados.remove(id)
    }

    override suspend fun update(id: Int, entity: Empleado): Empleado {
        println("Actualizando empleado: ${entity.id}")

        empleados[id] = entity

        return entity
    }

    override suspend fun save(entity: Empleado): Empleado {
        println("Guardando empleado: $entity")

        empleados[entity.id] = entity
        return entity
    }

    override suspend fun findById(id: Int): Empleado? {
        println("Mostrando empleado con id: $id")

        return empleados[id]
    }
}