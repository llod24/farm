package com.farm.domain;

public class Member {
	 private Long id;
	 private String username;
	 private String password;
	 private String email;

    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Member(String password, String email) {
        this.password = password;
        this.email = email;
    }
    

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
