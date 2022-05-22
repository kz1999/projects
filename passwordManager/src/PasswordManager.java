import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PasswordManager {
    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException,
            BadPaddingException, InvalidKeyException {

        byte[] iv = new byte[16];
        byte[] salt = new byte[16];
        String command = args[0];
        String master = args[1];

        File passwords = new File("storage/passwords.txt");
        File saltIv = new File("storage/saltIv.txt");

        byte[] checkEmpty = Files.readAllBytes(saltIv.toPath());

        if (checkEmpty.length < 1 && command.equals("init")) {
            new SecureRandom().nextBytes(iv);
            new SecureRandom().nextBytes(salt);

            writeSaltIv(saltIv, iv, salt);
            byte[] bez = new byte[16];
            new SecureRandom().nextBytes(bez);
            encrypt(bez, master, salt, iv, passwords);
            System.out.println("Password manager initialized!");
            System.exit(0);
        }
        byte[] text = loadPasswords(passwords);
        byte[] loadIv = loadIv(saltIv);
        byte[] loadSalt = loadSalt(saltIv);
        String data = "";
        try {
            data = decrypt(text, master, loadIv, loadSalt);
        } catch (Exception e) {
            System.out.println("Master password incorrect or integrity check failed: ");
            e.printStackTrace();
            System.exit(0);
        }

        if (command.equals("init")) {
            String newMaster = args[2];
            new FileWriter(passwords, false).close();
            new SecureRandom().nextBytes(iv);
            new SecureRandom().nextBytes(salt);
            writeSaltIv(saltIv, iv, salt);
            byte[] bez = new byte[16];
            new SecureRandom().nextBytes(bez);
            encrypt(bez, newMaster, salt, iv, passwords);
            System.out.println("Password manager initialized, old passwords deleted!");
        } else if (command.equals("put")) {
            data += "," + args[2] + "=" + args[3];
            new SecureRandom().nextBytes(iv);
            new SecureRandom().nextBytes(salt);
            encrypt(data.getBytes(UTF_8), master, salt, iv, passwords);
            writeSaltIv(saltIv, iv, salt);
            System.out.println("Stored password for " + args[2]);
        } else if (command.equals("get")) {
            String[] lines = data.split(",");
            String target = "";

            for (String s : lines) {
                if (s.contains(args[2]))
                    target = s;
            }
            if (target.isEmpty()) {
                System.out.println("No password found");
            } else {
                String[] temp = target.split("=");
                System.out.println("Password for " + temp[0] + " is: " + temp[1]);
                new SecureRandom().nextBytes(iv);
                new SecureRandom().nextBytes(salt);
                encrypt(data.getBytes(UTF_8), master, salt, iv, passwords);
                writeSaltIv(saltIv, iv, salt);
            }

        } else {
            new SecureRandom().nextBytes(iv);
            new SecureRandom().nextBytes(salt);
            writeSaltIv(saltIv, iv, salt);
            encrypt(data.getBytes(UTF_8), master, salt, iv, passwords);
            throw new IllegalArgumentException("Illegal command!");
        }
    }

    public static void encrypt(byte[] text, String master, byte[] salt, byte[] iv, File file) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, IOException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(master.toCharArray(), salt, 65536, 256);
        SecretKey secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");


        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));
        byte[] cipherText = cipher.doFinal(text);

        writePassword(file, cipherText);
    }

    public static String decrypt(byte[] text, String master, byte[] iv, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] decode = text;

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(master.toCharArray(), salt, 65536, 256);
        SecretKey secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));
        byte[] plainText = cipher.doFinal(decode);

        return new String(plainText, UTF_8);
    }

    public static byte[] loadPasswords(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    public static byte[] loadSalt(File file) throws FileNotFoundException {
        byte[] arr = new byte[16];
        Scanner sc = new Scanner(file);
        String[] line = sc.nextLine().replaceAll("\\[", "").replaceAll("]", "").split(",");
        for (int i = 0; i < line.length; i++) {
            arr[i] = Byte.parseByte(line[i].trim());
        }
        return arr;
    }

    public static byte[] loadIv(File file) throws FileNotFoundException {
        byte[] arr = new byte[16];
        Scanner sc = new Scanner(file);
        String skip = sc.nextLine();
        String[] line = sc.nextLine().replaceAll("\\[", "").replaceAll("]", "").split(",");
        for (int i = 0; i < line.length; i++) {
            arr[i] = Byte.parseByte(line[i].trim());
        }
        return arr;
    }

    public static void writeSaltIv(File file, byte[] iv, byte[] salt) throws IOException {
        FileWriter fw = new FileWriter(file, false);
        fw.write(Arrays.toString(salt) + "\n" + Arrays.toString(iv));
        fw.close();
    }

    public static void writePassword(File file, byte[] data) throws IOException {
        Files.write(file.toPath(), data);
    }
}