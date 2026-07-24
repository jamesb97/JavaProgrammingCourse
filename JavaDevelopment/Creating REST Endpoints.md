# Creating REST Endpoints Lab

## Overview

This lab demonstrates how to build structures service and controller layers in a Spring Boot application for a Clinic Management System. Will implement RESTful APIs using controllers, structure business logic within service classes, and pass data efficiently using Data Transfer Objects (DTOs). The service and controller layer will enable communication between the client-side frontend and the backend models and repositories created in the earlier labs.

Will also handle request validation, error handling, and service delegation to ensure modular and maintainable code.

## Learning Objectives

By the end of this lab, will be able to:

- Create and annotate controller classes to handle HTTP requests
- Implement service classes that encapsulate business logic
- Use DTOs to transfer data between layers cleanly and securely
- Apply validation annotations to incoming data through DTOs
- Handle exceptions gracefully with global or controller-level handlers
- Map between entities and DTOs for input or output transformation

## Key Terms

Before starting with the lab, let's learn about some important terms and concepts.

### Key Terms and Concepts

- **Controller**: A Spring Component that handles incoming HTTP requests and maps them to service methods
- **@RestController**: Annotation to define a controller where every methods returns a domain object instead of a view
- **@RequestMapping/@GetMapping/@PostMapping**: Annotations to define HTTP route handlers
- **Service Layer**: The part of the application that contains business logic and interacts with the repository layer
- **@Service**: Annotation to mark a class as a service provider in Spring
- **DTO(Data Transfer Object)**: A plain Java object used to transfer data between the frontend and backend
- **@Valid**: Annotation used to trigger validation on incoming DTOs.
- **@RequestBody/@PathVariable/@RequestParam**: Used to bind web request data to method parameters
- **Exception Handling**: Managing and responding to errors gracefully in the API.

### Creating DTO's

In this section, will create Data Transfer Object (DTO) classes to simplify and structure the data exchanged between the backend and frontend.

### Appointment DTO

Will now create a DTO to represent appointment data. This class helps decouple frontend requirements from the internal database structure.

1. Open up the `AppointmentDTO.java` file.

2. Create a **DTO class** that represents appointment data to be used in communication between the backend services and frontend clients.

3. Add the following fields:

- `id`: `Long` - Unique identifier for the appointment
- `doctorId`: `Long` - ID of the doctor assigned to the appointment
- `doctorName`: `String` - Full name of the doctor
- `patientId`: `Long` - ID of the patient
- `patientName`: `String` - Full name of the patient
- `patientEmail`: `String` - Email address of the patient
- `patientPhone`: `String` - Contact number of the patient
- `patientAddress`: `String` - Residential address of the patient
- `appointmentTime`: `LocalDateTime` - Full date and time of the appointment
- `status`: `int` - Appointment status (e.g., scheduled, completed)
- `appointmentDate`: `LocalDate` - Extracted date from `appointmentTime`
- `appointmentTimeOnly`: `LocalTime` - Extracted time from `appointmentTime`
- `endTime`: `LocalDateTime` - Calculated as `appointmentTime + 1 hour`

4. Add a constructor that initializes all core fields and automatically computes the following:

- `appointmentDate` using `appointmentTime.toLocalDate()`
- `appointmentTimeOnly using appointmentTime.toLocalTime()`
- `endTime` using `appointmentTime.plusHours(1)`

5. Add standard **getter methods** for each field to allow serialization of the DTO in API responses.

Hint:

- This DTO class should not contain persistence annotations like `@Entity` or `@Id`.
- It is meant to **simplify** and **format** data transferred to/from the frontend, decoupling it form internal database models.

## Login DTO
