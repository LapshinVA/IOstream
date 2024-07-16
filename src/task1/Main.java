package task1;

import java.io.*;


public class Main {
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {


        //1-й этап:
        File dirSrc = new File("C:\\Games\\src");
        File dirRes = new File("C:\\Games\\res");
        File dirSavegames = new File("C:\\Games\\savegames");
        File dirTemp = new File("C:\\Games\\temp");
        creatorDir(dirSrc);
        creatorDir(dirRes);
        creatorDir(dirSavegames);
        creatorDir(dirTemp);

        //2-й этап:
        File dirMain = new File(dirSrc, "main");
        File dirTest = new File(dirSrc, "test");
        creatorDir(dirMain);
        creatorDir(dirTest);

        //3-й этап:
        File fileMain = new File(dirMain, "Main.java");
        File fileUtils = new File(dirMain, "Utils.java");
        creatorFile(fileMain);
        creatorFile(fileUtils);

        //4-й этап:
        File dirDrawables = new File(dirRes, "drawables");
        File dirVectors = new File(dirRes, "vectors");
        File dirIcons = new File(dirRes, "icons");
        creatorDir(dirDrawables);
        creatorDir(dirVectors);
        creatorDir(dirIcons);

        //5-й этап:
        File fileTemp = new File(dirTemp, "temp.txt");
        creatorFile(fileTemp);

        System.out.println(stringBuilder);

        try (FileWriter fileWriter = new FileWriter(fileTemp)) {
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Создает директорию
     * Метод также выполняет логирование
     *
     * @param directory
     */
    private static void creatorDir(File directory) {
        if (!directory.exists()) {
            if (directory.mkdir()) {
                stringBuilder.append("Директория ")
                        .append(directory.getName())
                        .append(" создана.\n");
            } else {
                stringBuilder.append("Директория ")
                        .append(directory.getName())
                        .append(" не создана.\n");
            }
        } else {
            stringBuilder.append("Директория ")
                    .append(directory.getName())
                    .append(" не создана, т.к. уже существует.\n");
        }
    }

    /**
     * Создает файл
     * Метод выполняет логирование
     *
     * @param file
     */
    private static void creatorFile(File file) {
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    stringBuilder.append("Файл ")
                            .append(file.getName())
                            .append(" создан.\n");
                } else {
                    stringBuilder.append("Файл ")
                            .append(file.getName())
                            .append(" не создан.\n");
                }
            } else {
                stringBuilder.append("Файл ")
                        .append(file.getName())
                        .append(" не создан, т.к. уже существует.\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}