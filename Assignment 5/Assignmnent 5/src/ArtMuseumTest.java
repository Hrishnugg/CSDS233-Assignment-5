import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArtMuseumTest {
    @Test
    public void testAdd() {
        ArtMuseum artMuseum = new ArtMuseum("Cleveland Museum of Art");
        Art art = new Art(10, 100, 5, "Mona Lisa", "Leonardo da Vinci");
        assertTrue(artMuseum.add(art));
        assertEquals(1, artMuseum.sort("height", 1).size());
        // Try adding another painting for the same artist.
        Art art1 = new Art(10, 100, 5, "Last Supper", "Leonardo da Vinci");
        Assert.assertTrue(artMuseum.add(art1));
        assertEquals(2, artMuseum.sort("name", 1).size());
    }

    @Test
    public void testSort() {
        ArtMuseum artMuseum = new ArtMuseum("Cleveland Museum of Art");
        Art art1 = new Art(10, 230, 7, "Mona Lisa", "Leonardo da Vinci");
        Art art2 = new Art(20, 200, 10, "Starry Night", "Vincent van Gogh");
        Art art3 = new Art(15, 150, 5, "The Persistence of Memory", "Salvador Dali");
        Art art4 = new Art(30, 125, 20, "The Creation of Adam", "Michelangelo");
        Art art5 = new Art(17, 250, 12, "The Scream", "Edward Munch");
        Art art6 = new Art(13, 175, 25, "Luncheon on the Grass", "Edward Manet");
        Art art7 = new Art(22, 225, 20, "The Night Watch", "Rembrandt");
        assertTrue(artMuseum.add(art1));
        assertTrue(artMuseum.add(art2));
        assertTrue(artMuseum.add(art3));
        assertTrue(artMuseum.add(art4));
        assertTrue(artMuseum.add(art5));
        assertTrue(artMuseum.add(art6));
        assertTrue(artMuseum.add(art7));

        List<Art> sortedList = artMuseum.sort("height", 1);
        assertEquals(art1, sortedList.get(0));
        assertEquals(art6, sortedList.get(1));
        assertEquals(art3, sortedList.get(2));
        // Check sort order correctness.
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getHeight() >= sortedList.get(i-1).getHeight());
        }

        // Sort in descending order
        sortedList = artMuseum.sort("height", -1);
        // Check sort order correctness.
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getHeight() <= sortedList.get(i-1).getHeight());
        }

        sortedList = artMuseum.sort("price", 0);
        assertEquals(art4, sortedList.get(0));
        // Check sort order correctness.
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getPrice() >= sortedList.get(i-1).getPrice());
        }

        sortedList = artMuseum.sort("price", -1);
        assertEquals(art5, sortedList.get(0));
        // Check sort order correctness.
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getPrice() <= sortedList.get(i-1).getPrice());
        }

        sortedList = artMuseum.sort("width", 1);
        assertEquals(art3, sortedList.get(0));
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getWidth() >= sortedList.get(i-1).getWidth());
        }

        sortedList = artMuseum.sort("width", -1);
        assertEquals(art6, sortedList.get(0));
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getWidth() <= sortedList.get(i-1).getWidth());
        }

        sortedList = artMuseum.sort("name", 0);
        assertEquals(art6, sortedList.get(0));
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getName().compareTo(sortedList.get(i-1).getName()) >= 0);
        }

        sortedList = artMuseum.sort("name", -1);
        assertEquals(art5, sortedList.get(0));
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getName().compareTo(sortedList.get(i-1).getName()) <= 0);
        }

        sortedList = artMuseum.sort("artistName", 1);
        assertEquals(art6, sortedList.get(0));
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getArtistName().compareTo(sortedList.get(i-1).getArtistName()) >= 0);
        }

        sortedList = artMuseum.sort("artistName", -1);
        assertEquals(art2, sortedList.get(0));
        for (int i = 1; i < sortedList.size(); i++) {
            assertTrue(sortedList.get(i).getArtistName().compareTo(sortedList.get(i-1).getArtistName()) <= 0);
        }
    }

    @Test
    public void testFindArts() {
        ArtMuseum artMuseum = new ArtMuseum("Cleveland Museum of Art");
        Art art1 = new Art(10, 100, 5, "Mona Lisa", "Leonardo da Vinci");
        Art art2 = new Art(20, 200, 10, "Starry Night", "Vincent van Gogh");
        Art art3 = new Art(15, 150, 7, "The Persistence of Memory", "Salvador Dali");
        Art art4 = new Art(30, 125, 20, "The Creation of Adam", "Michelangelo");
        Art art5 = new Art(17, 250, 12, "Cafe Terrace at Night", "Vincent van Gogh");
        Art art6 = new Art(10, 100, 5, "Last Supper", "Leonardo da Vinci");
        Art art7 = new Art(17, 250, 12, "The Sunflowers", "Vincent van Gogh");

        artMuseum.add(art1);
        artMuseum.add(art2);
        artMuseum.add(art3);
        artMuseum.add(art4);
        artMuseum.add(art5);
        artMuseum.add(art6);
        artMuseum.add(art7);

        List<Art> daVinciArts = artMuseum.findArts("Leonardo da Vinci");
        assertEquals(daVinciArts.size(), 2);
        assertTrue(daVinciArts.contains(art1));
        assertTrue(daVinciArts.contains(art6));

        List<Art> vangoghArts = artMuseum.findArts("Vincent van Gogh");
        assertEquals(vangoghArts.size(), 3);
        assertTrue(vangoghArts.contains(art2));
        assertTrue(vangoghArts.contains(art5));
        assertTrue(vangoghArts.contains(art7));

        // Check for non-existent artist.
        assertEquals(artMuseum.findArts("non-existent").size(), 0);
    }

    @Test
    public void testRandomizeArts() {
        ArtMuseum artMuseum = new ArtMuseum("Cleveland Museum of Art");
        Art art1 = new Art(10, 100, 5, "Mona Lisa", "Leonardo da Vinci");
        Art art2 = new Art(20, 200, 10, "Starry Night", "Vincent van Gogh");
        Art art3 = new Art(15, 150, 7, "The Persistence of Memory", "Salvador Dali");
        Art art4 = new Art(30, 125, 20, "The Creation of Adam", "Michelangelo");
        Art art5 = new Art(17, 250, 12, "The Scream", "Edward Munch");
        artMuseum.add(art1);
        artMuseum.add(art2);
        artMuseum.add(art3);
        artMuseum.add(art4);
        artMuseum.add(art5);
        ArrayList<Art> addedArts = new ArrayList<>();
        addedArts.add(art1);
        addedArts.add(art2);
        addedArts.add(art3);
        addedArts.add(art4);
        addedArts.add(art5);

        List<Art> randomizedList = artMuseum.randomizeArts(3);
        // Make sure we get as many as we asked for.
        assertEquals(randomizedList.size(), 3);

        // Make sure arts are present in the expected collection.
        for (Art art : randomizedList) {
            assertTrue(addedArts.contains(art));
        }
    }

    @Test
    public void testRandomSort() {
        ArtMuseum artMuseum = new ArtMuseum("Cleveland Museum of Art");
        Art art1 = new Art(20, 200, 10, "Starry Night", "Vincent van Gogh");
        Art art2 = new Art(10, 230, 7, "Mona Lisa", "Leonardo da Vinci");
        Art art3 = new Art(15, 150, 5, "The Persistence of Memory", "Salvador Dali");
        Art art4 = new Art(30, 125, 20, "The Creation of Adam", "Michelangelo");
        Art art5 = new Art(17, 250, 12, "The Scream", "Edward Munch");
        Art art6 = new Art(13, 175, 25, "Luncheon on the Grass", "Edward Manet");
        Art art7 = new Art(22, 225, 20, "The Night Watch", "Rembrandt");
        Art art8 = new Art(17, 250, 18, "Cafe Terrace at Night", "Vincent van Gogh");
        Art art9 = new Art(12, 300, 25, "Last Supper", "Leonardo da Vinci");
        Art art10 = new Art(35, 250, 12, "The Sunflowers", "Vincent van Gogh");
        Art art11 = new Art(21, 190, 16, "Vitruvian Man", "Leonardo da Vinci");
        Art art12 = new Art(42, 300, 30, "The Flower Carrier", "Diego Rivera");
        Art art13 = new Art(30, 220, 25, "The School of Athens", "Raphael Urbino");
        Art art14 = new Art(40, 310, 18, "The Kiss", "Gustav Klimt");
        Art art15 = new Art(20, 190, 19, "Guernica", "Pablo Picasso");
        Art art16 = new Art(45, 270, 33, "The Great Wave off Kanagawa", "Katsushika Hokusai");
        Art art17 = new Art(15, 130, 23, "Las Meninas", "Diego Velazquez");

        Art[] allArts = new Art[] {art1, art2, art3, art4, art5, art6, art7, art8, art9, art10, art11, art12,
                art13, art14, art15, art16, art17};

        // Try a list smaller than 5 elements.
        ArrayList<Art> smallList = new ArrayList<>();
        smallList.add(art1);
        smallList.add(art2);
        smallList.add(art3);

        List<Art> sortedList = artMuseum.randomSort(smallList);
        assertEquals(sortedList.size(), 3);
        assertEquals(sortedList.get(0), art1);
        assertEquals(sortedList.get(1), art2);
        assertEquals(sortedList.get(2), art3);

        // Try a list of 12 elements, not exactly divisible by 5.
        ArrayList<Art> twelveArts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            twelveArts.add(allArts[i]);
        }
        sortedList = artMuseum.randomSort(twelveArts);
        assertEquals(sortedList.size(), 12);
        // Check sorting on height.
        assertEquals(sortedList.get(0), art2);
        assertEquals(sortedList.get(1), art1);

        // Check sorting on price
        assertEquals(sortedList.get(2), art4);
        assertEquals(sortedList.get(3), art3);

        // Check sorting on width
        assertEquals(sortedList.get(4), art5);
        assertEquals(sortedList.get(5), art6);

        // Check sorting on name
        assertEquals(sortedList.get(6), art8);
        assertEquals(sortedList.get(7), art7);

        // Check sorting on artist name
        assertEquals(sortedList.get(8), art12 );
        assertEquals(sortedList.get(9), art11);
        assertEquals(sortedList.get(10), art9);
        assertEquals(sortedList.get(11), art10);

        // Try a list of 17 elements, again not exactly divisible by 5.
        ArrayList<Art> seventeenArts = new ArrayList<>();
        for (int i = 0; i < allArts.length; i++) {
            seventeenArts.add(allArts[i]);
        }
        sortedList = artMuseum.randomSort(seventeenArts);
        assertEquals(sortedList.size(), 17);

        // Check if the first 3 are sorted on height
        for (int i = 1; i < 3; i++) {
            assertTrue(sortedList.get(i).getHeight() >= sortedList.get(i-1).getHeight());
        }
        // Check if the next 3 are sorted on price
        for (int i = 4; i < 6; i++) {
            assertTrue(sortedList.get(i).getPrice() >= sortedList.get(i-1).getPrice());
        }
        // Check if the next 3 are sorted on width
        for (int i = 7; i < 9; i++) {
            assertTrue(sortedList.get(i).getWidth() >= sortedList.get(i-1).getWidth());
        }
        // Check if the next 3 are sorted on name
        for (int i = 10; i < 12; i++) {
            assertTrue(sortedList.get(i).getName().compareTo(sortedList.get(i-1).getName()) >= 0);
        }
        // Check if the next 5 are sorted on artist name
        for (int i = 13; i < 17; i++) {
            assertTrue(sortedList.get(i).getArtistName().compareTo(sortedList.get(i-1).getArtistName()) >= 0);
        }
    }
}
