package com.taskScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class App 
{ private static final Logger logger = Logger.getLogger(App.class.getName());

public static void main(String[] args) {
    // Create a scheduled executor service with a single thread
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    // Define the task to be scheduled
    Runnable task = () -> {
        // Your task logic goes here
        logger.log(Level.INFO, "Executing scheduled task at: {0}", System.currentTimeMillis());
        System.out.println("Executing scheduled task at: "+System.currentTimeMillis());
    };

    // Schedule the task to run every 1 minute
    scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);

    // Optionally, you can also schedule the task to run after an initial delay
    // scheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MINUTES);

    // Add a shutdown hook to gracefully shutdown the scheduler when the application exits
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        scheduler.shutdown();
        logger.log(Level.INFO, "Scheduler has been shut down.");
        System.out.println("Scheduler has been shut down: "+System.currentTimeMillis());
    }));
}
}
