package com.mgumussoy.humanresourcesbackend.controller;

import com.mgumussoy.humanresourcesbackend.dto.EmployeeDTO;
import com.mgumussoy.humanresourcesbackend.dto.NewEmployeeDTO;
import com.mgumussoy.humanresourcesbackend.dto.UpdateEmployeeDTO;
import com.mgumussoy.humanresourcesbackend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:8100")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EmployeeDTO> createEmployee(@ModelAttribute @Valid NewEmployeeDTO employeeDTO) throws IOException {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @PutMapping(value = "/update/{employeeId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EmployeeDTO> updateEmployee(@ModelAttribute @Valid UpdateEmployeeDTO employeeDTO, @PathVariable Long employeeId) throws IOException {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
    }

    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> getEmployeeCv(@PathVariable Long id) {
        byte[] employeeCV = employeeService.getEmployeeCv(id);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv");
        return new ResponseEntity<>(employeeCV, headers, HttpStatus.OK);
    }

}
