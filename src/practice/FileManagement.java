package practice;

import java.io.*;

public class FileManagement {
    public static void main(String[] args) {
        File file = new File("text.txt");
//        System.out.println(file.isDirectory());
//        file.renameTo(new File("text1.txt"));

//        String[] files = file.list();

//        for (int i = 0; i < files.length; i++) {
//            System.out.println(files[i]);
//        }


//        recursiveEntring(file,2);

    }

    private static void recursiveEntring(File file, Integer tabCount) {
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < tabCount; j++) {
                System.out.print("\t");
            }
            System.out.println(files[i].getName());
            if(files[i].isDirectory()) {
                recursiveEntring(files[i], tabCount +2);
            }
        }
    }



}

