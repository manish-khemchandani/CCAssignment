package chapter5;

// A monochrome screen is stored as a single array of bytes, allowing eight consecutive
// pixels to be stored in one byte.The screen has width w, where w is divisible
// by 8 (that is, no byte will be split across rows).The height of the screen, of course,
// can be derived from the length of the array and the width. Implement a function
// drawHorizontalLine(byte[] screen, int width, int xl, int x2,
// int y) which draws a horizontal line from (xl, y)to(x2, y).
public class Solution08 {
    public static void drawHorizontalLine(byte[] screen, int width, int x1, int x2, int y) {
        int startOffset = x1 % 8;
        int firstFullByte = (startOffset == 0 ? x1 / 8 : x1 / 8 + 1);
        int endOffset = x2 % 8;
        int lastFullByte = (endOffset == 7 ? x2 / 8 : x2 / 8 - 1);

        for(int byteIndex = firstFullByte; byteIndex <= lastFullByte; byteIndex++) {
            screen[(y * width / 8) + byteIndex] = (byte) 0xFF;
        }

        byte startMask = (byte) (0xFF >> startOffset);
        byte endMask = (byte) ~(0xFF >> endOffset + 1);

        if((x1 / 8) == (x2 / 8)) {
            byte mask = (byte) (startMask & endMask);
            screen[(y * width / 8) + (x1 / 8)] |= mask;
        } else {
            if(startOffset != 0) {
                int byteNumber = (width / 8) * y + firstFullByte - 1;
                screen[byteNumber] |= startMask;
            }
            if(endOffset != 7) {
                int byteNumber = (width / 8) * y + lastFullByte - 1;
                screen[byteNumber] |= endMask;
            }
        }
    }

    private static void printByte(byte screenByte) {
        for(int bitIndex = 7; bitIndex >= 0; bitIndex--) {
            int mask = 1 << bitIndex;
            System.out.print((screenByte & mask) != 0 ? "1" : "0");
        }
        System.out.print(" ");
    }

    public static void main(String[] args) {
        int counter = 0;
        byte[] screen = new byte[64];
        drawHorizontalLine(screen, 8, 0, 7, 15);
        for(byte screenByte : screen) {
            printByte(screenByte);
            if(++counter == 8) {
                System.out.println();
                counter = 0;
            }
        }
    }
}
