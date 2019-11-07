package one.diao.com.a19_io;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.Okio;
import okio.Source;

/**
 * @author diaokaibin@gmail.com on 2019-05-01.
 */
public class main {

    public static void main(String[] args) {

        test1();
//        okio2();
    }

    private static void test1() {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("./19_io/text.txt");
            outputStream.write('a');
            outputStream.write('b');
            outputStream.write('c');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void okio2() {

        Buffer buffer = new Buffer();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(buffer.outputStream())
        ) {
            objectOutputStream.writeChar('a');
            objectOutputStream.writeUTF("utf");
            objectOutputStream.writeBoolean(true);

            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(buffer.inputStream());
            System.out.println(objectInputStream.readChar());
            System.out.println(objectInputStream.readUTF());
            System.out.println(objectInputStream.readBoolean());


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void okio1() {

        try (Source source = Okio.buffer(Okio.source(new File("./19_io/text.txt")))) {
            Buffer buffer = new Buffer();
            source.read(buffer, 1024);
            String readUtf8 = buffer.readUtf8Line();
            System.out.println(readUtf8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static void nio2() {
        try {
            // 阻塞式的网络非io 和非阻塞
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));

            serverSocketChannel.configureBlocking(false);
            SocketChannel socketChannel = serverSocketChannel.accept();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void nio1() {


        try {
            RandomAccessFile file = new RandomAccessFile("./19_io/text.txt", "r");

            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);

            byteBuffer.flip();
            System.out.println(Charset.defaultCharset().decode(byteBuffer));
            byteBuffer.clear();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        // 实际情况可能是 等待的时候是一个线程  工作的时候是另一个线程

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String data;
            while ((data = bufferedReader.readLine()) != null) {

                bufferedWriter.write("onebit response :" + data);
                bufferedWriter.write("\n");
                bufferedWriter.flush();


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
