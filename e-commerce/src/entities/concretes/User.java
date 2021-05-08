package entities.concretes;

import java.util.Date;

import entities.abstracts.Entity;

public class User implements Entity<User> {
	private int id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private boolean mailConfirmed = false;
	private Date lastLoginTime;
	private boolean active = true;

	public User() {

	}

	public User(int id, String name, String surname, String email, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public boolean getMailConfirmed() {
		return mailConfirmed;
	}

	public void setMailConfirmed(boolean mailConfirmed) {
		this.mailConfirmed = mailConfirmed;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void clone(User entity) {
		this.id = entity.id;
		this.name = entity.name;
		this.surname = entity.surname;
		this.email = entity.email;
		this.password = entity.password;
		this.lastLoginTime = entity.lastLoginTime;
		this.mailConfirmed = entity.mailConfirmed;
		this.active = entity.active;
	}

}
