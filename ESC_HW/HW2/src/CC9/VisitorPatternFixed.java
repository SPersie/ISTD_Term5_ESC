package CC9;

import java.util.ArrayList;

public class VisitorPatternFixed {

    public static void main(String[] args){
        SUTD oneSUTD = new SUTD ();

        ArrayList<Employee> employee = oneSUTD.getEmployee();
        Auditing auditor =new Auditing();

        //auditing
        for (Employee e: employee) {
            e.accept(auditor);
        }
    }
}

class SUTD {
    private ArrayList<Employee> employee;

    public SUTD () {
        employee = new ArrayList<Employee>();
        employee.add(new Professor("Sun Jun ", 0));
        employee.add(new AdminStuff("Stacey ", 5));
        employee.add(new Student("Allan ", 3));
    }

    public ArrayList<Employee> getEmployee () {
        return employee;
    }
}

class Auditing implements Visitor {

    @Override
    public void visit(Professor professor) {
        System.out.println("Prof: " + professor.getName() + professor.getNo_of_publications());

    }

    @Override
    public void visit(AdminStuff adminStuff) {
        System.out.println("Admin: " +adminStuff.getName() +adminStuff.getEfficiency());
    }

    @Override
    public void visit(Student student) {
        System.out.println("Student: " +student.getName() +student.getGPA());
    }
}

class Employee implements Visitable {

    @Override
    public void accept(Visitor visitor) {

    }
}

class Professor extends Employee {
    private String name;
    private int no_of_publications;

    public Professor (String name, int no_of_publications) {
        this.name = name;
        this.no_of_publications = no_of_publications;
    }

    public String getName () {
        return name;
    }

    public int getNo_of_publications() {
        return no_of_publications;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class AdminStuff extends Employee {
    private String name;
    private float efficiency;

    public AdminStuff (String name, float efficiency) {
        this.name = name;
        this.efficiency = efficiency;
    }

    public String getName() {
        return name;
    }

    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Student extends Employee {
    private String name;
    private float GPA;

    public Student (String name, float GPA) {
        this.name = name;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public float getGPA() {
        return GPA;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
