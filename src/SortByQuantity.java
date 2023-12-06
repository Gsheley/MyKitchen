import java.util.ArrayList;

public class SortByQuantity implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        if(list.isEmpty() == false) 
        {
            for (int i = 1; i < list.size(); ++i)
            {
                Item curr = list.get(i);
                int key = list.get(i).getQuantity();
                int j = i - 1;
                while (j >= 0 && list.get(j).getQuantity() > key) 
                {
                    list.set(j+1, list.get(j));
                    j--;
                }
                list.set(j+1,curr);
            }
        }
        return list;
    }   

}