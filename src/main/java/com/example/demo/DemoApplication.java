package com.example.demo;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class DemoApplication {

	private final CopyOnWriteArrayList<Employee> employees = new CopyOnWriteArrayList<>();

	public DemoApplication() {
		employees.add(new Employee(1, "Josh", "dev"));
		employees.add(new Employee(2, "Rev", "qa"));
		employees.add(new Employee(3, "Kaustuv", "dev"));
		employees.add(new Employee(4, "Sam", "Hr"));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {

		StringBuffer welcome = new StringBuffer();
		welcome.append("<center><h1>Welcome to Spring boot example. !!!</h1></center></br>");
		welcome.append(" This poc will cover spring boot app with scrud examples. </br>");
		welcome.append(" Default media type is jason </br>");
		welcome.append("List of services and urls are as following </br>");
		welcome.append("<ul>");
		welcome.append("<li> List of Employees url will be  Example of Read -> /listOfEmployee </li>");
		welcome.append("<li> Employee Detail url will be id match Example of Read -> /employeeDetail </li>");
		welcome.append("<li> Add employee  url will be id match Example of Create-> /addEmployee </li>");
		welcome.append("<li> Add employee  url will be id match Example of Update-> /updateEmployee </li>");
		welcome.append("<li> Add employee  url will be id match Example of delete-> /deleteEmployee </li>");
		welcome.append("</ul>");
		return welcome.toString();
	}

	@RequestMapping(value = "/listOfEmployee")
	@ResponseBody
	CopyOnWriteArrayList<Employee> getEmployees(Integer id) {
		return employees;
	}

	@RequestMapping(value = "/employeeDetail", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	Employee getEmployee(@RequestParam("id") Integer id) {
		Employee emp = null;

		for (Employee each : employees) {
			if (id.equals(each.id)) {
				emp = each;
			}
		}

		return emp;
	}

	@RequestMapping(value = "/addEmployee", method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	Employee getEmployees1(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam(value = "department", required = false, defaultValue = "dev") String department) {
		Employee emp = new Employee(id, name, department);
		employees.add(emp);
		return emp;
	}

	@RequestMapping(value = "/deleteEmployee", method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	Employee deleteEmployee(@RequestParam("id") Integer id,
			@RequestParam(value = "name", required = false) String name) {

		for (Employee emp : employees) {
			if (null != emp) {
				if (id.equals(emp.getId()) || name.equalsIgnoreCase(emp.getName())) {
					employees.remove(emp);
					return emp;

				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/updateEmployee", method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	Employee updateEmployee(@RequestParam("id") Integer id, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "department", required = true) String department) {
		for (Employee emp : employees) {
			if (emp != null) {
				if (id.equals(emp.getId()) || name.equalsIgnoreCase(emp.getName())) {
					emp.setDepartment(department);
					return emp;
				}
			}
		}
		return null;
	}
}

class Employee implements Serializable {

	private static final long serialVersionUID = 7098286166079680079L;

	public Employee(Integer id, String name, String department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

	Integer id;
	String name;
	String department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}