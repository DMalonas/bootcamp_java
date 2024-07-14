package org.example.operators;

public class BooleanOperatorExample {
    public static void main(String[] args) {
        boolean[] vals = {true, false};
        for(int i = 0; i < vals.length; i++) {
            for(int j = 0; j < vals.length; j++) {
                // practice, starting println with a ""
                System.out.println("" + vals[i] + " and " +
                        vals[j] + " = " + (vals[i] && vals[j]));
                System.out.println("" + vals[i] + " or " +
                        vals[j] + " = " + (vals[i] || vals[j]));
                System.out.println();
            }
        }
        // shortcuts
        int x = 0;
        boolean res1 = (x != 0) && ((1/x) > 2);
        boolean res2 = (x == 0) || ((1/x) > 2);
        System.out.println("res1 = " + res1);
        System.out.println("res2 = " + res2);
    }
}

