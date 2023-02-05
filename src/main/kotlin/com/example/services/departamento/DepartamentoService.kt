package com.example.services.departamento

import com.example.models.Departamento
import kotlinx.coroutines.flow.Flow

interface DepartamentoService {
    suspend fun findAll(): Flow<Departamento>
    suspend fun findById(id: Int): Departamento
    suspend fun save(entity: Departamento): Departamento
    suspend fun update(id: Int, entity: Departamento): Departamento
    suspend fun delete(id: Int): Departamento
}