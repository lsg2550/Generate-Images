package building;

/**
 *
 * @author Luis
 */
class FindList {

    private static final String[] ENGLISH_ALPHABET = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
        "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    /**
     * Checks word for one of the corresponding characters ^ However, it will
     * only return the last character it matches. For example, if an image is
     * named "testK", the character returned is 'K'
     */
    static String getListDir(String fileName) {
        fileName = fileName.toLowerCase().substring(0, fileName.length() - 4);

        for (String letter : ENGLISH_ALPHABET) {
            if (fileName.contains(letter)) {
                return letter;
            }
        }

        return "";
    }
}
