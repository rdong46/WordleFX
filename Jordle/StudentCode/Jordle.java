import javafx.scene.Scene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
/**
 * This represents the interface that diplays everything to the user.
 * @author rdong46
 * @version 13.3.1
 */
public class Jordle extends Application {
    @Override
    public void start(Stage primaryStage) {
        UpdateGame updateGame = new UpdateGame();
        Scene scene = new Scene(updateGame.getVBox(), 650, 700);
        updateGame.startGame(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            updateGame.handleKeyPressed(e);
        });
        updateGame.getInstuctionButton().setOnAction(
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    updateGame.showInstructions();
                }
            }
        );
        updateGame.getResetButton().setOnAction(
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    updateGame.resetGame();
                }
            }
        );
    }
}