import org.junit.Assert;
import org.junit.Test;

public class ArtTest {
    @Test
    public void testConstructorAndGetters() {
        Art art = new Art(10, 100, 5, "Mona Lisa", "Leonardo da Vinci");
        Assert.assertEquals(10, art.getHeight());
        Assert.assertEquals(100, art.getPrice());
        Assert.assertEquals(5, art.getWidth());
        Assert.assertEquals("Mona Lisa", art.getName());
        Assert.assertEquals("Leonardo da Vinci", art.getArtistName());
    }

    @Test
    public void testSetters() {
        Art art = new Art(10, 100, 5, "Mona Lisa", "Leonardo da Vinci");
        art.setHeight(20);
        art.setPrice(200);
        art.setWidth(10);
        art.setName("Starry Night");
        art.setArtistName("Vincent van Gogh");
        Assert.assertEquals(20, art.getHeight());
        Assert.assertEquals(200, art.getPrice());
        Assert.assertEquals(10, art.getWidth());
        Assert.assertEquals("Starry Night", art.getName());
        Assert.assertEquals("Vincent van Gogh", art.getArtistName());
    }
}
