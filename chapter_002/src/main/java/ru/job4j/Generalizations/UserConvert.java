package ru.job4j.Generalizations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserConvert{

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> newMap = new HashMap<>();
        for (User i : list){
            newMap.put(i.getId(), i);
        }

        return newMap;
    }





}