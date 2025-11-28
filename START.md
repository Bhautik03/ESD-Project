# Course Timetable - Quick Start Guide

## 🚀 Running the Application

### Step 1: Start the Backend (Spring Boot)

Open a terminal in the project root and run:

```powershell
.\mvnw.cmd spring-boot:run
```

Wait for the message:
```
╔══════════════════════════════════════════════════════════════════════╗
║                    APPLICATION IS READY!                            ║
╚══════════════════════════════════════════════════════════════════════╝
```

The backend will be running on: **http://localhost:8080**

### Method 2: Build and Run JAR (Alternative)

1. **Build the project:**
   ```powershell
   .\mvnw.cmd clean package
   ```

2. **Run the JAR:**
   ```powershell
   java -jar target\demo-0.0.1-SNAPSHOT.jar
   ```

### What Happens During Startup

1. ✅ Spring Boot initializes
2. ✅ Connects to MySQL database
3. ✅ Creates database schema (if not exists)
4. ✅ Loads sample data from `data.sql`
5. ✅ Starts Tomcat server on port 8080
6. ✅ Displays "APPLICATION IS READY!" message

### Step 2: Start the Frontend (React)

Open a **NEW** terminal window and run:

```powershell
cd frontend
npm start
```

The React app will automatically open in your browser at: **http://localhost:3000**

## 📋 Prerequisites

- ✅ Java 17 or higher
- ✅ MySQL database running
- ✅ Node.js and npm installed (for React frontend)
- ✅ MySQL credentials configured in `application.properties`

## 🎯 Features

1. **Domain Selection**: Choose from available domains (MTech CSE, MTech ECE, MTech AIDS)
2. **Timetable View**: See all courses with:
   - Course schedules (day, time, room, building)
   - Faculty members for each course
3. **Student List**: Click any course to view enrolled students

## 🔧 Troubleshooting

### Port 8080 already in use
```powershell
# Find the process
netstat -ano | findstr :8080

# Kill the process (replace PID with actual process ID)
taskkill /F /PID <PID>
```

### Frontend can't connect to backend
- Make sure backend is running on port 8080
- Check CORS configuration in `CorsConfig.java`
- Verify backend URL in `frontend/src/services/api.js`

### Database connection issues
- Ensure MySQL is running
- Check credentials in `src/main/resources/application.properties`
- Verify database `course_timetable` exists or will be created

## 📁 Project Structure

```
demo/
├── src/main/java/          # Spring Boot backend
│   └── com/example/demo/
│       ├── controller/     # REST controllers
│       ├── service/         # Business logic
│       ├── repository/      # Data access
│       └── entity/          # Database entities
├── frontend/               # React frontend
│   ├── src/
│   │   ├── components/    # React components
│   │   └── services/      # API service
│   └── package.json
```

## 🌐 API Endpoints

- `GET /api/timetable/domains` - Get all domains
- `GET /api/timetable/domains/{id}` - Get timetable for domain
- `GET /api/timetable/courses/{id}/students` - Get students for course

## 📝 Notes

- Backend runs on port **8080**
- Frontend runs on port **3000**
- Database: `course_timetable` (auto-created if not exists)
- Sample data is loaded automatically from `data.sql`

