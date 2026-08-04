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

In this section, will create a DTO to handle user login requests. This class will encapsulate user credentials submitted from the frontend.

Let's define a simple DTO class to receive the user's **identifier** (which can be an email or username depending on the user type) and password during login operations.

1. Open up the `Login.java` file

2. Create a **DTO class** to represent login request data. This class will be used to receive login credentials from the client.

3. Add the following fields:

- `identifier`: `String` - The unique identifier of the user attempting to log in (`email` for Doctor/Patient, `username` for Admin)
- `password`: `String` - The password provided by the user

4. Add standard **getter and setter methods** for both fields to enable deserialization of the login request body.

Hint:

- This class is typically used in `@RequestBody` parameters inside controller methods.
- Do **not** add any persistence annotations (`@Entity`, `@Id`, and so on).
- This DTO is used only for authentication input and is not stored in the database.

## Creating Repositories

Will now build the AdminRepository to enable admin-related databases queries using Spring Data JPA.

### Admin Repository

This repository will support basic CRUD operations for admin users and allow to find admins by username.

1. Open up the `AdminRepository.java` file.

2. Create a repository for the `Admin` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

3. Add the following method:

**findByUsername**: Find an admin by their username.

- Return type: `Admin`
- Parameter: `String username`

Hint:

- Extend `JpaRepository<Admin, Long>` to inherit basic CRUD functionality.
- Declare custom query methods using Spring Data conventions, such as `findByUsername`.

## Appointment Repository

Next, will define custom query methods in AppointmentRepository to support advanced appointment search and filtering logic.

1. Open the `AppointmentRepository.java` file.

2. Create a repository for the `Appointment` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

3. Add the following methods:

- **findByDoctorIdAndAppointmentTimeBetween**: Retrieve appointments for a doctor within a given time range.

- Return type: `List<Appointment>`
- Parameters: `Long doctorId`, `LocalDateTime start`, `LocalDateTime end`
- Query: Use `@Query` with `LEFT JOIN FETCH` to include doctor and availability info

- **findByDoctorIdAndPatient_NameContainingIgnoreCaseAndAppointmentTimeBetween**: Filter appointments by doctor ID, partial patient name (case-insensitive), and time range.

- **deleteAllByDoctorId**: Delete all appointments related to a specific doctor.

- Return type: `void`
- Parameter: `Long doctorId`
- Annotations: Use `@Modifying` and `@Transactional` to enable delete operation

- **findByPatientId**: Find all appointments for a specific patient.

- Return type: `List<Appointment></Appointment>`
- Parameters: `Long patientId`, `int status`

- **filterByDoctorNameAndPatientId**: Search appointments by partial doctor name and patient ID.

- Return type: `List<Appointment>`
- Parameters: `String doctorName`, `Long patientId`
- Query: Use `@Query` with `LOWER` and `CONCAT` for case-insensitive partial matching

- **filterByDoctorNameAndPatientIdAndStatus**: Filter appointments by doctor name, patient ID, and status.

- Return type: `List<Appointment>`
- Parameters: `String doctorName`, `Long patientId`, `int status`
- Query: Use `@Query` with `LOWER`, `CONCAT`, and additional filtering on `status`

Hint:

- Extend `JpaRepository<Appointment, Long>` for basic CRUD functionality.
- Use `@Query` for custom joins and filtering logic.
- Use `@Modifying` and `@Transactional` for delete operations.
- Leverage method naming conventions (e.g., `findBy`, `filterBy`) for additional queries.
- Use `LOWER`, `CONCAT`, and `%` for partial, case-insensitive text matches.

## Doctor Repository

In this step, will build the DoctorRepository interface to query doctors by name, email, and specialty using both conventions and custom queries.

1. Open the `DoctorRepository.java` file.

2. Create a repository for the `Doctor` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

3. Add the following methods:

- **findByEmail**: Find a doctor by their email address.

- Return type: `Doctor`
- Parameter: `String email`

- **findByNameLike**: Find doctors by partial name match.

- Return type: `List<Doctor></Doctor>`
- Parameter: `String name`
- Query: Use `@Query` with `LIKE` and `CONCAT` for flexible pattern matching

- **findByNameContainingIgnoreCaseAndSpecialtyIgnoreCase**: Filter doctors by partial name and exact specialty (case-insensitive).

- Return type: `List<Doctor>`
- Parameters: `String name`, `String specialty`
- Query: Use `@Query` with `LOWER`, `CONCAT`, and `LIKE` for case-insensitive matching

- **findBySpecialtyIgnoreCase**: Find doctors by specialty, ignoring case.
- Return type: `List<Doctor>`
- Parameter: `String specialty`

Hint:

- Extend `JpaRepository<Doctor, Long>` to inherit basic CRUD functionality.
- Use Spring Data naming conventions (e.g., `findBy`, `Containing`, `IgnoreCase`) for simple queries.
- Use `@Query` and `LIKE` with `LOWER` and `CONCAT` to support case-insensitive and partial matching.

## Patient Repository

Will now implement the repository interface to retrieve patients using their email or phone number for identification and validation.

1. Open up the `PatientRepository.java` file.

2. Create a repository for the `Patient` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

3. Add the following methods:

- **findByEmail**: Find a patient by their email address.

- Return type: `Patient`
- Parameter: `String email`

- **findByEmailOrPhone**: Find a patient using either email or phone number.

- Return type: `Patient`
- Parameters: `String email`, `String phone`

Hint:

- Extend `JpaRepository<Patient, Long>` to inherit basic CRUD functionality.
- Use Spring Data naming conventions to define compound query methods such as `findByEmailOrPhone`.

## Prescription Repository

Let's define the repository for MongoDB-based prescription data. This interface will allow to fetch prescriptions by appointment ID.

1. Open up the `PrescriptionRepository.java` file.

2. Create a repository for the `Prescription` model by extending `MongoRepository`. This will allow for basic CRUD operations on MongoDB without implementing methods manually.

3. Add the following method:

- **findByAppointmentId**: Find prescriptions associated with a specific appointment.

- Return type: `List<Prescription>`
- Parameter: `Long appointmentId`

Hint:

- Extend `MongoRepository<Prescription, String>` to enable MongoDB CRUD functionality.
- Use method naming conventions like `findByAppointmentId` for query generation in MongoDB.

## Creating Services

In this section, will implement the service layer that handles core business logic. Start of with appointment-related services.

### AppointmentService

Let's define the AppointmentService class to handle appointment creation, updating, cancellation, and filtering.

1. Open the `AppointmentService.java` file.

2. Create a service class to handle operations related to appointments, including booking, updating, canceling, and retrieving appointments.

- Hint: Add `@Service` annotation above the class definition.

3. Declare necessary repositories to be used as private:

- `AppointmentRepository` for accessing appointment data
- `PatientRepository` for accessing patient data
- `DoctorRepository` for accessing doctor data
- `TokenService` for extracting tokens from the request

4. Add the following methods:

- **bookAppointment**: This method books a new appointment.

- Parameters: `Appointment appointment` (The appointment object you want to book)
- Return Type: `int` (Returns `1` if successful, `0` if there's an error)
- Hint: Use `appointmentRepository.save(appointment)` to save the appointment.

- **updateAppointment**: This method updates an existing appointment.

- Parameters: `Appointment appointment` (The appointment object you want to update)
- Return Type: `ResponseEntity<Map<String, String>>` (Returns a response message indicating success or failure)
- Hint: Use `appointmentRepository.findById(appointment.getId())` to check if the appointment exists before updating and use `service.validateAppointment()` to check if the update id valid.

- **cancelAppointment**: This method cancels an existing appointment.

- Parameters: `long id` (The Id of the appointment to cancel), `String token` (The authorization token)
- Return Type: `ResponseEntity<Map<String, String>>` (Returns a response message indicating success or failure)
- Hint: Use `appointmentRepository.findById(id)` to find the appointment and `appointmentRepository.delete(appointment)` to delete it.

- **getAppointment**: This method retrieves a list of appointments for a specific doctor on a specific date.

- Parameters: `String pname` (Patient name to filter by), `LocalDate date` (The date for appointments), `String token` (The authorization token)
- Return Type: `Map<String, Object>` (Returns a map containing the list of appointments)
- Hint: Use `appointmentRepository.findByDoctorIdAndAppointmentTimeBetween()` to fetch appointments for the given doctor and date. Filter by patient name if provided.

### Additional Hints

1. **bookAppointment Method**

- Use this method to handle the creation of new appointments. If any errors occur during the booking process, it will return `0` to indicate failure.

2. **updateAppointment Method**

- This method is used to modify an existing appointment, making sure to validate the data before saving it. It handles different error responses based on the type of issue (for example, invalid doctor ID, appointment already booked, and so on).

3. **cancelAppointment Method**

- Use this method to cancel appointments. It ensures that the patient attempting to cancel the appointment is the one who originally booked it.

4. **getAppointment Method**

- This method retrieves the list of appointments for a doctor on a particular date. It filters by patient name if provided, making it easy to search for specific appointments.

## Doctor Service

Will now implement logic for managing doctor entities, including saving, updating, searching, and validating doctor information.

1. Open up the `DoctorService.java` file.

2. Create a service class to manage operations related to doctors, including retrieving availability, saving, updating, deleting, and validating doctors.

Hint: Add `@Service` annotation above the class definition.

3. Declare necessary repositories to be used as private:

- `DoctorRepository` for accessing doctor data
- `AppointmentRepository` for accessing appointment data
- `TokenService` for generating and validating tokens

4. Add the following methods:

- **getDoctorAvailability** This method fetches the available slots for a specific doctor on a given date.
- Parameters: `Long doctorId` (The ID of the doctor), `LocalDate date` (The date for which availability is needed)
- Return Type: `List<String>` (A list of available time slots for the doctor on the specified date)
- Hint: Fetch appointments for the doctor on the specified date and filter out the booked slots from the available slots.

- **saveDoctor**: This method saves a new doctor to the database.

- Parameters: `Doctor doctor` (The doctor object you want to save)
- Return Type: `int` (Returns `1` for success, `-1` if the doctor already exists, `0` for internal errors)
- Hint: Check if the doctor already exists by email before saving it.

- **updateDoctor**: This method updates the details of an existing doctor.

- Parameters: `Doctor doctor` (The doctor object with updated details)
- Return Type: `int` (Returns `1` for success, `-1` if doctor not found, `0` for internal errors)
- Hint: Check if the doctor exists by ID before updating.

- **getDoctors**: This method retrieves a list of all doctors.

- Return Type: `List<Doctor>` (A list of all doctors)
- Hint: Use `doctorRepository.findAll()` to fetch all doctors.

- **deleteDoctor**: This method deletes a doctor by ID.

- Parameters: `long id` (The ID of the doctor to be deleted)
- Return Type: `int` (Returns `1` for success, `-1` if doctor not found, `0` for internal errors)
- Hint: Use `appointmentRepository.deleteAllByDoctorId()` to delete all associated appointments before deleting the doctor.

- **validateDoctor**: This method validates a doctor's login credentials.

- Parameters: `Login login` (The login object containing email and password)
- Return Type: `ResponseEntity<Map<String, String>>` (Returns a response with a token if valid, or an error message if not)
- Hint: Use `doctorRepository.findByEmail()` to find the doctor by email and verify the password.

- **findDoctorByName**: This method finds doctors by their name.

- Parameters: `String name` (Doctor's name), `String specialty` (Doctor's specialty), `String amOrPm` (Time of day: AM/PM)
- Return Type: `Map<String, Object>` (Returns a map with the filtered list of doctors)
- Hint: Use `doctorRepository.findByNameContainingIgnoreCaseAndSpecialtyIgnoreCase()` to filter by name and specialty, then filter the results by time.

- **filterDoctorByNameAndTime**: This method filters doctors by name and their availability during AM/PM.

- Parameters: `String name` (Doctor's name), `String amOrPm` (Time of day: AM/PM)
- Return Type: `Map<String, Object>` (Returns a map with the filtered list of doctors)
- Hint: First, filter by name, then filter the result by time.

- **filterDoctorByNameAndSpecialty**: This method filters doctos by name and specialty.

- Parameters: `String name` (Doctor's name), `String specialty` (Doctor's specialty)
- Return Type: `Map<String, Object>` (Returns a map with the filtered list of doctors)
- Hint: Use `doctorRepository.findByNameContainingIgnoreCaseAndSpecialtyIgnoreCase()` to filter by name and specialty.

- **filterDoctorByTimeAndSpecialty**: This method filters doctors by specialty and their availability during AM/PM.

- Parameters: `String specialty` (Doctor's specialty), `String amOrPm` (Time of day: AM/PM)
- Return Type: `Map<String, Object>` (Returns a map with the filtered list of doctors)
- Hint: Use `doctorRepository.findBySpecialtyIgnoreCase()` to filter by specialty, then filter the results by time.

- **filterDoctorBySpecility**: This method filters doctors by specialty.

- Parameters: `String specility` (Doctor's specialty)
- Return Type: `Map<String, Object>` (Returns a map with the filtered list of doctors)
- Hint: Use `doctorRepository.findBySpecialtyIgnoreCase()` to filter by specialty.

- **filterDoctorsByTime**: This method filters doctors by their availability during AM/PM.

- Parameters: `String amOrPm` (Time of day: AM/PM)
- Return Type: `Map<String, Object>` (Returns a map with the filtered list of doctors)
- Hint: Use `doctorRepository.findAll()` to fetch all doctors, then filter by available time.

- **filterDoctorByTime**: This private method filters a list of doctors by their available times (AM/PM).

- Parameters: `List<Doctor> doctors` (The list of doctors to filter), `String amOrPm` (Time of day: AM/PM)
- Return Type: `List<Doctor>` (Returns a filtered list of doctors)
- Hint: Filter doctors based on their available times, comparing the time slots with AM/PM.

### Additional Hints

1. **getDoctorAvailability Method**

- Useful to find available time slots for a specific doctor on a given date. Can return the available slots after filtering out the booked ones.

2. **saveDoctor Method**

- Ensures that no duplicate doctor entries exist by email before saving the doctor

3. **updateDoctor Method**

- Updates an existing doctor's details if found. If not found, it returns a conflict error

4. **filterDoctorsByNameSpecilityandTime Method**

- Combines filtering by name, specialty, and time (AM/PM) for searching doctors

## Patient Service

The `PatientService` class handles various operations related to patients, such as creating a patient, fetching their appointments, and filtering those appointments based on specific conditions (for example, past, future, by doctor).

Here is a detailed breakdown of the methods in the `PatientService` class:

Open the `PatientService.java` file.

1. \*\*createPatient(Patient patient)

- **Purpose**
- Saves a new patient to the database

- **Parameters**
- `Patient patient` - The patient object to be saved

- **Return Type**
- `int` - Returns `1` on success, and `0` on failure (for example, exception)

- **Explanation**
- This method saves the patient to the database and handles exceptions that may arise during the save process.

2. **getPatientAppointment(Long id, String token)**

- **Purpose**
- Retrieves a list of appointments for a specific patient

- **Parameters**
- `Long id` - The patient's ID
- `String token` - The JWT token containing the email

- **Return Type**
- `ResponseEntity<Map<String, Object>>` - Returns a response containing a list of appointments or an error message.

- **Explanation**
- The method checks if the provided patient ID matches the one decoded from the token (by email). If there's a mismatch, it returns an `Unauthorized` status.
- If the IDs match, it retrieves the patient's appointments and returns them as a list of `AppointmentDTO` objects.

3. **filterByCondition(String condition, Long id)**

- **Purpose**
- Filters appointments by condition (`past` or `future`) for a specific patient

- **Parameters**
- `String condition` - The condition to filter by (`past` or `future`)
- `Long id` - The patient's ID

- **Return Type**
- `ResponseEntity<Map<String, Object>>` - Returns the filtered appointments or an error message

- **Explanation**
- The method checks the `condition` value (`past` or `future`) and filters appointments accordingly. It uses the status (`1` for past and `0` for future) to determine the filtering criteria.

4. \*\*filterByDoctor(String name, Long patientId)

- **Purpose**
- Filters the patient's appointments by doctor's name

- **Parameters**

- `String name` - The name of the doctor
- `Long patientId` - The ID of the patient

- **Return Type**
- `ResponseEntity<Map<String, Object>>` - Returns the filtered appointments or an error message

- **Explanation**
- The method fetches appointments where the doctor's name matches the provided `name` and the patient ID matches the given `patientId`.

5. **filterByDoctorAndCondition(String condition, String name, long patientId)**

- **Purpose**
- Filters the patient's appointments by doctor's name and appointment condition (`past` or `future`)

- **Parameters**
- `String condition` - The condition to filter by (`past` or `future`)
- `String name` - The name of the doctor
- `long patientId` - The ID of the patient

- **Return Type**
- `ResponseEntity<Map<String, Object>>` - Returns the filtered appointments or an error message

- **Explanation**
- The method combines the filtering criteria of both the doctor's name and the condition (`past` or `future`).

6. **getPatientDetails(String token)**

- **Purpose**
- Fetches the patient's details based on the provided JWT token

- **Parameters**
- `String token` - The JWT token containing the email

- **Return Type**
- `ResponseEntity<Map<String, Object>>` - Returns the patient's details or an error message

- **Explanation**

- The method extracts the email from the token and retrieves the corresponding patient from the database. The patient details are then returned as part of the response.

### Helper Methods

- AppointmentDTO: The data transfer object (DTO) that represents an appointment. It is used to send appointment data in a simplified format, without including sensitive data.
- TokenService: Used to extract the email from the JWT token, which helps in ensuring the patient is authorized to access their information.

## Prescription Service

The `PrescriptionService` class handles the creation and retrieval of prescriptions. It provides two key functionalities: saving a new prescription and retrieving an existing prescription based on an appointment ID.

Here's a breakdown of the methods in this service:

Open up the `PrescriptionService.java` file.

1. **savePrescription(Prescription prescription)**

- **Purpose**
- Saves a prescription to the database

- **Parameters**
- `Prescription prescription` - The prescription object to be saved

- **Return Type**
- `ResponseEntity<Map<String, String>>` - Returns a response with a message indicating the result of the save operation

- **Explanation**
- The method attempts to save the prescription to the database using the `prescriptionRepository`. If successful, it returns a `201 Created` status with a message "Prescription saved". If there is an error, it returns a `500 Internal Server Error` with a generic error message.

2. **getPrescription(Long appointmentId)**

- **Purpose**
- Retrieves the prescription associated with a specific appointment ID

- **Parameters**
- `Long appointmentId` - The appointment ID whose associated prescription is to be retrieved

- **Return Type**
- `ResponseEntity<Map<String, Object>>` - Returns a response containing the prescription details or an error message

- **Explanation**
- The method attempts to fetch the prescription from the database using the `prescriptionRepository.findByAppointmentId(appointmentId)` method. If successful, it returns the prescription as part of the response with a `200 OK` status. If there is an error, it returns a `500 Internal Server Error` with an error message.

## Service Class

Here, will create a central service class that combines multiple functionalities: authentication, validation, and coordination across entities.

1. Open up the `Service.java` file.

2. Create a service class to handle authentication, doctor and patient management, and appointment validation.

Hint: Add `@Service` annotation above the class definition.

3. Declare the necessary services and repositories to be used as private.

Hint:

```java
private final TokenService tokenService;
private final AdminRepository adminRepository;
private final DoctorRepository doctorRepository;
private final PatientRepository patientRepository;
private final DoctorService doctorService;
private final PatientService patientService;
```

4. Add the following logic:

- **validateToken**: This method checks the validity of a token for a given user.

- **Parameters**

- `String token`: The token to be validated
- `String user`: The user to whom the token belongs

- **Return Type**

- `ResponseEntity<Map<String, String>>`: Returns an error message if the token is invalid or expired

- **Hint**: Use `tokenService.validateToken()` to check if the token is valid. Return an `Unauthorized` response if the token is invalid or expired.

- **validateAdmin** - This method validates the login credentials of an admin.

- **Parameters**

- `Admin recievedAdmin`: The admin credentials (username and password) to be validated

- **Return Type**

- `ResponseEntity<Map<String, String>>`: Returns a generated token if the admin is authenticated

- Hint: Use `adminRepository.findByUsername()` to check if the admin exists and compare the password. If valid, generate a token with `tokenService.generateToken()`.

- **filterDoctor**: This method filters doctors based on name, specialty, and available time.

- **Parameters**

- `String name`: The name of the doctor
- `String specialty`: The specialty of the doctor
- `String time`: The available time of the doctor

- **Return Type**

- `Map<String, Object>`: Returns a list of doctors that match the filtering criteria

- Hint: Use `doctorService` methods like `filterDoctorsByNameSpecilityandTime()` to perform filtering. Return doctors that match the provided criteria.

- **validateAppointment**: This method validates whether an appointment is available based on the doctor's schedule.

- **Parameters**

- `Appointment appointment`: The appointment to validate

- Return Type

- `int`:
- `1` if the appointment time is valid
- `0` if the time is unavailable
- `-1` if the doctor doesn't exist

- Hint: Use `doctorRepository.findById()` to find the doctor and `doctorService.getDoctorAvailability()` to check available time slots for that doctor.

- **validatePatient**: This method checks whether a patient exists based on their email or phone number.

- **Parameters**
- `Patient patient`: The patient to validate

- **Return Type**

- `boolean`:
- `true` if the patient does not exist
- `false` if the patient exists already

Hint: Use `patientRepository.findByEmailOrPhone()` to check if the patient exists. If the patient is found, return `false`.

- **validatePatientLogin**: This method validates a patient's login credentials (email and password).

- **Parameters**

- `Login login`: The login credentials of the patient (email and password)

- **Return Type**

- `ResponseEntity<Map<String, String>>`: Returns a generated token if the login is valid

- Hint: Used `patientRepository.findByEmail()` to check if the email exists. Compare the password, and if valid, generate a token with `tokenService.generateToken()`

- **filterPatient**: This method filters patient appointments based on certain criteria, such as condition and doctor name.

- **Parameters**

- `String condition`: The medical condition to filter appointments by
- `String name`: The doctor's name to filter appointments by
- `String token`: The authentication token to identify the patient

- **Return Type**

- `ResponseEntity<Map<String, Object>>`: Returns the filtered list of patient appointments based on their criteria

- Hint: Use `patientService` methods like `filterByCondition()`, `filterByDoctor()`, or `filterByDoctorAndCondition()` to apply the necessary filters based on the input.

### Additional Hints

1. **validateToken Method**

- This method ensures that the provided token is valid. It's essential for authenticating user requests.

2. **validateAdmin Method**

- This method is critical for admin authentication. Only valid admins will be granted access, and an authentication token will be returned for subsequent requests.

3. **filterDoctor Method**

- This method helps in filtering doctors based on different criteria. If none of the filters are provided, it defaults to returning all available doctors.

4. **validateAppointment Method**

- The method checks if an appointment time matches the available slots for a doctor. It's important for ensuring patients are scheduled only during valid time slots.

5. **validatePatient Method**

- This validation method ensures there are no duplicate patient records based on either email or phone number. It's helpful in preventing duplicate registrations.

6. **validatePatientLogin Method**

- This method checks patient credentials during login. If the login is successful, the method returns a token, which can be used for authentication in future requests.

7. **filterPatient Method**

- This method allows filtering of patient appointments based on specific conditions or doctors. It's crucial for managing patient appointment data effectively.

## Token Service

This section covers how to generate and validate JWT tokens to authenticate users securely across the application.

1. Open the `TokenService.java` file.

2. Create a service class to handle JWT token generation, extraction, and validation.

- Hint: Add `@Component` annotation above the class definition.

3. Declare necessary repositories to be used as private.

- Example:

```java
private final AdminRepository adminRepository;
private final DoctorRepository doctorRepository;
private final PatientRepository patientRepository;
```

4. Add the following logic:

- **generateToken**: This method generates a JWT token for a given user's **identifier**.

- **Parameters**

- `String identifier`: The unique identifier for the user - `username` for Admin, `email` for Doctor and Patient.

- **Return Type**

- `String`: The generated JWT token

- Hint: The method uses `Jwts.builder()` to create a JWT token. The token includes the user's identifier as the subject, the current date as the issued date, and sets an expiration of 7 days.

- **extractIdentifer**: This method extracts the identifier (subject) from a JWT token.

- **Parameters**

- `String token`: The JWT token from which the identifier is to be extracted

- \*Return Type\*\*

- `String`: The identifier extracted from the token

- Hint: The method parses the token using `Jwts.parser()` and extracts the subject (which can be email or username).

- **validateToken**: This method validates the JWT token for a given user type (admin, doctor, or patient).

- **Parameters**

- `String token`: The JWT token to be validated
- `String user`: The type of user (e.g., admin, doctor, patient)

- **Return Type**

- `boolean`:

- `true` if the token is valid for the specified user type.
- `false` if the token is invalid or expired.

- Hint: The method extracts the email from the token and checks if a corresponding user exists in the database. The validation checks depend on the provided user type (admin, doctor, or patient).

- **getSigningKey**: This method retrieves the signing key used for JWT token signing.

- **Parameters**

- None

- **Return Type**

- `SecretKey`: The key used for signing the JWT

- Hint: Uses the `secret` from the application configuration (`application.properties`) to generate a signing key using `Keys.hmacShaKeyFor()`.

### Additional Hints

1. **generateToken Method**

- This method is essential for generating a JWT token for authenticated users. The token includes an expiration time of 7 days, which can be adjusted based on the application needs.

2. **extractIdentifier Method**

- This method is useful for extracting the user identifier (email/username) from the JWT token. It allows identifying the user without additional data in the token.

3. **validateToken Method**

- This method is used to validate the authenticity of the token. It checks if the token belongs to a valid user and whether the user exists in the database based on the user type (admin, doctor, or patient).

4. **getSigningKey Method**

- This method provides the signing key for the JWT token generation. It ensures that the same key is used for both signing and verifying the token, enhancing security.

## Creating Controllers

Now, it's time to build the controllers, which expose the application's business logic through HTTP endpoints.

### Admin Controller

The admin controller will handle login operations, validating credentials and issuing tokens to authorized users.

**Purpose**: This controller handles the login functionality for the admin. It provides an endpoint for admin login validation.

1. Open the `AdminController.java` file.

2. **Set Up the Controller Class**

- Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
- Use `@RequestMapping("${api.path}" + "admin")` to set the base URL path for all methods in this controller.

3. **Autowired Dependencies**

- Autowire the necessary service.

- `Service` for handling the business logic, including admin validation

4. **Define the `adminLogin` Method**

- Annotate this method with `@PostMapping`.
- The method should accept an `Admin` object in the request body.
- It should call `validateAdmin` method from `Service` to perform the admin login validation.
- Return the response from the `validateAdmin` method which provides the result of the admin login validation

5. **Response**

- The method returns a `ResponseEntity<Map<String, String>>`.
- If the admin credentials are correct, the response will include a token.
- If the credentials are incorrect, the response will contain an error message.

### Steps Summary

1. Set up the controller: Annotate with `@RestController` and `@RequestMapping`.
2. Inject the `Service`: Autowire the `Service` class for validation logic.
3. Define the logic endpoint: Use `@PostMapping` to handle login requests, returning appropriate responses.

## Appointment Controller

This controller handles all CRUD operations related to appointments. It provides endpoints for booking, retrieving, updating, and canceling appointments. It also performs validation on tokens and ensures proper actions are taken based on user roles.

1. Open the `AppointmentController.java` file.

2. Set Up the Controller Class

- Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
- Use `@RequestMapping("/appointments")` to set the base URL path for all methods in this controller.

3. Autowired Dependencies

- Autowire the necessary services.
- AppointmentService for handling the business logic related to appointments (booking, retrieving, updating, and canceling appointments)
- `Service` for validation logic (token validation and appointment validation)

4. Define the `getAppointments` Method

- Annotate this method with `@GetMapping("/{date}/{patientName}/{token}")`.
- It takes the `date`, `patientName`, and `token` as path variables.
- It calls `service.validateToken(token, "doctor")` to validate the token, ensuring that only doctors can access appointment data.
- If token validation fails, return an error response.
- If token validation is successful, fetch the appoinments using `appointmentService.getAppointment()` and return the appointments in the response.

5. Define the `bookAppointment` Method

- Annotate this method with `@PostMapping("/{token}")`.
- It accepts an `Appointment` object in the request body.
- It uses `service.validateToken(token, "patient")` to ensure that the request is from a valid patient.
- It validates the appointment using `service.validateAppointment()`.
- If valid, it proceeds to book the appointment using `appointmentService.bookAppointment()`.
- If booking is successful, returns a success message with HTTP status `201 Created`.
- If there is an error, return an appropriate error message and status.

6. Define the `updateAppointment` Method

- Annotate this method with `@PutMapping("/{token}")`.
- It accepts the `token` as a path variable and the `Appointment` object in the request body.
- It validates the token using `service.validateToken(token, "patient")`.
- If valid, it updates the appointment using `appointmentService.updateAppointment()` and returns the result.

7. Define the `cancelAppointment` Method

- Annotate this method with `@DeleteMapping("/{id}/{token}")`.
- It accepts the `id` of the appointment and the `token` as path variables.
- It validates the token using `service.validateToken(token, "patient")`.
- If valid, it cancels the appointment using `appointmentService.cancelAppointment()` and returns the result.

### Steps Summary

1. Set up the controller: Annotate with `@RestController` and `@RequestMapping`.
2. Inject the necessary services: Autowire `AppointmentService` for appointment-related logic and `Service` for validation logic.
3. Define CRUD methods: Implement `getAppointments`, `bookAppointment`, `updateAppointment` and `cancelAppointment` methods with proper validation and response handling.

## Doctor Controller

This controller handles all operations related to the `Doctor` entity. It allows adding, updating, deleting, fetching, and filtering doctors. It also manages login functionality for doctors and validates their tokens.

Open up the `DoctorController.java` file.

### Steps and Endpoints

1. Set Up the Controller Class

- Annotate the class with `@RestController` to designate it as a REST controller.
- Use `@RequestMapping("${api.path}" + "doctor")` to set the base URL path for all methods in this controller.

2. Autowired Dependencies

- Autowire the `DoctorService` for handling business logic related to doctor operations.
- Autowire the `Service` class for handling token validation and filtering operations.

## Method Definitions

1. **Get Doctor Availability**

- Method: `@GetMapping("/avilability/{user}/{doctorId}/{date}/{token}")`

- Parameters

- `user`: Role of the user (doctor, patient, admin, and so on)
- `doctorId`: The unique ID of the doctor
- `date`: The date for which the availability needs to be fetched
- `token`: The authentication token for validating the user

- **Process**: Validates the token using `service.validateToken()` and fetches the doctor's availability using `doctorService.getDoctorAvailability()`
- **Response**: Returns a map with the doctor's availability or an error message

2. **Get List of Doctors**

- **Method**: `@GetMapping`
- **Process**: Fetches a list of all doctors from the `doctorService.getDoctors()` method
- **Response**: Returns a list of doctors in the response map

3. **Add New Doctor**

- **Method**: `@PostMapping("/{token}")`
- **Parameters**:
- `doctor`: The doctor details to be added
- `token`: The authentication token for validation

- **Process**: Validates the token with `service.validateToken()`
- If the token is valid (admin), attempts to save the doctor using `doctorService.saveDoctor()`
- Returns success or error messages based on the result of the save operation

- **Response**
- Success: "Doctor added to db"
- Conflict: "Doctor already exists"
- Internal error: "Some internal error occurred"

4. **Doctor Login**

- **Method**: `@PostMapping("/login")`
- Parameters
- `login`: The login details (email, password)
- Process: Calls `doctorService.validateDoctor()` to validate the doctor's credentials and returns the corresponding response
- **Response**: Returns the result of the login validation

5. **Update Doctor Details**

- Method: `@PutMapping("/{token}")`
- Parameters
- `doctor`: The doctor object with updated details
- `token`: The authentication token for validation

- **Process**: Validates the token using `service.validateToken()`.
- If the token is valid, attempts to update the doctor using `doctorService.updateDoctor()`
- Returns success or error messages based on the result of the update operation

- **Response**
- Success: "Doctor updated"
- Not found: "Doctor not found"
- Internal error: "Some internal error occurred"

6. **Delete Doctor**

- **Method**: `@DeleteMapping("/{id}/{token}")`
- **Parameters**
- `id`: The ID of the doctor to be deleted
- `token`: The authentication token for validation

- **Process**: Validates the token using `service.validateToken()`
- If valid, attempts to delete the doctor using `doctorService.deleteDoctor()`
- Returns success or error messages based on the result of the delete operation

- **Response**
- Success: "Doctor deleted successfully"
- Not found: "Doctor not found with id"
- Internal error: "Some internal error occurred"

7. **Filter Doctors**

- **Method**: `@GetMapping("/filter/{name}/{time}/{speciality}")`
- **Parameters**
- `name`: The name of the doctor (can be partial)
- `time`: THe available time for filtering
- `speciality`: The speciality of the doctor
- **Process**: Uses `service.filterDoctor()` to filter doctors based on the given parameters (name, time, and specialty)
- **Response**: Returns a map of filtered doctor data

## Patient Controller

This controller handles the operations related to the `Patient` entity. It allows patient registration, login, fetching patient details, getting and filtering patient appointments.

Open up the `PatientController.java` file.

### Methods and Endpoints

1. Set up the Controller Class

- Annotate the class with `@RestController` to designate it as a REST controller
- Use `@RequestMapping("/patient")` to set the base URL path for all methods in this controller

2. **Autowired Dependencies**

- Autowire `PatientService` to handle business logic related to patient operations
- Autowire `Service` to handle token validation and other common functionality

### Method Definitions

1. **Get Patient Details**

- **Method**: `GetMapping("/{token}")`
- **Parameters**
- `token`: The authentication token for the patient.
- **Process**: Validates the token using `service.validateToken()`. If the token is valid, fetches the patient details using `patientService.getPatientDetails()`.
- **Response**
- If token is invalid: Returns an error message with appropriate HTTP status.
- If successful: Returns the patient's details.

2. **Create a New Patient**

- **Method**: `@PostMapping()`
- **Parameters**
- `patient`: The patient details to be created
- **Process**
- Validates if the patient already exists by checking email or phone number
- If the validation passes, calls `patientService.createPatient()` to create a new patient record
- **Response**
- Success: "Signup successful"
- Conflict: "Patient with email id or phone no already exist"
- Internal error: "Internal server error"

3. **Patient Login**

- **Method**: `@PostMapping("/login")`
- **Parameters**
- `login`: The login credentials (email, password)
- **Process**: Calls `service.validatePatientLogin()` to validate the patient's login credentials
- **Response**: Returns the result of the login validation (success or failure)

4. **Get Patient Appointments**

- **Method**: `@GetMapping("/{id}/{token}")`
- **Parameters**
- `id`: The ID of the patient
- `token`: The authentication token for the patient
- **Process**
- Validates the token using `service.validateToken()`
- If valid, fetches the patient's appointments using `patientService.getPatientAppointment()`
- **Response**
- Returns the list of patient appointments or an error message

5. **Filter Patient Appointments**

- **Method**: `@GetMapping("/filter/{condition}/{name}/{token}")`
- **Parameters**
- `condition`: The condition to filter appointments (for example, "upcoming", "past")
- `name`: The name or description for filtering (for example, doctor name, appointment type)
- `token`: The authentication token for the patient

- **Process**
- Validates the token using `service.validateToken()`
- If valid, calls `service.filterPatient()` to filter the patient's appointments based on the given criteria

- **Response**
- Returns the filtered appointments or an error message

## Prescription Controller

This controller is responsible for handling operations related to prescriptions in the system. It allows doctors to save prescriptions and retrieve prescriptions based on the appointment ID.

Open up the `PrescriptionController.java` file.

### Methods and Endpoints

1. **Set Up the Controller Class**

- Annoate the class with `@RestController` to designate it as a REST controller
- Use `@RequestMapping("${api.path}" + "prescription")` to set the base URL path for all methods in this controller

2. **Autowired Dependencies**

- Autowire `PrescriptionService` to handle business logic of managing prescriptions
- Autowire `Service` to handle common functionality, like token validation

### Method Definitions

1. **Save Prescription**

- **Method**: `@PostMapping("/{token}")`
- **Parameters**
- `token`: The authentication token for the doctor
- `prescription`: The prescription details to be saved (passed in the request body)
- **Process**
- The `token` is validated to ensure that the request is made by a doctor using `service.validateToken()`
- If the token is valid, the prescription is saved using `prescriptionService.savePrescription()`
- **Response**
- If the token is invalid: Returns an error message with appropriate HTTP status
- If the prescription is successfully saved: Returns a success message

2. **Get Prescription by Appointment ID**

- **Method**: `@GetMapping("/{appointmentId}/{token}")`
- **Parameters**
- `appointmentId`: The ID of the appointment to retrieve the prescription for
- `token`: The authentication token for the doctor

- **Process**
- The `token` is validated using `service.validateToken()` to ensure the request us from a valid doctor
- If the token is valid, the prescription is retrieved for the given `appointmentId` using `prescriptionService.getPrescription()`

- **Response**
- If the token is invalid: Returns an error message with appropriate HTTP status
- If the prescription is found, returns the prescription details
- If no prescription is found, returns a message indicating no prescription exists for that appointment

### Explanation of the `ValidationFailed` Class

The `ValidationFailed` class is a custom exception handler that handles validation errors in a Spring Boot application. It is annotated with `@RestControllerAdvice`, which makes it a global exception handler for REST controllers.

This class handles the `MethodArgumentNotValidException` which occurs when a validation fails during the binding of request parameters or request body fields to the method parameters in a controller. Typically, this happens when input data does not meet the constraints defined by annotations such as `@NotNull`, `@Size`, `@Email`, and so on, in the model class.

### Key Points

1. `@RestControllerAdvice`

- This annotation is a combination of `@ControllerAdvice` and `@ResponseBody`, which makes it a global exception handler that can return the response directly as JSON (or any other format) in case of errors.

2. **Exception Handler Method**

- The `@ExceptionHandler(MethodArgumentNotValidException.class)` annotation specifies that this method will handle exception of type `MethodArgumentNotValidException`. This exception is thrown when a validation error occurs on the request body (such as when data in a `@RequestBody` doesn't match the required constraints).

3. **Handling Validation Errors**

- The method `handleValidationException` is invoked when a `MethodArgumentNotValidException` is thrown.
- Inside this method, the exception object (`ex`) provides access to the binding result of the validation errors, which includes the field errors (for example, which fields failed validation).

4. **Creating the Error Response**

- The `FieldError` object contains information about the specific field that failed validation and the corresponding error message.
- We loop through all the field errors in the exception and map them to a `Map<String, String>`, where the key is `"message"` and the value is the actual error message associated with the field.

5. **Returning the Response**

- After processing all validation errors, the method returns a `ResponseEntity` with an HTTP status of `BAD_REQUEST(400)` and a body containing the validation error messages.

## Test The App

1. **Set Up Configuration and Database**

As per Module 3 / Lab: Adding Databases and Tables:

- Configure `application.properties` with MySQL and MongoDB usernames and passwords.
- Import the provided SQL and MongoDB data files into the databases.

2. Build the Project

In the terminal, run:

- `mvn clean install`

3. Run the App Locally:

Start the Spring Boot app with:

- `mvn spring-boot:run`

4. Launch on Port 8080

Once the app is running, open the application in the browser.

Note:

- Copy the application url to use in curl testing
- Can use the login credentials for the `admin dashboard` and `doctor dashboard` from the data that was loaded into the tables during Lab: Adding Databases and Tables.

5. **Update API Base URL in `config.js` file**

After launching the app, update the API base url in the `config.js` to match the application URL.

Open the `config.js` file

Update this line: `export const API_BASE_URL = "http://localhost:8080";`

Replace `http://localhost:8080` with the actual app URL, such as `http://<your-theia-url>`

After making this change, refresh the browser to ensure the updated URL is applied.

6. **Test the Endpoint Using CURL**:

To test if the app is working using a terminal command, you can run:

- `curl http://localhost:8080/doctor`

Note: Replace `http://localhost:8080` to the actual app URL.

This should return a list of doctors.

7. **Test the endpoint using a curl command to retrieve all appointments booked by a patient from the database, authenticated via the patient's login credentials**.

If already done the sign up using UI then can skip the Sign up command and move to the login command.

The task involves three curl commands:

A - Sign Up

This command registers a new patient in the system

`curl -X POST <URL>/patient -H "Content-Type: application/json" -d '{"name":"name","email":"useremail","phone":"phonenumber","password":"password","age":age,"address":"address","gender":"gender"}'`

Make sure to replace the command with the own URL and personal details that you intend to use for account creation.

B - Login

This command authenticates the patient and returns a JWT token which will be used to fetch all the appointments for any patient.

`curl -X POST <URL>/patient/login -H "Content-Type: application/json" -d '{"email":"email","password":"password"}'`

Ensure to replace the command with the URL and Token generated after running the Sign up command.

C - Get Appointments

This command fetches appointments for the patient using the JWT token

`curl -i -X GET <URL>/patient/1/patient/<JWT-TOKEN> -H "Accept: application/json"`

Ensure to replace the command with the own URL and Token generated after running the login command.

Keep a note of the final curl command as it will be used in the final project submission.

8. **Test the endpoint using a curl command to retrieve all doctor details for any specialty and time(can choose any specialty)**

`curl -X GET <URL>/doctor/filter/null/09:00-10:00/Cardiologist`

This returns a list of doctors who specialize in Cardiologist and are available from 09:00 to 10:00, may choose any other speciality and time

Ensure to replace the command with the URL and keep a note of the final curl command as it will be used in the final project submission.

### Deliverables

Take the following screenshots ans save them using the suggested filenames. Can save them either in a `.png` or `.jpg` format.

- **Admin Portal Login Screen**

Take a screenshot showing the login screen of the admin portal.
**Filename**: `Admin Portal.png` or `Admin Portal.jpg`

- **Doctor Portal Login Screen**

Take a screenshot showing the login screen of the doctor portal.
**Filename**: `Doctor Portal.png` or `Doctor Portal.jpg`

- **Patient Portal Login Screen**

Take a screenshot showing the login screen of the patient portal.
**Filename**: `Patient Portal.png` or `Patient Portal.jpg`

- **Admin Adding a Doctor**

Take a screenshot showing the admin portal while an admin is adding a doctor to the Smart Clinic Management System.
**Filename**: `Adding Doctor.png` or `Adding Doctor.jpg`

- **Patient Searching for a Doctor**

Take a screenshot showing the patient portal while a patient is searching for a doctor by name.
**Filename**: `Searching Doctor.png` or `Searching Doctor.jpg`

- **Doctor Viewing Appointments**

Take a screenshot showing the doctor portal where a doctor is viewing a list of all their patient appointments.
**Filename**: `Appointments List.png` or `Appointments List.jpg`

## Save Work

Before finishing the lab, make sure to **add, commit, and push** the updated code to the GitHub repository. Will be asked to provide the public **GitHub repo URL** for graded evaluation at the end of the capstone.

Follow these steps to push the changes:

1. Stage changes

- `git add`

2. Commit changes with a meaningful message.

- `git commit -m "Completed DTO's, controllers, service classes, and repositories"`

3. Push changes using GitHub Personal Access Token.

`git push https://<your_github_username>:<your_personal_access_token>@github.com/<your_github_username>/java-database-capstone.git`

## Conclusion

In this lab:

Implemented key components of a layered Spring Boot Application such as:

- Creating **DTOs** to structure data transfer
- Developing **controllers** to handle incoming HTTP requests
- Building **service classes** to encapsulate business logic
- Using **repositories** to interact with the database layer

These components work together to create a scalable, modular, and maintainable backend for the Clinic Management System.

Also applied best practices such as:

- Separating concerns between layers (Controller -> Service -> Repository)
- Using DTOs to decouple internal models from external APIs
- Leveraging Spring Data to avoid boilerplate CRUD code
- Validating input data for reliability and security

## Next Steps

Now, can take the following steps to continue improving and extending the submission:

- Implement **authentication and authorization** using Spring Security.
- Protect API endpoints based on user roles such as admin, doctor, or patient.
- Store and verify passwords securely using hashing algorithms.
- Apply role-based access control (RBAC) to secure sensitive operations.

These enhancements will make the application production-ready by ensuring only authorized users can access specific features.
