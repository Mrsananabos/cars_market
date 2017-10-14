package ru.job4j.start;

import com.sun.deploy.security.MozillaJSSNONEwithRSASignature;

import java.util.ArrayList;
import java.util.Random;

public class Tracker{
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        System.out.println("id = "+item.getId());
        this.items[position++]=item;
        return item;

    }

    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public void update(Item NewItem) {
        for (int index = 0; index < items.length; index++) {
            if ((items[index].getId()).equals(NewItem.getId())) {
                items[index] = NewItem;
            }
            break;
        }
    }
    public void delete(String id) {

        for (int index = 0; index != position; index++) {

            if ((items[index].getId().equals(id))) {
                for (int index1 = index; index1 < (position - 1); index1++) {
                    items[index1] = items[(index1 + 1)];
                }
                items[position - 1] = null;
            }

        }
    }





    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int i = 0;
            for (int index = 0; index != this.position; index++) {
                if (items[index].getName().equals(key)) {
                    result[i] = this.items[index];
                    i++;
                } ;
            };


                Item[] NewResult = new Item[i];
                for (;i>0;i--) {
                    NewResult[i-1]=result[i-1];
                };

        return NewResult;
    };


    public Item[] getAll() {
        Item[] result = new Item[position];
        for (int index=0; index !=position; index++) {
            result[index] = this.items[index];
        }
        return result;
        }



    }





