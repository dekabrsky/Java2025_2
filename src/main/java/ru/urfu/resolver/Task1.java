package ru.urfu.resolver;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import ru.urfu.model.Position;
import ru.urfu.model.Player;

public class Task1 implements IResolver {

	private final List<Player> players;

	public Task1(List<Player> players) {
		this.players = players;
	}

	public int getCountWithoutAgency() {
		return (int) players
		.stream()
		.filter(p -> p.agency().isEmpty())
		.count();
	}

	public int getMaxDefenderGoalsCount() {
		return players.stream()
		.filter(p -> p.position().equals(Position.DEFENDER))
		.mapToInt(Player::goals)
		.max()
		.orElse(0);
	}

	public String getTheExpensiveGermanPlayerPosition() {
		return players.stream()
		.filter(
			p -> p.nationality().equals("Germany")
		)
		.max(Comparator.comparing(Player::transfer_cost))
		.map(Player::position)
		.map(Position::getTitle)
		.orElse(null);
	}

	public String getTheRudestTeam() {
		Map<String, Integer> team2playersNum = new HashMap<>();
		Map<String, Integer> team2playersRedCards = new HashMap<>();
		Integer count;

		for (Player p: players) {
			if(p.team().isEmpty()) {
				continue;
			}

			count = team2playersNum.get(p.team());
			if(count == null) {
				count = 0;
			}
			count ++;
			team2playersNum.put(p.team(), count);

			count = team2playersRedCards.get(p.team());
			if(count == null) {
				count = 0;
			}
			count += p.red_cards();
			team2playersRedCards.put(p.team(), count);
		}
		double best = 0.0;
		double cur;
		String best_team = null;
		for (Map.Entry<String, Integer> team: team2playersNum.entrySet()) {
			cur = (
				team2playersRedCards.get(team.getKey())
				/ (double) team.getValue()
			);
			if(best < cur) {
				best = cur;
				best_team = team.getKey();
			}
		}
		return best_team;
	}
};
