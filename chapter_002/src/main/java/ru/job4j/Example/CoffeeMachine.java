package ru.job4j.Example;

class CoffeMachine {

    int ten = 0;
    int five = 0;
    int two = 0;
    int one = 0;
    int remain = 0;


    int[] changes(int value, int price) {
        int remain = value - price;
        if (remain >= 10) {
            ten = remain / 10;
            remain = remain % 10;
        }

        if (remain >= 5) {
            five = remain / 5;
            remain = remain % 5;
        }
            if (remain > 2) {
                two = remain / 2;
                remain = remain % 2;
                one = remain / 1;
            }


        int sum = ten + five + two + one;
        int[] change = new int[sum];
        int i = 0;
        for (; i < ten; i++) {
            change[i] = 10;
        }
        for (; i < ten+five; i++) {
            change[i] = 5;
        }
        for (; i < ten+five+two; i++) {
            change[i] = 2;
        }
        for (; i < ten+five+two+one; i++) {
            change[i] = 1;
        }



        return change;
    }

}


