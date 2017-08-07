package data;
import org.springframework.stereotype.Repository;

@Repository
public interface GameplayDao {
	public GameCharacter indexGameCharacters();
	public GameCharacter showGameCharacter(int id);
	public GameCharacter createGameCharacter(GameCharacter GameCharacter);
	public GameCharacter updateGameCharacter(int id, GameCharacter GameCharacter);
	public boolean destroyGameCharacter(int id);
	
	public Ability useDefaultAbility();
	public Ability useAbilityByName(String abilityName);
	
	public Stage getDefaultStage();
	
	public GameCharacter getDefaultGameCharacter();

}
