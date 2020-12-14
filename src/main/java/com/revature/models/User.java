package com.revature.models;

public abstract class User implements Displayable {
	
	protected final int user_id;
	protected String password;
	
	
	// constructors
	public User(int user_id, String password) {
		this.user_id = user_id;
		this.password = password;
	}
	
	
	// getters and setters
	public int GetUserId() {
		return this.user_id;
	}
	
	// note: I may want to protect access to passwords
	public String GetPassword() {
		return this.password;
	}
	
	public void SetPassword(String nextPassword) {
		this.password = nextPassword;
	}

	
	// overridden default methods
	public String Display() {
		return "ID: " + this.user_id;
	}
	
	@Override
	public String toString() {
		return "User [ID=" + user_id + "]";
	}


	// note: at the moment an id is the only thing equality is based on 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + user_id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (user_id != other.user_id)
			return false;
		return true;
	}	
	
}
