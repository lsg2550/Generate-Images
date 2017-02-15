package utils.notifications;

import gfx.gui.GUI;
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

    private Stage stage = new Stage();
    private Desktop dt;

    public ConfirmationBox() {
        buildStage();
    }

    private void buildStage() {
        //UI
        VBox vb = new VBox(10);
        HBox hb = new HBox(10);
        Button yes = new Button("Yes"),
                no = new Button("No");
        Text text = new Text("Would you like to open the image?");
        Scene scene = new Scene(vb, 200, 100);

        //HBox
        hb.getChildren().addAll(yes, no);
        hb.setAlignment(Pos.CENTER);

        //VBox
        vb.getChildren().addAll(text, hb);
        vb.setAlignment(Pos.CENTER);

        //Handlers
        yes.setOnAction(e -> {
            /*
            try {
                stage.close();
                dt = Desktop.getDesktop();
                dt.open(location);
            } catch (IOException ex) {
            }
             */
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
        stage.initOwner(GUI.getScene().getWindow());
    }

    public void show(File location) {
        stage.show();
    }
}
