package in.sitesh.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sitesh.Entity.Employee;
import in.sitesh.Repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesEligibleForBonus(LocalDate date) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> isEligibleForBonus(employee, date))
                .collect(Collectors.toList());
    }

    private boolean isEligibleForBonus(Employee employee, LocalDate date) {
		return true;
       
    }
}
