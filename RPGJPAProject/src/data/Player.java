package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

	@Column(name = "display_name")  //either admin or user
	private String displayName;

//	@OneToMany(mappedBy = "friend")
//	private List<Friend> friends;

	@Column(name = "user_type")  //1 for admin, 2 for player
	private int userType;

//	@ManyToMany
//	@JoinTable(name = "player_quest", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "quest_id"))
//	private List<Quest> quests;


	@OneToMany(mappedBy = "player", fetch=FetchType.EAGER)
	private List<GameCharacter> gameCharacters;

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

//	public List<Friend> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(List<Friend> friends) {
//		this.friends = friends;
//	}







	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

//	public List<Quest> getQuests() {
//		return quests;
//	}
//
//	public void setQuests(List<Quest> quests) {
//		quests = quests;
//	}

	public int getId() {
		return id;
	}

	public List<GameCharacter> getGameCharacters() {
		return gameCharacters;
	}

	public void setGameCharacters(List<GameCharacter> gameCharacters) {
		this.gameCharacters = gameCharacters;
	}

	// public boolean addPlayerAsFriend(Friend friend) {
	// friends.add(friend);
	// return true;
	// }
	//
	// public boolean removePlayerAsFriend(Friend friend) {
	// friends.remove(friend);
	// return true;
	// }

	@Override
	public String toString() {
		return "Player [id=" + id + ", email=" + email + ", password=" + password + ", displayName=" + displayName;
	}

}
