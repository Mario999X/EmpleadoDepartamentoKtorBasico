package com.example.db

import com.example.models.Departamento
import com.example.models.Empleado

fun getDepartamentos() = listOf(
    Departamento(1, "Interfaces"),
    Departamento(2, "Administracion"),
    Departamento(3, "PSP")
)

fun getEmpleados() = listOf(
    Empleado(1, "Mario", getDepartamentos()[0]),
    Empleado(2, "Alysys", getDepartamentos()[1]),
    Empleado(3, "Vincent", getDepartamentos()[1]),
    Empleado(4, "Kratos"),
)
