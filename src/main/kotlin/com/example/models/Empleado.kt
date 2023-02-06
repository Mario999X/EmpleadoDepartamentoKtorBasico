package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Empleado(
    val id: Int,
    val name: String,
    val departamento: Departamento? = null
) {
    override fun toString(): String {
        return "Empleado(id=$id, name='$name', departamento=$departamento)"
    }
}