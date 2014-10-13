import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VirtualKeyboardExample extends Application {

    private boolean keyboardNotShown = true;

    @Override
  public void start(final Stage primaryStage) {

    final TextField textField = new TextField();
    textField.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("text field: " + textField.getText());
        }
    });


        final VBox root = new VBox(5);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root);

        final VirtualKeyboard vkb = new VirtualKeyboard();

        // just add a border to easily visualize the boundary of the keyboard:
        vkb.view().setStyle("-fx-border-color: darkblue; -fx-border-radius: 5;");
        final Button showKeyboardButton = new Button("Show Keyboard!");
        showKeyboardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (keyboardNotShown) {
                    root.getChildren().add(vkb.view());
                    primaryStage.sizeToScene();
                } else {
                    root.getChildren().remove(vkb.view());
                    primaryStage.sizeToScene();
                }
                keyboardNotShown = !keyboardNotShown;
            }
        });

        root.getChildren().addAll(textField, showKeyboardButton);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

  public static void main(String[] args) {
    launch(args);
  }

}