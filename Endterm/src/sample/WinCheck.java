package sample;

import java.util.ArrayList;
import java.util.List;

public class WinCheck {

    //Array list private Tile[] tile;
    private static final List<WinConditionCombo> WinConditions= new ArrayList<>();
    //tile for Observer
    private Tile tile = null;

    //gets all Tile[] tile from Arraylist, and check if winCondition combo is done oe not
    public void checkWinCondition() {
        for (WinConditionCombo WinConditionCombo : WinConditions) {
            if (WinConditionCombo.isDone()) {
                System.out.println("Win");
                //if game is over , observer tells to Tile that game is over
                winStateObserver();
                break;
            }
        }
    }

    //setter for tile
    public void SubscribeBus(Tile tile)
    {
        this.tile = tile;
    }

    //observer
    public void winStateObserver()
    {
        this.tile.gameOver = true;
    }

    //add  Tile[] to Arraylist
    public void add(WinConditionCombo winConditionCombo)
    {
        WinConditions.add(winConditionCombo);
    }

}
