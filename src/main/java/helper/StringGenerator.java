package helper;

import java.util.Random;

public class StringGenerator {
    public static String generateString(int length)
    {
        Random rng = new Random();
        String characters = "new courier 12345";
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
