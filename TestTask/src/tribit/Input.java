package tribit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ларочка on 04.09.2015.
 */
public class Input {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.print("Enter the number: ");
        long num = inputInteger();
        String bin_str = Long.toBinaryString(num);
        while (bin_str.length() != 4) {
            bin_str = "0" + bin_str;
        }
        Tribit.setTribit(bin_str);
        Tribit.main(args);

    }

    public static long inputInteger() throws IOException {
        long num = 0;
        try {
            num = Long.parseLong(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Input long! Try again!");
            num = inputInteger();
        }
        return num;
    }
}
