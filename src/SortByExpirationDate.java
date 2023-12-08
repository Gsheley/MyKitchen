import java.util.ArrayList;

public class SortByExpirationDate implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        ArrayList<Item> nlist= new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getExpirationDate() == null)
            {
                nlist.add(list.remove(i));
            }
        }

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

        for(int i =0; i < nlist.size(); i++)
        {
            
            list.add(nlist.get(i));
        }
        return list;
    }   
}

