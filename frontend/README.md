# Course Timetable Frontend

React + TypeScript frontend application for the Course Timetable Management System.

## Features

- 📚 Display all available domains (MTech CSE, MTech ECE, MTech AIDS)
- 📅 View timetable for selected domain with courses, schedules, and faculty
- 👥 View enrolled students for any course
- 🎨 Modern, responsive UI design

## Prerequisites

- Node.js (v14 or higher)
- npm or yarn
- TypeScript (installed automatically with dependencies)
- Backend API running on http://localhost:8080

## Installation

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

## Running the Application

1. Make sure the Spring Boot backend is running on port 8080

2. Start the React development server:
```bash
npm start
```

3. Open [http://localhost:3000](http://localhost:3000) in your browser

## Usage

1. **Select a Domain**: Click on any domain from the left panel (MTech CSE, MTech ECE, or MTech AIDS)

2. **View Timetable**: The timetable will display all courses for the selected domain, including:
   - Course name and code
   - Schedule (day, time, room, building)
   - Faculty members

3. **View Students**: Click on any course card to view the list of enrolled students

## Project Structure

```
frontend/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── DomainSelector.tsx
│   │   ├── TimetableDisplay.tsx
│   │   └── StudentList.tsx
│   ├── services/
│   │   └── api.ts
│   ├── types/
│   │   └── index.ts
│   ├── App.tsx
│   ├── App.css
│   ├── index.tsx
│   ├── index.css
│   └── react-app-env.d.ts
├── tsconfig.json
├── package.json
└── README.md
```

## API Endpoints

The frontend communicates with the backend API:
- `GET /api/timetable/domains` - Get all domains
- `GET /api/timetable/domains/{id}` - Get timetable for a domain
- `GET /api/timetable/courses/{id}/students` - Get students for a course

## Build for Production

```bash
npm run build
```

This creates an optimized production build in the `build` folder.

