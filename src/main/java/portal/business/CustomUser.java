package portal.business;

public class CustomUser{
    private final String username;
    private final String password;

    public CustomUser(String username,String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
}
