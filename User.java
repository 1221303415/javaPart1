public class User {
    private int id;
    private String name;
    private String userType;

    public User(int id, String name, String userType){
        this.id = id;
        this.name = name;
        this.userType = userType;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return name;
    }
    public String getUserType() {
        return userType;
    }
}
