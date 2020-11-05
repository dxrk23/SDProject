package sample;

public class MinimaxSingletone {
    //init minimax
    private static Minimax minimax = null;

    public Minimax getInstance()
    {
        //if minimax wasnt inited before  , we init it
        if (minimax == null) minimax = new Minimax();

        return this.minimax;
    }

}
