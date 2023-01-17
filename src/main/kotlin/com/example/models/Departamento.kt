package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Departamento(
    val id: Int,
    val nombre: String
)
