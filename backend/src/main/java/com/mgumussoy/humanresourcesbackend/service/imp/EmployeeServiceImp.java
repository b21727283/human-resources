package com.mgumussoy.humanresourcesbackend.service.imp;

import com.mgumussoy.humanresourcesbackend.dto.EmployeeDTO;
import com.mgumussoy.humanresourcesbackend.dto.NewEmployeeDTO;
import com.mgumussoy.humanresourcesbackend.dto.UpdateEmployeeDTO;
import com.mgumussoy.humanresourcesbackend.entity.Employee;
import com.mgumussoy.humanresourcesbackend.exception.CVNotFoundException;
import com.mgumussoy.humanresourcesbackend.exception.EmployeeNotFoundException;
import com.mgumussoy.humanresourcesbackend.repository.EmployeeRepository;
import com.mgumussoy.humanresourcesbackend.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public EmployeeDTO createEmployee(NewEmployeeDTO employeeDTO) throws IOException {
        if (employeeDTO.getCv() == null) {
            throw new CVNotFoundException();
        }
        Employee newEmployeeEntity = modelMapper.map(employeeDTO, Employee.class);
        newEmployeeEntity.setCv(employeeDTO.getCv().getBytes());
        employeeRepository.save(newEmployeeEntity);
        return modelMapper.map(newEmployeeEntity, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Long employeeId, UpdateEmployeeDTO employeeDTO) throws IOException {
        Employee oldEmployeeEntity = employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        Employee targetEmployee = modelMapper.map(employeeDTO, Employee.class);
        targetEmployee.setId(employeeId);
        if (employeeDTO.getCv() != null) {
            targetEmployee.setCv(employeeDTO.getCv().getBytes());
        } else {
            targetEmployee.setCv(oldEmployeeEntity.getCv());
        }
        employeeRepository.save(targetEmployee);
        return modelMapper.map(targetEmployee, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public EmployeeDTO deleteEmployee(Long id) {
        Employee targetEmployee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        employeeRepository.deleteById(id);
        return modelMapper.map(targetEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public byte[] getEmployeeCv(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        if (employee.getCv() == null) {
            throw new EmployeeNotFoundException();
        }
        return employee.getCv();
    }
}
