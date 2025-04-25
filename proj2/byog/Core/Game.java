package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.lab5.RandomWorldDemo;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public StringBuilder findseed (String S){
         int index;
         index = 0;
         StringBuilder seedBuilder = new StringBuilder();
         if(S.charAt(0) == 'n'||S.charAt(0) == 'N'){
             index = 1;
             for( ; index<S.length(); index++){
                 if( Character.isDigit(S.charAt(index))){
                     seedBuilder.append(S.charAt(index));
                 }
                 if( S.charAt(index) == 's'||S.charAt(index) == 'S'){
                     return seedBuilder;
                 }
             }


         }
         return seedBuilder;
    }
    public TETile[][] playWithInputString(String input) {
            StringBuilder sd=findseed(input);
            long seed = Long.parseLong(sd.toString());
             World world= new World(WIDTH,HEIGHT,seed);
             return world.getTiles();
        //ter.initialize(WIDTH, HEIGHT);
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

       // TETile[][] finalWorldFrame = null;
        //return finalWorldFrame;
    }
   /* public static void main(String[] args) {
        Game game = new Game();
        TETile[][] tiles;
        tiles=game.playWithInputString("n865562189400100566s");
        tiles=game.playWithInputString("n8272166368955537510s");
        tiles=game.playWithInputString("n8554565356223690293s");
        // tiles = game.playWithInputString("n865562189400100566s");
        game.ter.initialize(WIDTH, HEIGHT);
        game.ter.renderFrame(tiles);
    }*/
}
