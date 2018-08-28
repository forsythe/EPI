package chapter5;

public class PrimitiveTypes {
    /**
     * Bootcamp
     *
     * @param x The int
     * @return count number of bits set to 1
     */
    public static short countBits(int x) {
        short sum = 0;
        while (x > 0) {
            sum += (x & 1);
            x >>= 1;
        }
        return sum;
    }
}
