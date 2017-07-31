package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Player {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String password;

	@Column
	private String displayName;
	
	@OneToMany
	@JoinColumn
	private List<Friend> friends;
	
	@ManyToOne
	@JoinColumn(name="user_type_id")
	private int userType;

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", email=" + email + ", password=" + password + ", displayName=" + displayName
				+ ", friends=" + friends + ", userType=" + userType + "]";
	}
	
}
