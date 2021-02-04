/*

There is a string, s, of lowercase English letters that is repeated infinitely many times. 
Given an integer, n, find and print the number of letter a's in the first n letters of the infinite string.

(https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup)

*/



import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        long result = 0;
        int cntAinS = 0;
        String remChar = s.substring(0, (int)(n%s.length()));
        
        for (int i=s.length()-1; i>=0; i--) {
            if (s.charAt(i) == 'a') {
                cntAinS++;
            }
            if (i < remChar.length() && s.charAt(i) == 'a') {
                result++;
            }
        }
        result += cntAinS * (n/s.length());
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
