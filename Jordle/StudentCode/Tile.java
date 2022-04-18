import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * This represents a single tile where the user will enter their guess.
 * @author rdong46
 * @version 13.3.1
 */
public class Tile {

    private static final int WIDTH = 62;
    private static final int HEIGHT = 62;
    private static final Font FONT = Font.font("Verdana", FontWeight.BOLD, 32);;
    private String letter;
    private Status status;
    private Label label;
    private Rectangle rectangle;
    private StackPane stackpane;
    /**
     * This is a constuctor for the tile class.
     *
     */
    public Tile() {
        this.letter = "";
        this.status = Status.EMPTY;
        this.rectangle = new Rectangle(WIDTH, HEIGHT);
        this.label = new Label(letter);
        this.label.setFont(FONT);
        this.rectangle.setFill(Color.TRANSPARENT);
        this.rectangle.setStroke(Color.LIGHTGRAY);
        this.rectangle.setStrokeWidth(2);
        this.stackpane = new StackPane();
        stackpane.getChildren().addAll(this.rectangle, this.label);
    }
    /**
     *
     * @return return the current letter of this tile
     */
    public String getLetter() {
        return this.letter;
    }
    /**
     *
     * @param letter takes in a single character to set the letter for this tile
     */
    public void setLetter(String letter) {
        this.letter = letter;
        this.label.setText(letter);
    }
    /**
     *
     * @return the status of the tile
     */
    public Status getStatus() {
        return this.status;
    }
    /**
     *
     * @param status the status of the tile
     */
    public void setStatus(Status status) {
        this.status = status;
        switch (this.status) {
        case EMPTY:
            this.rectangle.setFill(Color.TRANSPARENT);
            this.label.setTextFill(Color.BLACK);
            this.rectangle.setStroke(Color.LIGHTGRAY);
            break;
        case CORRECT:
            this.rectangle.setFill(Color.LIMEGREEN);
            this.label.setTextFill(Color.WHITE);
            this.rectangle.setStroke(Color.TRANSPARENT);
            break;
        case WRONG:
            this.rectangle.setFill(Color.GRAY);
            this.label.setTextFill(Color.WHITE);
            this.rectangle.setStroke(Color.TRANSPARENT);
            break;
        case SIMILAR:
            this.rectangle.setFill(Color.KHAKI);
            this.label.setTextFill(Color.WHITE);
            this.rectangle.setStroke(Color.TRANSPARENT);
            break;
        case FILL:
            this.rectangle.setFill(Color.TRANSPARENT);
            this.rectangle.setStroke(Color.DIMGRAY);
            this.label.setTextFill(Color.BLACK);
            break;
        default:
        }
    }
    /**
     *
     * @return the label of the tile
     */
    public Label getLabel() {
        return this.label;
    }
    /**
     *
     * @return the rectangle that makes the tile
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }
    /**
     *
     * @return the stackpane that hold both the rectangle and the label
     */
    public StackPane getStackPane() {
        return this.stackpane;
    }
}