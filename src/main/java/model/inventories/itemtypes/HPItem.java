package model.inventories.itemtypes;

import model.entities.ITrainer;
import model.inventories.IConsumable;
import model.inventories.Item;

public class HPItem extends Item implements IConsumable {

    public HPItem(String name, String desc, int prio, int quant, int dmg, int heal, int price) {
        super(name, desc, prio, quant, dmg, heal, price);
    }

    @Override
    public void unfoldEffect(ITrainer trainer) {

    }


}
