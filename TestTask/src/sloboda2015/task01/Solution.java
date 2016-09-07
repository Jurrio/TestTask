package sloboda2015.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by Yurii Mikhailichenko on 15.08.2015.
 * For Sloboda Studio. Test task #01
 *
 * This application calculates number of variants of bracket expressions. Read more on task.txt
 *
 */
public class Solution {
    private int countBrackets;
    private BigInteger result;  //if @countBrackets >= 20 than value of @result will exceed Integer.MAX_VALUE, therefore use BigInteger

    public Solution(int num) {
        this.countBrackets = num;
        result = catalan();
    }

    public BigInteger factorial(int num) {
        BigInteger result = BigInteger.valueOf(1);
        while (num > 0) {
            result = result.multiply(BigInteger.valueOf(num));
            num--;
        }
        return result;
    }

    public BigInteger catalan() {
        /** The number of variants of bracket expression is calculated the Catalan numbers. Formula:
         *
         *  C(n) = (2n)!/n!(n+1)!
         *
         *  You can read more on http://en.wikipedia.org/wiki/Catalan_number
         */
        return (factorial(countBrackets * 2)).divide(factorial(countBrackets).multiply(factorial(countBrackets + 1)));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input num: ");
        int num = Integer.parseInt(reader.readLine());
        //long start = System.currentTimeMillis();
        System.out.println(new Solution(num).result);
        //System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
