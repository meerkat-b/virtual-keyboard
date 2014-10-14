import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class VirtualKeyboardExample extends Application {

    private boolean keyboardNotShown = true;

    @Override
  public void start(final Stage primaryStage) {

    final TextField textField = new TextField();
    final TextField textField2 = new TextField();

//    textField.setOnAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent event) {
//            System.out.println("text field: " + textField.getText());
//        }
//    });


        final VBox root = new VBox(5);
        root.setPadding(new Insets(10));
        final Scene scene = new Scene(root);

        final VirtualKeyboard vkb = new VirtualKeyboard();

        // just add a border to easily visualize the boundary of the keyboard:
        vkb.setStyle("-fx-border-color: darkblue; -fx-border-radius: 5;");

        //button which adds and removes the keyboard to the root scene
        final Button showKeyboardButton = new Button("Show Keyboard!");
        showKeyboardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (keyboardNotShown) {
                    root.getChildren().add(vkb);
                } else {
                    root.getChildren().remove(vkb);
                }
                keyboardNotShown = !keyboardNotShown;
                primaryStage.sizeToScene();
            }
        });

        VirtualKeyboard popupVkb = new VirtualKeyboard();
        final Stage keyboardPopupWindow = new Stage();
        keyboardPopupWindow.initModality(Modality.NONE);
        keyboardPopupWindow.initOwner(primaryStage);
        Scene keyboardScene = new Scene(popupVkb);
        keyboardPopupWindow.setScene(keyboardScene);
        
        final Button popupKeyboardButton = new Button("Open Keyboard!");
        popupKeyboardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (keyboardNotShown) {
                    keyboardPopupWindow.show();
                } else {
                    keyboardPopupWindow.close();
                }
                keyboardNotShown = !keyboardNotShown;
            }
        });

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue) {
                    popupVkb.setTarget(new SimpleObjectProperty(textField));
                }
            }
        });

        textField2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue) {
                    popupVkb.setTarget(new SimpleObjectProperty(textField2));
                }
            }
        });
//        textField.setOnTouchPressed((mouseEvent) -> {
//            System.out.println("Mouse entered textfield 1");
//            popupKeyboard.setTarget(new SimpleObjectProperty(textField));
//        });
//        textField2.setOnTouchPressed((mouseEvent) -> {
//            System.out.println("Mouse entered textfield 2");
//            popupKeyboard.setTarget(new SimpleObjectProperty(textField2));
//        });

        root.getChildren().addAll(textField, textField2, showKeyboardButton, popupKeyboardButton);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

  public static void main(String[] args) {
    launch(args);
  }

}