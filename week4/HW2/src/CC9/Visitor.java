package CC9;

public interface Visitor {

    public void visit(Professor professor);

    public void visit(AdminStuff adminStuff);

    public void visit(Student student);


}
