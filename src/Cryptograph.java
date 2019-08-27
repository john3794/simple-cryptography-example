import java.io.*;
import java.util.HashMap;

public class Cryptograph
{
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private char[] encoding = "defghijklmnopqrstuvwxyzabc".toCharArray();
    private HashMap<Character, Character> cryptMap;
    private HashMap<Character, Character> decryptMap;

    public Cryptograph()
    {
        this.cryptMap = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++)
        {
            cryptMap.put(alphabet[i], encoding[i]);
        }

        this.decryptMap = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++)
        {
            decryptMap.put(encoding[i], alphabet[i]);
        }
    }

    /**
     * Encrypt given txt file using defined encryption key
     *
     * @param filename
     * @return
     */
    public String encrypt(String filename)
    {
        StringBuilder encryptedText = new StringBuilder();

        try
        {
            String workingDir = "src/txt/";
            File file = new File(workingDir.concat(filename.concat(".txt")));
            FileInputStream fileInputStream = new FileInputStream(file.getPath());
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            BufferedReader input = new BufferedReader(new InputStreamReader(dataInputStream));

            for (String line = input.readLine(); line != null; line = input.readLine())
            {
                for (int i = 0; i < line.length(); i++)
                {
                    char c = line.charAt(i);
                    if (Character.isUpperCase(c))
                    {
                        c = Character.toLowerCase(c);
                    }
                    if (cryptMap.containsKey(c))
                    {
                        encryptedText.append(cryptMap.get(c));
                    }
                    else
                    {
                        encryptedText.append(c);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        String result = encryptedText.toString();
        saveToTxt(filename + "Encrypted", result);
        return result;
    }

    /**
     * Decrypt given txt file using defined decryption key
     *
     * @param filename
     * @return
     */
    public String decrypt(String filename)
    {
        StringBuilder decryptedText = new StringBuilder();

        try
        {
            String workingDir = "src/txt/";
            File file = new File(workingDir.concat(filename.concat(".txt")));
            FileInputStream fileInputStream = new FileInputStream(file.getPath());
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            BufferedReader input = new BufferedReader(new InputStreamReader(dataInputStream));

            for (String line = input.readLine(); line != null; line = input.readLine())
            {
                for (int i = 0; i < line.length(); i++)
                {
                    char c = line.charAt(i);
                    if (decryptMap.containsKey(c))
                    {
                        decryptedText.append(decryptMap.get(c));
                    }
                    else
                    {
                        decryptedText.append(c);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        String result = decryptedText.toString();
        saveToTxt("AliceInWonderlandDecrypted", result);
        return result;
    }

    /**
     * Save output text into txt file
     *
     * @param filename
     * @param text
     */
    private void saveToTxt(String filename, String text)
    {
        String workingDir = "src/txt/";
        File file = new File(workingDir.concat(filename.concat(".txt")));

        try
        {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false); // overwrite file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (!file.exists())
            {
                file.createNewFile();
            }
            bufferedWriter.append(text);
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}
