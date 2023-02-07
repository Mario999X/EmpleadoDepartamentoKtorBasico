package com.example.plugins

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.koin.ksp.generated.defaultModule

// Bug visual, en realidad funciona una vez se ejecuta la aplicacion
fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()

        defaultModule()
    }
}