package helloworld;

public class Item {

    private String itemName;
    private int amount;
    private Enum<ItemEffects> itemEffect;
    private String itemDescription;

    public Item(String name, int amount, Enum<ItemEffects> itemEffect, String itemDescription){
        this.itemName = name;
        this.amount = amount;
        this.itemEffect = itemEffect;
        this.itemDescription = itemDescription;
    }

}
