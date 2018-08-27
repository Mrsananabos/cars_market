package ru.job4j.collections;

import javax.swing.text.View;
import java.util.*;

public class Perfomance {


    public String createRandomString() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        String mCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ`";
        int strlength = 1 + random.nextInt(16); //кол-во символов в произвольной строке в диапозоне [1;15]
        for (int i = 0; i < strlength; i++) {
            int number = random.nextInt(mCHAR.length());
            char ch = mCHAR.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }


    public long add(Collection<String> collection, String[] strings) { //метод  замеряет время вставки в коллекцию случайных строк
        long start = System.nanoTime();
        for (int j = 0; j < strings.length; j++) {
            collection.add(strings[j]);
        }
        long end = System.nanoTime();
        long traceTime = end - start;
        return traceTime;
    }

    public long delete(Collection<String> collection, int amount, String[] strings) {
        long start = System.nanoTime();
        for (int a = 0; a < amount; a++) {
            collection.remove(strings[a]);
        }
        long end = System.nanoTime();
        long traceTime = end - start;
        return traceTime;
    }


    /*public static void main(String[] args) {
        Random random = new Random();
        Perfomance perfomance = new Perfomance();
        int numberOfStrings = 100 + random.nextInt(401); // количество случайных строк
        String[] arrayOfStrings = new String[numberOfStrings];
        for (int i = 0; i < numberOfStrings; i++) {
            arrayOfStrings[i] = perfomance.createRandomString(); //инициализация массива со случайными строками
        }
        int removedStrings = 1 + random.nextInt(201); //случайное кол-во строк для удаления

        List<String> arList = new ArrayList<String>();
        List<String> linkList = new LinkedList<String>();
        Set<String> trSet = new TreeSet<String>();
        System.out.println("Операция добавление новых элементов ArrayList: " + perfomance.add(arList, arrayOfStrings));
        System.out.println("Операция добавление новых элементов LinkedList: " + perfomance.add(linkList, arrayOfStrings));
        System.out.println("Операция добавление новых элементов TreeSet: " + perfomance.add(trSet, arrayOfStrings));
        System.out.println("Операция удаление элементов ArrayList: " + perfomance.delete(arList, RemovedStrings, ArrayOfStrings));
        System.out.println("Операция удаление элементов LinkedList: " + perfomance.delete(LinkList, RemovedStrings, ArrayOfStrings));
        System.out.println("Операция удаление элементов TreeSet: " + perfomance.delete(TrSet, RemovedStrings, ArrayOfStrings));


    }*/
}