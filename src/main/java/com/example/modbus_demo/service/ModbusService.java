package com.example.modbus_demo.service;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ip.TcpParameters;
import com.serotonin.modbus4j.ModbusMaster;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ModbusService {

    private ModbusMaster modbusMaster;

    // This will set up a Modbus TCP connection to a server at IP 192.168.1.100
    public void initializeModbusRouter() {
        TcpParameters tcpParameters = new TcpParameters();
        tcpParameters.setHost("192.168.1.100");  // Modbus server IP
        tcpParameters.setPort(502);  // Default Modbus TCP port

        ModbusFactory modbusFactory = new ModbusFactory();
        modbusMaster = modbusFactory.createTcpMaster(tcpParameters, true);
        modbusMaster.setTimeout(5000);  // Set timeout in milliseconds

        try {
            modbusMaster.init();
            System.out.println("Modbus router initialized successfully.");
        } catch (Exception e) {
            System.err.println("Failed to initialize Modbus router: " + e.getMessage());
        }
    }

    public List<String> getConnectedDevices() {
        List<String> devices = new ArrayList<>();
        try {
            // For example, reading a register from a device
            int registerValue = modbusMaster.readDiscreteInput(1, 0);  // Slave ID = 1, Register = 0
            devices.add("Device 1 IP: 192.168.1.101, Register Value: " + registerValue);
        } catch (Exception e) {
            devices.add("Error while reading from Modbus router: " + e.getMessage());
        }

        return devices;
    }
}
