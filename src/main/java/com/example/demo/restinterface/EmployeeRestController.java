/**
 * 
 */
package com.example.demo.restinterface;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.EmployeeApplicatonService;
import com.example.demo.application.io.EmployeeIO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/**
 * Employee management services
 * 
 * @author KMaji
 *
 */
@RequestMapping("/employee/services")
@Api(value = "/employee/services", description = "Employee management services", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
public class EmployeeRestController {

	@Autowired
	EmployeeApplicatonService employeeService;

	@RequestMapping(value = "/listOfEmployee", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "All employee details", notes = "Avialable employees", response = EmployeeIO.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "basic"/* "security scope bounded to 'ROLE_USER' users " */) })
	Collection<EmployeeIO> getEmployees() {
		return employeeService.getEmployees();
	}

	@RequestMapping(value = "/employeeDetail", method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Employee detail by id", notes = "Employee detail", response = EmployeeIO.class, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	EmployeeIO getEmployee(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		return employeeService.getEmployee(firstName, lastName);
	}

	@RequestMapping(value = "/addEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add Employee", notes = "Employee add", response = EmployeeIO.class, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	EmployeeIO addEmployee(@RequestBody EmployeeIO employeeIO) {
		return employeeService.addEmployee(employeeIO);
	}

	@RequestMapping(value = "/deleteEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.DELETE })
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Employee deletion", notes = "Employee delete", authorizations = {
			@Authorization(value = "security scope bounded to 'ROLE_ADMIN' users ") })
	void deleteEmployee(@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName) {
		employeeService.deleteEmployee(firstName, lastName);
	}

	@RequestMapping(value = "/updateEmployee", method = {
			org.springframework.web.bind.annotation.RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Employee details update", notes = "present scope:	Only department	details updation", response = EmployeeIO.class, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {
			@Authorization(value = "security scope bounded to'ROLE_ADMIN'users") })
	EmployeeIO updateEmployee(@RequestBody EmployeeIO employeeIO) {
		return employeeService.updateEmployee(employeeIO);
	}

}
