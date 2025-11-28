import React, { useState, useEffect } from 'react';
import DomainSelector from '../components/DomainSelector';
import TimetableDisplay from '../components/TimetableDisplay';
import StudentList from '../components/StudentList';
import { timetableService } from '../services/api';
import {
    DomainSummary,
    DomainTimetableResponse,
    StudentDto,
} from '../types';

interface DashboardProps {
    onLogout: () => void;
}

const Dashboard: React.FC<DashboardProps> = ({ onLogout }) => {
    const [domains, setDomains] = useState<DomainSummary[]>([]);
    const [selectedDomain, setSelectedDomain] = useState<number | null>(null);
    const [timetable, setTimetable] = useState<DomainTimetableResponse | null>(null);
    const [selectedCourse, setSelectedCourse] = useState<number | null>(null);
    const [students, setStudents] = useState<StudentDto[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        loadDomains();
    }, []);

    const loadDomains = async (): Promise<void> => {
        try {
            setLoading(true);
            const data = await timetableService.getAllDomains();
            setDomains(data);
            setError(null);
        } catch (err) {
            setError('Failed to load domains. Make sure the backend is running.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    const handleDomainSelect = async (domainId: number): Promise<void> => {
        try {
            setLoading(true);
            setSelectedDomain(domainId);
            setSelectedCourse(null);
            setStudents([]);
            const data = await timetableService.getDomainTimetable(domainId);
            setTimetable(data);
            setError(null);
        } catch (err) {
            setError('Failed to load timetable. Please try again.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    const handleCourseSelect = async (courseId: number): Promise<void> => {
        try {
            setLoading(true);
            setSelectedCourse(courseId);
            const data = await timetableService.getCourseStudents(courseId);
            setStudents(data.students || []);
            setError(null);
        } catch (err) {
            setError('Failed to load students. Please try again.');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="dashboard">
            <header className="app-header">
                <div>
                    <h1>📚 Course Timetable</h1>
                    <p>Select a domain to view its timetable and course details</p>
                </div>
                <button
                    onClick={onLogout}
                    className="logout-btn"
                >
                    Logout
                </button>
            </header>

            {error && (
                <div className="error-message">
                    <span>⚠️</span> {error}
                </div>
            )}

            <div className="main-container">
                <div className="left-panel">
                    <DomainSelector
                        domains={domains}
                        selectedDomain={selectedDomain}
                        onSelect={handleDomainSelect}
                        loading={loading}
                    />
                </div>

                <div className="right-panel">
                    {loading && !timetable && (
                        <div className="loading">Loading...</div>
                    )}

                    {timetable && (
                        <TimetableDisplay
                            timetable={timetable}
                            selectedCourse={selectedCourse}
                            onCourseSelect={handleCourseSelect}
                        />
                    )}

                    {selectedCourse && students.length > 0 && (
                        <StudentList
                            courseName={timetable?.courses.find(c => c.id === selectedCourse)?.name}
                            students={students}
                        />
                    )}
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
