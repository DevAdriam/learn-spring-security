package com.ecommerce.Ecommerce.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;

    @Column(name = "email")
    private  String email;

    @Column(name  = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id",referencedColumnName = "id")
    private Transaction transaction;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getUsername() {
        return username;
    }



}
