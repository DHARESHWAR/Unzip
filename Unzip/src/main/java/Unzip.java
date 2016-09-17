import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by ganeshdhareshwar on 16/03/16.
 */
public class Unzip {
    public String zipFilePath = "/Users/ganeshdhareshwar/Documents/java_code_downloaded/Campaign_Overview_CampaignScorecard.zip";
    public String destDirectory = "/Users/ganeshdhareshwar/Documents/unziped_file";
    private static final int BUFFER_SIZE = 4096;

    public void unzip() throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    public void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public static void main(String[] args) {
        Unzip unzip = new Unzip();
        try {
            unzip.unzip();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
