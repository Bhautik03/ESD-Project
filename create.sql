-- Domains table
CREATE TABLE Domains (
    domain_id INT PRIMARY KEY AUTO_INCREMENT,
    program VARCHAR(100) NOT NULL
);

-- Courses table
CREATE TABLE Courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    course_code VARCHAR(20) NOT NULL
);

-- Course_Domain (many-to-many between Courses and Domains)
CREATE TABLE Course_Domain (
    course_id INT,
    domain_id INT,
    PRIMARY KEY (course_id, domain_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE,
    FOREIGN KEY (domain_id) REFERENCES Domains(domain_id) ON DELETE CASCADE
);

-- Course_Schedule table
CREATE TABLE Course_Schedule (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT,
    day VARCHAR(20) NOT NULL,
    time TIME NOT NULL,
    room VARCHAR(20),
    building VARCHAR(50),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE
);

-- Employees table (faculty)
CREATE TABLE Employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100)
);

-- Faculty_Courses (many-to-many between Employees and Courses)
CREATE TABLE Faculty_Courses (
    faculty INT,
    course_id INT,
    PRIMARY KEY (faculty, course_id),
    FOREIGN KEY (faculty) REFERENCES Employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE
);

-- Students table
CREATE TABLE Students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    roll_number VARCHAR(20) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100)
);

-- Student_Courses (many-to-many between Students and Courses)
CREATE TABLE Student_Courses (
    student_id INT,
    course_id INT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE
);
