package ru.job4j.Collections;

import javax.swing.text.View;
import java.util.*;

public class Perfomance{



    public String createRandomString() {//метод создаёт случайную строчку
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        String mCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ`";
        int STR_LENGTH = 1 + random.nextInt(16); //кол-во символов в произвольной строке в диапозоне [1;15]
        for (int i = 0; i < STR_LENGTH; i++) {
            int number = random.nextInt(mCHAR.length());
            char ch = mCHAR.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }



    public long add(Collection<String> collection, String[] strings) { //метод  замеряет время вставки в коллекцию случайных строк
        long start = System.nanoTime();
        for (int j = 0; j<strings.length; j++){
            collection.add(strings[j]);
        }
        long end = System.nanoTime();
        long traceTime = end-start;
        return traceTime;
    }

   public long delete(Collection<String> collection, int amount, String[] strings) {//метод замеряет время удаления в коллекции первых a элементов
       long start = System.nanoTime();
        for (int a = 0; a<amount; a++){
            collection.remove(strings[a]);
        }
       long end = System.nanoTime();
       long traceTime = end-start;
       return traceTime;
   }


    public static void main(String[] args) {
        Random random = new Random();
        Perfomance perfomance = new Perfomance();
        int NumberOfStrings = 100 + random.nextInt(401); // количество случайных строк
        String[] ArrayOfStrings = new String[NumberOfStrings];
       for (int i = 0; i<NumberOfStrings; i++){
           ArrayOfStrings[i] = perfomance.createRandomString(); //инициализация массива со случайными строками
        }
        int RemovedStrings = 1 + random.nextInt(201); //случайное кол-во строк для удаления

        List<String> ArList = new ArrayList<String>();
        List<String> LinkList = new LinkedList<String>();
        Set<String> TrSet = new TreeSet<String>();
        System.out.println("Операция добавление новых элементов ArrayList: " + perfomance.add(ArList, ArrayOfStrings));
        System.out.println("Операция добавление новых элементов LinkedList: " + perfomance.add(LinkList, ArrayOfStrings));
        System.out.println("Операция добавление новых элементов TreeSet: " + perfomance.add(TrSet, ArrayOfStrings));
        System.out.println("Операция удаление элементов ArrayList: " + perfomance.delete(ArList, RemovedStrings, ArrayOfStrings));
        System.out.println("Операция удаление элементов LinkedList: " + perfomance.delete(LinkList, RemovedStrings, ArrayOfStrings));
        System.out.println("Операция удаление элементов TreeSet: " + perfomance.delete(TrSet, RemovedStrings, ArrayOfStrings));




    }
}