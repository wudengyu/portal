package portal.business;

public class Employees {
    private int id;
    private String name;
    private String phone;
    private String username;

    public Employees(String name,String username){
        this.name=name;
        this.username=username;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }
}
