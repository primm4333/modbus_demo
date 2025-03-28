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

    @GetMapping("/init")
    public String initializeModbusRouter() {
        modbusService.initializeModbusRouter();
        return "Modbus router initialized successfully.";
    }

    @GetMapping("/devices")
    public List<String> getConnectedDevices() {
        return modbusService.getConnectedDevices();
    }

    @GetMapping("/shutdown")
    public String shutdownModbus() {
        // Implement shutdown logic if necessary.
        return "Modbus service has been stopped.";
    }
}
