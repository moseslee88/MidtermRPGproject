package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Quest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "intro")
	private String intro;

	@Column(name = "conclusion")
	private String conclusion;

	 @ManyToMany(mappedBy = "questList", fetch = FetchType.EAGER)
	 private List<Stage> stages;

//	@ManyToMany(mappedBy = "quests")
//	private List<Player> players;

	// getters and setters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	 public List<Stage> getStages() {
	 return stages;
	 }
	
	 public void setStages(List<Stage> stages) {
	 this.stages = stages;
	 }

//	public List<Player> getPlayers() {
//		return players;
//	}
//
//	public void setPlayers(List<Player> players) {
//		this.players = players;
//	}

	@Override
	public String toString() {
		return "Quest [id=" + id + ", name=" + name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Quest other = (Quest) obj;
		if (id != other.id)
			return false;
		return true;
	}

}