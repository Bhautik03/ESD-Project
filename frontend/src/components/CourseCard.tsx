import React from 'react';
import { CourseWithScheduleDto } from '../types';
import './TimetableDisplay.css';

interface CourseCardProps {
    course: CourseWithScheduleDto;
    isSelected: boolean;
    onSelect: (courseId: number) => void;
}

const CourseCard: React.FC<CourseCardProps> = ({ course, isSelected, onSelect }) => {
    const formatTime = (timeString: string): string => {
        if (!timeString) return '';
        return timeString.substring(0, 5);
    };

    return (
        <div
            className={`course-card ${isSelected ? 'selected' : ''}`}
        >
            <div className="course-header">
                <h3 className="course-name">{course.name}</h3>
                <span className="course-code">{course.courseCode}</span>
            </div>

            {course.schedule && course.schedule.length > 0 && (
                <div className="schedule-section">
                    <h4 className="section-title">📆 Schedule</h4>
                    <div className="schedule-list">
                        {[...course.schedule]
                            .sort((a, b) => {
                                const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
                                return days.indexOf(a.day) - days.indexOf(b.day);
                            })
                            .map((slot, index) => (
                                <div key={index} className="schedule-item">
                                    <span className="schedule-day">{slot.day}</span>
                                    <span className="schedule-time">
                                        {formatTime(slot.time)}
                                    </span>
                                    <span className="schedule-location">
                                        {slot.room} - {slot.building}
                                    </span>
                                </div>
                            ))}
                    </div>
                </div>
            )}

            {course.faculty && course.faculty.length > 0 && (
                <div className="faculty-section">
                    <h4 className="section-title">👨‍🏫 Faculty</h4>
                    <div className="faculty-list">
                        {course.faculty.map((member) => (
                            <div key={member.id} className="faculty-item">
                                <span className="faculty-name">
                                    {member.firstName} {member.lastName}
                                </span>
                                <span className="faculty-email">{member.email}</span>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            {(!course.faculty || course.faculty.length === 0) &&
                (!course.schedule || course.schedule.length === 0) && (
                    <div className="no-details">
                        No schedule or faculty information available.
                    </div>
                )}

            <div className="course-action">
                <button
                    className="view-students-btn"
                    onClick={(e) => {
                        e.stopPropagation();
                        onSelect(course.id);
                    }}
                >
                    👥 View Enrolled Students
                </button>
            </div>
        </div>
    );
};

export default CourseCard;
