# Reserva de hotel

API REST que permite gestionar la reserva de habitaciones en diferentes hoteles. La aplicación utiliza una de base de datos Postgres, por lo que, debes  actualizar
el fichero application.properties. En resources se encuentran los archivos SQL que debes ejecutar en tu gestor de base de datos para generar la base de datos.

## Guia de uso
#### Crear nuevo hotel
POST -> http://localhost:8080/hotels

Ejemplo JSON:

    {
        "name": "El pueblo",
        "category": 5
    }

#### Actualizar hotel
PUT -> http://localhost:8080/hotels/{id}

Ejemplo JSON:

    {
        "name": "El pueblo actualizado",
        "category": 4
    }

#### Consultar hotel
GET -> http://localhost:8080/hotels/{id}

#### Consulta de hoteles
GET -> http://localhost:8080/hotels
