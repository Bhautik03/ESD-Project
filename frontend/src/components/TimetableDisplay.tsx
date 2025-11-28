import React from 'react';
import './TimetableDisplay.css';
import { DomainTimetableResponse } from '../types';
import CourseCard from './CourseCard';

interface TimetableDisplayProps {
  timetable: DomainTimetableResponse;
  selectedCourse: number | null;
  onCourseSelect: (courseId: number) => void;
}

const TimetableDisplay: React.FC<TimetableDisplayProps> = ({
  timetable,
  selectedCourse,
  onCourseSelect,
}) => {
  if (!timetable) return null;

  return (
    <div className="timetable-display">
      <div className="timetable-header">
        <h2>Timetable: {timetable.program}</h2>
        <div className="course-count">
          {timetable.courses?.length || 0} Course{timetable.courses?.length !== 1 ? 's' : ''}
        </div>
      </div>

      {!timetable.courses || timetable.courses.length === 0 ? (
        <div className="no-courses">
          No courses available for this domain.
        </div>
      ) : (
        <div className="courses-grid">
          {timetable.courses.map((course) => (
            <CourseCard
              key={course.id}
              course={course}
              isSelected={selectedCourse === course.id}
              onSelect={onCourseSelect}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default TimetableDisplay;

