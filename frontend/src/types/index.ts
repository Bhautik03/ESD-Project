// Type definitions for the Course Timetable API

export interface DomainSummary {
  id: number;
  program: string;
}

export interface CourseScheduleDto {
  day: string;
  time: string;
  room: string;
  building: string;
}

export interface FacultyDto {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  title?: string;
}

export interface CourseWithScheduleDto {
  id: number;
  name: string;
  courseCode: string;
  description?: string;
  credits?: number;
  schedule: CourseScheduleDto[];
  faculty: FacultyDto[];
}

export interface DomainTimetableResponse {
  id: number;
  program: string;
  courses: CourseWithScheduleDto[];
}

export interface StudentDto {
  id: number;
  rollNumber: string;
  firstName: string;
  lastName: string;
  email: string;
  cgpa?: number;
  totalCredits?: number;
  graduationYear?: number;
}

export interface CourseStudentsResponse {
  courseId: number;
  courseName: string;
  students: StudentDto[];
}

