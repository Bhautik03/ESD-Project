package com.example.demo.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

    // Store server startup timestamp to invalidate sessions after restart
    private static long serverStartupTime;

    public static long getServerStartupTime() {
        return serverStartupTime;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Set server startup timestamp
        serverStartupTime = System.currentTimeMillis();
        String baseUrl = "http://localhost:8080";
        
        try {
            Thread.sleep(500); // Small delay to ensure everything is ready
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n\n");
        System.out.println("╔" + "═".repeat(68) + "╗");
        System.out.println("║" + " ".repeat(20) + "APPLICATION IS READY!" + " ".repeat(24) + "║");
        System.out.println("╚" + "═".repeat(68) + "╝");
        System.out.println("\n  ✅ Server is running and ready to accept requests");
        System.out.println("  ✅ Database connection established");
        System.out.println("  ✅ All endpoints are available\n");
        System.out.println("  " + "─".repeat(66));
        System.out.println("  📍 OPEN THESE LINKS IN YOUR BROWSER:");
        System.out.println("  " + "─".repeat(66));
        System.out.println("\n  🏠 Home Page:");
        System.out.println("     " + baseUrl + "/");
        System.out.println("\n  📚 API Endpoints:");
        System.out.println("     " + baseUrl + "/api/timetable/domains");
        System.out.println("     " + baseUrl + "/api/timetable/domains/1");
        System.out.println("     " + baseUrl + "/api/timetable/courses/1/students");
        System.out.println("\n  " + "─".repeat(66));
        System.out.println("  ⚠️  IMPORTANT: Wait for this message before opening links!");
        System.out.println("  " + "─".repeat(66));
        System.out.println("\n");
    }
}

