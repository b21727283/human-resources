package com.mgumussoy.humanresourcesbackend.service;

import com.mgumussoy.humanresourcesbackend.dto.EmployeeDTO;
import com.mgumussoy.humanresourcesbackend.dto.NewEmployeeDTO;
import com.mgumussoy.humanresourcesbackend.dto.UpdateEmployeeDTO;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO createEmployee(NewEmployeeDTO employeeDTO) throws IOException;

    EmployeeDTO updateEmployee(Long employeeId, UpdateEmployeeDTO employeeDTO) throws IOException;

    EmployeeDTO deleteEmployee(Long id);

    EmployeeDTO getEmployee(Long id);

    byte[] getEmployeeCv(Long id);
}
