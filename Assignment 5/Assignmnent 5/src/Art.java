// Represents an art piece to be added to the ArtMuseum database.
public class Art {
    private int height;
    private int width;
    private int price;
    private String name;
    private String artistName;

    public Art(int height, int price, int width, String name, String artistName) {
        this.height = height;
        this.width = width;
        this.price = price;
        this.name = name;
        this.artistName = artistName;
    }

    // Getters and Setters for Art.
    public int getHeight() {
        return height;
    }

    public int getWidth() { return width; }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
