package iitu.kz.product.entity;

public class User {
    private Long id;
    private String login;
    private String password;
    private String fullName;
    private String picture;

    public User(Long id, String login, String password, String fullName, String picture) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.picture = picture;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
