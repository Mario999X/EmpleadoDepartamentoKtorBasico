package com.example.db

import com.example.models.Departamento
import com.example.models.Empleado

fun getDepartamentos() = listOf(
    Departamento(1, "Interfaces", listOf(getEmpleados()[0]) as MutableList<Empleado>),
    Departamento(2, "Administracion"),
    Departamento(3, "PSP", listOf(getEmpleados()[1], getEmpleados()[2]) as MutableList<Empleado>)
)

fun getEmpleados() = listOf(
    Empleado(1, "Mario"),
    Empleado(2, "Alysys"),
    Empleado(3, "Vincent"),
    Empleado(4, "Buch"),
)
