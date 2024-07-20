package pro.sky.EmployeeBook;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_EMPLOYEES = 15;
    private final List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Petr", "Petrov"),
            new Employee("Dima"," Dmitriev"),
            new Employee("Maxim", "Maximov")
    ));

    public String hello () {
        return "Добро пожаловать в программу!";
    }

    @Override
    public String addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        } else if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return "Сотрудник " + employee + " добавлен";
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employees.remove(employee);
        return "Сотрудник " + employee + " удален.";
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (!employees.contains(newEmployee)) {
               throw new EmployeeNotFoundException();
            }
         return "Сотрудник " + newEmployee + " найден";
    }

    @Override
    public String allEmployees() {
        for (Employee employees : employees) {
            System.out.println(employees);
        }
        return "Список распечатан: " + employees;
    }
}
