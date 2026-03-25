# Hands-On Database Practice Exercises

Try solving the following exercises to strengthen your understanding:

1. Create a procedure named `GetTopStudents` that retrieves students with a GPA greater than 3.5.
2. Write a procedure named `UpdateStudentMajor` that updates a student's major based on their ID.
3. Develop a procedure named `DeleteStudent` that deletes a student record when provided with an ID.
4. Implement a procedure named `CalculateAverageGrade` that accepts a course ID and returns the average grade for that course.

## Creating a stored procedure for retrieving students with GPA > 3.5. The syntax varies slightly depending on the SQL Database System

**SQL Server**

```
CREATE PROCEDURE GetTopStudents
AS
BEGIN
    SELECT StudentID, StudentName, GPA
    FROM Students
    WHERE GPA > 3.5
    ORDER BY GPA DESC
END
```

**MySQL**

```
DELIMITER //

CREATE PROCEDURE GetTopStudents()
BEGIN
    SELECT StudentID, StudentName, GPA
    FROM Students
    WHERE GPA > 3.5
    ORDER BY GPA DESC;
END //

DELIMITER ;
```

To execute the procedure:

```
-- SQL Server / MySQL / PostgreSQL
CALL GetTopStudents();
```

**Key components:**

- `CREATE PROCEDURE` - defines the stored procedure
- `GetTopStudents` - procedure name
- `WHERE GPA > 3.5` - filters students with GPA greater than 3.5
- `ORDER BY GPA DESC` - sorts results by highest GPA first

## Writing a procedure that updates a student's major based on their ID

**SQL Server**

```
CREATE PROCEDURE UpdateStudentMajor
    @StudentID INT,
    @NewMajor NVARCHAR(100)
AS
BEGIN
    UPDATE Students
    SET Major = @NewMajor
    WHERE StudentID = @StudentID

    IF @@ROWCOUNT = 0
        PRINT 'No student found with that ID'
    ELSE
        PRINT 'Major updated successfully'
END

-- Execute
EXEC UpdateStudentMajor @StudentID = 1, @NewMajor = 'Computer Science'
```

**MySQL**

```
DELIMITER //

CREATE PROCEDURE UpdateStudentMajor(
    IN p_StudentID INT,
    IN p_NewMajor VARCHAR(100)
)
BEGIN
    UPDATE Students
    SET Major = p_NewMajor
    WHERE StudentID = p_StudentID;

    IF ROW_COUNT() = 0 THEN
        SELECT 'No student found with that ID' AS Message;
    ELSE
        SELECT 'Major updated successfully' AS Message;
    END IF;
END //

DELIMITER ;

-- Execute
CALL UpdateStudentMajor(1, 'Computer Science');
```

**Key Components:**

- `@StudentID/p_StudentID` - Input parameter for the student ID
- `@NewMajor/p_NewMajor` - Input parameter for the new major
- `UPDATE...WHERE` - Updates only the matching student
- Row validation - Checks if the student exists before updating

## Creating a stored procedure that deletes a student record by their ID:

**SQL Server**

```
CREATE PROCEDURE DeleteStudent
    @StudentID INT
AS
BEGIN
    DELETE FROM Students
    WHERE StudentID = @StudentID

    IF @@ROWCOUNT = 0
        PRINT 'No student found with that ID'
    ELSE
        PRINT 'Student record deleted successfully'
END

-- Execute
EXEC DeleteStudent @StudentID = 1
```

**MySQL**

```
DELIMITER //

CREATE PROCEDURE DeleteStudent(
    IN p_StudentID INT
)
BEGIN
    DELETE FROM Students
    WHERE StudentID = p_StudentID;

    IF ROW_COUNT() = 0 THEN
        SELECT 'No student found with that ID' AS Message;
    ELSE
        SELECT 'Student record deleted successfully' AS Message;
    END IF;
END //

DELIMITER ;

-- Execute
CALL DeleteStudent(1);
```

**Key Components:**

- `@StudentID/p_StudentID` - Input parameter for student ID to delete
- `DELETE...WHERE` - Removes the matching student record
- Row validation - Checks if a student was actually deleted
- Safety feedback - Confirms deletion or reports no matching record found

**Optional:** Add validation to prevent accidental deletions

```
-- Example: Add a confirmation parameter for extra safety
CREATE PROCEDURE DeleteStudent
    @StudentID INT,
    @ConfirmDelete BIT = 0
AS
BEGIN
    IF @ConfirmDelete = 0
        PRINT 'Confirmation required. Set @ConfirmDelete = 1'
    ELSE
        DELETE FROM Students
        WHERE StudentID = @StudentID
END
```

## Implementing a stored procedure that calculates and returns the average grade for a course

**SQL Server**

```
CREATE PROCEDURE CalculateAverageGrade
    @CourseID INT,
    @AverageGrade DECIMAL(5,2) OUTPUT
AS
BEGIN
    SELECT @AverageGrade = AVG(CAST(Grade AS DECIMAL(5,2)))
    FROM Grades
    WHERE CourseID = @CourseID

    IF @AverageGrade IS NULL
        PRINT 'No grades found for this course'
    ELSE
        PRINT 'Average grade calculated: ' + CAST(@AverageGrade AS VARCHAR(10))
END

-- Execute
DECLARE @AvgGrade DECIMAL(5,2)
EXEC CalculateAverageGrade @CourseID = 1, @AverageGrade = @AvgGrade OUTPUT
SELECT @AvgGrade AS AverageGrade
```

**MySQL**

```
DELIMITER //

CREATE PROCEDURE CalculateAverageGrade(
    IN p_CourseID INT,
    OUT p_AverageGrade DECIMAL(5,2)
)
BEGIN
    SELECT AVG(CAST(Grade AS DECIMAL(5,2))) INTO p_AverageGrade
    FROM Grades
    WHERE CourseID = p_CourseID;

    IF p_AverageGrade IS NULL THEN
        SELECT 'No grades found for this course' AS Message;
    ELSE
        SELECT CONCAT('Average grade: ', p_AverageGrade) AS Message;
    END IF;
END //

DELIMITER ;

-- Execute
CALL CalculateAverageGrade(1, @AvgGrade);
SELECT @AvgGrade AS AverageGrade;
```

**Alternative:** Using a Function (Better for Return Values)

```
CREATE FUNCTION GetAverageGrade(@CourseID INT)
RETURNS DECIMAL(5,2)
AS
BEGIN
    DECLARE @AverageGrade DECIMAL(5,2)
    SELECT @AverageGrade = AVG(CAST(Grade AS DECIMAL(5,2)))
    FROM Grades
    WHERE CourseID = @CourseID
    RETURN ISNULL(@AverageGrade, 0)
END

-- Execute
SELECT dbo.GetAverageGrade(1) AS AverageGrade
```

**Key Components:**

- `@CourseID/p_CourseID` - Input parameter for course ID
- `@AverageGrade/p_AverageGrade` - OUTPUT parameter to return the result
- `@AVG(Grade)` - Calculates the average of all grades for the course
- `INTO` (MySQL/PostgreSQL) - Stores the result in the output variable
- Null handling - Checks if any grades exist for the course

**Assumptions:**

- Table named `Grades` with columns `CourseID` and `Grade`
- Adjust if your schema uses different names
