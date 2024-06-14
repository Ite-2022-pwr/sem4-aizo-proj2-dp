package pl.pwr.ite.aizo2.service.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    private static final String DATA_PATH = "D:\\Workspace\\PWR\\OiAK\\sem4-oiak-proj-dp-pk\\sem4-aizo-proj2-dp\\src\\main\\resources\\data";

    public static void saveToCSV(String filename, List<String> data) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(createFile("\\output\\".concat(filename))))) {
            for(String result : data) {
                writer.write(result.concat("\n"));
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private static File createFile(String filename) {
        try {
            File file = new File(DATA_PATH.concat(filename));
            file.createNewFile();
            return file;
        } catch (IOException ex) {
            throw  new IllegalArgumentException(ex);
        }
    }
}
