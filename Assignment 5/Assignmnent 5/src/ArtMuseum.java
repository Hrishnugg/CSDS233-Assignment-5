import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashSet;

// Represents a database of Arts.
public class ArtMuseum {
    // Internal database of all arts.
    private ArrayList<Art> artCollection = new ArrayList<Art>();
    private String museumName;

    // Constructor of ArtMuseum, takes in museum name.
    public ArtMuseum(String museumName) {
        this.museumName = museumName;
    }
    private String getMuseumName() {
        return museumName;
    }

    // Adds an art work to the database.
    public boolean add(Art art) {
        return artCollection.add(art);
    }
    // Sorts all the arts in the museum on the give attribute and direction (ascending/descending).
    // Quicksort is used as the sorting algorithm.
    // Returns the sorted list. Returns null if the given attribute is not valid.
    public List<Art> sort(String attribute, int direction) {
        Comparator<Art> comparator = null;
        // If there are no items, or only 1 item, no sorting is needed.
        if (artCollection.size() <= 1) {
            return artCollection;
        }
        // Set up the comparators needed for sorting, for all valid combinations of attribute and direction.
        switch (attribute) {
            case "height":
                if (direction >= 0) {
                    comparator = ((Art art1, Art art2) -> art1.getHeight() - art2.getHeight());
                } else {
                    comparator = ((Art art1, Art art2) -> art2.getHeight() - art1.getHeight());
                }
                break;
            case "price":
                if (direction >= 0) {
                    comparator = ((Art art1, Art art2) -> art1.getPrice() - art2.getPrice());
                } else {
                    comparator = ((Art art1, Art art2) -> art2.getPrice() - art1.getPrice());
                }
                break;
            case "width":
                if (direction >= 0) {
                    comparator = ((Art art1, Art art2) -> art1.getWidth() - art2.getWidth());
                } else {
                    comparator = ((Art art1, Art art2) -> art2.getWidth() - art1.getWidth());
                }
                break;
            case "name":
                if (direction >= 0) {
                    comparator = ((Art art1, Art art2) -> art1.getName().compareTo(art2.getName()));
                } else {
                    comparator = ((Art art1, Art art2) -> art2.getName().compareTo(art1.getName()));
                }
                break;
            case "artistName":
                if (direction >= 0) {
                    comparator = ((Art art1, Art art2) -> art1.getArtistName().compareTo(art2.getArtistName()));
                } else {
                    comparator = ((Art art1, Art art2) -> art2.getArtistName().compareTo(art1.getArtistName()));
                }
                break;
            default:
                return null;
        }
        return quickSort(comparator);
    }

    // Quicksort implementation with a generic comparator for Art object.
    private List<Art> quickSort(Comparator<Art> comparator){
        if (artCollection.size() <= 1) {
            return artCollection;
        }
        ArrayList<Art> arts = new ArrayList<>(artCollection);
        quickSortRecursive(arts, comparator, 0, arts.size() - 1);
        return arts;
    }
    private void quickSortRecursive(ArrayList<Art> arts, Comparator<Art> comparator, int first, int last) {
        if (first >= last) {
            return;
        }
        // Partition the input into two sub-arrays.
        int split = partition(arts, comparator, first, last);
        // Recursively sort the two sub-arrays.
        quickSortRecursive(arts, comparator, first, split);
        quickSortRecursive(arts, comparator, split + 1, last);
    }
    private int partition(ArrayList<Art> arts, Comparator<Art> comparator, int first, int last) {
        Art pivot = arts.get((first + last) / 2);
        int i = first - 1;
        int j = last + 1;
        // Scan the array from left and right and exchange elements so that all elements
        // in left subarray are less <= pivot and all elements in right subarray are >= pivot.
        while (true) {
            do {
                i++;
            } while (comparator.compare(arts.get(i), pivot) < 0);
            do {
                j--;
            } while (comparator.compare(arts.get(j), pivot) > 0);
            if (i >= j) {
                return j;
            }
            Art temp = arts.get(i);
            arts.set(i, arts.get(j));
            arts.set(j, temp);
        }
    }

    // Searches and returns all arts in the museum which belong to the specified artist.
    public List<Art> findArts (String artistName) {
        List<Art> foundArts = new ArrayList<Art>();
        // Linear search through art collection.
        for (Art art : artCollection) {
            if (art.getArtistName().equals(artistName)) {
                foundArts.add(art);
            }
        }
        return foundArts;
    }
    public List<Art> randomizeArts(int n){
        List<Art> randomizedArts = new ArrayList<Art>();
        if (n <= 0) {
            return randomizedArts;
        }
        HashSet<Integer> randomIndices = new HashSet<>();
        // If specified number is greater than collection size, set it to collection size.
        if (n > artCollection.size()) {
            n = artCollection.size();
        }
        // Generate n random indices and copy the art at the random indices to the output array.
        for (int i = 0; i < n; i++) {
            int randomIndex = (int) (Math.random() * artCollection.size());
            // Check if the index was already generated, and add only if the index is not duplicate.
            if (!randomIndices.contains(randomIndex)) {
                randomizedArts.add(artCollection.get(randomIndex));
                randomIndices.add(randomIndex);
            }
        }
        // Add any remaining items missed due to duplicate random indices being generated.
        if (randomizedArts.size() < n) {
           for (int i = 0; i < artCollection.size(); i++) {
               if (!randomIndices.contains(i)) {
                   randomizedArts.add(artCollection.get(i));
                   if (randomizedArts.size() == n) break;
               }
           }
        }
        return randomizedArts;
    }
    public List<Art> randomSort(List<Art> arts) {
        ArrayList<Art> sortedList = new ArrayList<Art>(arts);
        int chunkSize = arts.size() / 5;
        // Create comparators for sorting on different attributes of Art.
        Comparator<Art> heightComparator = ((Art art1, Art art2) -> art1.getHeight() - art2.getHeight());
        Comparator<Art> artistNameComparator = ((Art art1, Art art2) -> art1.getArtistName().compareTo(art2.getArtistName()));
        Comparator<Art> priceComparator = ((Art art1, Art art2) -> art1.getPrice() - art2.getPrice());
        Comparator<Art> widthComparator = ((Art art1, Art art2) -> art1.getWidth() - art2.getWidth());
        Comparator<Art> nameComparator = ((Art art1, Art art2) -> art1.getName().compareTo(art2.getName()));

        // If list size is less than 5, we don't need to sort.
        if (chunkSize == 0) {
            return sortedList;
        }
        // Use quicksort to sort slices of the given list, on different attributes as specified.
        quickSortRecursive(sortedList, heightComparator, 0, chunkSize - 1);
        quickSortRecursive(sortedList, priceComparator, chunkSize, chunkSize * 2 - 1);
        quickSortRecursive(sortedList, widthComparator, chunkSize * 2, chunkSize * 3 - 1);
        quickSortRecursive(sortedList, nameComparator, chunkSize * 3, chunkSize * 4 - 1);
        quickSortRecursive(sortedList, artistNameComparator, chunkSize * 4, sortedList.size() - 1);
        return sortedList;
    }

}
