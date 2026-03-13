package app.models;

public class Attack {
    private int id;
    private String name;
    private String description;
    private int typeId;
    private int power;
    private int accuracy;
    private int pp;
    private String category;

    public Attack(int id, String name, String description, int typeId, int power, int accuracy, int pp, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeId = typeId;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPp() {
        return pp;
    }

    public String getCategory() {
        return category;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }
}
