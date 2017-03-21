package building.stages;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class StageList {

    public static ArrayList<StageBuilds> sbAL = new ArrayList();

    public static void clearBoxes() {
        sbAL.forEach((stageBuilds) -> {
            stageBuilds.clearAll();
        });
    }
}
