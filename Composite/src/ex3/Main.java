package ex3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

interface Worker {
    void assignWork(Employee manager, Work work);

    void performWork();
}

class Work {
    private Calculator workType;
    private List<String> work = new ArrayList<String>();

    public Work(Calculator workType, List<String> work) {

        super();

        this.workType = workType;

        this.work = work;

    }

    public Calculator getWorkType() {
        return workType;
    }

    public void setWorkType(Calculator workType) {
        this.workType = workType;
    }

    public List<String> getWork() {
        return work;
    }

    public void setWork(List<String> work) {
        this.work = work;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Work [workType=").append(workType).append(", work=").append(work).append("]");
        return builder.toString();
    }
}

enum Calculator {
    FACTORIAL {
        @Override
        public String calculate(String value) {
            String answer = "NA";
            try {
                long longValue = Long.parseLong(value);
                BigInteger factorialValue = BigInteger.valueOf(1);
                for (long i = 1; i <= longValue; i++) {
                    factorialValue = factorialValue.multiply(BigInteger.valueOf(i));
                }
                answer = factorialValue.toString();
            } catch (NumberFormatException exp) {
                System.out.println("Can't calculate factorial of " + value);
            }
            return answer;
        }
    },
    PALINDROME {
        @Override
        public String calculate(String value) {
            String answer = "false";
            if (value != null && !value.trim().isEmpty()) {
                String reverse = (new StringBuilder(value).reverse().toString());
                answer = Boolean.toString(reverse.equals(value));
            }
            return answer;
        }
    },
    ARMSTRONG {
        @Override
        public String calculate(String value) {
            String answer = "false";
            try {
                long longValue = Long.parseLong(value);
                long number = longValue;
                long armstrongValue = 0;
                while (number != 0) {
                    long temp = number % 10;
                    armstrongValue = armstrongValue + temp * temp * temp;
                    number /= 10;
                }
                answer = Boolean.toString(String.valueOf(armstrongValue).equals(value));
            } catch (NumberFormatException exp) {
                System.out.println("Can't calculate armstrong of " + value);
            }
            return answer;
        }
    };

    public abstract String calculate(String value);
}

abstract class Employee implements Worker {
    protected long employeeId;
    protected String employeeName;
    protected String designation;

    public Employee(long employeeId, String employeeName, String designation) {
        super();
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public abstract int teamSize();

    public String fullDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee [").append(employeeId).append(", ").append(employeeName).append(", ")
                .append(designation).append(", ").append(", Team=").append(teamSize()).append("]");
        return builder.toString();
    }

    public String shortDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("'").append(employeeName).append("'");
        return builder.toString();
    }

    @Override
    public String toString() {
        return shortDetails();
    }
}

class Engineer extends Employee {
    private List<Work> works = new ArrayList<Work>();

    public Engineer(long employeeId, String employeeName, String designation) {
        super(employeeId, employeeName, designation);
    }

    @Override
    public int teamSize() {
        return 1;
    }

    @Override
    public void assignWork(Employee manager, Work work) {
        this.works.add(work);
        System.out.println(this + " has assigned work of '" + work + "' by manager " + manager);
    }

    @Override
    public void performWork() {
        System.out.println(this + " is performing work of '" + works + "'");
        works.stream().forEach(work -> {
            work.getWork().stream().forEach(value -> {
                Calculator calculator = work.getWorkType();
                System.out.println(this + " has result of work of '" + work + "' as : " + calculator.calculate(value));
            });
        });
        works.clear();
    }
}

class Manager extends Employee {
    List<Employee> managingEmployees = new ArrayList<Employee>();

    public Manager(long employeeId, String employeeName, String designation) {
        super(employeeId, employeeName, designation);
    }

    public boolean manages(Employee employee) {
        return managingEmployees.add(employee);
    }

    public boolean stopManaging(Employee employee) {
        return managingEmployees.remove(employee);
    }

    @Override
    public int teamSize() {
        return managingEmployees.stream().mapToInt(employee -> employee.teamSize()).sum();
    }

    @Override
    public void assignWork(Employee manager, Work work) {
        System.out.println(this + " has assigned work of '" + work + "' by manager " + manager);
        System.out.println();
        System.out.println(this + " distributing work '" + work + "' to managing-employees..");
        int fromIndex = 0;
        int toIndex = 0;
        int totalWork = work.getWork().size();
        List<String> assignWork = null;
        while (toIndex < totalWork) {
            for (Employee employee : managingEmployees) {
                System.out.println("Assigning to " + employee);
                int size = employee.teamSize();
                toIndex = fromIndex + size;
                assignWork = work.getWork().subList(fromIndex, toIndex);
                if (assignWork.isEmpty()) {
                    return;
                }
                employee.assignWork(this, new Work(work.getWorkType(), assignWork));
                fromIndex = toIndex;
            }
            break;
        }
    }

    @Override
    public void performWork() {

        System.out.println(this + " is asking his managing employees to perform assigned work");

        System.out.println();
        managingEmployees.stream().forEach(employee -> employee.performWork());
        System.out.println();
        System.out.println(this + " has completed assigned work with the help of his managing employees");

        System.out.println();
    }
}

class WorkLoader {

    protected Properties properties = new Properties();

    public WorkLoader(String fileName) {

    }

    public Properties getProperties() {
        return properties;
    }

    public List<Work> getWorkList() {
        List<Work> workList = new ArrayList<Work>();
        Set<Object> keys = properties.keySet();
        for (Object key : keys) {

            String workType = key.toString().substring("Calculate".length() + 1).toUpperCase();
            String values = properties.getProperty(key.toString());
            Work work = new Work(Calculator.valueOf(workType), Arrays.asList(values.split(", ")));
            workList.add(work);
        }
        return workList;
    }
}

public class Main {
    public static void main(String[] args) {
//        Calculate.Palindrome = 1234321, 12341234, ABCDEDCBA, 4567887654, XYZZYX, 45676543, 3456543;
//        Calculate.Armstrong = 153, 8208, 2104, 4210818, 345321, 32164049651, 876412347;
//        Calculate.Factorial = 20, 43, 15, 120, 543, 35, 456, 432, 350, 44, 26, 17, 8;

        Engineer ajay = new Engineer(1001l, "Ajay", "Developer");
        Engineer vijay = new Engineer(1002l, "Vijay", "SR. Developer");
        Engineer jay = new Engineer(1003l, "Jay", "Lead");
        Engineer martin = new Engineer(1004l, "Martin", "QA");
        Manager kim = new Manager(1005l, "Kim", "Manager");
        Engineer anders = new Engineer(1006l, "Andersen", "Developer");
        Manager niels = new Manager(1007l, "Niels", "Sr. Manager");
        Engineer robert = new Engineer(1008l, "Robert", "Developer");
        Manager rachelle = new Manager(1009l, "Rachelle", "Product Manager");
        Engineer shailesh = new Engineer(1010l, "Shailesh", "Engineer");
        kim.manages(ajay);
        kim.manages(martin);
        kim.manages(vijay);
        niels.manages(jay);
        niels.manages(anders);
        niels.manages(shailesh);
        rachelle.manages(kim);
        rachelle.manages(robert);
        rachelle.manages(niels);
        WorkLoader workLoad = new WorkLoader("work.properties");
        workLoad.getWorkList().stream().forEach(work -> {
            rachelle.assignWork(rachelle, work);
        });
        rachelle.performWork();
    }
}



