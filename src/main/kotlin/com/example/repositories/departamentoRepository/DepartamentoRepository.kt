package com.example.repositories.departamentoRepository

import com.example.db.getDepartamentos
import com.example.models.Departamento
import com.example.repositories.CrudRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class DepartamentoRepository : CrudRepository<Departamento, Int> {

    // Fuente de datos
    private val departamentos : MutableMap<Int, Departamento> = mutableMapOf()

    init {
        println("Iniciando departamentos")

        getDepartamentos().forEach {
            departamentos[it.id] = it
        }
    }

    override suspend fun findAll(): Flow<Departamento> {
        println("Mostrando flujo de departamentos")

        return departamentos.values.toList().asFlow()
    }

    override suspend fun delete(id: Int): Departamento? = withContext(Dispatchers.IO) {
        println("Eliminando departamento: $id")

        return@withContext departamentos.remove(id)
    }

    override suspend fun update(id: Int, entity: Departamento): Departamento = withContext(Dispatchers.IO) {
        println("Actualizando departamento: ${entity.id}")

        departamentos[id] = entity
        return@withContext entity
    }

    override suspend fun save(entity: Departamento): Departamento = withContext(Dispatchers.IO) {
        println("Guardando departamento: $entity")

        departamentos[entity.id] = entity
        return@withContext entity
    }

    override suspend fun findById(id: Int): Departamento? = withContext(Dispatchers.IO) {
        println("Mostrando departamento con id: $id")

        //Buscamos
        return@withContext departamentos[id]
    }


}