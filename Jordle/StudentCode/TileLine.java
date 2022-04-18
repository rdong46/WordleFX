import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

/**
 * This represents a single tile line, which represents a 5-letter word, where the user will enter their guess.
 * @author rdong46
 * @version 13.3.1
 */
public class TileLine {
    private HBox tileLine;
    private Tile[] tiles;
    /**
     * The constructor for the tileline class.
     */
    public TileLine() {
        this.tileLine = new HBox(4);
        this.tiles = new Tile[5];
        for (int i = 0; i < 5; i++) {
            this.tiles[i] = new Tile();
            tileLine.getChildren().add(this.tiles[i].getStackPane());
        }
        tileLine.setAlignment(Pos.CENTER);
    }
    /**
     *
     * @return the hbox that hold a tileline
     */
    public HBox getTileLine() {
        return this.tileLine;
    }
    /**
     *
     * @return the tile array that consists of tiles
     */
    public Tile[] getTiles() {
        return this.tiles;
    }
}