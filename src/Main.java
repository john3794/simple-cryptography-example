public class Main
{
    public static void main(String[] args)
    {
        Cryptograph cryptograph = new Cryptograph();

        // Encrypt original file
        System.out.println(cryptograph.encrypt("AliceInWonderland"));

        // Decrypt *Encrypted.txt file into *Decrypted.txt file
        System.out.println(cryptograph.decrypt("AliceInWonderlandEncrypted"));
    }
}
