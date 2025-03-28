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

    @GetMapping("/read-register")
    public String readHoldingRegister(@RequestParam int slaveId, @RequestParam int registerAddress) {
        int value = modbusService.readHoldingRegister(slaveId, registerAddress);
        if (value == -1) {
            return "Failed to read register " + registerAddress + " from slave " + slaveId;
        }
        return "Register " + registerAddress + " from slave " + slaveId + " has value: " + value;
    }

    @GetMapping("/shutdown")
    public String shutdownModbus() {
        try {
            if (modbusService != null) {
                return "Modbus service has been stopped.";
            } else {
                return "Modbus service was not running.";
            }
        } catch (Exception e) {
            return "Error shutting down Modbus service: " + e.getMessage();
        }
    }
}
