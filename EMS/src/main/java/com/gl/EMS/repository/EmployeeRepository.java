package com.gl.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.EMS.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
