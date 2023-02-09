package com.example

import io.ktor.server.application.*
import com.example.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    // Koin
    configureKoin()

    // Principales
    configureSerialization()
    //configureSecurity()
    configureRouting()
}

/* -- EXPLICACION --

El proyecto es generado en la siguiente pagina: https://start.ktor.io/#/settings?name=ktor-sample&website=example.com&artifact=com.example.ktor-sample&kotlinVersion=1.8.10&ktorVersion=2.2.3&buildSystem=GRADLE_KTS&engine=NETTY&configurationIn=CODE&addSampleCode=true&plugins=

    Ajustes de proyecto a tener en cuenta:
        - Engine = Netty
        - Configuration in Hocon

    Vamos a seleccionar los siguientes plugins:
        - kotlinx.serialization
        - Authentication JWT [no se aplica en este proyecto]
        - Routing
            * Routing se instalara con uno de los plugins anteriores, junto a otros que ni menciono, los importantes son
            los dos primeros

    1. Comentar en Application la fun de seguridad
    2. Configurar la serializacion -> plugins -> Serialization
    3. Configurar ktor, en application.conf
    4. Configurar rutas
        - Yo recomendaria comenzar con unas rutas Test sin usar objetos ni repositorios

        - Para una mejor organizacion del proyecto, generaremos un nuevo paquete "routes", donde realizaremos las rutas

            - En routing llamaremos a los metodos de las distintas clases que crearemos

            - Usando TestRoutes como base:
                - Crear una constante que se refiera al end point que sera usado en ese caso
                - Generar una funcion que extienda de ktor.server.application
                    - Tod0 es muy mecanico a partir de aqui, recomiendo ver la propia clase.

            - Ejecutar y usar thunderClient [VSC] o bien Postman

   4.5. Para el resto de elementos, se seguiran los pasos normales:
        - Generacion de modelos
        - Generacion de repositorios
        - Generacion de servicios
        - Generacion de rutas

  - En mi caso, y por ser una primera toma de contacto, no estoy usando ninguna base de datos, si no mapas
  insertados directamente en los repositorios, asi que alguna cosa no termina de funcionar bien, pero lo basico si funciona.

  - Lo dicho, en este caso no aplicare seguridad ni jwt, pero para futuros proyectos sera importante

  - El uso de Koin es un tema, recomiendo mirar el gradle y el que como esta usado en plugins + el uso
   de componentes generados en el build cuando se compila el programa [debajo de depencencies] y repositorios/servicios
 */
