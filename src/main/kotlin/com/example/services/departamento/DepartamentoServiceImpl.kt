package com.example.services.departamento

import com.example.models.Departamento
import com.example.repositories.departamentoRepository.DepartamentoRepository
import com.example.repositories.empleadoRepository.EmpleadoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class DepartamentoServiceImpl(
    @Named("DepartamentoRepository")
    private val repository: DepartamentoRepository,
    @Named("EmpleadoRepository")
    private val empleadoRepository: EmpleadoRepository
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

        System.err.println(existe)
        existe?.let {
            val empleados = empleadoRepository.findAll().toList().filter { it.departamento?.id == existe.id }
            val count = empleados.size

            System.err.println(empleados.size)
            if (count == 0) {
                return repository.delete(id)!!
            } else throw Exception("No fue posible eliminar el departamento | $count empleados")
        } ?: throw Exception("No se encontro ese departamento")
    }
}