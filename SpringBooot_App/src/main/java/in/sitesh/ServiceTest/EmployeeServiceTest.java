package in.sitesh.ServiceTest;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSalary(50000);
        employee.setJoiningDate(LocalDate.now());

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

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

        List<Employee> allEmployees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(allEmployees);

        List<Employee> eligibleEmployees = employeeService.getEmployeesEligibleForBonus(LocalDate.now());

        assertEquals(1, eligibleEmployees.size());
        assertEquals("Alice", eligibleEmployees.get(0).getName());
    }
}
