package utils.notifications;

import gfx.GUI;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class ConfirmationBox {

    public ConfirmationBox(Scene owner) {
        confirmStage(owner);
    }

    private void confirmStage(Scene owner) {
        //UI
        VBox vb = new VBox(10);
        HBox hb = new HBox(10);
        Button yes = new Button("Yes"),
                no = new Button("No");
        Scene scene = new Scene(vb, 200, 100);
        Stage stage = new Stage();

        //HBox
        hb.getChildren().addAll(yes, no);
        hb.setAlignment(Pos.CENTER);

        //VBox
        vb.getChildren().addAll(new Text("Would you like to open the image?"), hb);
        vb.setAlignment(Pos.CENTER);

        //Handlers
        yes.setOnAction(e -> {
            try {
                stage.close();
                Desktop dt = Desktop.getDesktop();
                dt.open(new File("src/savedImages/getUserInput.png"));
            } catch (IOException ex) {
            }
        });
        no.setOnAction(e -> {
            stage.close();
        });

        //Stage
        stage.getIcons().add(GUI.getICON());
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner.getWindow());
        stage.show();
    }
}
