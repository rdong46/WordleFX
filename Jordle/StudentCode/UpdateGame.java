import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.text.FontWeight;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.animation.RotateTransition;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Alert;
import javafx.animation.TranslateTransition;
import javafx.scene.text.TextAlignment;
/**
 * This represents the class that handles the changes to the interface based on the user's input.
 * @author rdong46
 * @version 13.3.1
 */
public class UpdateGame {
    private int currentRow;
    private int currentColumn;
    private Text statusText;
    private TilePlane tilePlane;
    private VBox vbox;
    private Text gameName;
    private Button resetButton;
    private Button instructionButton;
    private String currentWord;
    private String enteredWord;
    private boolean winGame;
    /**
     * The constructor of update game class.
     */
    public UpdateGame() {
        this.currentRow = 0;
        this.currentColumn = 0;
        this.statusText = new Text("Try guessing a word!");
        this.tilePlane = new TilePlane();
        this.vbox = new VBox(50);
        this.gameName = new Text("Jordle");
        this.resetButton = new Button("Reset");
        this.instructionButton = new Button("Instructions");
        this.currentWord = WordHandler.randomWord();
        this.enteredWord = "";
        this.winGame = false;
    }
    /**
     *
     * @param primaryStage the stage
     */
    public void startGame(Stage primaryStage) {
        this.vbox.setAlignment(Pos.CENTER);
        Font font = Font.font("Verdana", FontWeight.BOLD, 40);
        this.gameName.setFont(font);
        this.vbox.getChildren().addAll(this.gameName, this.tilePlane.getTilePlane());
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(this.statusText, this.resetButton, this.instructionButton);
        hbox.setAlignment(Pos.CENTER);
        this.vbox.getChildren().add(hbox);
    }
    /**
     * Gets called when the user loses the game.
     */
    public void loseGame() {
        this.statusText.setText(String.format("Game over. The word was %s.", this.currentWord));
    }
    /**
     * Gets called when the user wins the game.
     */
    public void winGame() {
        this.statusText.setText("Congratulations! You’ve guessed the word!");
    }
    /**
     * Gets called when the user resets the game.
     */
    public void resetGame() {
        statusText.setText("Try guessing a word!");
        currentWord = WordHandler.randomWord();
        this.winGame = false;
        this.currentColumn = 0;
        this.currentRow = 0;
        this.enteredWord = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                tilePlane.getTileLines()[i].getTiles()[j].setLetter("");
                tilePlane.getTileLines()[i].getTiles()[j].setStatus(Status.EMPTY);
            }
        }
    }
    /**
     *
     * @param keyEvent the key entered by the user
     */
    public void handleKeyPressed(KeyEvent keyEvent) {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        ArrayList<String> lettersList = new ArrayList<>(Arrays.asList(letters));

        if (lettersList.contains(keyEvent.getText())) {
            if (currentColumn < 5 && currentRow < 6 && !winGame) {
                this.enteredWord += keyEvent.getText();
                this.tilePlane.getTileLines()[currentRow].getTiles()[currentColumn].setLetter(
                    keyEvent.getText().toUpperCase());
                this.tilePlane.getTileLines()[currentRow].getTiles()[currentColumn].setStatus(Status.FILL);
                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setDuration(Duration.millis(50));
                scaleTransition.setNode(
                    this.tilePlane.getTileLines()[currentRow].getTiles()[currentColumn].getRectangle());
                scaleTransition.setByY(0.1);
                scaleTransition.setByX(0.1);
                scaleTransition.setCycleCount(2);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
                currentColumn++;
            }
        } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            if (currentColumn > 0) {
                this.enteredWord = this.enteredWord.substring(0, this.enteredWord.length() - 1);
                currentColumn--;
                this.tilePlane.getTileLines()[currentRow].getTiles()[currentColumn].setLetter("");
                this.tilePlane.getTileLines()[currentRow].getTiles()[currentColumn].setStatus(Status.EMPTY);
            }
        } else if (keyEvent.getCode() == KeyCode.ENTER) {
            if (currentColumn < 5 && currentRow < 6 && !winGame) {
                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setDuration(Duration.millis(50));
                translateTransition.setNode(this.tilePlane.getTileLines()[currentRow].getTileLine());
                translateTransition.setByX(5);
                translateTransition.setCycleCount(6);
                translateTransition.setAutoReverse(true);
                translateTransition.play();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough Letters. Guess again.");
                alert.showAndWait();
                statusText.setText("Not enough Letters. Guess again.");
            } else if (currentRow < 6 && !winGame) {
                flipTile();
                this.winGame = true;
                statusText.setText("Keep guessing.");
                for (int i = 0; i < 5; i++) {
                    if (this.tilePlane.getTileLines()[currentRow].getTiles()[i].getStatus() != Status.CORRECT) {
                        this.winGame = false;
                    }
                }
                if (this.winGame) {
                    winGame();
                    for (int i = 0; i < 5; i++) {
                        TranslateTransition translateTransition1 = new TranslateTransition();
                        translateTransition1.setNode(
                            this.tilePlane.getTileLines()[currentRow].getTiles()[i].getStackPane());
                        translateTransition1.setByY(-15);
                        translateTransition1.setCycleCount(2);
                        translateTransition1.setAutoReverse(true);
                        translateTransition1.setDuration(Duration.millis(500));
                        translateTransition1.play();
                    }
                }
                this.enteredWord = "";
                currentRow++;
                currentColumn = 0;
                if (this.currentRow == 6) {
                    loseGame();
                }
            }
        }
    }
    /**
     * Gets called when the user enters a valid 5-letter word.
     */
    public void flipTile() {
        for (int i = 0; i < 5; i++) {
            RotateTransition rt = new RotateTransition();
            rt.setNode(this.tilePlane.getTileLines()[currentRow].getTiles()[i].getStackPane());
            rt.setAxis(Rotate.X_AXIS);
            rt.setCycleCount(2);
            rt.setDuration(Duration.millis(500));
            rt.setByAngle(90);
            rt.setAutoReverse(true);
            rt.play();
            this.tilePlane.getTileLines()[currentRow].getTiles()[i].setStatus(
                WordHandler.checkIndex(this.enteredWord, i));
        }
    }
    /**
     * Gets called when the user click the instruction button.
     */
    public void showInstructions() {
        Text text = new Text("Hello, welcome to Jordle.");
        text.setTextAlignment(TextAlignment.RIGHT);
        Text text2 = new Text("\nGuess the JORDLE in six tries.");
        text2.setTextAlignment(TextAlignment.CENTER);
        Text text3 = new Text("\nEach guess must be a valid five-letter word.");
        text3.setTextAlignment(TextAlignment.CENTER);
        Text text4 = new Text("\nHit the enter button to submit.");
        text4.setTextAlignment(TextAlignment.CENTER);
        Text text5 = new Text("\nAfter each guess, the color of the tiles will"
            + "\nchange to show how close your guess was to the word."
            + "\n\nIf the letter is in the word and in the right spot, "
            + "\nit will appear as green."
            + "\n\nIf the letter is is the word and is not in the right spot, "
            + "\nit will appear as yellow."
            + "\n\nIf the letter is not in the word, it will appear as grey.");
        text5.setTextAlignment(TextAlignment.CENTER);
        VBox vbox2 = new VBox(text, text2, text3, text4, text5);
        vbox2.setAlignment(Pos.CENTER);
        Scene instruction = new Scene(vbox2, 350, 300);
        Stage stage = new Stage();
        stage.setTitle("Instructions");
        stage.setScene(instruction);
        stage.show();
    }
    /**
     *
     * @return the tile plane
     */
    public TilePlane getTilePlane() {
        return this.tilePlane;
    }
    /**
     *
     * @return the current row
     */
    public int getRow() {
        return this.currentRow;
    }
    /**
     *
     * @return the current column
     */
    public int getColumn() {
        return this.currentColumn;
    }
    /**
     *
     * @return the vbox
     */
    public VBox getVBox() {
        return this.vbox;
    }
    /**
     *
     * @return the current word
     */
    public String getCurrentWord() {
        return this.currentWord;
    }
    /**
     *
     * @return the current instruction button
     */
    public Button getInstuctionButton() {
        return this.instructionButton;
    }
    /**
     *
     * @return the current reset button
     */
    public Button getResetButton() {
        return this.resetButton;
    }
}