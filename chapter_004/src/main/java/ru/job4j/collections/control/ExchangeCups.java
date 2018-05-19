package ru.job4j.collections.control;

import java.util.*;

import static ru.job4j.collections.control.Item.Action.ASK;
import static ru.job4j.collections.control.Item.Action.BID;
import static ru.job4j.collections.control.Item.Type.ADD;
import static ru.job4j.collections.control.Item.Type.DELETED;

public class ExchangeCups {

    private List<Item> purchase = new ArrayList();
    private List<Item> sale = new ArrayList();
    private static final Random RN = new Random();

    public void accept(Item item) {
        if (item.selectedType == ADD) {
            this.addItem(item);
        } else {
            if (item.selectedType == DELETED) {
                for (Item it : purchase) {
                    if (it.getId() == item.getId() & it.book.equals(item.book)) {
                        purchase.remove(it);
                        break;
                    }
                }
                for (Item it : sale) {
                    if (it.getId() == item.getId() & it.book.equals(item.book)) {

                        sale.remove(it);
                        break;
                    }
                }
            }
        }
    }

    private void addItem(Item item) {
        if (item.selectedActiion == ASK) {
            walkOnOppositeItemsForAsk(item);
            if (item.volume != 0) {
                this.purchase.add(item);
                item.setId(Math.abs(RN.nextInt()));
                System.out.println("ID заявки: " + item.getId());
                sortPriceLevel(this.purchase);
            }

        } else {
            if (item.selectedActiion == BID) {
                walkOnOppositeItemsForBid(item);
                if (item.volume != 0) {
                    this.sale.add(item);
                    item.setId(Math.abs(RN.nextInt()));
                    System.out.println("ID заявки: " + item.getId());
                    sortPriceLevel(this.sale);
                }
            }
        }
    }

    private void walkOnOppositeItemsForAsk(Item item) {
        Item itemForRemove = null;
        for (int i = this.sale.size() - 1; i >= 0; i--) {
            Item currItem = this.sale.get(i);
            if (currItem.book.equals(item.book) & currItem.price <= item.price) {
                int difference = currItem.volume - item.volume;
                if (difference < 0) {
                    item.volume -= currItem.volume;
                    itemForRemove = currItem;
                } else {
                    if (difference > 0) {
                        currItem.volume -= item.volume;
                        item.volume = 0;
                        break;
                    } else {
                        item.volume = 0;
                        itemForRemove = currItem;
                        break;
                    }
                }
            }
        }
        if (itemForRemove != null) {
            this.sale.remove(itemForRemove);
        }

    }

    private void walkOnOppositeItemsForBid(Item item) {
        Item itemForRemove = null;
        for (Item currItem : purchase) {
            if (item.book.equals(currItem.book) & currItem.price >= item.price) {
                int difference = currItem.volume - item.volume;
                if (difference < 0) {
                    item.volume -= currItem.volume;
                    itemForRemove = currItem;
                } else {
                    if (difference > 0) {
                        currItem.volume -= item.volume;
                        item.volume = 0;
                        break;
                    } else {
                        item.volume = 0;
                        itemForRemove = currItem;
                        break;
                    }
                }
            }
        }
        if (itemForRemove != null) {
            this.purchase.remove(itemForRemove);
        }
    }




    public List<Item> sortPriceLevel(List<Item> list) {
        list.sort(new Comparator<Item>() {
                      @Override
                      public int compare(Item o1, Item o2) {
                          return Integer.compare(o2.price, o1.price);
                      }
                  }
        );
        return list;
    }

    public void printByBook(String book) {
        List<Item> newPurchase = new ArrayList<>();
        for (Item item : this.purchase) {
            if (item.book.equals(book)) {
                newPurchase.add(item);
            }
        }
        System.out.println("Покупка  Цена");
        printBySortPrice(newPurchase);
        List<Item> newSale = new ArrayList<>();
        for (Item item : this.sale) {
            if (item.book.equals(book)) {
                newSale.add(item);
            }
        }
        System.out.println("Продажа  Цена");
        printBySortPrice(newSale);
    }

    public void printBySortPrice(List<Item> newList) {
        int currentPrice = 0;
        int currentVolume = 0;
        if (newList.size() > 0) {
            currentPrice = newList.get(0).price;
            currentVolume = newList.get(0).volume;
            for (int i = 0; i < newList.size() - 1; i++) {
                if (currentPrice == newList.get(i + 1).price) {
                    currentVolume += newList.get(i + 1).volume;
                } else {
                    System.out.println(currentVolume + "        " + currentPrice);
                    if (i != newList.size() - 1) {
                        currentPrice = newList.get(i + 1).price;
                        currentVolume = newList.get(i + 1).volume;
                    }
                }
            }
            if (newList.get(newList.size() - 1).price == currentPrice) {
                System.out.println(currentVolume + "        " + currentPrice);
            } else {
                System.out.println(newList.get(newList.size() - 1).volume + "       " + newList.get(newList.size() - 1).price);
            }
        }
    }


}
