package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.TypeOfUser;

@Entity
@Table(name = "user_type")
public class UserType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column
	private TypeOfUser type;

	public UserType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserType(int id, TypeOfUser type) {

		this.id = id;
		this.type = type;
	}

	public TypeOfUser getUserType() {
		return type;
	}

	public void setUserType(TypeOfUser userType) {
		this.type = userType;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "UserType [id=" + id + "]";
	}

}
