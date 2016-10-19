package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        try {
            String originalTxt = readData("C:\\Users\\Zaher\\IdeaProjects\\Bit Stuffing\\src\\sample\\inputText.txt", StandardCharsets.UTF_8);
            System.out.println(originalTxt);
            String hexString = convertStringToHex(originalTxt);
            System.out.println(hexString);
            String binary = hexToBin(hexString);
            System.out.println(binary);
            System.out.println();
            System.out.println("01111110111011");
            System.out.println(bitStuffing("01111110111011", 's'));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readData(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String convertStringToHex(String str) {

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }
        System.out.println("Decimal : " + temp.toString());

        return sb.toString();
    }

    public static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

    public static String bitStuffing(String data, char status) {
        String out = new String();
        String res = new String();
        int counter = 0;
        for (int i = 0; i < data.length(); i++) {

            if (data.charAt(i) != '1' && data.charAt(i) != '0') {
                System.out.println("Enter only Binary values!!!");
                return null;
            }
            if (data.charAt(i) == '1') {
                counter++;
                res = res + data.charAt(i);
            } else {
                res = res + data.charAt(i);
                counter = 0;
            }
            if (counter == 5) {
                res = res + '0';
                counter = 0;
            }
        }
//        String inc = "01111110" + res + "01111110";
//        System.out.println("The Message to be transfered: " + inc);
//        System.out.println("Seding Message....");
        counter = 0;
        for (int i = 0; i < res.length(); i++) {

            if (res.charAt(i) == '1') {

                counter++;
                out = out + res.charAt(i);

            } else {
                out = out + res.charAt(i);
                counter = 0;
            }
            if (counter == 5) {
                if ((i + 2) != res.length())
                    out = out + res.charAt(i + 2);
                else
                    out = out + '1';
                i = i + 2;
                counter = 1;
            }
        }
        if (status == 's')
            return res;
        else
            return out;
    }
}
