package edu.eci.TaskPlanner.entities;

public class User {

	private int id;
	private byte[] avatar;
	private String name;
    private String username;
    private String email;
    private String password;
    
    public User() {}

	public User(int id, byte[] avatar, String name, String username, String email, String password) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
