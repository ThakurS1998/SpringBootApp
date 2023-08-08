package in.sitesh.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sitesh.Entity.Employee;
import in.sitesh.Service.EmployeeService;

@RestController
@RequestMapping("/api/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		try {
			
			Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/bonus")
    public ResponseEntity<List<Employee>> getEmployeesEligibleForBonus(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<Employee> eligibleEmployees = employeeService.getEmployeesEligibleForBonus(date);
            return ResponseEntity.ok(eligibleEmployees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}







	
	
	








	
	

