package org.example;

public class DatabaseSingleton {
    /**
     * Single instance of OrdersStore
     */
    private static Database instance;

    /**
     * Private constructor
     */
    private DatabaseSingleton() {
    }

    /**
     * Get single instance of OrdersStore
     *
     * @return single instance of OrdersStore
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
