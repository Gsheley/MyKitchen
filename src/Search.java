import java.util.ArrayList;

public class Search 
{
    public static ArrayList<Object> search(String query, ArrayList<Object> list )
    {
        ArrayList<Object> results = new ArrayList<Object>();
        if(list.size()>0)
        {
            
            //Search case for Item objects 
            if(list.get(0) instanceof Item)
            {
                for(int i =0; i < list.size(); i++)
                {
                    if(((Item)list.get(i)).getName().contains(query))
                    {
                        results.add(list.get(i));
                    }
                }
                return  results;
            }
       
            //Search case for Recipe objects 
            if(list.get(0) instanceof Recipe)
            {
                for(int i =0; i < list.size(); i++)
                {
                    if(((Recipe)list.get(i)).recipeName.contains(query))
                    {
                        results.add(list.get(i));
                    }
                }
                return  results;
            }
        }
       return results;
    }
}
