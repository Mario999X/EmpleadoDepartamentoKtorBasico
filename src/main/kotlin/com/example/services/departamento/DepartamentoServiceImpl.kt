package com.example.services.departamento

import com.example.models.Departamento
import com.example.repositories.departamentoRepository.DepartamentoRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class DepartamentoServiceImpl(
    private val repository: DepartamentoRepository
) : DepartamentoService {

    override suspend fun findAll(): Flow<Departamento> {
        return repository.findAll()
    }

    override suspend fun findById(id: Int): Departamento {
        return repository.findById(id) ?: throw Exception("No existe ese departamento")
    }

    override suspend fun save(entity: Departamento): Departamento {
        return repository.save(entity)
    }

    override suspend fun update(id: Int, entity: Departamento): Departamento {
        val existe = repository.findById(id)

        existe?.let {
            return repository.update(id, entity)
        } ?: throw Exception("No se encontro ese departamento")
    }

    override suspend fun delete(id: Int): Departamento {
        val existe = repository.findById(id)

        existe?.let {
            if (existe.listadoEmpleados.isEmpty()) {
                return repository.delete(id)!!
            } else throw Exception("No pudo eliminarse, al menos existe un empleado")
        } ?: throw Exception("No se encontro ese departamento")
    }
}