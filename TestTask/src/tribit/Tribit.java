package tribit;


public class Tribit {

    //binary string from task
    private static String tribit = "";

    //array of size lines for pyramid
    private static int[] lines;

    //array of pyramids
    private static String[] pyramids;

    //check for simplify
    private static boolean isNextIteration = false;

    private static String[] pyramidBeforeTransform = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010",
            "1011", "1100", "1101", "1110", "1111"};

    private static String[] pyramidAfterTransform = {"0000", "1000", "0001", "0010", "0000", "0010", "1011", "1011", "0100", "0101", "0111",
            "1111", "1101", "1110", "0111", "1111"};

    public static void setTribit(String tribit) {
        Tribit.tribit = tribit;
    }



    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(tribit);
        while (tribit.length() > 1) {
            createPyramids();
            while (!isNextIteration) {
                transform();
                checkSum();
            }
            isNextIteration = false;
        }
        System.out.println("Time for calculation: " + (System.currentTimeMillis() - start) + "ms");
    }

    //split string to array pyramids
    public static void createLines(){
        /** method createPyramids() filled of array lines. Each value is length of level of pyramid.
         *        ^
         *       /0\  <- lines[0] = 1
         *      *---*
         *     /0\1/0\  <- lines[1] = 3
         *    *---*---*
         *   /1\1/0\0/0\  <- lines[2] = 5
         *
         *   and so on...
         */

        //length of the array "lines" is equal to the square root of the length of the line "tribit"
        lines = new int[(int) Math.sqrt(tribit.length())];

        for (int i = 0, j = 1; i < Math.sqrt(tribit.length()); i++, j += 2) {
            lines[i] = j;
        }
    }

    public static void createPyramids() {

        createLines();
        //length of the array "lines" is equal to the quarter of the length of the line "tribit"
        pyramids = new String[tribit.length()/4];
        //number of pyramids
        int pyramidsNum = 0;

        //line counters for create pyramids
        int bigLineCounter = 0;
        int smallLineCounter = 0;
        /**                                   ...and so on...
         *         *---*---*---*---*         ----------------------------
         *        /0\0/0\1/0\1/1\0/1\        <- "Small Line" \
         *       *---*---*---*---*---*                         > iteration #2
         *      /0\0/0\0/1\0/0\0/1\0/0\      <- "Big Line"   /
         *     *---*---*---*---*---*---*     ----------------------------
         *    /0\1/0\1/0\1/0\1/1\0/1\1/1\    <- "Small Line" \
         *   *---*---*---*---*---*---*---*                     > iteration #1
         *  /1\1/1\0/0\0/0\1/0\0/1\0/0\1/1\  <- "Big Line"   /
         * *---*---*---*---*---*---*---*---*
         *
         *  But really bigLineCounter and smallLineCounter work like this:
         *
         *  num bits in tribit     | 00 | 01 | ... | 14 | 15 | 16 | ... | 27 | 28 | 29 | ... | 38 | 39 | 40 | ... | 47 |
         *                         *----*----*-- --*----*----*----*-- --*----*----*-- --*----*----*----*----|-- --|----|
         *  smallLineCounter       |    |    | ... |    | #1 |    | ... |end1|    |    | ... |    | #2 |    | ... |end2|
         *  bigLineCounter         | #1 |    | ... |end1|    |    | ... |    | #2 |    | ... |end2|    |    | ... |    |
         *
         */

        //StringBuilder for output transformed tribit
        StringBuilder tribitBuilder = new StringBuilder(tribit);

        initPyramids(bigLineCounter, smallLineCounter, pyramidsNum, tribitBuilder);

        //output the created pyramids
        System.out.println(tribitBuilder);
    }


    public static void initPyramids(int bigLineCounter, int smallLineCounter, int pyramidsNum, StringBuilder tribitBuilder){
        for (int i = lines.length - 1; i >= 0; i -= 2) {
            /**
             *
             *
             *
             * For a pyramid with 16 cells, the rules apply in groups of 4:
             *
             *
             *                 ^
             *                /A\
             *               *---*
             *              /A\A/A\
             *             *---*---*
             *            /B\C/C\C/D\
             *           *---*---*---*
             *          /B\B/B\C/D\D/D\
             *         *---*---*---*---*
             *
             *
             *  where in groups A, B and D the bits numbered like this:
             *
             *                 ^
             *                /0\
             *               *---*
             *              /1\2/3\
             *             *---*---*
             *
             *
             *  the group C is upside-down and the bits numbered like this:
             *
             *             *---*---*
             *              \1/2\3/
             *               *---*
             *                \0/
             *                 *
             *
             *
             *
             *  In loop while array pyramids[j] filled values from tribit
             *
             *  num bits in tribit     | 00 | 01 | 02 | 03 | 04 | 05 | 06 | ... | 14 | 15 | 16 | 17 | 18 | 19 | ...
             *                         *----*----*----*----*----*----*----*-- --*----*----*----*----*----*----*--
             *          pyramids[0]    |  1 |  1 |  0 |    |    |    |    | ... |    |  1 |    |    |    |    | is 1101
             *          pyramids[1]    |    |    |    |  0 |    |    |    | ... |    |    |  1 |  1 |  0 |    | is 1100
             *          pyramids[2]    |    |    |    |    |  1 |  0 |  0 | ... |    |    |    |    |    |  1 | is 1001
             *                                 and so on...
             */

            smallLineCounter += lines[i];           //smallLineCounter in this cycle begins with the following "Small Line"
            int counter = lines[i];                 //counter for lines[i]
            while (counter > 0) {
                createOnePyramid(pyramidsNum, bigLineCounter, smallLineCounter, tribitBuilder);
                bigLineCounter += 3;                //bigLineCounter add traversed 3 bits
                smallLineCounter += 1;              //smallLineCounter add traversed 1 bit
                counter -= 3;
                pyramidsNum++;                      //next pyramid
                //additional check so as not to overstep the bounds of the array in the last pyramid in loop for
                if (counter > 0) {
                    createOnePyramid(pyramidsNum, smallLineCounter, bigLineCounter, tribitBuilder);
                    smallLineCounter += 3;          //smallLineCounter add traversed 3 bit
                    bigLineCounter += 1;            //bigLiteCounter add traversed 1 bit
                    counter -= 1;
                    pyramidsNum++;                  //next pyramid
                }
            }
            bigLineCounter += lines[i-1];           //bigLineCounter in the next cycle begins with the following "Big Line"
        }
    }

    public static void createOnePyramid(int pyramidsNum, int firstLineCounter, int secondLineCounter, StringBuilder tribitBuilder){
        pyramids[pyramidsNum] = transform(tribit.substring(firstLineCounter, firstLineCounter + 3) +
                tribit.substring(secondLineCounter, secondLineCounter + 1));

        replaceBitsInTribit(tribitBuilder, firstLineCounter, secondLineCounter, pyramids[pyramidsNum]);

    }

    public static void replaceBitsInTribit(StringBuilder tribitBuilder, int firstLineCounter, int secondLineCounter, String str) {
        tribitBuilder.replace(firstLineCounter, firstLineCounter + 3, str.substring(0, 3));
        tribitBuilder.replace(secondLineCounter, secondLineCounter + 1, str.substring(3));
    }


    public static String transform(String s) {
        /**
         * method transformed pyramid by one of next rules:
         *  old value transform to ->  new value
         *      0000        ->      0000
         *      0001        ->      1000
         *      0010        ->      0001
         *      0011        ->      0010
         *      0100        ->      0000
         *      0101        ->      0010
         *      0110        ->      1011
         *      0111        ->      1011
         *      1000        ->      0100
         *      1001        ->      0101
         *      1010        ->      0111
         *      1011        ->      1111
         *      1100        ->      1101
         *      1101        ->      1110
         *      1110        ->      0111
         *      1111        ->      1111
         *
         */
        for (int i = 0; i < pyramidBeforeTransform.length; i++) {
            if (s.equals(pyramidBeforeTransform[i])) {
                s = pyramidAfterTransform[i];
                break;
            }
        }
        return s;
    }


    public static void transform() {
        /**
         *  method transformed pyramids and create new string "tribit"
         *  transformed pyramids by next rules:
         *  old values transform to ->  new values
         *      0000        ->      0000
         *      0001        ->      1000
         *      0010        ->      0001
         *      0011        ->      0010
         *      0100        ->      0000
         *      0101        ->      0010
         *      0110        ->      1011
         *      0111        ->      1011
         *      1000        ->      0100
         *      1001        ->      0101
         *      1010        ->      0111
         *      1011        ->      1111
         *      1100        ->      1101
         *      1101        ->      1110
         *      1110        ->      0111
         *      1111        ->      1111
         *
         *  tribit is created from pyramids
         *
         */
        StringBuilder tribitBuilder = new StringBuilder();
        StringBuilder sbBigLine = new StringBuilder();
        StringBuilder sbSmallLine = new StringBuilder();
        int linesCounter = lines.length - 1;
        int counter = lines[linesCounter] / 2;
        for (int i = 0; i < pyramids.length; i++) {
            for (int j = 0; j < pyramidBeforeTransform.length; j++) {
                if (pyramids[i].equals(pyramidBeforeTransform[j])){
                    pyramids[i] = pyramidAfterTransform[j];
                    break;
                }
            }
            if (counter == 0) {
                linesCounter -= 2;
                counter = lines[linesCounter] / 2;
                appendToTribit(tribitBuilder, sbBigLine, sbSmallLine);
                sbBigLine = new StringBuilder();
                sbSmallLine = new StringBuilder();
            }
            if (counter % 2 == 1) {
                appendToBuilders(sbBigLine, sbSmallLine, pyramids[i]);
                counter--;
            } else {
                appendToBuilders(sbSmallLine, sbBigLine, pyramids[i]);
                counter--;
            }
            if (linesCounter == 1) {
                appendToTribit(tribitBuilder, sbBigLine, sbSmallLine);
                sbBigLine = new StringBuilder();
                sbSmallLine = new StringBuilder();
            }
        }
        System.out.println(tribitBuilder);
    }

    public static void appendToBuilders(StringBuilder firstSB, StringBuilder secondSB, String currentString) {
        firstSB.append(currentString.substring(0, 3));
        secondSB.append(currentString.substring(3));
    }


    public static void appendToTribit(StringBuilder main, StringBuilder firstStringBuilder, StringBuilder secondStringBuilder) {
        main.append(firstStringBuilder);
        main.append(secondStringBuilder);
    }

    public static void checkSum(){
        int checkSum = 0;
        StringBuilder sb = new StringBuilder();
        for (String pyramid : pyramids) {
            if (pyramid.equals("0000")) {
                checkSum++;
                sb.append(0);
            }
            else if (pyramid.equals("1111")) {
                checkSum++;
                sb.append(1);
            } else {
                break;
            }
            if (checkSum == pyramids.length) {
                tribit = sb.toString();
                System.out.println(tribit);
                isNextIteration = true;
            }
        }
    }

}
