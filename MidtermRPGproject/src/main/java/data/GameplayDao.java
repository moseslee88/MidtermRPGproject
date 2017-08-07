package data;
import org.springframework.stereotype.Repository;

@Repository
public interface GameplayDao {
	
	public Ability useDefaultAbility();
	public Ability useAbilityByName(String abilityName);
	
	public Stage getDefaultStage();
	
	public GameCharacter getDefaultGameCharacter();
	
	public Item addItemToGameCharacter(GameCharacter gameCharacter);
	
	public Stage getNextStage(Quest quest, Stage stage);

}