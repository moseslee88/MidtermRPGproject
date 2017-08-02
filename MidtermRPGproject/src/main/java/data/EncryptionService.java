package data;

import org.springframework.stereotype.Component;

@Component
public class EncryptionService {

	private String email;

	private String password;
	
	private String displayName;  //either 'admin' or 'user'
	
	public String getEmail()  {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
	
}
