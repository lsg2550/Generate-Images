package utils.notifications;

import assets.ico.Icon;
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
    private static Stage stage = new Stage();

    //Message
    private static Text message = new Text("");

    public static void init() {
        //UI
        VBox vb = new VBox(10);
        Button ok = new Button("OK");
        Scene scene = new Scene(vb, 200, 100);

        //VBox
        vb.getChildren().addAll(message, ok);
        vb.setAlignment(Pos.CENTER);

        //Handlers
        ok.setOnAction(e -> {
            stage.close();
        });

        //Stage
        stage.getIcons().add(Icon.ICON);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(GUI.getScene().getWindow());
    }

    public static void show(String alertMessage) {
        message.setText(alertMessage);
        stage.show();
    }
}
