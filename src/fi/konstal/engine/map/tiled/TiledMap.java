package fi.konstal.engine.map.tiled;

import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TiledMap implements Map {
    private Document doc;
    private NodeList layerNodes;
    private NodeList objectNodes;
    private NodeList tilesetNodes;

    private int mapHeight;
    private int mapWidth;

    private int tileHeight;
    private int tileWidth;

    private int pixelWidth;
    private int pixelHeight;

    private List<Image> tileImages;
    private List<Tileset> tilesets;
    private List<Layer> layers;
    private List<MapObjectLayer> objectLayers;

    private String pathToTilesets;


    public TiledMap(String pathToMap, String pathToTilesets) {
        tileImages = new ArrayList<>();
        tilesets = new ArrayList<>();
        layers = new ArrayList<>();
        objectLayers = new ArrayList<>();



        this.pathToTilesets = pathToTilesets;
        File tiledMap = new File(pathToMap);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(tiledMap);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Could not parse Tiled map tmx");
            e.printStackTrace();
        }

        // Gets basic information about the map.
        Element map = doc.getDocumentElement();
        mapWidth = Integer.parseInt(map.getAttribute("width"));
        mapHeight = Integer.parseInt(map.getAttribute("height"));
        tileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
        tileHeight = Integer.parseInt(map.getAttribute("tileheight"));
        pixelWidth = (int) Math.floor(mapWidth * tileWidth);
        pixelHeight = (int) Math.floor(mapHeight * tileHeight);

        // Gets different tags and creates nodelists objects accordingly.
        layerNodes = doc.getElementsByTagName("layer");
        objectNodes = doc.getElementsByTagName("objectgroup");
        tilesetNodes = doc.getElementsByTagName("tileset");

        //Create actual Arraylists containing the elements
        createTilesets();
        createTilesetImage();
        createLayers();
        createObjectLayers();
    }

    /**
     * Creates tileset objects.
     */
    private void createTilesets() {
        for (int i = 0; i < tilesetNodes.getLength(); i++) {
            if (tilesetNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element d = (Element) tilesetNodes.item(i);
                int firstgid =  Integer.parseInt(d.getAttribute("firstgid"));



                File tsxFile = new File(d.getAttribute("source"));


                Document doc = null;
                try {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    doc = dBuilder.parse("src/fi/konstal/example/game1/resources/" + tsxFile);
                    doc.getDocumentElement().normalize();
                } catch (Exception e) {
                    System.out.println("Could not parse Tiled map tmx");
                    e.printStackTrace();
                }

                Element e = (Element) doc.getDocumentElement();

                Element image = (Element) e.getElementsByTagName("image")
                        .item(0);
                tilesets.add(new Tileset(
                        firstgid,
                        e.getAttribute("name"),
                        Integer.parseInt(e.getAttribute("tilewidth")),
                        Integer.parseInt(e.getAttribute("tileheight")),
                        image.getAttribute("source"),
                        Integer.parseInt(image.getAttribute("width")),
                        Integer.parseInt(image.getAttribute("height")),
                        pathToTilesets));
            }
        }
    }


    /**
     * Creates layer objects with tile objects according to content data.
     */
    private void createLayers() {
        for (int i = 0; i < layerNodes.getLength(); i++) {
            int coordinateY = 0;
            int coordinateX = 0;
            int mapIndex = 0;
            ArrayList<Tile> layerData = new ArrayList<>();

            if (layerNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) layerNodes.item(i);
                Element data = (Element) e.getElementsByTagName("tiles").
                        item(0);

                if (e.getElementsByTagName("tiles").item(0) == null) {
                    data = (Element) e.getElementsByTagName("data").item(0);
                }

                String[] values = data.getTextContent().split
                        ("(,[\\s\\r]+)|([\\s\\r]+)|(,)");

                for (int j = 0; j < values.length - 1; j++) {
                    // Saves only tiles with an image.
                    if (!values[j+1].equals("0")) {
                        layerData.add(new Tile(Integer.parseInt(values[j + 1])
                                - 1, coordinateX, coordinateY));
                    }

                    mapIndex++;

                    if (mapIndex < mapWidth) {
                        coordinateX = coordinateX + tileWidth;
                    } else {
                        coordinateY = coordinateY + tileHeight;
                        coordinateX = 0;
                        mapIndex = 0;
                    }
                }

                layers.add(new Layer(e.getAttribute("name"),
                        Integer.parseInt(e.getAttribute("width")),
                        Integer.parseInt(e.getAttribute("height")),
                        layerData));
            }
        }
    }


    /**
     * Compiles and casts all tilesets images into one Image array.
     */
    private void createTilesetImage() {
        for (int i = 0; i < tilesets.size(); i++) {
            for (int j = 0; j < tilesets.get(i).getTileImages().size(); j++) {
                BufferedImage temp = tilesets.get(i).getTileImages().get(j);
                tilesets.get(i).getTileImages().get(j).flush();

                tileImages.add(SwingFXUtils.toFXImage(temp, null));
                temp.flush();
            }
        }
    }

    /**
     * Creates objectLayer objects with corresponding tiledObjects.
     */
    private void createObjectLayers() {
        for (int i = 0; i < objectNodes.getLength(); i++) {
            ArrayList<MapObject> mapObjects = new ArrayList<>();

            if (objectNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) objectNodes.item(i);
                NodeList objectNodes = e.getElementsByTagName("object");

                for (int j = 0; j < objectNodes.getLength(); j++) {
                    Element object = (Element) objectNodes.item(j);
                    mapObjects.add(new MapObject(
                            Integer.parseInt(object.getAttribute("id")),
                            object.getAttribute("name"),
                            (int) Double.parseDouble(object.getAttribute("x")),
                            (int) Double.parseDouble(object.getAttribute("y")),
                            (int) Double.parseDouble(object.getAttribute("width")),
                            (int) Double.parseDouble(object.getAttribute("height"))
                    ));
                }

                objectLayers.add(new MapObjectLayer((e.getAttribute("name")),
                        mapObjects));
            }
        }
    }

    //Returns a full MapObjectLayer if match is made
    public MapObjectLayer getObjectLayer(String name) {
        MapObjectLayer toReturn = null;

        for (int i = 0; i < objectLayers.size(); i++) {
            if (objectLayers.get(i).getName().equals(name)) {
                toReturn = objectLayers.get(i);
            }
        }

        return toReturn;
    }


    //Search object by ID
    public MapObject getObject(int id) {
        MapObject toReturn = null;

        for (int i = 0; i < objectLayers.size(); i++) {
            for (int j = 0; j < objectLayers.get(i).getMapObjects().size();
                 j++) {
                if (objectLayers.get(i).getMapObjects().get(j).getId()
                        == id) {
                    toReturn = objectLayers.get(i).getMapObjects().get(j);
                }
            }
        }

        return toReturn;
    }

    //Search object by name
    public MapObject getObject(String name) {
        MapObject toReturn = null;

        for (int i = 0; i < objectLayers.size(); i++) {
            for (int j = 0; j < objectLayers.get(i).getMapObjects().size();
                 j++) {
                if (objectLayers.get(i).getMapObjects().get(j).getName().
                        equals(name)) {
                    toReturn = objectLayers.get(i).getMapObjects().get(j);
                }
            }
        }

        return toReturn;
    }

    //return a layer if a match is made
    public Layer getLayer(String name) {
        Layer toReturn = null;

        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).getName().equals(name)) {
                toReturn = layers.get(i);
            }
        }

        return toReturn;
    }



    //Draws the map one layer at a time
    public void draw(GraphicsContext gc, Camera c) {
        for (int i = 0; i < layers.size(); i++) {
            for (int j = 0; j < layers.get(i).getTiles().size(); j++) {
                gc.drawImage(
                        tileImages.get(layers.get(i).getTiles().get(j).getImageCoordinate()),
                        layers.get(i).getTiles().get(j).getX() - c.getXOffset(),
                        layers.get(i).getTiles().get(j).getY() - c.getYOffset(),
                        tileWidth,
                        tileHeight
                );
            }
        }
    }

    @Override
    public double getWidth() {
        return pixelWidth;
    }

    @Override
    public double getHeight() {
        return pixelHeight;
    }
}
