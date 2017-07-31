package data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Column(name="description")
	private String description;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="conclusion")
	private String conclusion;
	
	@Column(name="level_min")
	private Integer levelMin;
	
	@Column(name="level_max")
	private Integer levelMax;
	
	@Column(name="completed")
	private Boolean completed;
	
	 @ManyToMany(mappedBy="questList")
	 private List<Stage> stages; 
	 
	 @ManyToMany(mappedBy="quests")
	 private List<Player> players;    
	 
	//getters and setters

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

		public Integer getLevelMin() {
			return levelMin;
		}

		public void setLevelMin(Integer levelMin) {
			this.levelMin = levelMin;
		}

		public Integer getLevelMax() {
			return levelMax;
		}

		public void setLevelMax(Integer levelMax) {
			this.levelMax = levelMax;
		}

		public Boolean getCompleted() {
			return completed;
		}

		public void setCompleted(Boolean completed) {
			this.completed = completed;
		}

		public List<Stage> getStages() {
			return stages;
		}

		public void setStages(List<Stage> stages) {
			this.stages = stages;
		}

		public List<Player> getPlayers() {
			return players;
		}

		public void setPlayers(List<Player> players) {
			this.players = players;
		}

		@Override
		public String toString() {
			return "Quest [id=" + id + ", name=" + name + ", description=" + description + ", intro=" + intro
					+ ", conclusion=" + conclusion + ", levelMin=" + levelMin + ", levelMax=" + levelMax
					+ ", completed=" + this.getCompleted() + ", stages size=" + this.getStages().size() + "]";
		}
		
		
	 
}