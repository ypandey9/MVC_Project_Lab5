package com.gl.EMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.EMS.entity.Employee;
import com.gl.EMS.service.EmployeeService;

@Controller
public class EmployeeController {

	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//handling list of employees and return model and view
	
	@GetMapping("/employees")
	
	public String listEmployees(Model model)
	{
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employees";
	}
	
	//for calling create_employee.html
	@GetMapping("/employees/new")
	public String createEmployeeForm(Model model)
	{
		Employee employee =new Employee();
		model.addAttribute("employee", employee);
		return "create_employee";
	}
	
	//for saving new or updated employee
	
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id,Model model)
	{
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "edit_employee";
	}
	
	@PostMapping("/employees/{id}")
	public String editEmployee(@PathVariable Long id,@ModelAttribute("employee") Employee employee,Model model)
	{
		Employee existingEmployee=employeeService.getEmployeeById(id);
		existingEmployee.setId(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		employeeService.updateEmployee(existingEmployee);
		return "redirect:/employees";
		
	}
	
	@GetMapping("/employees/{id}")
	public String dropEmployee(@PathVariable Long id)
	{
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}
	
}
