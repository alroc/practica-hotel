# Reserva de hotel

API REST que permite gestionar la reserva de habitaciones en diferentes hoteles. La aplicación utiliza una de base de datos Postgres, por lo que, debes  actualizar
el fichero application.properties. En resources se encuentran los archivos SQL que debes ejecutar en tu gestor de base de datos para generar la base de datos.

## Guia de uso
### Hotel
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

### Disponibilidad

#### Abrir disponibilidad
POST -> http://localhost:8080/availabilities

Ejemplo JSON:

    {
        "dateFrom": "2023-08-10",
        "dateTo": "2023-08-13",
        "idHotel": 1,
        "rooms": 2
    }

### Reservas
#### Reserva de habitación
POST -> http://localhost:8080/bookings

Ejemplo JSON:

    {
        "idHotel": 1,
        "dateFrom": "2023-06-06",
        "dateTo": "2023-06-08",
        "email": "carlos@gmail.com"
    }

##### Consulta de reservas
GET -> http://localhost:8080/bookings

Se deben enviar el id del hotal, y las fechas de inicio y fin como parametros.

Ejemplo URL completa: 

http://localhost:8080/bookings?idHotel=1&dateFrom=2023-09-20&dateTo=2023-09-22

#### Obtener reserva
GET -> http://localhost:8080/bookings/{id}

#### Cancelar reserva
DELETE -> http://localhost:8080/bookings/{id}
