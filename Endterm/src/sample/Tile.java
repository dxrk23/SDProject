package sample;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//StackPane is Pane inside main Pane
public class Tile extends StackPane {
    //who goes
    public static boolean turnX = true;
    //game over condition, while false gave is not over
    public static boolean gameOver = false;

    //game result
    public static boolean xWon = false;
    public static boolean oWon = false;
    public static boolean Tie = false;

    //game mode
    public static boolean vsAI = false;

    //counter for tie,  when counter == 9 -> tie
    public static int counter = 0;

    //Object to write text to our tiles
    private final Text text = new Text();

    //WinCheck objects, checks game is over or not
    WinCheck winCheck = new WinCheck();

    //AI for X player
    Minimax minimax = new MinimaxSingletone().getInstance();


    //constructor
    public Tile()
    {
        //Specify that tile is rectangle
        Rectangle border = new Rectangle(200,200);
        //Rect not filled
        border.setFill(null);
        //Black border for rect
        border.setStroke(Color.BLACK);

        //font size
        text.setFont(Font.font(72));
        //default text
        text.setText("");

        //Alignment of tiles by center
        setAlignment(Pos.CENTER);
        //add dependency of text
        getChildren().addAll(border, text);

        //event for MouseClick
        setOnMouseClicked(event -> {
            //when clicked left button of mouse
            if (event.getButton() == MouseButton.PRIMARY)
            {
                //while is not over
                if (!gameOver) {
                    //when X goes
                    if (turnX){
                        //make AI do move
                        if (vsAI) minimax.bestMove();

                        if (getValue().isEmpty()) {
                            if (!vsAI) drawX();
                            turnX = !turnX;
                        }
                        counter++;
                        //check did AI won or nor
                        winCheck.checkWinCondition();

                        //if game is over , x -> won
                        if (gameOver) xWon = true;
                    }
                    if (!turnX) {
                        //if tile is not marked
                        if (getValue().isEmpty()) {
                            System.out.println(1213);
                            //player goes
                            draw0();
                            //gives turn to AI
                            turnX = true;
                            counter++;
                            //check did AI won or nor
                            winCheck.checkWinCondition();
                            if (gameOver) oWon = true;
                        }
                    }
                    //if counter == 9 , means all moves are made , but no one won -> tie
                    if (counter == 9)
                    {
                        gameOver = true;
                        Tie = true;
                    }
                }
            }
        });
    }

    //getter for tile's text
    public String getValue()
    {
        return text.getText();
    }

    //draw X
    public void drawX() {
        text.setText("X");
    }
    //draw 0
    public void draw0()
    {
         text.setText("O");
    }

    //clear tile
    public void erase()
    {
        text.setText("");
    }

    public static void newGame()
    {
        xWon = false;
        oWon = false;
        gameOver = false;
        Tie = false;
        turnX = true;
    }
}
