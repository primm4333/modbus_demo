package com.example.modbus_demo.controller;

import com.example.modbus_demo.service.ModbusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modbus")
public class ModbusController {

    private final ModbusService modbusService;

    public ModbusController(ModbusService modbusService) {
        this.modbusService = modbusService;
    }

    /**
     * Initializes the Modbus router.
     */
    @GetMapping("/init")
    public String initializeModbusRouter() {
        return modbusService.initializeModbusRouter();
    }

    /**
     * Retrieves the connected IoT devices.
     */
    @GetMapping("/devices")
    public List<String> getConnectedDevices() {
        return modbusService.getConnectedDevices();
    }

    /**
     * Shuts down the Modbus service.
     */
    @GetMapping("/shutdown")
    public String shutdownModbus() {
        return modbusService.shutdownModbus();
    }
}

