package byog.SaveDemo;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.Color;
import java.io.Serializable;

public class World implements Serializable {
    private static final long serialVersionUID = 123123123123123L;
    private List<Square> squares;
    private Random r = new Random();
    private  long seed;
    TETile[][] randomTiles;
    public World() {
        squares = new ArrayList<Square>();
    }

    public void addRandomSquare() {
        double x = RandomUtils.uniform(r, 0, 1.0);
        double y = RandomUtils.uniform(r, 0, 1.0);
        double size = RandomUtils.uniform(r, 0.01, 0.1);
        int red = RandomUtils.uniform(r, 0, 256);
        int green = RandomUtils.uniform(r, 0, 256);
        int blue = RandomUtils.uniform(r, 0, 256);
        Color c = new Color(red, green, blue);
        Square newSquare = new Square(x, y, size, c);
        squares.add(newSquare);
    }
    public TETile randomTile() {
       // final Random RANDOM = new Random(seed);
        int tileNum = r.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.NOTHING;
            default: return Tileset.NOTHING;
        }

    }
    public void fillFuckingWorld(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = randomTile();
            }
        }

    }
    public  World(int width, int height,long seedinput) {
        randomTiles = new TETile[width][height];
        seed = seedinput;
        this.r = new Random(seed);
        fillFuckingWorld(randomTiles);
    }
    public TETile[][] getTiles() {
        return randomTiles;
    }
    public void draw() {
        for (int i = 0; i < squares.size(); i += 1) {
            squares.get(i).draw();
        }
    }
}
