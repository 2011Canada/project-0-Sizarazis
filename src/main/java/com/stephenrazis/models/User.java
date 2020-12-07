package com.stephenrazis.models;

public abstract class User {
	
	String id;
	String password;
	int accessLevel;
	
	
	// constructors
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	
	// getters and setters
	public String GetId() {
		return this.id;
	}
	
	// note: I may want to protect access to passwords
	public String GetPassword() {
		return this.password;
	}
	
	public void SetPassword(String nextPassword) {
		this.password = nextPassword;
	}

	
	// overridden object methods
	// note: at the moment an id is the only thing equality is based on 
	@Override
	public String toString() {
		return "User [ID=" + id + ", accessLevel=" + accessLevel + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
