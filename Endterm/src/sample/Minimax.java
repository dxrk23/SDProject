package sample;

import java.lang.Math;

public class Minimax {

    //Minimax algorithm principal is
    //First player tries to Maximize win chance , so he is maximizer
    //Second player tries to minimize first player's win chance, he is minimizer
    //So this is why algorithm called Minimax
    //It iterates through all possible moves , and calculates best moves for each player

    public void bestMove()
    {
        //specify from beginning very big negative number
        double bestScore = Double.NEGATIVE_INFINITY;
        //rows for best move
        int xRow = 0;
        int yRow = 0;

        //our board , which we created and filled in TicTacToe.createContent()
        Tile[][] tiles = TicTacToe.board;

        //nested for loop goes through all possible moves
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //check if this tile is available
                if (tiles[i][j].getValue().equals(""))
                {
                    //if it s available drawsX
                    tiles[i][j].drawX();
                    //gets move score from minimax recurssion func
                    double score = minimax(tiles, false);
                    //when we checked this move, we clear it
                    tiles[i][j].erase();
                    //if this move better than previous , re-record it
                    if (score > bestScore)
                    {
                        bestScore = score;
                        xRow = i;
                        yRow = j;
                    }
                }
            }
        }
        //draws best move
        TicTacToe.board[xRow][yRow].drawX();
        //passes move to human
        Tile.turnX = false;
    }

    public double minimax(Tile[][] board , boolean isMaximizing)
    {
        //basecase , if game ends , we will get score for this moves
        if (Tile.gameOver)
        {
            //if X one +10 points , means good move
            if (Tile.xWon) return 10;
            //if O wins , its bad
            else if (Tile.oWon) return -10;
            //tie is neutral
            else return 0;
        }

        //checks move for X
        if (isMaximizing)
        {
            //specify from begining very big negative number, can be -99999999
            double bestScore = Double.NEGATIVE_INFINITY;
            //does same as in BestMove()
            //nested for loop goes through all possible moves
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    //check if this tile is available
                    if (TicTacToe.board[i][j].getValue().equals(""))
                    {
                        //if it s available drawsX
                        TicTacToe.board[i][j].drawX();
                        //calls recursion , calculates human moves
                        double score = minimax(board, false);
                        //when we checked this move, we clear it
                        TicTacToe.board[i][j].erase();
                        //if this move better than previous , re-record it
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
        else
        {
            //just very big positive number, can be 9999999
            double bestScore = Double.POSITIVE_INFINITY;
            //nested for loop goes through all possible moves
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    //check if this tile is available
                    if (TicTacToe.board[i][j].getValue().equals(""))
                    {
                        //if it s available draws 0
                        TicTacToe.board[i][j].draw0();
                        //calls recursion , calculates AI moves
                        double score = minimax(board,true);
                        //when we checked this move, we clear it
                        TicTacToe.board[i][j].erase();
                        //if this move better than previous , re-record it
                        bestScore = Math.min(score , bestScore);
                    }
                }
            }
            //return bestScore
            return bestScore;
        }
    }
}
