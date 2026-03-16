package app;

public class BattleLogEntry {

    public enum Type {
        ATTACK,
        DAMAGE,
        EFFECTIVENESS,
        STATUS,
        KO,
        INFO
    }

    private final Type type;
    private final String message;

    public BattleLogEntry(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
