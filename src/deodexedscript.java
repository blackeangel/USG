import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

public class deodexedscript extends Generator {
    public static void deodexgenerat() {
        Functions.writefile(ERRORLOG, "deodexgenerat Start");
//создаем папку -->
        if (NAME_OS > 0) {
            if (new File(dirTmp).exists()) {
                Functions.deletefile(new File(dirTmp));//если есть папка tmp - удаляем
            }
            new File(dirScript).mkdirs();//создаем tmp папку со всеми подпапками
            Path path = Paths.get(dirTmp);
            try {
                Files.setAttribute(path, "dos:hidden", true);//делаем скрытой
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (new File(dirTmp).exists()) {
                Functions.deletefile(new File(dirTmp));//если есть папка tmp - удаляем
            }
            new File(dirScript).mkdirs();//создаем tmp папку со всеми подпапками
        }
// <-- создаем папку
        String f;
        f = dirScript + System.getProperty("file.separator") + "update-binary";
        Functions.writefile(ERRORLOG, f);
        Functions.writefile(f, "#!/sbin/sh");
        Functions.writefile(f, "PIPE=$2; ZIP=$3");
        Functions.writefile(f, "ui_print() { ");
        Functions.writefile(f, "echo -n -e \"ui_print $1\n\" > /proc/self/fd/$PIPE");
        Functions.writefile(f, "echo -n -e \"ui_print\n\" > /proc/self/fd/$PIPE");
        Functions.writefile(f, "echo \"$1\"");
        Functions.writefile(f, "}");
        Functions.writefile(f, "mount /system");
        Functions.writefile(f, "mount -o remount,rw /system");
        Functions.writefile(f, "API=$(cat /system/build.prop | grep \"ro.build.version.sdk=\" | dd bs=1 skip=21 count=2 2>/dev/null)");
        Functions.writefile(f, "if [ \"${API}\" -le \"19\" ]; then");
        Functions.writefile(f, "   d=$(unzip -l \"$ZIP\" | awk '/apk/ { print $4 }' | sed 's/^/\\//;s/$*.apk/.odex/')");
        Functions.writefile(f, "   for f in $d");
        Functions.writefile(f, "     do");
        Functions.writefile(f, "     rm -f $f");
        Functions.writefile(f, "   done");
        Functions.writefile(f, "else");
        Functions.writefile(f, "  for f in `unzip -l \"$ZIP\" | awk '/apk/ { print $4 }' | sed 's/^/\\//'`");
        Functions.writefile(f, "    do");
        Functions.writefile(f, "      rm -rf ${f%\\/*}/oat ${f%\\/*}/arm*");
        Functions.writefile(f, "  done");
        Functions.writefile(f, "fi");
        Functions.writefile(f, "unzip -o \"$ZIP\" \"system\\*\" -d \"/\"");
        Functions.writefile(f, "umount /system");
//если галочка на zip стоит то пакуем
        if (instance.zipcheckBox.isSelected()) {
            if (new File(FlashZip).exists()) {
                Functions.deletefile(new File(FlashZip));//удаляем архив если есть
            }
                Functions.Zipping();
            Functions.deletefile(new File(dirTmp));//удаляем папку tmp
        } else {
            if (NAME_OS > 0) {
                //делаем папку не скрытой
                Path path = Paths.get(dirTmp);
                try {
                    Files.setAttribute(path, "dos:hidden", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                new File(dirTmp).renameTo(new File(pathProgram + System.getProperty("file.separator") + "tmp"));
            }
        }
        if (Objects.equals(LANGUAGE, "русский")) {
            instance.toolbarLabel.setText("Готово!");
            JOptionPane.showMessageDialog(null, "Готово!", NAMEPROGRAMM, 1);
            instance.toolbarLabel.setText("");
            instance.progressBar1.setValue(0);
        } else {
            instance.toolbarLabel.setText("Done!");
            JOptionPane.showMessageDialog(null, "Done!", NAMEPROGRAMM, 1);
            instance.toolbarLabel.setText("");
            instance.progressBar1.setValue(0);
        }
        Functions.writefile(ERRORLOG, "#End deodexgenerat#");
    }

}
