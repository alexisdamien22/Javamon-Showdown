package app.models;

public class Attack {

    private int id;
    private String name;
    private String description;
    private int typeId;
    private int pp;
    private String attackClass; // Physical / Special / Status
    private Integer power;
    private int precision;
    private int priority;

    public Attack(int id, String name, String description, int typeId, int pp, String attackClass, int power, int precision, int priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeId = typeId;
        this.pp = pp;
        this.attackClass = attackClass;
        this.power = power;
        this.precision = precision;
        this.priority = priority;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getTypeId() { return typeId; }
    public int getPp() { return pp; }
    public String getAttackClass() { return attackClass; }
    public Integer getPower() { return power; }
    public int getPrecision() { return precision; }
    public int getPriority() { return priority; }

    public void setPp(int pp) { this.pp = pp; }

    public boolean isPhysical(){
        if(this.attackClass == "Phys."){
            return true;
        }
        return false;
    }

    public boolean isSpecial(){
        if(this.attackClass == "Spéc."){
            return true;
        }
        return false;
    }

    public boolean isOther(){
        if(this.attackClass == "Autre"){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + pp + " PP)";
    }
}

