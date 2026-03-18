package app;

import java.util.ArrayList;
import java.util.List;

public class BattleResult {
    private final List<BattleLogEntry> logs = new ArrayList<>();
    private String winner = null;
    private boolean forceSwitch = false;

    public boolean mustForceSwitch() {
        return forceSwitch;
    }

    public void setForceSwitch(boolean value) {
        this.forceSwitch = value;
    }

    public void add(BattleLogEntry.Type type, String msg) {
        logs.add(new BattleLogEntry(type, msg));
    }

    public List<BattleLogEntry> getLogs() {
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
