package building.stages;

import building.Handlers;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import utils.operations.enhancednodes.EnhancedButton;
import utils.operations.enhancednodes.EnhancedImageView;
import utils.operations.parsing.TryNode;

/**
 *
 * @author Luis
 */
public class StageBuilds {

    private VBox root = new VBox();
    private EnhancedImageView eImageView;

    public StageBuilds(Image image) { //SubMenus
        eImageView = new EnhancedImageView(image);
        eImageView.setFitWidth(250);
        eImageView.setFitHeight(250);

        CheckBox enableDisable = new CheckBox();

        Handlers.imageViewListener(eImageView);
        Handlers.enableDisable(eImageView, enableDisable);

        root.getChildren().addAll(eImageView, enableDisable);
        root.setAlignment(Pos.CENTER);
    }

    public StageBuilds(ArrayList<Image> imageList) { //MainMenu
        eImageView = new EnhancedImageView(imageList.get(0));
        root.getChildren().add(eImageView);

        if (imageList.size() == 1) { //If ImageList contains 1 image
            CheckBox enableDisable = new CheckBox();
            root.getChildren().add(enableDisable);

            Handlers.imageViewListener(eImageView);
            Handlers.enableDisable(eImageView, enableDisable);
        } else { //If ImageList contains more than 1 image
            EnhancedButton extend = new EnhancedButton("Extend");
            root.getChildren().add(extend);

            Handlers.extendAndCreateSubMenu(imageList, eImageView, extend);
        }

        root.setAlignment(Pos.CENTER);
    }

    public void clearAll() {
        root.getChildren().forEach((node) -> {
            if (TryNode.TryImageView(node)) {
                eImageView.setImage(null);
            } else {
                node = null;
            }
        });
    }

    public VBox getRoot() {
        return root;
    }
}
