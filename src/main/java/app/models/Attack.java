package app.models;

public class Attack {

    private int id;
    private String name;
    private String description;
    private int typeId;
    private int pp;
    private String attackClass; // Physical / Special / Status
    private int power;
    private int precision;

    public Attack(int id, String name, String description, int typeId, int pp, String attackClass, int power, int precision) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeId = typeId;
        this.pp = pp;
        this.attackClass = attackClass;
        this.power = power;
        this.precision = precision;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getTypeId() { return typeId; }
    public int getPp() { return pp; }
    public String getAttackClass() { return attackClass; }
    public int getPower() { return power; }
    public int getPrecision() { return precision; }

    public void setPp(int pp) { this.pp = pp; }

    @Override
    public String toString() {
        return name + " (" + pp + " PP)";
    }
}

