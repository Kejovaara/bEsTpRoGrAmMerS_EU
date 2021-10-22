package model.inventories;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestInventory {
    @Test
    public void testAddItem(){
        Inventory i = new Inventory();
        Item item = ItemBuilder.getRandom();

        i.addItem(item);

        assertTrue(i.getInventorySize() == 1);
    }

    @Test
    public void testDecrementItemAmount(){
        Inventory i = new Inventory();
        Item item = ItemBuilder.getRandom();

        i.addItem(item);
        assertEquals(i.getInventorySize(),1);
        i.decrementItemAmount(item);
        assertEquals(i.getInventorySize(), 0);
    }

    @Test
    public void testItemStacking(){
        Inventory i = new Inventory();
        Item item = ItemBuilder.getRandom();

        i.addItem(item);
        i.addItem(item);
        assertEquals(i.getInventorySize(),1);
        assertEquals(item.getQuantity(),2);
    }

    @Test
    public void testGetItem(){
        Inventory i = new Inventory();
        Item item = ItemBuilder.getRandom();

        i.addItem(item);

        assertEquals(i.getItem(0),item);
    }

    @Test
    public void testClearInventory(){
        Inventory i = new Inventory();
        List<Item> itemList = new ArrayList<Item>();

        Item item = ItemBuilder.getRandom();
        Item item2 = ItemBuilder.getRandom();
        Item item3 = ItemBuilder.getRandom();

        itemList.add(item);
        itemList.add(item2);
        itemList.add(item3);

        for(Item itm : itemList){
            i.addItem(itm);
        }

        assertTrue(i.getInventorySize() > 0);

        i.clearInventory();

        assertEquals(i.getInventorySize(),0);
    }

    @Test
    public void testInventoryWithItems(){
        List<Item> itemList = new ArrayList<>();
        Item item1 = ItemBuilder.getRandom();
        Item item2 = ItemBuilder.getRandom();
        Item item3 = ItemBuilder.getRandom();
        Item item4 = ItemBuilder.getRandom();
        Item item5 = ItemBuilder.getRandom();

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);

        Inventory i = new Inventory(itemList);
        assertTrue(i.getInventorySize() > 0);
    }

    @Test
    public void testGetInventory(){
        Inventory i = new Inventory();
        List<Item> itemList = new ArrayList<>();

        i.addItem(ItemBuilder.getRandom());

        itemList = i.getInventory();

        assertTrue(itemList.size() >0);
    }

    @Test
    public void testDecrementItemQuantity(){
        Inventory i = new Inventory();
        Item item = ItemBuilder.getRandom();

        i.addItem(item);
        i.addItem(item);
        i.addItem(item);
        assertEquals(i.getInventorySize(),1);
        assertEquals(i.getItem(0).getQuantity(), 3);
        i.decrementItemAmount(item);
        assertEquals(i.getInventorySize(),1);
        assertEquals(i.getItem(0).getQuantity(), 2);
    }
}
