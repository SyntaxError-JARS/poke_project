package com.revature.pokedex.util.collections;

import com.revature.pokedex.models.Trainer;

public class TempDriver {

    public static void main(String[] args) {
        // Establishing a new list object called trainersLL as a linkedlist to memory
        List<Trainer> trainersLL = new LinkedList<>();

        Trainer trainer = new Trainer("char", "le", "cl@mail.com", "pass", "02-21-1987");
        Trainer trainer2 = new Trainer("ar", "qa", "aq@mail.com", "pass", "02-21-1987");
        Trainer trainer3 = new Trainer();
        System.out.println(trainersLL.add(trainer));
        System.out.println(trainersLL.add(trainer));
        System.out.println(trainersLL.add(trainer2));
        System.out.println(trainersLL.add(trainer));
        System.out.println(trainersLL.add(trainer));
        System.out.println(trainersLL.add(trainer));
        System.out.println(trainersLL.size());

        System.out.println(trainersLL.contains(trainer));
        System.out.println(trainersLL.remove(trainer3));
        System.out.println(trainersLL.size());
        System.out.println(trainersLL.get(2));

        List<String> stringLL = new LinkedList<>();
        stringLL.add("Hello there");
        stringLL.add("Ahhhh");
        stringLL.add("General");
        stringLL.add("Kenobi");

        System.out.println(stringLL.contains("General"));
        System.out.println(stringLL.get(2));

        List<Integer> integerList = new LinkedList<>();
        integerList.add(1);
    }

}
