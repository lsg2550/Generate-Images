package utils.operations.parsing;

/**
 *
 * @author Luis
 */
public class ListDirectoryIdentifier {

    private static final char[] ENGLISH_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z'};

    public static char getListDir(String fileName) {
        fileName = fileName.toLowerCase().substring(0, fileName.length() - 4);
        for (char letter : ENGLISH_ALPHABET) {
            for (int i = fileName.length() - 1; i >= 0; i--) {
                if (fileName.charAt(i) == letter) {
                    return letter;
                }
            }
        }

        return ' ';
    }
}
