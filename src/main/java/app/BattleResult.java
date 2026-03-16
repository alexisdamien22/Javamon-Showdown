package app;

import java.util.ArrayList;
import java.util.List;

public class BattleResult {

    private List<String> logs = new ArrayList<>();
    private String winner = null;

    public void addLog(String msg) {
        logs.add(msg);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setWinner(String w) {
        this.winner = w;
    }

    public String getWinner() {
        return winner;
    }

    public boolean hasWinner() {
        return winner != null;
    }
}
