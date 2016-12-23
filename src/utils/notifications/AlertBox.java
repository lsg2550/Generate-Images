package utils.notifications;

import gfx.GUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class AlertBox {

    public AlertBox(String Message) {
        alertStage(Message);
    }

    private void alertStage(String Message) {
        //UI
        VBox vb = new VBox(10);
        Button ok = new Button("OK");
        Stage stage = new Stage();
        Scene scene = new Scene(vb, 200, 100);
        
        //VBox
        vb.getChildren().addAll(new Text(Message), ok);
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
        stage.showAndWait();
    }
}
