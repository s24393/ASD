import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Huffman_Encoding {
    public static void main(String[] args) {
        String encodedString = "";
        FileScanner fileScanner = new FileScanner("test.txt");
        Map<Character, String> encodingMap = fileScanner.CreateMap();
        for (char ch : fileScanner.getDataFile().toCharArray()) {
            encodedString += encodingMap.get(ch);
        }

        SaveCodesMap(encodingMap);
        System.out.println(encodedString);
    WriteBinaryFile(encodedString,"result.bin");
    }


    private static void SaveCodesMap(Map<Character, String> encodingMap) {
        try {
            FileWriter writer = new FileWriter("codes.txt");
            encodingMap.forEach((c, s) -> {
                try {
                    writer.write(c + " " + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void WriteBinaryFile(String content, String path) {
        char[] chars = content.toCharArray();
        int integer=(int)chars[0];
        File file=new File(path);

        try (FileOutputStream fos=new FileOutputStream(file)){
           for (int i = 0; i < content.length() - 7; i += 8) {
              String byteToParse = content.substring(i, Math.min(content.length(), i + 8));
            fos.write((byte) Integer.parseInt(byteToParse, 2));

          }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
