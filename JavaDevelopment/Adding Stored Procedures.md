# Adding Stored Procedures Lab

## Learning Objectives

After completing this lab, will be able to:

- Write stored procedures to generate daily and summary reports
- Use SQL joins to query across multiple tables
- Practice executing stored procedures with parameters

### Overview

This lab demonstrates how to create stored procedures for reports. In this lab, you will write SQL stored procedures to automate report generation and generate reports using SQL joins and aggregation.

## Key Terms

- **Stored Procedure**: A saved collection of SQL statements that can be executed with a single call, often used for automation and reporting.
- **SQL JOIN**: Combines rows from two or more tables based on a related column between them (Example: `doctor_id`, `patient_id`).
- **DELIMITER**: A command used in MySQL to temporarily change the statement delimiter, allowing multi-statement procedures to be created.
- **CALL**: The SQL command used to invoke or run a stored procedure.
- **Input Parameters**: Variables passed into a procedure when it's called (Example: `report_date`, `input_month`, `input_year`).
- **GROUP BY**: A clause used to group rows that have the same values in specified columns, often used with aggregate functions.
- **ORDER BY**: Used to sort the result set based on one or more columns.
- **Aggregation Function**: A function like `COUNT()` that operates on sets of values to return a single summarizing value.

## Daily Appointment Report By Doctor

### Procedure: `GetDailyAppointmentReportByDoctor`

This procedure generates a report listing all appointments on a specific date, grouped by doctor. It displays the doctor's name, appointment time, appointment status, and the patient's name and phone number. This is useful for daily operational reviews in the clinic.

1. Define the stored procedure

```SQL
DELIMITER $$
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(
    IN report_date DATE
)
BEGIN
    SELECT
        d.name AS doctor_name,
        a.appointment_time,
        a.status,
        p.name AS patient_name,
        p.phone AS patient_phone
    FROM
        appointment a
    JOIN
        doctor d ON a.doctor_id = d.id
    JOIN
        patient p ON a.patient_id = p.id
    WHERE
        DATE(a.appointment_time) = report_date
    ORDER BY
        d.name, a.appointment_time;
END$$
DELIMITER ;
```

2. Run the stored procedure

`CALL GetDailyAppointmentReportByDoctor('2025-04-15');`

## Doctors with Most Patients By Month

### Procedure: `GetDoctorsWithMostPatientsByMonth`

This procedure identifies the doctor who saw the most patients in a given month and year. It helps clinic managers understand which doctor had the highest patient load during a time period.

1. Define the stored procedure

```SQL
DELIMITER $$
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(
    IN input_month INT,
    IN input_year INT
)
BEGIN
    SELECT
        doctor_id,
        COUNT(patient_id) AS patients_seen
    FROM
        appointment
    WHERE
        MONTH(appointment_time) = input_month
        AND YEAR(appointment_time) = input_year
    GROUP BY
        doctor_id
    ORDER BY
        patients_seen DESC
    LIMIT 1;
END $$
DELIMITER ;
```

2. Run the stored procedure

`CALL GetDoctorWithMostPatientsByMonth(4, 2025);`

## Doctor with Most Patients By Year

### Procedure: `GetDoctorWithMostPatientsByYear`

This procedure identifies the doctor with the most patients in a given year. It is helpful for generating annual performance summaries.

### Define the stored procedure

```SQL
DELIMITER $$
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(
    IN input_year INT
)
BEGIN
    SELECT
        doctor_id,
        COUNT(patient_id) AS patients_seen
    FROM
        appointment
    WHERE
        YEAR(appointment_time) = input_year
    GROUP BY
        doctor_id
    ORDER BY
        patients_seen DESC
    LIMIT 1;
END $$
DELIMITER ;
```

### Run the stored procedure

`CALL GetDoctorWithMostPatientsByYear(2025);`

## Conclusion

In this lab, have written stored procedures that:

- Generate daily appointment reports
- Identify the doctor with the most patients by month and year

This concludes the reporting module.
