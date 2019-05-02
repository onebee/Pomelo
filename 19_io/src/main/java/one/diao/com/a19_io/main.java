package one.diao.com.a19_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author diaokaibin@gmail.com on 2019-05-01.
 */
public class main {

    public static void main(String[] args) {


        io5();
    }

    private static void io1() {
        // 自动关
        try (OutputStream outputStream = new FileOutputStream("./19_io/text.txt")) {
            outputStream.write('a');
            outputStream.write('b');

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io2() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("./19_io/text.txt");
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            System.out.println(line);

            System.out.println(reader.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void io3() {
        try (OutputStream outputStream = new FileOutputStream("./19_io/text.txt");
             Writer writer = new OutputStreamWriter(outputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)

        ) {
            bufferedWriter.write("this is a sample 😆 拟稿");
            bufferedWriter.write("this is a sddddample 😆 拟稿");

//            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void io4() {


        try (InputStream inputStream = new FileInputStream("./19_io/text.txt");
             OutputStream outputStream = new FileOutputStream("./19_io/new_text.txt");) {


            byte[] data = new byte[1024];
            int read;
            while ((read = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, read);
            }


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io5() {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String data;
            while ((data = bufferedReader.readLine()) !=null) {

                bufferedWriter.write("onebit response :" + data);
                bufferedWriter.write("\n");
                bufferedWriter.flush();


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
