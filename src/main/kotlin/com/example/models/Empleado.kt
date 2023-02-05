package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Empleado(
    val id: Int,
    val name: String,
) {
    override fun toString(): String {
        return "Empleado(id=$id, name='$name')"
    }
}