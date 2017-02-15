package utils.caching;

/**
 *
 * @author Luis
 */
class FindList {

    private static String[] engAlpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
        "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    protected static String getChar(String fileName) {
        for (int i = 0; i < engAlpha.length; i++) {
            if (fileName.contains(engAlpha[i])) {
                return engAlpha[i];
            }
        }
        return "";
    }
}
