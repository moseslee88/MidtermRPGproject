package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Ability;
import data.Friend;
import data.GameCharacter;
import data.Inventory;
import data.InventoryItem;
import data.Player;
import data.Quest;
import data.Shop;
import data.Stage;
import data.UserType;
import enums.Element;
import enums.TypeOfItem;
import enums.TypeOfUser;

public class ShopTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	GameCharacter ch = null;

	@Before
	public void setup() throws Exception {
		emf = Persistence.createEntityManagerFactory("Midterm");
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();

	}
	
	
    @Test
    public void test() {
        boolean pass = true;
        assertEquals(pass, true);
    }
    
    @Test
    public void test_game_character_association (){
    	GameCharacter gc = em.find(GameCharacter.class, 5);  //test for id#5 in GameCharacter class
    	assertNotNull(gc);
    	assertEquals("Bogle", gc.getName());   //the fifth name in the database is Bogle, not Banshee
    	assertEquals(36, gc.getExperienceGiven());     //should be 36
    	
    }
    @Test
    public void test_player_gamecharacter_mappings() {
        Player p =em.find(Player.class, 1);   //'admin' in ENUM TypeOfUser
         List<GameCharacter> chs = p.getGameCharacters();
        assertNotNull(p);
        assertEquals("admin@admin.com", p.getEmail());
        assertEquals(55, chs.size());  //tests list of game characters for player 1
        //assertEquals(p.getPhone(), “3333333”);
    }
    
    @Test
    public void test_player_friend_association (){
    	Player p =em.find(Player.class, 1);   //'admin' in ENUM TypeOfUser
    	List<Friend> friends = p.getFriends();
    	assertNotNull(p);
    	assertEquals(1, friends.get(0).getId());
    	assertEquals("admin@admin.com", friends.get(0).getFriend().getEmail());
    }
    
    @Test
    public void test_player_quest_association (){
    	Player p = em.find(Player.class, 1);  //'admin' in ENUM TypeOfUser
    	List<Quest> quests = p.getQuests();
    	assertNotNull(p);
    	assertEquals("Beginning Again", quests.get(0).getName());
    }
    
    @Test
    public void test_player_usertype_association (){
    	Player p =em.find(Player.class, 1);  //'admin' in ENUM TypeOfUser
    	UserType ut = p.getUserType();
    	assertEquals(TypeOfUser.admin, ut.getUserType());
    } 
    
    @Test
    public void test_game_character_inventory_association (){
    	GameCharacter gc = em.find(GameCharacter.class, 1);       //'Banshee' in GameCharacter database
    	List<Inventory> invent = gc.getInventory();
    	assertNotNull(gc);
    	assertEquals("Banshee", invent.get(0).getGameCharacter().getName());
    	assertEquals(100, invent.get(0).getGameCharacter().getEnergy());  //here we expect an integer for Energy with a value of 100
    }
    
    @Test
    public void test_quest_stage_association (){
    	Quest q= em.find(Quest.class, 1);
    	List<Stage> stages = q.getStages();
    	assertNotNull(q);
    	assertEquals("The First Stage", stages.get(0).getName());
    	assertEquals("You entered the world under suspicious circum", stages.get(0).getIntro());
    }
    
    @Test
    public void test_game_character_ability_association (){
    	GameCharacter gc = em.find(GameCharacter.class, 1);   //'Banshee' in GameCharacter in the database
    	List<Ability> abilities = gc.getAbilities();
    	assertNotNull(gc);
    	assertEquals("Screech", abilities.get(0).getName());
    	assertEquals(Element.frost, abilities.get(0).getElement());
    }
    
    @Test
    public void test_inventory_item_association (){
    	Inventory i = em.find(Inventory.class, 1);
    	List<InventoryItem> items = i.getInventory();
    	assertNotNull(i);
    	assertEquals("Lesser Potion", items.get(0).getItems().getName());
    	//assertEquals("Lessr Potion", items.get(0).getItems().getName()); //test DOES NOT Pass because of spelling
    	assertEquals(Element.physical, items.get(0).getItems().getElement());
    	
    }
    
    @Test
    public void test_inventory_shop_association (){
      	Inventory i = em.find(Inventory.class, 1);
    	    Shop shop = i.getShop();
    	    assertNotNull(i);
    	    assertEquals("[Inventory [id=1, gameCharacterEnergy=100, inventorySize=1]]", shop.getInventory().toString());
    	    assertEquals(1, shop.getInventory().size());  
    }  
    
    @Test
    public void test_stage_has_gameCharacter_association (){
    	Stage stage = em.find(Stage.class, 1);
    	GameCharacter cha = stage.getGameCharacter();
    	assertNotNull(stage);
    	assertEquals("User", cha.getName());
    	assertEquals(20, cha.getBloodR());  //expect int for Blood
    }
    
	
}
