package data;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PlayerQuest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "player_id")
	private int playerId;

	@Column(name="quest_id")
	private int questId;
	
	 @OneToOne    //define foreign key (Address_id)in JOIN COLUMN
     @JoinColumn(name="address_id")
     private Address address;
	 
	 @OneToMany(mappedBy="customer")
	 private List<Rental> rentals;  
	 
	 @ManyToOne
	 @JoinColumn(name="store_id")
	 private Store store;
	 

   //getters and setters


	public int getId() {
		return id;
	}
	

}