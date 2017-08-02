package data;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CharacterEditDao {
	public GameCharacter create(GameCharacter newChar);
	public GameCharacter update(GameCharacter updatedChar, int id);
	public boolean killChar(int charId, Player p);
	public GameCharacter getCharByName(String name);
	public GameCharacter getCharById(int id);
	public int getCharIdByName (String charName);
	public List<GameCharacter> getAllGameCharacters ();
	public List<GameCharacter> getPlayersGameCharacters(Player player);

}
