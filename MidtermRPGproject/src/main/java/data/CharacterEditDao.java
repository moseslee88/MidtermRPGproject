package data;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterEditDao {
	public GameCharacter create(GameCharacter newChar);
	public GameCharacter update(GameCharacter updatedChar, int id);
	public boolean killChar(int charId);
}
