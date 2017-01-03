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

    public AlertBox(String message, Scene owner) {
        alertStage(message, owner);
    }

    private void alertStage(String message, Scene owner) {
        //UI
        VBox vb = new VBox(10);
        Button ok = new Button("OK");
        Scene scene = new Scene(vb, 200, 100);
        Stage stage = new Stage();

        //VBox
        vb.getChildren().addAll(new Text(message), ok);
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
        stage.initOwner(owner.getWindow());
        stage.show();
    }
}
