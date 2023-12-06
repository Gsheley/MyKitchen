import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class  SortByNameTest
{
    SortByName sorter;
    ArrayList<Item> emptyList;
    ArrayList<Item> itemList;
    ArrayList<Item> emptyItemList;
    ArrayList<Item> sortedItemList;
    ArrayList<Item> unsortedItemList;

    @Before
    public void setup()
    {
        sorter = new SortByName();
        emptyList= new ArrayList<Item>();
        emptyItemList= new ArrayList<Item>();
        sortedItemList= new ArrayList<Item>();
        unsortedItemList= new ArrayList<Item>();
        String[] names = {"apple", "bannana", "taco", "pancake", "tomato"};
        Item item1 = new Item(5, names[4], null, 1);
        Item item2 = new Item(4, names[3], null, 2);
        Item item3 = new Item(3, names[2], null, 3);
        Item item4 = new Item(2, names[1], null, 4);
        Item item5 = new Item(1, names[0], null, 5);
        sortedItemList.add(item5);
        sortedItemList.add(item4);
        sortedItemList.add(item2);
        sortedItemList.add(item3);
        sortedItemList.add(item1);

        unsortedItemList.add(item1);
        unsortedItemList.add(item2);
        unsortedItemList.add(item3);
        unsortedItemList.add(item4);
        unsortedItemList.add(item5);
    }


    @Test
    public void emptyItemList()
    {
        ArrayList<Item> result = sorter.sort(emptyItemList);
        assertArrayEquals(emptyList.toArray(), result.toArray());
        
    }

    @Test
    public void sortedList()
    {
        ArrayList<Item> result = sorter.sort(sortedItemList);
        assertArrayEquals(sortedItemList.toArray(), result.toArray());
    }

    @Test
    public void unSortedList()
    {
        ArrayList<Item> result = sorter.sort(unsortedItemList);
        for(int i =0; i < result.size(); i++)
        {
            System.out.println(result.get(i).getName());
        }
        assertArrayEquals(sortedItemList.toArray(), result.toArray());
    
    }
}
