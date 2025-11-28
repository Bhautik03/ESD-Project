import axios, { AxiosInstance } from 'axios';
import {
  DomainSummary,
  DomainTimetableResponse,
  CourseStudentsResponse,
} from '../types';

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api/timetable';

const apiClient: AxiosInstance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true, // Important: send cookies with requests
});

export const timetableService = {
  getAllDomains: async (): Promise<DomainSummary[]> => {
    const response = await apiClient.get<DomainSummary[]>('/domains');
    return response.data;
  },

  getDomainTimetable: async (domainId: number): Promise<DomainTimetableResponse> => {
    const response = await apiClient.get<DomainTimetableResponse>(`/domains/${domainId}`);
    return response.data;
  },

  getCourseStudents: async (courseId: number): Promise<CourseStudentsResponse> => {
    const response = await apiClient.get<CourseStudentsResponse>(`/courses/${courseId}/students`);
    return response.data;
  },
};

export default apiClient;

