import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class blocks extends Generator {
    public static void block() {
        String Scatert;
        JFileChooser fileopen = new JFileChooser();
        fileopen.setFileFilter(new ExtFileFilter("txt", "*.txt"));//крутой фильтр по расширению
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            Scatert = file.getAbsolutePath();
        } else {
            return;
        }

        //читаем файл построчно, ищем значение
        try {
            String[] rows = new Scanner(new File(Scatert)).useDelimiter("\\Z").next().split("\n");
            String[][] mass = new String[1000][2];
            for (int i = 0; i < rows.length; i++) {
                if (rows[i].indexOf("partition_index:", 0) != -1) {
                    mass[i][0] = rows[i].substring(rows[i].indexOf("SYS", 0) + 3);
                    mass[i][1] = rows[i + 1].substring(rows[i + 1].indexOf(":", 0) + 1);
                }
            }
            String[][] blockmass = Functions.DelEmptyRowTwoDArray(mass);
            String f = projectDir + System.getProperty("file.separator") + "blocks.txt";
            if (new File(f).exists()) {
                Functions.deletefile(new File(f));
            }
            for (int i = 2; i < blockmass.length; i++) {
                Functions.writefile(f, "mmcblk0p" + (Integer.parseInt(blockmass[i][0]) - 1) + "  -->  " + blockmass[i][1]);
            }
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Готово!");
                JOptionPane.showMessageDialog(null, "Готово!", NAMEPROGRAMM, 1);
                instance.toolbarLabel.setText("");
            } else {
                instance.toolbarLabel.setText("Done!");
                JOptionPane.showMessageDialog(null, "Done!", NAMEPROGRAMM, 1);
                instance.toolbarLabel.setText("");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
