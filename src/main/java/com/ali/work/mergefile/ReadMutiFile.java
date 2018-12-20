package com.ali.work.mergefile;

import java.io.File;

public class ReadMutiFile {

    public static void main(String[] args) {

        String path = "E:\\file\\workE:\\file\\work";
        readFile(path);

    }

    private static void readFile(String path){
        File file = new File(path);

        String[] filelist = file.list();
        for (int i = 0; i < filelist.length; i++) {
            File readfile = new File(path + "\\" + filelist[i]);
            if (!readfile.isDirectory()) {
                System.out.println("path=" + readfile.getPath());
                System.out.println("absolutepath="
                        + readfile.getAbsolutePath());
                System.out.println("name=" + readfile.getName());

            }
        }
    }

}
