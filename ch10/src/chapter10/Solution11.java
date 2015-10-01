package chapter10;

public class Solution11 {
    public static void createPeaksAndValleys(int[] numbers) {
        if(numbers == null) {
            return;
        }
        for(int numberCounter = 1; numberCounter < numbers.length; numberCounter += 2) {
            int indexOfMaxValue = getIndexOfMaxValue(numbers, numberCounter - 1, numberCounter, numberCounter + 1);
            if(numberCounter != indexOfMaxValue) {
                swap(numbers, numberCounter, indexOfMaxValue);
            }
        }
    }

    private static int getIndexOfMaxValue(int[] numbers, int left, int middle, int right) {
        int leftValue = numbers[left];
        int middleValue = numbers[middle];
        int rightValue;

        if(right == numbers.length) {
            rightValue = Integer.MIN_VALUE;
        } else {
            rightValue = numbers[right];
        }

        int maxValue = Math.max(leftValue, Math.max(middleValue, rightValue));
        int indexOfMaxValue;
        if(maxValue == leftValue) {
            indexOfMaxValue = left;
        } else if(maxValue == middleValue) {
            indexOfMaxValue = middle;
        } else {
            indexOfMaxValue = right;
        }
        return indexOfMaxValue;
    }

    private static void swap(int[] numbers, int index1, int index2) {
        int temp = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = temp;
    }

    public static void main(String[] args) {
        int length = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the length of the integer array: "));
        int[] numbers = ArrayUtils.generateRandomIntArray(length);
        ArrayUtils.printArrayToConsole(numbers);
        System.out.println();
        createPeaksAndValleys(numbers);
        System.out.println("The array arranged into peaks and valleys: ");
        ArrayUtils.printArrayToConsole(numbers);
    }
}
