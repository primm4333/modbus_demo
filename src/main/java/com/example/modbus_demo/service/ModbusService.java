package com.example.modbus_demo.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModbusService {

    private boolean isInitialized = false;

    /**
     * Simulates initializing a Modbus router and returning its IP address.
     */
    public String initializeModbusRouter() {
        if (isInitialized) {
            return "Modbus router is already initialized. IP: 192.168.1.100";
        }

        isInitialized = true;
        return "Modbus router initialized successfully. IP: 192.168.1.100";
    }

    /**
     * Simulates discovering IoT devices connected to the Modbus router.
     */
    public List<String> getConnectedDevices() {
        List<String> devices = new ArrayList<>();

        if (!isInitialized) {
            devices.add("Modbus service is not initialized.");
            return devices;
        }

        // Simulated list of connected devices
        devices.add("Device ID: 1, IP: 192.168.1.101");
        devices.add("Device ID: 2, IP: 192.168.1.102");
        devices.add("Device ID: 3, IP: 192.168.1.103");

        return devices;
    }

    /**
     * Resets the simulation (shutdown).
     */
    public String shutdownModbus() {
        if (!isInitialized) {
            return "Modbus service is already stopped.";
        }

        isInitialized = false;
        return "Modbus service has been stopped.";
    }
}

