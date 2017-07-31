package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToMany(mappedBy = "shops")
	private Integer id;

	public Shop() {
		super();
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + "]";
	}

}
