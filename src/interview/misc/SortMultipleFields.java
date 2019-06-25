package interview.misc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortMultipleFields {

  /**
   * Sort List<Employee> objects with name first and then price.
   */
  static class Employee {

    String name;

    BigDecimal price;

    Employee(String employee, BigDecimal price) {
      this.name = employee;
      this.price = price;
    }

    public String getEmployee() {
      return name;
    }

    public BigDecimal getPrice() {
      return price;
    }

    @Override
    public String toString() {
      return this.name + "->" + this.price;
    }
  }

  public static void main(String[] args) {
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee("Anba", BigDecimal.valueOf(10000)));
    employeeList.add(new Employee("Ananya", BigDecimal.valueOf(30000)));
    employeeList.add(new Employee("Lavs", BigDecimal.valueOf(7500)));
    employeeList.add(new Employee("Rihaan", BigDecimal.valueOf(7500)));
    employeeList.add(new Employee("Aarvik", BigDecimal.valueOf(10000)));

    Collections.sort(employeeList, Comparator.comparing(Employee::getEmployee).reversed());
    System.out.println(employeeList.toString());

    Collections.sort(employeeList, Comparator.comparing(Employee::getPrice));
    System.out.println(employeeList.toString());

    /**
     * Sort Map objects by value
     */
    Map<String, BigDecimal> map = new HashMap<>();
    map.put("Anba", BigDecimal.valueOf(10000));
    map.put("Ananya", BigDecimal.valueOf(30000));
    map.put("Lavs", BigDecimal.valueOf(7500));
    map.put("Rihaan", BigDecimal.valueOf(7500));
    map.put("Aarvik", BigDecimal.valueOf(10000));

    Map<String, BigDecimal> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    System.out.println(sortedMap.toString());
  }
}
