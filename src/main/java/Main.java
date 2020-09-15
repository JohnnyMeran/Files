import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner inputUser = new Scanner(System.in);

    public static void main(String[] args) {
        File folderBackup = new File("Backup");
        if(!folderBackup.exists()){
            folderBackup.mkdir();
        }
        File file = new File("contacts.csv");
        // create a backup with a given name, read from input
        // if the user doesn't provide a backup file name, create the file with the default one
        // append current time to the file name
        System.out.println("Enter backup file name (leave blank for default):");
        String fileName = inputUser.nextLine();
        if (fileName.equals("")) {
            fileName = "Backup";
        }
        fileName = fileName + System.currentTimeMillis() + ".csv";
        File fileBackup = new File("Backup/"+fileName);
        try {
            Files.copy(file.toPath(), fileBackup.toPath());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // ex: backup_1600102352918.csv


        // list backup all backup files

        File[] backupFiles = folderBackup.listFiles();
        try{
            BasicFileAttributes basic = Files.readAttributes(fileBackup.toPath(),BasicFileAttributes.class);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Arrays.stream(backupFiles).forEach(backupFile -> System.out.println(backupFile.getName()+" (created at "+sdf.format(new Date(backupFile.lastModified()))+") "+basic.size() +"B"));
        }catch (IOException ex){

        }

        // backup_1600102352918.csv (created at 14/09/2020) 120 KB
    }
}
