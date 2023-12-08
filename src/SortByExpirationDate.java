import java.util.ArrayList;
import java.util.Calendar;

public class SortByExpirationDate implements Sort 
{
    public ArrayList<Item> sort(ArrayList<Item> list)
    {
        ArrayList<Item> nlist= new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getExpirationDate() == null)
            {
                System.out.println("Removed " + list.get(i).getName());
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

    public static void main(String[] args) 
    {
        ArrayList<Item> list = new ArrayList<>();
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
        //item1.setExpirationDate(date1);
        Item item2 = new Item(4, names[3], date2, 2);
        item2.setExpirationDate(date2);
        Item item3 = new Item(3, names[2], date3, 3);
        item3.setExpirationDate(date3);
        Item item4 = new Item(2, names[1], date4, 4);
        item4.setExpirationDate(date4);
        Item item5 = new Item(1, names[0], date5, 5);
        //item5.setExpirationDate(date5);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        SortByExpirationDate sorter = new SortByExpirationDate();
        ArrayList<Item> result = sorter.sort(list);

        for(int i =0; i < result.size(); i++)
        {
            System.out.println(result.get(i).getName());
        }


    }


}

