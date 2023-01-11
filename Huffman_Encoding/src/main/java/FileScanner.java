import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileScanner {
    private File decryptedFile;
    private String dataFile;
    private ArrayList<Character> chars=new ArrayList<>();
    private ArrayList<Integer> countedChars=new ArrayList<>();
 private HashMap<Character,Integer> Q=new HashMap<>();
    public FileScanner(String path) {
        try {
            decryptedFile = new File(path);
            Scanner reader = new Scanner(decryptedFile);
            while (reader.hasNextLine())
            {
                String line=reader.nextLine();
                isCharacterInMap(line);
                dataFile += line;
             //   System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void isCharacterInMap(String sequence)
    {   for(char x : sequence.toCharArray()) {
        if (Q.containsKey(x)) {
            Q.merge(x, 1, Integer::sum);
        } else
            Q.putIfAbsent(x, 1);
    }
    }
    public Map<Character,String> CreateMap()
    {   for(Map.Entry<Character,Integer> entry: Q.entrySet() )
    {
      //  System.out.println(entry.getKey()+ " jest ich :"+entry.getValue()+"\n");
        chars.add(entry.getKey());
        countedChars.add(entry.getValue());

    }
        char[] chars1=new char[chars.size()];
        for(int i=0;i<chars.size();i++)
        {
            chars1[i]=chars.get(i);
        }
        int[] ammountOfCharacters= new int[countedChars.size()];
        for(int i=0;i<countedChars.size();i++)
        {
            ammountOfCharacters[i]=countedChars.get(i);
        }
        Map<Character, String> encodingMap = HuffmanEncoder.buildEncodingMap(chars1,ammountOfCharacters);
        return encodingMap;
    }

    public String getDataFile() {
        return dataFile;
    }
}
