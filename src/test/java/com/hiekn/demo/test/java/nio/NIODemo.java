package com.hiekn.demo.test.java.nio;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 数据总是从缓冲区写入通道，并从通道读取到缓冲区.
 * <p>
 * 主要通道：
 * DatagramChannel：数据报通道可以通过UDP(用户数据报协议)通过网络读取和写入数据。
 * SocketChannel：数据报通道可以通过TCP(传输控制协议)通过网络读取和写入数据
 * FileChannel：文件通道用于从文件读取数据。
 * ServerSocketChannel：允许用户监听传入的TCP连接，与Web服务器相同。对于每个传入连接，都会为连接创建一个SocketChannel。
 * <p>
 * 核心缓冲区：
 * CharBuffer
 * DoubleBuffer
 * IntBuffer
 * LongBuffer
 * ByteBuffer
 * ShortBuffer
 * FloatBuffer
 * <p>
 * 选择器：用于监视多个通道的对象，如数据到达，连接打开等，单线程可以监视多个通道中的数据。
 * 线程之间的切换对于操作系统来说是昂贵的
 */
public class NIODemo extends TestBase {

    @Test
    public void nio(){

        File file = new File("pom.xml");
        file.toPath();
        Path path = Paths.get("G:\\data");
        path.toFile();
        try {
           Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return super.visitFileFailed(file, exc);
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void socketChannelDemo() throws IOException {
        SocketChannelDemo client = new SocketChannelDemo();
        client.initClient("localhost", 8080);
        client.listen();
    }

    @Test
    public void serverSocketChannelDemo() throws IOException {
        ServerSocketChannelDemo server = new ServerSocketChannelDemo();
        server.initServer(8080);
        server.listen();
    }

    @Test
    public void fileLock() throws IOException {
        String input = "* end of the file.";
        System.out.println("Input string to the test file is: " + input);
        ByteBuffer buf = ByteBuffer.wrap(input.getBytes());
        String fp = "testout-file.txt";
        Path pt = Paths.get(fp);
        FileChannel fc = FileChannel.open(pt, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        System.out.println("File channel is open for write and Acquiring lock...");
        fc.position(fc.size() - 1); // position of a cursor at the end of file
        FileLock lock = fc.lock();
        System.out.println("The Lock is shared: " + lock.isShared());
        fc.write(buf);
        fc.close(); // Releases the Lock
        System.out.println("Content Writing is complete. Therefore close the channel and release the lock.");
        print(fp);
    }

    private void print(String path) throws IOException {
        BufferedReader bufferedreader = Files.newBufferedReader(Paths.get(path));
        String tr = bufferedreader.readLine();
        System.out.println("The Content of testout-file.txt file is: ");
        while (tr != null) {
            System.out.println("    " + tr);
            tr = bufferedreader.readLine();
        }
        bufferedreader.close();
    }

    @Test
    public void scatterGather() {
        String data = "Scattering and Gathering example shown in yiibai.com";
        ScatterGatherDemo scatterGatherDemo = new ScatterGatherDemo();
        scatterGatherDemo.gatherBytes(data);
        scatterGatherDemo.scatterBytes();
    }

    @Test
    public void charset() {
        Charset cs = Charset.forName("UTF-8");
        System.out.println(cs.displayName());
        System.out.println(cs.canEncode());
        String st = "Welcome to yiibai.com, it is Charset test Example.";
        // The conversion of byte buffer from given charset to char buffer in
        // unicode
        ByteBuffer bytebuffer = ByteBuffer.wrap(st.getBytes());
        CharBuffer charbuffer = cs.decode(bytebuffer);
        // The converesion of char buffer from unicode to byte buffer in given
        // charset
        ByteBuffer newbytebuffer = cs.encode(charbuffer);
        while (newbytebuffer.hasRemaining()) {
            char ca = (char) newbytebuffer.get();
            System.out.print(ca);
        }
        newbytebuffer.clear();
    }

    @Test
    public void pipe () throws IOException {

        // The Pipe is created
        Pipe pipe = Pipe.open();
        // For accessing the pipe sink channel
        Pipe.SinkChannel skChannel = pipe.sink();
        String td = "Data is successfully sent for checking the java NIO Channel Pipe.";
        ByteBuffer bb = ByteBuffer.allocate(512);
        bb.clear();
        bb.put(td.getBytes());
        bb.flip();
        // write the data into a sink channel.
        while (bb.hasRemaining()) {
            skChannel.write(bb);
        }
        // For accessing the pipe source channel
        Pipe.SourceChannel sourceChannel = pipe.source();
        bb = ByteBuffer.allocate(512);
        // The data is write to the console
        while (sourceChannel.read(bb) > 0) {
            bb.flip();

            while (bb.hasRemaining()) {
                char TestData = (char) bb.get();
                System.out.print(TestData);
            }
            bb.clear();
        }


    }

    @Test
    public void transfer() throws Exception {
        String relativelyPath = System.getProperty("user.dir");
        // Path of Input files
        String[] iF = new String[]{"/input1.txt", "/input2.txt", "/input3.txt", "/input4.txt"};
        // Path of Output file and contents will be written in this file
        String oF = relativelyPath + "/combine_output.txt";
        // Acquired the channel for output file
        FileOutputStream output = new FileOutputStream(new File(oF));
        WritableByteChannel targetChannel = output.getChannel();
        for (int j = 0; j < iF.length; j++) {
            // Get the channel for input files
            FileInputStream input = new FileInputStream(relativelyPath + iF[j]);
            FileChannel inputChannel = input.getChannel();

            // The data is transfer from input channel to output channel
            inputChannel.transferTo(0, inputChannel.size(), targetChannel);

            // close an input channel
            inputChannel.close();
            input.close();
        }
        // close the target channel
        targetChannel.close();
        output.close();
        System.out.println("All jobs done...");

    }

    private void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        while (src.read(buffer) != -1) {
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
        }
    }

}
