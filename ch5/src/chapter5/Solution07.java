package chapter5;

// Write a program to swap odd and even bits in an integer with as few instructions
// as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and
// soon).
public class Solution07 {
    // We AND the number with 2 masks, 101010... and 010101... . For the first mask,
    // after we AND it with the given number, we (logical) right shift the outcome by
    // 1 place to shift the bits. For the second mask, similarly, we left shift the bits.
    // Finally, we OR the 2 outcomes to get the number with the bits swapped.
    public static int swapPairwiseBits(int number) {
        return ( ((number & 0xAAAAAAAA) >>> 1) | ((number & 0x55555555) << 1) );
    }

    public static void main(String[] args) {
        String binaryString = CommonUtils.readLineFromConsole("Enter a binary number: ");
        try {
            int binary = Integer.parseInt(binaryString, 2);
            System.out.println("The number with swapped pairwise bits is: " + Integer.toBinaryString(swapPairwiseBits(binary)));
        } catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("Input is not a binary number.");
        }
    }
}
