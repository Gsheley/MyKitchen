import java.util.ArrayList;

public class SortByExpirationDate implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        for (int i = 1; i < list.size(); ++i)
         {
            Item curr = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getExpirationDate().compareTo(list.get(i).getExpirationDate()) > 0) 
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1,curr);
        }
        return list;
    }   
}

