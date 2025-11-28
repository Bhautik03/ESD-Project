import React from 'react';
import './StudentList.css';
import { StudentDto } from '../types';

interface StudentListProps {
  courseName?: string;
  students: StudentDto[];
}

const StudentList: React.FC<StudentListProps> = ({ courseName, students }) => {
  if (!students || students.length === 0) {
    return null;
  }

  return (
    <div className="student-list-container">
      <div className="student-list-header">
        <h3>👥 Enrolled Students</h3>
        <span className="student-count">
          {students.length} Student{students.length !== 1 ? 's' : ''}
        </span>
      </div>
      {courseName && <div className="course-name-badge">{courseName}</div>}
      
      <div className="students-table">
        <div className="table-header">
          <div className="col-roll">Roll Number</div>
          <div className="col-name">Name</div>
          <div className="col-email">Email</div>
        </div>
        
        <div className="table-body">
          {students.map((student) => (
            <div key={student.id} className="table-row">
              <div className="col-roll">
                <span className="roll-badge">{student.rollNumber}</span>
              </div>
              <div className="col-name">
                {student.firstName} {student.lastName}
              </div>
              <div className="col-email">{student.email}</div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default StudentList;

