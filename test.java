import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test{

    public static void main(String[] args) {
        String inpath=".\\ml-latest-small\\movies.csv";
        //新建Hash表读csv文件
        List<HashMap<String, Object>> retHashMap = new ArrayList<HashMap<String, Object>>();
        retHashMap = getcsvTableList(inpath);
        
        //P1a
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        for(HashMap k:retHashMap) {
            String genres_temp = k.get("genres").toString();
            String[] genres = genres_temp.split("\\|");
            // System.out.println(genres.length); //  k.get("title") k.get("genres") k.get("movieId")
            for(int i = 0; i < genres.length; i++){
                String temp = genres[i];
                if(map1.containsKey(temp)){
                    int num = Integer.parseInt(String.valueOf(map1.get(temp))); 
                    num++;
                    map1.put(genres[i],num);
                }
                else{
                    map1.put(genres[i],1);
                }
            }
        }
        System.out.println(map1);
        
        //P1b
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        for(HashMap k:retHashMap) {
            String title_temp = k.get("title").toString();
            int year = 0;
            if(title_temp.charAt(title_temp.length()-1) == '\"'){
                year = Integer.parseInt(title_temp.substring(title_temp.length()-6,title_temp.length()-2 ));
                //System.out.println( year );
            }
            if(title_temp.charAt(title_temp.length()-1) == ')'){
                year = Integer.parseInt(title_temp.substring(title_temp.length()-5,title_temp.length()-1 ));
                //System.out.println( year );
            }
            if( year < 2019 && 2013 < year)
            {
                String genres_temp = k.get("genres").toString();
                String[] genres = genres_temp.split("\\|");
                for(int i = 0; i < genres.length; i++){
                    String temp = genres[i];
                    if(map2.containsKey(temp)){
                        int num = Integer.parseInt(String.valueOf(map2.get(temp))); 
                        num++;
                        map2.put(genres[i],num);
                    }
                    else{
                        map2.put(genres[i],1);
                    }
                }
            }
        }
        System.out.println(map2);
        
        //p2 
        HashMap<String, HashMap<String, Object>> map3 = new HashMap<String, HashMap<String, Object>>();
        for(HashMap k:retHashMap) {
            String title_temp = k.get("title").toString();
            String genres_temp = k.get("genres").toString();
            String[] genres = genres_temp.split("\\|");
            int year = 0;
            if(title_temp.charAt(title_temp.length()-1) == '\"'){
                year = Integer.parseInt(title_temp.substring(title_temp.length()-6,title_temp.length()-2 ));
                //System.out.println( year );
            }
            if(title_temp.charAt(title_temp.length()-1) == ')'){
                year = Integer.parseInt(title_temp.substring(title_temp.length()-5,title_temp.length()-1 ));
                //System.out.println( year );
            }
            
            String Year = String.valueOf(year);
            if(map3.containsKey(Year)){
                HashMap<String, Object> t = map3.get(Year);
                for(int i = 0; i < genres.length; i++){
                    String temp = genres[i];
                    if(t.containsKey(temp)){
                        int num = Integer.parseInt(String.valueOf(t.get(temp))); 
                        num++;
                        t.put(temp,num);
                    }
                    else{
                        t.put(temp,1);
                    }
                }
                map3.put(Year, t);
            }
            else{
                HashMap<String, Object> t = new HashMap<String, Object>();
                for(int i = 0; i < genres.length; i++){
                    String temp = genres[i];
                    if(t.containsKey(temp)){
                        int num = Integer.parseInt(String.valueOf(t.get(temp))); 
                        num++;
                        t.put(temp,num);
                    }
                    else{
                        t.put(temp,1);
                    }
                }
                map3.put(Year, t);
            }
        }
        System.out.println(map3);
        
    }
    
    public static List<HashMap<String, Object>> getcsvTableList(String path) {
        List<HashMap<String, Object>> retHashMap = new ArrayList<HashMap<String, Object>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String[] headtilte = reader.readLine().split(",");
            String line = null;
            while ((line = reader.readLine()) != null) {
                HashMap<String, Object> itemMap = new HashMap<String, Object>();
                String[] itemArray = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1); 
                for (int i = 0; i < itemArray.length; i++) {
                    itemMap.put(headtilte[i], itemArray[i]);
                }
                retHashMap.add(itemMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retHashMap;
    }

}

