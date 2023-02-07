package com.example.services.empleado

import com.example.models.Empleado
import com.example.repositories.empleadoRepository.EmpleadoRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class EmpleadoServiceImpl(
    @Named("EmpleadoRepository")
    private val repository: EmpleadoRepository,
) : EmpleadoService {

    override suspend fun findAll(): Flow<Empleado> {
        return repository.findAll()
    }

    override suspend fun findById(id: Int): Empleado {
        return repository.findById(id) ?: throw Exception("No existe ese empleado")
    }

    override suspend fun save(entity: Empleado): Empleado {
        return repository.save(entity)
    }

    override suspend fun update(id: Int, entity: Empleado): Empleado {
        val existe = repository.findById(id)

        existe?.let {
            return repository.update(id, entity)
        } ?: throw Exception("No se encontro ese empleado")
    }

    override suspend fun delete(id: Int): Empleado {
        val existe = repository.findById(id)

        existe?.let {
            return repository.delete(id)!!
        } ?: throw Exception("No se encontro ese empleado")
    }
}