package data;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface AdminDao {
	public List<GameCharacter> indexGameCharacters();
	public GameCharacter showGameCharacter(int id);
	public GameCharacter createGameCharacter(GameCharacter GameCharacter);
	public GameCharacter updateGameCharacter(int id, GameCharacter GameCharacter);
	public boolean destroyGameCharacter(int id);
	
	public List<Item> indexItems();
	public Item showItem(int id);
	public Item createItem(Item Item);
	public Item updateItem(int id, Item Item);
	public boolean destroyItem(int id);

	public List<Player> indexPlayers();
	public Player showPlayer(int id);
	public Player createPlayer(Player Player);
	public Player updatePlayer(int id, Player Player);
	public boolean destroyPlayer(int id);
	
	public List<Quest> indexQuests();
	public Quest showQuest(int id);
	public Quest createQuest(Quest Quest);
	public Quest updateQuest(int id, Quest Quest);
	public boolean destroyQuest(int id);
	
	public Stage createStage(Stage Stage);
	public Stage updateStage(int id, Stage Stage);
	public boolean destroyStage(int id);
	
	public boolean checkEmail(Player player);
	public boolean checkDisplayName(Player player);
}
