package net.securespringapp;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 45)
    private String email;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 255)
    private String text;
    @Column(length = 200)
    private String password;

    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    private boolean shared;
    private boolean encrypted;

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }






    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public boolean getShared() {
        return shared;
    }

    public void setShared(boolean aPublic) {
        shared = aPublic;
    }
}
