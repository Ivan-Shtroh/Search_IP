mport java.io.*;
import java.nio.file.Files;
import java.util.Scanner;


public class MainClass {

    public static void main(String[] args) throws IOException {
        File f1 = getPathFile();
        File f2 = getDestinationFile();
        String pattern = getPattern();
        searchForMatches(f1, f2, pattern);

    }

    public static File getPathFile() {
        System.out.println("Введите путь до файла или папки, в которых будете искать");
        Scanner sc1 = new Scanner(System.in);
        String path = sc1.nextLine();
        File f1 = new File(path);
        if (f1 == null || !f1.exists()) {
            System.out.println("Путь введен некорректно");
            return getPathFile();
        } else {
            return f1;
        }
    }

    public static File getDestinationFile() {
        System.out.println("Введите путь до папки, в которую будете копировать");
        Scanner sc2 = new Scanner(System.in);
        String destination = sc2.nextLine();
        File f2 = new File(destination);
        if (!f2.exists()) {
            f2.mkdirs();
        }
        return f2;
    }

    public static String getPattern() {
        System.out.println("Введите строку-шаблон");
        Scanner sc3 = new Scanner(System.in);
        String pattern = sc3.nextLine();
        return pattern;
    }


    public static void searchForMatches(File f1, File f2, String pattern) throws IOException {

        if (f1.isDirectory()) {
            for (File i : f1.listFiles()) {
                searchForMatches(i, f2, pattern);
            }
        } else {
            try (FileInputStream fis1 = new FileInputStream(f1)) {
                byte[] buffer = new byte[fis1.available()];
                fis1.read(buffer);
                String[] str1 = new String(buffer, "UTF-16").split("/n");
                for (String i : str1) {
                    if (i.contains(pattern)) {
                        File source = new File(f1.getParent());
                        File dest = new File(f2.getPath() + File.separator + source.getName());
                        File ipFile = new File(f1.getParent() + File.separator + "ip");
                        File destIp = new File(f2 + File.separator + "ip.txt");
                        if (!destIp.exists())
                            destIp.createNewFile();
                        if (ipFile.exists()) {
                        if (!dest.exists()){
                            writeIp(ipFile, destIp);
                        }
                        }
                        copyFolder(source, dest);

                    }
                }
            }
        }
    }

    public static void copyFolder(File source, File dest) throws IOException {
        if (source.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdirs();
            }
            String files[] = source.list();
            for (String i : files) {
                File srcFile = new File(source, i);
                File dstFile = new File(dest, i);
                copyFolder(srcFile, dstFile);

            }
        } else {
            if (!dest.exists())
                Files.copy(source.toPath(), dest.toPath());
        }


    }


    public static void writeIp(File ipFile, File destIp) throws IOException {
        try (FileInputStream fis2 = new FileInputStream(ipFile);
             FileOutputStream fos2 = new FileOutputStream(destIp, true)) {
            byte[] buffer = new byte[fis2.available()];
            fis2.read(buffer);
            fos2.write(buffer);
            fos2.write("\n".getBytes());
        }
    }

}
