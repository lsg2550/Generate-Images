package utils.notifications;

import gfx.gui.GUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class AlertBox {

    //UI
    private Stage stage = new Stage(); //Stage
    private VBox vb = new VBox(10);

    public AlertBox() {
        buildStage();
    }

    private void buildStage() {
        //UI
        Button ok = new Button("OK");
        Scene scene = new Scene(vb, 200, 100);

        //VBox
        vb.getChildren().addAll(new Text(""), ok);
        vb.setAlignment(Pos.CENTER);

        //Handlers
        ok.setOnAction(e -> {
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

    public void show(String message) {
        vb.getChildren().remove(0);
        vb.getChildren().add(0, new Text(message));
        stage.show();
    }
}
