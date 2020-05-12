import java.util.Random;
import java.util.Scanner;

public class SortingApplication {
    public static final int NUMBER_NOT_FOUND = -1;
    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        boolean isArrayInputStillGoing = true;
        while (isArrayInputStillGoing){
        System.out.println("Please enter the size of the array");
        int sizeOfArray = input.nextInt();
        if (sizeOfArray < 0){
            System.err.println("Array cannot have a negative value try again");
            continue;
        }
        int[] initialArray = new int[sizeOfArray];
        System.out.println("Please enter the array elements");
        for (int i=0;i<sizeOfArray;i++){
            initialArray[i] = input.nextInt();
            if (initialArray[i] <= 0 || initialArray[i] > 100){
                System.err.println("Try again with a number between 1 and 100");
                i--;
            }
        }
        menuRender();
        menuSelection(initialArray,sizeOfArray);
        isArrayInputStillGoing = false;
        }
    }
    public static void menuRender(){
        System.out.println("Option Menu");
        System.out.println("1. Sort numbers from smallest to biggest");
        System.out.println("2. Sort numbers from biggest to smallest");
        System.out.println("3. Search for a number");
        System.out.println("4. Shuffle the numbers");
        System.out.println("5. Add all the numbers together");
        System.out.println("6. Find the biggest number");
        System.out.println("7. Find the smallest number");
        System.out.println("8. Find the average of the numbers");
        System.out.println("9. Check if the array is symmetrical");
        System.out.println("10. Reverse the array of numbers");
        System.out.println("11. Visualize the array");
        System.out.println("12. Exit");
    }
    public static void renderArray(int[] initialArray){
        System.out.println("The array entered is");
        for(int i : initialArray) {
            System.out.print(i+" , ");
        }
    }
    public static void menuSelection(int[] initialArray,int size){
        Scanner menuInput =  new Scanner(System.in);
        System.out.println("");
        System.out.println("Select an option: ");
        int menuSelector = menuInput.nextInt();
        switch(menuSelector){
            case 9:
                int resultIfSymmetric = symmetryCheck(initialArray,size);
                if(resultIfSymmetric == 1){
                    System.out.println("The array is symmetric");
                } else{
                    System.out.println("The array isn't symmetric");
                }
                menuSelection(initialArray,size);
                break;
            case 10:
                for(int i = 0; i < initialArray.length / 2; i++)
                {
                    int temp = initialArray[i];
                    initialArray[i] = initialArray[initialArray.length - i - 1];
                    initialArray[initialArray.length - i - 1] = temp;
                }
                for(int i : initialArray) {
                    System.out.print(i+" , ");
                }
                System.out.println("");
                menuSelection(initialArray,size);
                break;
            case 8:
                double total = 0;
                for(int i=0; i<initialArray.length; i++){
                    total = total + initialArray[i];
                }
                double average = total/initialArray.length;
                System.out.println("The average number in the array is "+average);
                menuSelection(initialArray,size);
                break;
            case 7:
                int smallest = initialArray[0];
                for(int i = 1; i<initialArray.length;i++){
                    smallest = initialArray[0];
                    if(initialArray[i] < smallest)
                        smallest = initialArray[i];
                }
                System.out.println("Smallest number is " + smallest);
                menuSelection(initialArray,size);
                break;
            case 6:
                int biggest = initialArray[0];
                for(int i=1; i< initialArray.length; i++){
                    biggest = initialArray[0];
                    if(initialArray[i] > biggest)
                        biggest = initialArray[i];
                }
                System.out.println("Biggest number is " + biggest);
                menuSelection(initialArray,size);
                break;
            case 4:
                shuffleFunction(initialArray);
                for (int i = 0; i < initialArray.length; i++)
                {
                    System.out.print(initialArray[i] + " , ");
                }
                menuSelection(initialArray,size);
                break;
            case 3:
                Scanner search = new Scanner(System.in);
                System.out.println("Enter the searched number");
                int key = search.nextInt();
                int result = searchFunction(initialArray,key);
                if(result == NUMBER_NOT_FOUND){
                    System.err.print("Element not found");
                } else {
                    System.out.printf("Element found on position: %d",(result+1));
                }
                menuSelection(initialArray,size);
                break;
            case 5:
                int sum = 0;
                for(int i : initialArray) {
                    sum = sum+i;
                }
                System.out.print(sum);
                menuSelection(initialArray,size);
                break;
            case 2:
                int[] sortedArrayLow = sortFromBigToLow(initialArray);
                for(int i : sortedArrayLow) {
                    System.out.print(i+" , ");
                }
                menuSelection(initialArray,size);
                break;
            case 1:
                int[] sortedArrayBig = sortFromLowToBig(initialArray);
                for(int i : sortedArrayBig) {
                System.out.print(i+" , ");
            }
                menuSelection(initialArray,size);
                break;
            case 12:
                System.exit(0);
                break;
            case 11:
                renderArray(initialArray);
                menuSelection(initialArray,size);
                break;
            default:
                System.err.println("Invalid input please start the program and try again");
                menuSelection(initialArray,size);
        }
    }
    public static int[] sortFromLowToBig(int[] initialArray) {
        int length = initialArray.length;
        int element;

        for (int i = 1; i < length; i++) {

            for (int j = length - 1; j >= i; j--) {

                if (initialArray[j-1] > initialArray[j]) {
                    element = initialArray[j-1];
                    initialArray[j-1] = initialArray[j];
                    initialArray[j] = element;
                }
            }
        }

        return initialArray;
    }
    public static int[] sortFromBigToLow(int[] initialArray) {
        int length = initialArray.length;
        int element;

        for (int i = 1; i < length; i++) {

            for (int j = length - 1; j >= i; j--) {

                if (initialArray[j-1] < initialArray[j]) {
                    element = initialArray[j-1];
                    initialArray[j-1] = initialArray[j];
                    initialArray[j] = element;
                }
            }
        }

        return initialArray;
    }
    public static int searchFunction(int[] initialArray,int searchedElement){
        int size = initialArray.length;
        for(int i = 0; i<size;i++){
            if(initialArray[i] == searchedElement){
                return i;
            }
        }
        return NUMBER_NOT_FOUND;
    }
    public static void shuffleFunction(int[] initialArray){
        int index;
        Random random = new Random();
        for (int i = initialArray.length - 1;i>0;i--){
            index = random.nextInt(i+1);
            if(index != i){
                initialArray[index] ^= initialArray[i];
                initialArray[i] ^= initialArray[index];
                initialArray[index] ^= initialArray[i];
            }
        }
    }
    public static int symmetryCheck(int[] initialArray,int size){
        int length = size;
        for(int i = 0; i < length/2; i++) {
            if(initialArray[i] != initialArray[length - i - 1])    return 0;
        }
        return 1;
    }
}