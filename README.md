# Parcial Back End - Detección de Mutantes

- Alumno: Juan Cruz Yanardi Ferrer

- Legajo: 50206

- Curso: 3K09

- Materia: Desarrollo de Software

## Descripción

Este proyecto es una aplicación Spring Boot que permite detectar si una secuencia de ADN pertenece a un mutante o a un humano. La aplicación también proporciona estadísticas sobre las secuencias de ADN analizadas.

## Herramientas Utilizadas

- Spring Boot: Framework para el desarrollo de aplicaciones Java.

- H2 Database: Base de datos en memoria para el almacenamiento de datos.

- JUnit 5: Framework para pruebas unitarias.

- JaCoCo: Herramienta para medir la cobertura de código.

- Render: Plataforma para el despliegue de aplicaciones.

- JMeter: herramienta para probar el rendimiento y la carga de aplicaciones.

## Introducción del Problema

El objetivo de este proyecto es determinar si una secuencia de ADN dada pertenece a un mutante o a un humano. Una secuencia de ADN se considera mutante si contiene al menos dos secuencias repetitivas distintas de cuatro letras iguales (por ejemplo, "AAAA", "TTTT", "CCCC", "GGGG") en cualquier dirección (horizontal, vertical, diagonal).

## Funcionamiento

### Entrada

El algoritmo recibe una matriz de cadenas de ADN, donde cada cadena representa una fila de la matriz.

### Recorrido de la Matriz y Resultado

El método isMutant verifica si una secuencia de ADN pertenece a un mutante. Primero, genera todas las posibles combinaciones de cuatro letras en cada fila, columna y diagonal (principal y secundaria) de la secuencia. Luego, utiliza un conteo en paralelo para identificar patrones mutantes específicos ("AAAA", "CCCC", "GGGG", "TTTT"). Finalmente, devuelve true si detecta al menos dos tipos de patrones mutantes diferentes, indicando que el ADN es mutante.

## Instrucciones de Ejecución en Entorno Local

1. Descargar Repositorio en GitHub

2. Construir el Proyecto
   
"./gradlew build"

4. Ejecutar la Aplicación
   
"./gradlew bootRun"

6. Acceder a la API localmente desde PostMan:
  Importar las requests a Postman que se encuentran en el archivo Mutante Local.postman_collection, las cuales estarán subidas en el campus, en la parte de la entrega
del integrador. En el caso del GET obtenerEstadisticas, con presionar el send ya será suficiente.
Pero, en el caso del POST analizarAdn deberá colocar en el body un JSON de la siguiente forma:

{
    "dna":["AAAA", "CCCC", "AAAA", "AAAA"]
}

Evidentemente las cadenas de caracteres pueden ser del tamaño que usted quiera, y la cantidad de cadenas también las puede elegir
usted, sin embargo, tenga en cuenta que si la cantidad de caracteres de cada una de las cadenas no es igual
a la cantidad de candenas (es decir la matriz no es cuadrada), y si los caracteres no son o "A" o "C" o
"G" o "T" la API le devolverá un Bad Request.

  - URL Base: http://localhost:8080 

  - Endpoint /mutant: http://localhost:8080/parcial/backend/adn/mutant

  - Endpoint /stats: http://localhost:8080/parcial/backend/estadisticas/stats

5. Ejecutar Pruebas
"./gradlew test"

6. Ver Reporte de Cobertura de Código
"./gradlew jacocoTestReport" El reporte de cobertura estará disponible en: build/reports/jacoco/test/html/index.html Las URLs de la API son:

URL Base: https://parcial-backend-yanardi.onrender.com/)
Endpoint /mutant: https://garcianieto-parical-back-3k9.onrender.com/mutant
Endpoint /stats: https://garcianieto-parical-back-3k9.onrender.com/stats
