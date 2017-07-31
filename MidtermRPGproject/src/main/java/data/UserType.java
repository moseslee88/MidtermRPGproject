package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enums.TypeOfUser;

@Entity
public class UserType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column
	private TypeOfUser userType;

	public TypeOfUser getUserType() {
		return userType;
	}

	public void setUserType(TypeOfUser userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	
}
