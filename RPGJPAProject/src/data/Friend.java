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
import javax.persistence.OneToOne;

import enums.*;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "player_id1")
	private Player user;

	@ManyToOne
	@JoinColumn(name = "player_id2")
	private Player friend;

	public Player getUser() {
		return user;
	}

	public void setUser(Player user) {
		this.user = user;
	}

	public Player getFriend() {
		return friend;
	}

	public void setFriend(Player friend) {
		this.friend = friend;
	}

	public int getId() {
		return id;
	}
	

	@Override
	public String toString() {
		return "Friend [id=" + id;
	}

}
