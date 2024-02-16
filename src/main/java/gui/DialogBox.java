package gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static Background USER_BG = new Background(new BackgroundFill(Color.web("#AAACD4"), null, null));
    private static Background DESTINY_BG = new Background(new BackgroundFill(Color.web("B4D6D7"), null, null));
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ensure text is sized correctly
        dialog.setText(text);
        dialog.setWrapText(true);
        DialogBox.setHgrow(dialog, Priority.ALWAYS);
        dialog.setMaxWidth(Double.MAX_VALUE);

        // configuring image
        displayPicture.setImage(img);
        displayPicture.setPreserveRatio(false); // prevents cropping
        Circle circleView = new Circle(50,50, 50);
        displayPicture.setClip(circleView); // puts image in circle

        // ensure proper spacing
        this.setSpacing(10.0);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setBackground(USER_BG);
        return db;
    }

    public static DialogBox getDestinyDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setBackground(DESTINY_BG);
        return db;
    }
}

