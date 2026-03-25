-- ============================================
-- SQL Script: GetTopStudents Procedure
-- Purpose: Creates a stored procedure to retrieve students with GPA > 3.5
-- ============================================

-- ============================================
-- SECTION 1: SQL SERVER IMPLEMENTATION
-- ============================================
-- Uncomment for SQL Server

/*
-- Create the Students table (if not exists)
CREATE TABLE Students (
    StudentID INT PRIMARY KEY IDENTITY(1,1),
    StudentName NVARCHAR(100) NOT NULL,
    GPA DECIMAL(3,2) NOT NULL
);

-- Create the stored procedure
CREATE PROCEDURE GetTopStudents
AS
BEGIN
    SELECT StudentID, StudentName, GPA
    FROM Students
    WHERE GPA > 3.5
    ORDER BY GPA DESC
END

-- Execute the procedure
EXEC GetTopStudents;

-- To drop the procedure if needed:
-- DROP PROCEDURE GetTopStudents;
*/

-- ============================================
-- SECTION 2: MYSQL IMPLEMENTATION
-- ============================================
-- Uncomment for MySQL

/*
-- Create the Students table (if not exists)
CREATE TABLE IF NOT EXISTS Students (
    StudentID INT AUTO_INCREMENT PRIMARY KEY,
    StudentName VARCHAR(100) NOT NULL,
    GPA DECIMAL(3,2) NOT NULL
);

-- Change delimiter to allow semicolons in the procedure
DELIMITER //

-- Create the stored procedure
CREATE PROCEDURE GetTopStudents()
BEGIN
    SELECT StudentID, StudentName, GPA
    FROM Students
    WHERE GPA > 3.5
    ORDER BY GPA DESC;
END //

-- Change delimiter back
DELIMITER ;

-- Execute the procedure
CALL GetTopStudents();

-- To drop the procedure if needed:
-- DROP PROCEDURE GetTopStudents;
*/

-- ============================================
-- SECTION 3: POSTGRESQL IMPLEMENTATION
-- ============================================
-- Uncomment for PostgreSQL

/*
-- Create the Students table (if not exists)
CREATE TABLE IF NOT EXISTS Students (
    StudentID SERIAL PRIMARY KEY,
    StudentName VARCHAR(100) NOT NULL,
    GPA DECIMAL(3,2) NOT NULL
);

-- Create the stored procedure
CREATE PROCEDURE GetTopStudents()
LANGUAGE SQL
BEGIN ATOMIC
    SELECT StudentID, StudentName, GPA
    FROM Students
    WHERE GPA > 3.5
    ORDER BY GPA DESC;
END;

-- Execute the procedure
CALL GetTopStudents();

-- To drop the procedure if needed:
-- DROP PROCEDURE GetTopStudents;
*/

-- ============================================
-- SAMPLE TEST DATA
-- ============================================
-- Use this to insert test data into the Students table

/*
INSERT INTO Students (StudentName, GPA) VALUES
('Alice Johnson', 3.85),
('Bob Smith', 3.2),
('Carol White', 3.9),
('David Brown', 3.45),
('Emma Davis', 3.75),
('Frank Wilson', 2.95),
('Grace Lee', 4.0),
('Henry Martinez', 3.55);
*/

-- ============================================
-- SECTION 4: UPDATESTUDENTOR PROCEDURE - SQL SERVER
-- ============================================
-- Uncomment for SQL Server

/*
-- Alter the Students table to add Major column (if not exists)
ALTER TABLE Students
ADD Major NVARCHAR(100) NULL;

-- Create the UpdateStudentMajor stored procedure
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

-- Execute the procedure
EXEC UpdateStudentMajor @StudentID = 1, @NewMajor = 'Computer Science';

-- To drop the procedure if needed:
-- DROP PROCEDURE UpdateStudentMajor;
*/

-- ============================================
-- SECTION 5: UPDATESTUDENTOR PROCEDURE - MYSQL
-- ============================================
-- Uncomment for MySQL

/*
-- Alter the Students table to add Major column (if not exists)
ALTER TABLE Students
ADD COLUMN Major VARCHAR(100) NULL;

-- Change delimiter to allow semicolons in the procedure
DELIMITER //

-- Create the UpdateStudentMajor stored procedure
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

-- Change delimiter back
DELIMITER ;

-- Execute the procedure
CALL UpdateStudentMajor(1, 'Computer Science');

-- To drop the procedure if needed:
-- DROP PROCEDURE UpdateStudentMajor;
*/

-- ============================================
-- SECTION 6: UPDATESTUDENTOR PROCEDURE - POSTGRESQL
-- ============================================
-- Uncomment for PostgreSQL

/*
-- Alter the Students table to add Major column (if not exists)
ALTER TABLE Students
ADD COLUMN Major VARCHAR(100) NULL;

-- Create the UpdateStudentMajor stored procedure
CREATE PROCEDURE UpdateStudentMajor(
    p_StudentID INT,
    p_NewMajor VARCHAR(100)
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE Students
    SET Major = p_NewMajor
    WHERE StudentID = p_StudentID;
    
    IF NOT FOUND THEN
        RAISE NOTICE 'No student found with that ID';
    ELSE
        RAISE NOTICE 'Major updated successfully';
    END IF;
END $$;

-- Execute the procedure
CALL UpdateStudentMajor(1, 'Computer Science');

-- To drop the procedure if needed:
-- DROP PROCEDURE UpdateStudentMajor;
*/

-- ============================================
-- SECTION 7: DELETESTUDENT PROCEDURE - SQL SERVER
-- ============================================
-- Uncomment for SQL Server

/*
-- Create the DeleteStudent stored procedure
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

-- Execute the procedure
EXEC DeleteStudent @StudentID = 1;

-- To drop the procedure if needed:
-- DROP PROCEDURE DeleteStudent;
*/

-- ============================================
-- SECTION 8: DELETESTUDENT PROCEDURE - MYSQL
-- ============================================
-- Uncomment for MySQL

/*
-- Change delimiter to allow semicolons in the procedure
DELIMITER //

-- Create the DeleteStudent stored procedure
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

-- Change delimiter back
DELIMITER ;

-- Execute the procedure
CALL DeleteStudent(1);

-- To drop the procedure if needed:
-- DROP PROCEDURE DeleteStudent;
*/

-- ============================================
-- SECTION 9: DELETESTUDENT PROCEDURE - POSTGRESQL
-- ============================================
-- Uncomment for PostgreSQL

/*
-- Create the DeleteStudent stored procedure
CREATE PROCEDURE DeleteStudent(
    p_StudentID INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM Students
    WHERE StudentID = p_StudentID;
    
    IF NOT FOUND THEN
        RAISE NOTICE 'No student found with that ID';
    ELSE
        RAISE NOTICE 'Student record deleted successfully';
    END IF;
END $$;

-- Execute the procedure
CALL DeleteStudent(1);

-- To drop the procedure if needed:
-- DROP PROCEDURE DeleteStudent;
*/

-- ============================================
-- SECTION 10: CALCULATEAVERAGEGRADE PROCEDURE - SQL SERVER
-- ============================================
-- Uncomment for SQL Server

/*
-- Create the CalculateAverageGrade stored procedure
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

-- Execute the procedure
DECLARE @AvgGrade DECIMAL(5,2)
EXEC CalculateAverageGrade @CourseID = 1, @AverageGrade = @AvgGrade OUTPUT
SELECT @AvgGrade AS AverageGrade;

-- To drop the procedure if needed:
-- DROP PROCEDURE CalculateAverageGrade;
*/

-- ============================================
-- SECTION 11: CALCULATEAVERAGEGRADE PROCEDURE - MYSQL
-- ============================================
-- Uncomment for MySQL

/*
-- Change delimiter to allow semicolons in the procedure
DELIMITER //

-- Create the CalculateAverageGrade stored procedure
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

-- Change delimiter back
DELIMITER ;

-- Execute the procedure
CALL CalculateAverageGrade(1, @AvgGrade);
SELECT @AvgGrade AS AverageGrade;

-- To drop the procedure if needed:
-- DROP PROCEDURE CalculateAverageGrade;
*/

-- ============================================
-- SECTION 12: CALCULATEAVERAGEGRADE PROCEDURE - POSTGRESQL
-- ============================================
-- Uncomment for PostgreSQL

/*
-- Create the CalculateAverageGrade stored procedure
CREATE PROCEDURE CalculateAverageGrade(
    p_CourseID INT,
    OUT p_AverageGrade DECIMAL(5,2)
)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT AVG(CAST(Grade AS DECIMAL(5,2))) INTO p_AverageGrade
    FROM Grades
    WHERE CourseID = p_CourseID;
    
    IF p_AverageGrade IS NULL THEN
        RAISE NOTICE 'No grades found for this course';
    ELSE
        RAISE NOTICE 'Average grade: %', p_AverageGrade;
    END IF;
END $$;

-- Execute the procedure
CALL CalculateAverageGrade(1, NULL);

-- To drop the procedure if needed:
-- DROP PROCEDURE CalculateAverageGrade;
*/

-- ============================================
-- NOTES
-- ============================================
-- 1. Select the appropriate section for your database system
-- 2. Uncomment the entire section (Ctrl+K+U or Cmd+K+U)
-- 3. Execute the script in your database client
-- 4. GetTopStudents: Retrieves all students with GPA > 3.5, sorted by GPA descending
-- 5. UpdateStudentMajor: Updates a student's major based on their StudentID
-- 6. DeleteStudent: Deletes a student record by StudentID with confirmation feedback
-- 7. CalculateAverageGrade: Calculates and returns the average grade for a course
-- 8. All procedures support SQL Server, MySQL, and PostgreSQL
-- 9. Adjust column names and table names if different in your database
-- 10. Use Grades table with CourseID and Grade columns for CalculateAverageGrade
