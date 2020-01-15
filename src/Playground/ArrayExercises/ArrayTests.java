package Playground.ArrayExercises;


public class ArrayTests {

    public static void main(String[] args) {

    int [][] array1 = {{1,2,3},
                       {4,5,6,},
                       {7,8,9}};

        System.out.println();
        int test1 = array1[0][0];
        int test2 = array1 [0][1];
        int test3 = array1 [0][2];
        int test4 = array1[1][1];


        System.out.print(test1 + " " + test2+ " " + test3);
        System.out.println();
        System.out.print(test4);
        System.out.println();

    for (int i =0; i < array1.length; i++) {
        for (int j= 0; j <array1[i].length; j++) {
            if (array1[0][0] == array1[i][j]) {
                System.out.println(
                        array1[i][j+1] + " " +
                        array1[i+1][j] + " " +
                        array1[i+1][j+1]
                );
            }

        }
        System.out.println();
    }








    }
}
