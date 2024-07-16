package task2;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(100, 500, 3, 20);
        GameProgress gameProgress2 = new GameProgress(100, 1000, 2, 10);
        GameProgress gameProgress3 = new GameProgress(100, 300, 1, 40);

        saveGame("C:\\Games\\savegames\\save1.txt", gameProgress1);
        saveGame("C:\\Games\\savegames\\save2.txt", gameProgress2);
        saveGame("C:\\Games\\savegames\\save3.txt", gameProgress3);

        List<String> list = Arrays.asList(
                "C:\\Games\\savegames\\save1.txt",
                "C:\\Games\\savegames\\save2.txt",
                "C:\\Games\\savegames\\save3.txt"
        );

        zipFiles("C:\\Games\\savegames\\zip.zip", list);


    }

    /**
     * Метод создающий файлы сохранений игрового процесса
     *
     * @param path         - путь с именем файла, куда будет производится сохранение
     * @param gameProgress - объект, хранящий инфу об игровом процессе
     */
    public static void saveGame(String path, GameProgress gameProgress) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            objectOutputStream.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод архивирующий исходные файлы сохранений игрового процесса, пути к которым переданы списком
     * После архивации происходит удаление исходных файлов сохранений игрового процесса
     *
     * @param path - путь с именем файла архива, в который будет прозиводится упаковка
     * @param list - список путей исходных файлов сохранений игрового процесса, подлежащих упаковке
     */
    public static void zipFiles(String path, List<String> list) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(path));
        ) {
            for (String str : list) {
                zipOutputStream.putNextEntry(new ZipEntry(str));
                try (FileInputStream fileInputStream = new FileInputStream(str)) {
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    zipOutputStream.write(buffer);
                    zipOutputStream.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        for (String s : list) {
            File file = new File(s);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Сущность " + file.getName() + " удалена успешно");
                }
            }
        }
    }
}
