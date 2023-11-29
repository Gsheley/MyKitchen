import java.util.ArrayList;

public class Search 
{
    public static ArrayList<Object> search(String query, ArrayList<Object> list )
    {
        //Search case for Item objects 
        if(list.get(0) instanceof Item)
        {
            ArrayList<Object> results  = new ArrayList<Object>();
            for(int i =0; i < list.size(); i++)
            {
                if(((Item)list.get(i)).getName()==query)
                {
                    results.add(list.get(i));
                }
            }
            return  results;
        }
       
        //Search case for Recipe objects 
        if(list.get(0) instanceof Recipe)
        {
            ArrayList<Object> results  = new ArrayList<Object>();
            for(int i =0; i < list.size(); i++)
            {
                if(((Recipe)list.get(i)).recipeName==query)
                {
                    results.add(list.get(i));
                }
            }
            return  results;
        }
       
       return null;
    }
}
