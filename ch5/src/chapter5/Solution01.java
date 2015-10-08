package chapter5;

// You are given two 32-bit numbers, N and M, and two bit positions, land j. Write
// a method to insert M into N such that M starts at bit j and ends at bit i. You can
// assume that the bits j through i have enough space to fit all of M. That is, if
// M = 10011, you can assume that there are at least 5 bits between j and i. You
// would not, for example, have j = 3 and i = 2, because M could not fully fit
// between bit 3 and bit 2.
// EXAMPLE
// Input: N = 10000000000, M = 10011, i = 2, j = 6
// Output: N = 10001001100
public class Solution01 {
    // In our method, we clear the bits of the larger number where the smaller number is to
    // be inserted. We then perform the AND operation between the larger number, and the smaller
    // number shifted into the right place.
    public static String insertBits(int larger, int smaller, int right, int left) {
        int positionsToShift = left - right + 1;
        int LSBitsSet = (1 << positionsToShift) - 1;
        int mask = ~(LSBitsSet << right);

        larger &= mask;
        return Integer.toBinaryString(larger | (smaller << right));
    }

    public static void main(String[] args) {
        int larger = 0B10101010101;
        int smaller = 0B10011;
        int right = 2;
        int left = 6;
        System.out.println("Larger: " + Integer.toBinaryString(larger));
        System.out.println("Smaller: " + Integer.toBinaryString(smaller));
        System.out.println("Start Position: " + right);
        System.out.println("End Position: " + left);
        System.out.println("Smaller bits inserted into larger: " + insertBits(larger, smaller, right, left));
    }
}
