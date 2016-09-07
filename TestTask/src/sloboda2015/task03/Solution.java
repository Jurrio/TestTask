package sloboda2015.task03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 *
 * Created Yurii Mikhailichenko on 15.08.2015
 * for Sloboda Studio. Test task #03
 *
 * This task calculate sum of the digits of the number 100!
 *
 * Example:
 * 7! = 40320
 * sumDigits(40320) = 4 + 0 + 3 + 2 + 0 = 9
 *
 */
public class Solution {
    private int number;                 //number for calculation
    private BigInteger factOfNum;       //factorial of @number
    private int sum;                    //sum digits in @factOfNum


    public Solution(int num) {
        this.number = num;
        this.factOfNum = factorial(number);
    }

    public static void main(String[] args) throws IOException {
        //long start = System.currentTimeMillis();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	int number = Integer.parseInt(reader.readLine());
        Solution solution = new Solution(number);
        solution.sumDigits(solution.factOfNum);
        //System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
    }

    public BigInteger factorial(int num) {
        BigInteger integer = BigInteger.valueOf(1);
        while (num > 0) {
            integer = integer.multiply(BigInteger.valueOf(num));
            num--;
        }
        return integer;
    }

    public void sumDigits(BigInteger bigInteger) {
        while (bigInteger.bitLength() > 0){
            sum += Integer.parseInt(String.valueOf(bigInteger.mod(BigInteger.valueOf(10))));
            bigInteger = bigInteger.divide(BigInteger.valueOf(10));
        }
        System.out.println("Factorial of " + number + " is " + factOfNum + ", \nSum of the digits: " + sum);
    }
}
