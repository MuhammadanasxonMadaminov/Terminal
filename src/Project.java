import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Project {
    static String currentPath = "C://Users//user//IdeaProjects//terminal";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String cmd = "";

        while (!cmd.equals("exit") && !cmd.equals("quit")) {
            System.out.print(currentPath + ">");
            cmd = sc.nextLine();
            System.out.println("currentPath = " + currentPath);
            switch (cmd) {
                case "dir" -> showDirectoryFiles(currentPath);
                case "clear" -> clearConsole();
                default -> {
                    if (cmd.startsWith("cd ")) {
                        if (cmd.endsWith("..")) {
                            int lastIndex = currentPath.lastIndexOf("/");
                            currentPath = currentPath.substring(0, lastIndex);
                        } else {
                            String path = cmd.split(" ")[1];
                            currentPath = currentPath + "/" + path;
                        }
                    }
                    if (cmd.startsWith("mkdir")) {
                        String folder = cmd.split(" ")[1];
                        File file = new File(currentPath + "/"+ folder);
                        System.out.println("file = " + file);
                        System.out.println(file.mkdir());
                    }
                    if (cmd.startsWith("rmdir")) {
                        String folder = cmd.split(" ")[1];
                        File file = new File(currentPath + folder);
                        boolean mkdir = file.delete();
                    }
                    if (cmd.startsWith("create")) {
                        String fileName = cmd.split(" ")[1];
                        createFile(fileName);
                    }
                    if (cmd.startsWith("rm")) {
                        String fileName = cmd.split(" ")[1];
                        removeFile(fileName);
                    }
                    if (cmd.startsWith("touch")) {
                        String[] str = cmd.split(" ");
                        String path = currentPath+ "/" + str[1];
                        StringBuilder text = new StringBuilder();
                        for (int i = 2; i < str.length; i++) {
                            text.append(str[i]).append(" ");
                        }
                        touch(path, text.toString());
                    }
                    if (cmd.startsWith("copy")) {
                        String[] argsCopy = cmd.split(" ");
                        if (argsCopy.length == 3) {
                            copy(argsCopy[1], argsCopy[2]);
                        } else {
                            System.out.println("Invalid copy command.");
                        }
                    }
                }
            }
        }
        System.out.println("Goodbye!");
    }

    private static void showDirectoryFiles(String currentPath) {
        File file = new File(currentPath);
        String[] files = file.list();

        assert files != null;
        for (String fileName : files) {
            System.out.println(fileName);
        }
    }

    private static void removeFile(String fileName) {
        File file = new File(currentPath + "/" + fileName);
        if (file.delete()) {
            System.out.println("File deleted: " + file.getAbsolutePath());
        } else {
            System.out.println("Unable to delete the file or file not found.");
        }
    }

    private static void copy(String source, String target) {
        InputStream in;
        OutputStream out=null;
        try{
            in=new FileInputStream(source);
            out=new FileOutputStream(target);

            int a;
            while((a= in.read())!=-1){
                out.write((char)(a));
            }

            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void createFile(String fileName) {
        try {
            File file = new File(currentPath + "/" + fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    private static void touch(String path, String text) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(text.getBytes());
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
