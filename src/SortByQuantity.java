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

    public static void main(String[] args) 
    {
        ArrayList<Item> list = new ArrayList<>();
        String[] names = {"apple", "bannana", "pear", "lettuce", "chips"};
        Item item1 = new Item(5, names[0], null, 12);
        Item item2 = new Item(4, names[1], null, 9);
        Item item3 = new Item(3, names[2], null, 6);
        Item item4 = new Item(2, names[3], null, 4);
        Item item5 = new Item(1, names[4], null, 2212);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        SortByQuantity sorter = new SortByQuantity();
        ArrayList<Item> result = sorter.sort(list);

        for(int i =0; i < result.size(); i++)
        {
            System.out.println(result.get(i).getName());
        }
    }
}