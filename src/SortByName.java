import java.util.ArrayList;

public class SortByName implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        for (int i = 1; i < list.size(); ++i)
         {
            Item curr = list.get(i);
            String key = list.get(i).getName().toLowerCase();
            int j = i - 1;
            while (j >= 0 && key.compareTo(list.get(j).getName().toLowerCase()) < 0) 
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1,curr);
        }
        return list;
    }   
}


