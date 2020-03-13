package pl.sda.rafal.zientara.programowanie1.webinar;

public class InsertSortMain {

    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 4, 5, 23, 3, 4, 3, 4, 3};


        printArray(input);
        int[] map = new int[24];

        for (int i = 0; i < input.length; i++) {
            int value = input[i];
            map[value]++;
        }

        int index = 0;
        for (int i = 0; i < map.length; i++) {
            int value = map[i];
            System.out.printf("map[%d] = %d\n", i, value);
            if (value > 0) {
                for (int j = 0; j < value; j++) {
                    input[index] = i;
                    index++;
                }
            }
        }
        printArray(input);
    }

    private static void printArray(int[] input) {
        System.out.print("[");
        for (int value : input) {
            System.out.print(value + " ");
        }
        System.out.println("]");
    }
}
