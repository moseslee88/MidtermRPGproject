package data;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GameplayDaoImpl implements GameplayDao {
	@PersistenceContext
	private EntityManager em;

	public Ability useAbilityByName(String abilityName) {
		String qString = "select a from Ability a";
			List<Ability> abilities = em.createQuery(qString, Ability.class).getResultList();

			for (Ability ability : abilities) {
				if (ability.getName().equals(abilityName)) {
					return ability;
				}
			}
			return null;
	}

	public Ability useDefaultAbility() {
		return em.find(Ability.class, 11);
	}

	public Quest getDefaultQuest() {
		return em.find(Quest.class, 1);
	}

	public GameCharacter getDefaultGameCharacter() {
		return em.find(GameCharacter.class, 1);
	}
	
	public Item addItemToGameCharacter(GameCharacter gameCharacter) {
		Item reward = null;
		String q = "select i from Item i";
		List<Item> items = em.createQuery(q, Item.class).getResultList();
		
		int targetItemLevel = (gameCharacter.getHp()/40)+1;
		if (targetItemLevel > 3) targetItemLevel = 3;
		else if (targetItemLevel < 1) targetItemLevel = 1;
		
		Collections.shuffle(items);
		
		for (Item item : items) {
			if (item.getItemLevel() == targetItemLevel) {
				gameCharacter.getInventory().getItems().add(item);
				return item;
			}
		}
		return reward;
	}
	
	public Stage getNextStage(Quest quest, Stage stage) {
		if (stage == null) {
			return quest.getStages().get(0);
		} else if (stage.getId() == quest.getStages().get(quest.getStages().size()-1).getId()) {
			return null;
		} else {
			return quest.getStages().get(quest.getStages().indexOf(stage)+1);
		}
	}

}
