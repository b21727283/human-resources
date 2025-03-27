package com.mgumussoy.humanresourcesbackend.repository;

import com.mgumussoy.humanresourcesbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
