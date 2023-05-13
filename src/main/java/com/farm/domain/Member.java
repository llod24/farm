package com.farm.domain;

public class Member {
	 private Long id;
	 private String username;
	 private String password;
	 private String email;
	 private String role;

    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Member(Long id, String username, String email, String role) {
		this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
    public Member(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public Member(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
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
