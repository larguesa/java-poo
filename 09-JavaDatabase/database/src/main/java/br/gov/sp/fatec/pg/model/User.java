package br.gov.sp.fatec.pg.model;

/* Modelo de usuário para representar os dados do usuário. Padrão DAO/Repository.*/
public class User {
    
    private Integer id;
    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "User{username='" + username + "'}"; // Nunca inclua a senha no toString!
    }
}