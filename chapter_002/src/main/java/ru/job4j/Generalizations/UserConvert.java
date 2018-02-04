package ru.job4j.Generalizations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserConvert{

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> newMap = new HashMap<>();
        for (int i = 0; i<list.size(); i++){
            newMap.put(list.get(i).getId(), list.get(i));
        }

        return newMap;

    }





}