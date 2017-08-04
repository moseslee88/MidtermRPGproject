package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Stage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "intro")
	private String intro;

	@Column(name = "conclusion")
	private String conclusion;

	@ManyToOne
	@JoinColumn(name = "character_id")
	private GameCharacter gameCharacter;

	@ManyToMany
	@JoinTable(name = "quest_stage", joinColumns = @JoinColumn(name = "stage_id"), inverseJoinColumns = @JoinColumn(name = "quest_id"))
	private List<Quest> questList;

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

	public GameCharacter getGameCharacter() {
		return gameCharacter;
	}

	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	public List<Quest> getQuests() {
		return questList;
	}

	public void setQuests(List<Quest> quests) {
		this.questList = quests;
	}

	@Override
	public String toString() {
		return "Stage [id=" + id;
	}

}