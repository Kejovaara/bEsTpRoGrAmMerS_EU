package model.inventories;

public class Item {

    private String itemName;
    private int amount;
    private String itemDescription;

    private int heal;
    private int buff;
    private int cure;
    private int healPct;

//    public Item(){
//        this.heal = 0;
//        this.buff = 0;
//        this.cure = 0;
//    }

    public Item(String name, int amount, String itemDescription, int heal, int buff, int cure, int healPct){
        this.itemName = name;
        this.amount = amount;
        this.itemDescription = itemDescription;
        this.heal = heal;
        this.buff = buff;
        this.cure = cure;
        this.healPct = healPct;
    }

}
