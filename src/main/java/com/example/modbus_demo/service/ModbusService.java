package com.example.modbus_demo.service;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.code.DataType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModbusService {

    private ModbusMaster modbusMaster;

    // Initialize Modbus TCP connection
    public void initializeModbusRouter() {
        IpParameters ipParameters = new IpParameters();
        ipParameters.setHost("192.168.1.100");  // Modbus device IP
        ipParameters.setPort(502);  // Default Modbus TCP port
        ipParameters.setEncapsulated(false); // Set to false for standard Modbus TCP

        ModbusFactory modbusFactory = new ModbusFactory();
        modbusMaster = modbusFactory.createTcpMaster(ipParameters, true);
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
            // Create a locator for a discrete input (coil)
            BaseLocator<Boolean> locator = BaseLocator.coilStatus(1, 0);  // Slave ID = 1, Register = 0

            // Read value using the locator
            boolean registerValue = modbusMaster.getValue(locator);

            devices.add("Device 1 IP: 192.168.1.101, Register Value: " + registerValue);
        } catch (Exception e) {
            devices.add("Error while reading from Modbus router: " + e.getMessage());
        }

        return devices;
    }

    // Example: Reading a holding register (if needed)
    public int readHoldingRegister(int slaveId, int registerAddress) {
        try {
            // Create locator for a holding register (16-bit integer)
            BaseLocator<Number> locator = BaseLocator.holdingRegister(slaveId, registerAddress, DataType.TWO_BYTE_INT_SIGNED);

            // Read the register value
            return (int) modbusMaster.getValue(locator);
        } catch (Exception e) {
            System.err.println("Error reading holding register: " + e.getMessage());
            return -1; // Return an error value
        }
    }
}
