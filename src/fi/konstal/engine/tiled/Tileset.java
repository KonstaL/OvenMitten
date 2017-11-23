package fi.konstal.engine.tiled;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Tileset {

    /**
     * The index of the first tile image on the tileset.
     */
    private int firstGid;

    /**
     * The index of the last tile image on the tileset.
     */
    private int lastGid;

    /**
     * The name of the tileset.
     */
    private String name;

    /**
     * The width of a single tile in the tileset.
     */
    private int tileWidth;

    /**
     * The height of a single tile in the tileset.
     */
    private int tileHeight;

    /**
     * The path to the tileset image from the tiled map.
     */
    private String source;

    /**
     * The width of the tileset image.
     */
    private int imageWidth;

    /**
     * The height of the tileset image.
     */
    private int imageHeight;

    /**
     * The tileset image.
     */
    private BufferedImage sourceImage;

    /**
     * The separated tile images of the tileset.
     */
    private ArrayList<BufferedImage> tileImages = new ArrayList<>();

    /**
     * Creates the tileset.
     *
     * @param firstGid the index of the first tile image on the tileset
     * @param name the name of the tileset
     * @param tileWidth the width of a single tile in the tileset
     * @param tileHeight the height of a single tile in the tileset
     * @param source the path to the tileset image from the tiled map
     * @param imageWidth the width of the tileset image
     * @param imageHeight the height of the tileset image
     * @param path the path where to get the tileset image
     */
    public Tileset(int firstGid, String name, int tileWidth, int tileHeight,
                   String source, int imageWidth, int imageHeight,
                   String path) {
        this.firstGid = firstGid;
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.source = source;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        lastGid = (int) (Math.floor(imageWidth/tileWidth) * Math.floor
                (imageHeight/tileHeight) + firstGid - 1);
        loadImages(path);
    }

    /**
     * Reads tileset image and splits it into tiles.
     *
     * @param path the path where to get the tileset image
     */



        private void loadImages(String path) {
            File file = new File(path + source);

            try (FileInputStream fis = new FileInputStream(file)) {
                sourceImage = ImageIO.read(fis);
            } catch (IOException e) {
                System.out.println("Could not load tileset image: " + name);
                e.printStackTrace();
            }

            if (sourceImage != null) {
                int rows = (int) Math.floor(sourceImage.getHeight() / tileHeight);
                int cols = (int) Math.floor(sourceImage.getWidth() / tileWidth);
                // Determines the image chunk's width and height.
                int chunkWidth = sourceImage.getWidth() / cols;
                int chunkHeight = sourceImage.getHeight() / rows;

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        tileImages.add(sourceImage.getSubimage(chunkWidth * j,
                                chunkHeight * i, chunkWidth, chunkHeight));
                    }
                }

                // No need to keep the source image in memory anymore.
                sourceImage.flush();
            }
        }

    /**
     * Returns the tileset's tile images.
     *
     * @return tileset's tile images
     */
    public ArrayList<BufferedImage> getTileImages() {
        return tileImages;
    }

    /**
     * Returns the index of the first tile image on the tileset.
     *
     * @return the index of the first tile image on the tileset
     */
    public int getFirstGid() {
        return firstGid;
    }

    /**
     * Returns the index of the last tile image on the tileset.
     *
     * @return the index of the last tile image on the tileset
     */
    public int getLastGid() {
        return lastGid;
    }

    /**
     * Returns the name of the tileset.
     *
     * @return the name of the tileset
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the width of one tile in the tileset.
     *
     * @return the width of one tile
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Returns the height of one tile in the tileset.
     *
     * @return the height of one tile
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Returns the width of the tileset image.
     *
     * @return the width of the tileset image
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Returns the height of the tileset image.
     *
     * @return the height of the tileset image
     */
    public int getImageHeight() {
        return imageHeight;
    }
}
