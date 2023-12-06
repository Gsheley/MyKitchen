import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
public class SortByExpirationDateTest
{
    SortByExpirationDate sorter;
    ArrayList<Item> emptyList;
    ArrayList<Item> itemList;
    ArrayList<Item> emptyItemList;
    ArrayList<Item> sortedItemList;
    ArrayList<Item> unsortedItemList;

    @Before
    public void setup()
    {
        sorter = new SortByExpirationDate();
        emptyList= new ArrayList<Item>();
        emptyItemList= new ArrayList<Item>();
        sortedItemList= new ArrayList<Item>();
        unsortedItemList= new ArrayList<Item>();
        String[] names = {"apple", "bannana", "taco", "pancake", "tomato"};
        Calendar date1 = Calendar.getInstance();
        date1.set(2022, 07, 18);
        Calendar date2 = Calendar.getInstance();
        date2.set(1999, 8, 23);;
        Calendar date3 = Calendar.getInstance();
        date3.set(2003, 3, 12);
        Calendar date4 = Calendar.getInstance();
        date4.set(2023, 2, 22);
        Calendar date5 = Calendar.getInstance();
        Item item1 = new Item(5, names[4], date1, 1);
        item1.setExpirationDate(date1);
        Item item2 = new Item(4, names[3], date2, 2);
        item2.setExpirationDate(date2);
        Item item3 = new Item(3, names[2], date3, 3);
        item3.setExpirationDate(date3);
        Item item4 = new Item(2, names[1], date4, 4);
        item4.setExpirationDate(date4);
        Item item5 = new Item(1, names[0], date5, 5);
        item5.setExpirationDate(date5);
        sortedItemList.add(item2);
        sortedItemList.add(item3);
        sortedItemList.add(item1);
        sortedItemList.add(item4);
        sortedItemList.add(item5);

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
