# Reserva de hotel

API REST que permite gestionar la reserva de habitaciones en diferentes hoteles. La aplicación utiliza una de base de datos Postgres, por lo que, debes realizar los siguientes pasos:

- Generar la base de datos, para ello, debes ejecutar en tu gestor de base de datos los archivos SQL que se encuentran en la carpeta resources.
- Actualizar el archivo application.properties.

## Guia de uso
### Hotel
**Crear nuevo hotel** &ensp; POST -> http://localhost:8080/hotels

Se debe enviar un JSON con los datos del hotel. Ejemplo JSON:

    {
        "name": "El pueblo",
        "category": 5
    }

**Actualizar hotel** &ensp; PUT -> http://localhost:8080/hotels/{id}

Se debe enviar un JSON con los datos que se quieren actualizar. Ejemplo JSON:

    {
        "name": "El pueblo actualizado",
        "category": 4
    }

**Consultar hotel** &ensp; GET -> http://localhost:8080/hotels/{id}

**Consulta de hoteles** &ensp; GET -> http://localhost:8080/hotels

**Consultar hoteles con disponibilidad** &ensp; GET -> http://localhost:8080/hotels/availabilities

Se deben enviar las fechas de entrada y salida, el nombre (opcional) y la categoría (opcional) como parametros, Ejemplo URL completa: 

http://localhost:8080/hotels/availabilities?dateFrom=2023-06-06&dateTo=2023-06-10&name=Barcelo&category=4

### Disponibilidad

**Abrir disponibilidad** &ensp; POST -> http://localhost:8080/availabilities

Se debe enviar un JSON con el rango de fechas, el hotel y el número de habitaciones. Ejemplo JSON:

    {
        "dateFrom": "2023-08-10",
        "dateTo": "2023-08-13",
        "idHotel": 1,
        "rooms": 2
    }

### Reservas
**Reserva de habitación** &ensp; POST -> http://localhost:8080/bookings

Se debe enviar un JSON con el hotel, las fechas de entrada y salida, y el email. Ejemplo JSON:

    {
        "idHotel": 1,
        "dateFrom": "2023-06-06",
        "dateTo": "2023-06-08",
        "email": "carlos@gmail.com"
    }

**Consulta de reservas** &ensp; GET -> http://localhost:8080/bookings

Se debe enviar el id del hotal, y las fechas de inicio y fin como parametros. Ejemplo URL completa: 

http://localhost:8080/bookings?idHotel=1&startDate=2023-06-06&endDate=2023-06-10

**Obtener reserva** &ensp; GET -> http://localhost:8080/bookings/{id}

**Cancelar reserva** &ensp; DELETE -> http://localhost:8080/bookings/{id}
