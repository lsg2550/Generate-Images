package utils.math;

/**
 *
 * @author Luis
 */
public class MPC {

    /**
     *
     * @param <T>
     * @param objects
     * @param otherVariableLength
     * @return a maximum possible combination of items related to the 2D object
     * array in the argument, followed by multiplication of a variable length of
     * what is presumed to be the length of whatever is going to be merged with
     * the 2D object
     */
    public static <T> int maximumPossibleCombination_From2DArray(T[][] objects, int otherVariableLength) {
        //Maximum Possible Combinations
        int mpc = 1;

        //Display Size of Array vs Content in Array
        for (T[] o : objects) {
            //System.out.println("Size of Current Array: " + o.length);
            for (int j = 0; j < o.length; j++) {
                if (o[j] == null) {
                    //System.out.println("Amount of Content of Current Array: " + j);
                    mpc *= j;
                    break;
                }
                if (j == o.length - 1) {
                    //System.out.println("Amount of Content of Current Array: " + o.length);
                    mpc *= o.length;
                    break;
                }
            }
        }

        //Max Possible Combination
        if (otherVariableLength != -1) {
            mpc *= otherVariableLength;
        }

        //Return
        System.out.println("Maximum Possible Combinations: " + mpc);
        return mpc;
    }

}
