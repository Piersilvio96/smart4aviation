# Smart4Aviation

This Spring application provides two main functionalities:

### Flight Information by Flight Number and Date:

- Given a Flight Number and Date, it responds with the following information:
- Cargo Weight for the requested flight.
- Baggage Weight for the requested flight.
- Total Weight for the requested flight.


### Airport Information by IATA Airport Code and Date:

Given an IATA Airport Code and Date, it responds with the following information:
- Number of flights departing from this airport.
- Number of flights arriving at this airport.
- Total number (pieces) of baggage arriving at this airport.
- Total number (pieces) of baggage departing from this airport.

## Getting Started
These instructions will help you set up and run the Spring Flight Information Service on your local machine.

### Prerequisites
Before running the application, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 11 or higher
- Gradle (for building and running the project)
- Your favorite code editor or Integrated Development Environment (IDE)

### Installation and Setup

- Clone this repository to your local machine:

    ``git clone https://github.com/Piersilvio96/smart4aviation.git ``

- Navigate to the project directory: 

    ``cd smart4aviation``

- Build the application using Gradle:

    ``./gradlew build``

# Running the Application

1) Once the build is successful, you can run the application using the following command:
    
    ``./gradlew bootRun``

    The Spring Boot application will start, and you will see log output indicating that the server is running.
2) Open a web browser and go to http://localhost:8008/s4a/swagger-ui/ to use it or to see the Swagger


# Data Generation
For generating test data, you can use https://json-generator.com.
Here are sample data structures you can use:

## Flights:
Usable in POST http://localhost:8008/s4a/api/v1/flights/import
```
[
'{{repeat(5)}}',
{
"flightId": '{{index()}}',
"flightNumber": '{{integer(1000, 9999)}}',
"departureAirportIATACode": '{{random("SEA","YYZ","YYT","ANC","LAX")}}',
"arrivalAirportIATACode": '{{random("MIT","LEW","GDN","KRK","PPX")}}',
"departureDate": '{{date(new Date(2014, 0, 1), new Date(), "YYYY-MM-ddTHH:mm:ss Z")}}'
}
]

```

## Cargo
Usable in POST http://localhost:8008/s4a/api/v1/flights/cargo/imports

```
[
  '{{repeat(5)}}',
  {
    "flightId": '{{index()}}',
    "baggage": [
      '{{repeat(3,8)}}',
      {
        "id": '{{index()}}',
        "weight": '{{integer(1, 999)}}',
        "weightUnit": '{{random("kg","lb")}}',
        "pieces": '{{integer(1, 999)}}'
      }
    ],
    "cargo": [
      '{{repeat(3,5)}}',
      {
        "id": '{{index()}}',
        "weight": '{{integer(1, 999)}}',
        "weightUnit": '{{random("kg","lb")}}',
        "pieces": '{{integer(1, 999)}}'
      }
    ]
  }
]

```

