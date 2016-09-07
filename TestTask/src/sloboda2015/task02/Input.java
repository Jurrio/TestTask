package sloboda2015.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yurii Mikhailichenko on 18.08.2015.
 * For Sloboda Studio. Test task #02
 *
 *
 * this class is a class for input and check values.
 *
 * @since 1.0.3
 */
public class Input {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /*
     * The method for input any integers values
     */
    public int inputInteger(int maxValue) throws IOException {
        int num = 0;
        try {
            num = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Input integer from 0 to " + maxValue + " Try again!");
            num = inputInteger(maxValue);
        }
        if (num > maxValue || num < 0) {
            System.out.println("Max value is " + maxValue + ", min value is 0. Try again!");
            num = inputInteger(maxValue);
        }
        return num;
    }

    public String inputCityName() throws IOException {
        String s = reader.readLine();
        /*
         * NAME [city name]
         *
         * The name of a city is a string containing characters a,...,z and is at most 10 characters long.
         */
        Pattern p = Pattern.compile("[a-z]{1,10}");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            System.out.println("Input the string with low letter a..z, without digits and specific symbols. " +
                    "Length of city name is 10 symbols max.");
            s = inputCityName();
        }
        return s;
    }

    public String inputDoubleInteger(int maxValue) throws IOException {
        String s = reader.readLine();
        /*
         * [nr - index of a city connected to NAME (the index of the first city is 1)] [cost - the transportation cost]
         */
        Pattern p = Pattern.compile("[0-9]+\\s[0-9]+");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            System.out.println("Error! Input two integer separated by a space.");
            s = inputDoubleInteger(maxValue);
        }
        String[] strings = s.split(" ");
        try {
            if (Integer.parseInt(strings[0]) > maxValue) throw new NumberFormatException(strings[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error! Max ID of city in this test is " + maxValue + ". You input: " + strings[0]
                    + ". Try again!");
            s = inputDoubleInteger(maxValue);
        }
        return s;
    }

    public String inputPath() throws IOException {
        String s = reader.readLine();
        /*
         * NAME1 NAME2 [NAME1 - source, NAME2 - destination]
         */
        Pattern p = Pattern.compile("[a-z]{1,10}\\s[a-z]{1,10}");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            System.out.println("Input two city name separated by space");
            s = inputPath();
        }
        return s;
    }

    public boolean checkHelp() throws IOException {
        /*
         * Method for turn on the helper
         */
        System.out.print("Input 'help' for help mode on or anything for continue without help: ");
        String helpString = reader.readLine();
        if (helpString.equals("help")) return true;
        return false;
    }
}
