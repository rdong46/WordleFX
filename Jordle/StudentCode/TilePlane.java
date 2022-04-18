import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

/**
 * This represents a single plane which holds multiple tileline. This is the game board of Jordle.
 * @author rdong46
 * @version 13.3.1
 */
public class TilePlane {
    private VBox tilePlane;
    private TileLine[] tileLines;
    /**
     * The constructor for the tileplane class.
     */
    public TilePlane() {
        this.tilePlane = new VBox(5);
        this.tileLines = new TileLine[6];
        for (int i = 0; i < 6; i++) {
            this.tileLines[i] = new TileLine();
            tilePlane.getChildren().add(this.tileLines[i].getTileLine());
        }
        tilePlane.setAlignment(Pos.CENTER);
    }
    /**
     *
     * @return the tile plane
     */
    public VBox getTilePlane() {
        return this.tilePlane;
    }
    /**
     *
     * @return the array of tile lines
     */
    public TileLine[] getTileLines() {
        return this.tileLines;
    }
}