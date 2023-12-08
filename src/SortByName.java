import java.util.ArrayList;
import java.util.Calendar;

public class SortByName implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        for (int i = 1; i < list.size(); ++i)
         {
            Item curr = list.get(i);
            String key = list.get(i).getName();
            int j = i - 1;
            while (j >= 0 && key.compareTo(list.get(j).getName()) < 0) 
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1,curr);
        }
        return list;
    }   

    public static void main(String[] args) 
    {
        ArrayList<Item> list = new ArrayList<>();
        String[] names = {"apple", "bannana", "pear", "lettuce", "chips"};
        Item item1 = new Item(5, names[0], null, 3);
        Item item2 = new Item(4, names[1], null, 2);
        Item item3 = new Item(3, names[2], null, 6);
        Item item4 = new Item(2, names[3], null, 4);
        Item item5 = new Item(1, names[4], null, 1);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        SortByName sorter = new SortByName();
        ArrayList<Item> result = sorter.sort(list);

        for(int i =0; i < result.size(); i++)
        {
            System.out.println(result.get(i).getName());
        }
    }




}


