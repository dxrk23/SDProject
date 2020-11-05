package sample;

//Copy righted by team #1 SE1908

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    //2D array of Tiles
    public static Tile[][] board = new Tile[3][3];
    //Win check object
    //in this class we just add win condition combos to WinCheck's ArrayList
    private final WinCheck winCheck = new WinCheck();

    //Create Application window

    //menu line
    MenuBar menuBar = new MenuBar();
    //Dropdown for menu
    Menu menuGame = new Menu("Game");

    //options in dropdown
    MenuItem menuHuman = new MenuItem("Play vs human");
    MenuItem menuAI = new MenuItem("Play vs AI");
    MenuItem newGame = new MenuItem("New Game");

    //menu can be glued only to BorderPane
    BorderPane root = new BorderPane();
    //Simple pane will be add as a BorderPane's child
    Pane pane = new Pane();

    //Specify window
    public Parent createContent()
    {
        //Set child pane in this middle
        root.setCenter(pane);

        //Specify size of window
        //setPrefSize(x , y)
        pane.setPrefSize(600 , 600);

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //Creating tiles
                //tile extended from StackPane and its just pane inside main pane
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);


                //adding tile as a child of Pane
                pane.getChildren().add(tile);
                //save tiles in 2d array made above
                board[j][i] = tile;
            }
        }

        //add dropdown options to dropdown
        menuGame.getItems().addAll(menuHuman, menuAI , newGame);
        //add to menu line new dropdown
        menuBar.getMenus().addAll(menuGame);
        //glue menu to pane
        root.setTop(menuBar);


        //OnClick events for dropdown events
        menuHuman.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Tile.vsAI = false;
            }
        });

        menuAI.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Tile.vsAI = true;
            }
        });

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                newGame();
            }
        });
            
        //Init Observer for Tile.gameOver condition
        //gameOver is static, any tile is ok
        winCheck.SubscribeBus(board[0][0]);

        //Adding win conditions to WinCheck
        //private static List<WinConditionCombo> WinConditions= new ArrayList<>();

        //WinConditionCombo
        //private Tile[] tile;

        //horizontal
        for (int y = 0; y < 3; y++) {
            winCheck.add(new WinConditionCombo(board[0][y], board[1][y], board[2][y]));
        }
        //vertical
        for (int x = 0; x < 3; x++) {
            winCheck.add(new WinConditionCombo(board[x][0], board[x][1], board[x][2]));
        }
        //diagonals
        winCheck.add(new WinConditionCombo(board[0][0], board[1][1], board[2][2]));
        winCheck.add(new WinConditionCombo(board[2][0], board[1][1], board[0][2]));

        //return our Specified window
        return root;
    }

    public void newGame()
    {
        for (Tile[] tile : board)
        {
            for (Tile tile2 : tile)
            {
                tile2.erase();
            }
        }
        Tile.newGame();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create window
        primaryStage.setScene(new Scene(createContent()));
        //Specify name to window
        primaryStage.setTitle("TIC TAC TOE");
        //Render window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
