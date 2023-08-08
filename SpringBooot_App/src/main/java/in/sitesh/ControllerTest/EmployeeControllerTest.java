package in.sitesh.ControllerTest;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import in.sitesh.Controller.EmployeeController;
import in.sitesh.Entity.Employee;
import in.sitesh.Service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSalary(50000);
        employee.setJoiningDate(LocalDate.now());

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Employee> responseEntity = employeeController.saveEmployee(employee);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Employee savedEmployee = responseEntity.getBody();
        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getName());
        assertEquals(50000, savedEmployee.getSalary(), 0.01);
        assertEquals(LocalDate.now(), savedEmployee.getJoiningDate());
    }

    @Test
    public void testGetEmployeesEligibleForBonus() {
        Employee employee1 = new Employee();
        employee1.setName("Alice");
        employee1.setSalary(60000);
        employee1.setJoiningDate(LocalDate.now().minusYears(1)); // Eligible for bonus

        Employee employee2 = new Employee();
        employee2.setName("Bob");
        employee2.setSalary(40000);
        employee2.setJoiningDate(LocalDate.now().minusMonths(6)); // Not eligible for bonus

        List<Employee> eligibleEmployees = Collections.singletonList(employee1);

        when(employeeService.getEmployeesEligibleForBonus(any(LocalDate.class))).thenReturn(eligibleEmployees);

        ResponseEntity<List<Employee>> responseEntity = employeeController.getEmployeesEligibleForBonus(LocalDate.now());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Employee> responseList = responseEntity.getBody();
        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals("Alice", responseList.get(0).getName());
    }
}
