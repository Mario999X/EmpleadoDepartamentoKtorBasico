package com.example.services.empleado

import com.example.models.Empleado
import kotlinx.coroutines.flow.Flow

interface EmpleadoService {
    suspend fun findAll(): Flow<Empleado>
    suspend fun findById(id: Int): Empleado
    suspend fun save(entity: Empleado): Empleado
    suspend fun update(id: Int, entity: Empleado): Empleado
    suspend fun delete(id: Int): Empleado
}