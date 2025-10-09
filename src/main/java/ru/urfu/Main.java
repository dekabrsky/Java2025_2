package ru.urfu;
import ru.urfu.resolver.IResolver;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Resolver res = new Resolver();
        res.init();
        System.out.println(res.getCountWithoutAgency());
        System.out.println(res.getMaxDefenderGoalsCount());
        System.out.println(res.getTheExpensiveGermanPlayerPosition());
        System.out.println(res.getTheRudestTeam());
    }
}

class Resolver implements IResolver {
    Data data = new Data();
    
    public void init() {
        data.load_data();
    }

    public int getCountWithoutAgency() {
        int ans = 0;
        for (Player player : data.records) {
            if (player.agency == "") {
                ans += 1;
            }
        }
        return ans;
    }

    public int getMaxDefenderGoalsCount() {
        int ans = -1;
        for (Player player : data.records) {
            if (player.position.equals("DEFENDER")) {
                ans = Math.max(ans, player.goals);
            }
        }
        return ans;
    }

    public String getTheExpensiveGermanPlayerPosition() {
        String ans = "";
        int max_cost = -1;
        for (Player player : data.records) {
            if (player.nationality.equals("Germany") && player.transfer_cost > max_cost) {
                ans = player.position;
                max_cost = player.transfer_cost;
            }
        }
        switch (ans) {
            case "MIDFIELD":
                ans = "Полузащитник";
                break;
            case "DEFENDER":
                ans = "Защитник";
                break;
            case "FORWARD":
                ans = "Нападающий";
                break;
            case "GOALKEEPER":
                ans = "Вратарь";
                break;
        }
        return ans;
    }

    public String getTheRudestTeam() {
        HashMap<String, Integer> cnt = new HashMap<>();
        HashMap<String, Integer> deletes = new HashMap<>();
        float max_rudeness = -1;
        String ans = "";
        for (Player player : data.records) {
            if (cnt.get(player.team) == null) {
                cnt.put(player.team, 0);
                deletes.put(player.team, 0);
            }
            cnt.put(player.team, cnt.get(player.team) + 1);
            deletes.put(player.team, deletes.get(player.team) + player.red_cards);
        }
        for (String team : cnt.keySet()) {
            float rudeness = (float)deletes.get(team) / cnt.get(team);
            if (rudeness > max_rudeness) {
                max_rudeness = rudeness;
                ans = team;
            }
        }
        return ans;
    }
}