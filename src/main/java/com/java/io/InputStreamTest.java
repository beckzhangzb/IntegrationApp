package com.java.io;

import java.io.*;

/**
 * @author: zhangzb
 * @date: 2019/7/24 10:50
 * @description:
 */
public class InputStreamTest {

    public static void main(String[] args) throws IOException {
        String filepath = "C:/info/job/data/inputStrData.txt";
        File file = new File(filepath);
        inputStreamForFile(file);
        outputStreamToFile(file);
        readForFile(file);
        writeForFile(file);
    }

    private static void inputStreamForFile(File file) throws IOException {
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
/*        int result;
        while ((result = fis.read()) != -1) {
            System.out.print((char)result);
        }*/

            byte[] buffer = new byte[(fis.available())]; // 缓存大小跟文件字节数一致
            fis.read(buffer);
            System.out.println(new String(buffer));
        } finally {
            fis.close();
        }
    }

    public static void outputStreamToFile(File file) {
        String bufferStr = new String("\n hello from outStream!");
        byte buffer[] = bufferStr.getBytes();
        OutputStream out = null;
        try {
            // 以追加的方式写入文件
            out = new FileOutputStream(file, true);
            out.write(buffer);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ioEx) {
                System.out.println(ioEx.toString());
            }
        }
    }

    private static void readForFile(File file) throws IOException {
        StringBuffer sb;
        FileReader fileReader = null;
        try {
            sb = new StringBuffer();
            char[] buf = new char[1024];

            fileReader = new FileReader(file);
            while (fileReader.read(buf) > 0) {
                sb.append(buf);
            }
        } finally {
            fileReader.close();
        }
        System.out.println("readForFile,  " + sb.toString());
    }


    private static void writeForFile(File file) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            fw.write("this is from write");
        } finally {
            fw.close();
        }
    }
}
