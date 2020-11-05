package sample;

public class WinConditionCombo {
    //array of Tiles
    private final Tile[] tile;

    //Tile... tile means we can any number of tiles
    WinConditionCombo(Tile... tile)
    {
        this.tile = tile;
    }

    //check win condition combo is completed or not
    public boolean isDone() {
        if (tile[0].getValue().isEmpty())
            return false;

        return tile[0].getValue().equals(tile[1].getValue())
                && tile[0].getValue().equals(tile[2].getValue());
    }
}
