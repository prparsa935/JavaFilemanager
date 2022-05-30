package usermodel.userent;



public class UserEnt {
    private Long Id;

    public Long getId() {
        return Id;
    }

    public UserEnt setId(Long id) {
        Id = id;
        return this;
    }

    private String name;
    private String username;
    private String password;
    private String email;

    public String getName() {
        return name;
    }

    public UserEnt setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEnt setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEnt setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEnt setEmail(String email) {
        this.email = email;
        return this;
    }
}
