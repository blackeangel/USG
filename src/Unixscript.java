import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unixscript extends Generator {
    public static void script() {
        double Val = 0;
        ADD = 100 / 8;
        Val = Val + ADD;
        if (VERSION_ANDROID == 0) {
            Functions.VersionAndroid();
        }
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
        if (Objects.equals(LANGUAGE, "русский")) {
            instance.toolbarLabel.setText("Открываю system_statfile.txt...");
        } else {
            instance.toolbarLabel.setText("Opening system_statfile.txt...");
        }
        Val = Val + ADD;
        instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--
        /*читаем system_statfile в двумерный массив с помощью регулярки*/
        try {
            String[][] statfilemass = Functions.parsetxtfile(pathStatfile, "^(.+?)\\s(\\d+)\\s(\\d+)\\s(\\d+)(?:\\s(.+?))?$");
// статус/прогресс бар -->
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Работаю с system_statfile.txt...");
            } else {
                instance.toolbarLabel.setText("Working with system_statfile.txt...");
            }
            Val = Val + ADD;
            instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--
            /*перебираем массив проставляем в 3 столбец "0", заменяем сразу system на /system и // на / */
            for (int i = 0; i < statfilemass.length; i++) {
                statfilemass[i][3] = "0" + statfilemass[i][3];
                statfilemass[i][0] = statfilemass[i][0].replaceAll("system/", "/system/");/*замена одного на другое*/
                statfilemass[i][0] = statfilemass[i][0].replaceAll("//", "/");
            }
            /*готовим массив симлинков -->*/
            String[][] symlink_mass = new String[statfilemass.length][5];
            for (int i = 0; i < statfilemass.length; i++) {
                if (statfilemass[i][4] != null) {
                    for (int j = 0; j < 5; j++) {
                        symlink_mass[i][j] = statfilemass[i][j];
                    }
                }
            }
            symlink_mass = Functions.DelEmptyRowTwoDArray(symlink_mass);//удаляем пустые строки
            /*<-- Всё, массив симлинков готов*/
            /*делим на папки и файлы -->*/
//увеличиваем массив на 1 элемент для разделения на файлы и папки
            String[][] workstatfilemass = new String[statfilemass.length + 1][5];
            for (int i = 0; i < statfilemass.length; i++) {
                for (int j = 0; j < 5; j++) {
                    workstatfilemass[i][j] = statfilemass[i][j];
                }
            }
            workstatfilemass[workstatfilemass.length - 1][0] = "/system/zzzzz/booooooooooooooook";//заполняем последнюю запись

            String[][] statfile_folder = new String[workstatfilemass.length][workstatfilemass[0].length];//массив папок
            String[][] statfile_files = new String[workstatfilemass.length][workstatfilemass[0].length];//массив файлов
            for (int i = 1; i < workstatfilemass.length - 1; i++) {
                for (int j = 0; j < workstatfilemass[0].length; j++) {
                    if (workstatfilemass[i][4] == null) {
                        if (workstatfilemass[i + 1][0].indexOf(workstatfilemass[i][0] + "/", 0) != -1) {
                            if (workstatfilemass[i][2].compareToIgnoreCase("0") != 0 || workstatfilemass[i][3].compareToIgnoreCase("0755") != 0) {
                                statfile_folder[i][j] = workstatfilemass[i][j];
                            }
                        } else {
                            if (workstatfilemass[i][2].compareToIgnoreCase("0") != 0 || workstatfilemass[i][3].compareToIgnoreCase("0644") != 0) {
                                statfile_files[i][j] = workstatfilemass[i][j];
                            }
                            if (workstatfilemass[i][0].indexOf("system/lost+found", 0) != -1 || workstatfilemass[i][0].indexOf("system/app/miuisystem") != -1) {
                                statfile_files[i][0] = null;
                            }
                        }
                    }
                }
            }
            statfile_folder = Functions.DelEmptyRowTwoDArray(statfile_folder);
            statfile_files = Functions.DelEmptyRowTwoDArray(statfile_files);
            /*<-- делим на папки и файлы*/
            // статус/прогресс бар-->
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Cоздаю updater-script...");
            } else {
                instance.toolbarLabel.setText("Creating updater-script...");
            }
            Val = Val + ADD;
            instance.progressBar1.setValue((int) Val);
            // статус/прогресс бар <--
            ///если выбрана арома -->
            if (instance.aromaInstallerCheckBox.isSelected()) {
                new File(dirScript + System.getProperty("file.separator") + "aroma").mkdirs();
                new File(modDir).mkdirs();//папка с модами
                Functions.copyDir(aromamods, modDir + System.getProperty("file.separator") + instance.foldermods.getText());//копируем моды в папку с именем юзера
                Functions.copyDir(pathBin + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "icons", dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "icons");
                Functions.copyDir(pathBin + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "ttf", dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "ttf");
                Functions.copyFile(aromabin, aromabinto);
                if (instance.themeCheckBox.isSelected()) {
                    Functions.copyDir(pathBin + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "themes" + System.getProperty("file.separator") + instance.themeslist.getSelectedItem().toString(), dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "themes" + System.getProperty("file.separator") + instance.themeslist.getSelectedItem().toString());
                }

                aroma_config.gen();
                aromalangeng.englang();
                aromalangrus.ruslang();
                aromachengelog.changelog();
            }
///<--- если выбрана арома
            /* выводим в файл что наработали --> */
            String f = UpdaterScript;//наш файл

            double sch = 7;//счетчик для того сколько будет идти полоска
            if (instance.busyBoxCheckBox.isSelected()) {
                sch = sch + 1;
            }
            if (instance.superSuCheckBox.isSelected()) {
                sch = sch + 1;
            }
            if (instance.GAPPScheckBox.isSelected()) {
                sch = sch + 1;
            }
            if (instance.XposedcheckBox.isSelected()) {
                sch = sch + 1;
            }
            if (instance.initDCheckBox.isSelected()) {
                sch = sch + 1;
            }
            double shag = 1 / sch; //шаг на сколько прибавлять полоску
            double setprogress = 0;
            Functions.writefile(f, "# generated by" + NAMEPROGRAMM + ". USG created be blackeangel 4pda.ru");
            Functions.writefile(f, "show_progress 1.000000 0;");
            Functions.writefile(f, "ui_print \"Installation started\";");
            Functions.writefile(f, "ui_print \"- Unpacking kernel...\";");
            Functions.writefile(f, "install_boot");
            Functions.writefile(f, "ui_print \" - Mounting System\";");
            setprogress = setprogress + shag;
            Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
            Functions.writefile(f, "mount /system;");
            Functions.writefile(f, "mount /cache;");
            Functions.writefile(f, "mount /data;");
            if (instance.aromaInstallerCheckBox.isSelected()) {
                if (instance.updateClearCheckBox.isSelected()) {
//обновление
                    Functions.writefile(f, "if [ \"$(file_getprop \"/tmp/aroma/selectbox.prop\" \"selected.1\")\"  == 1 ]; then");
                    Functions.writefile(f, "ui_print \" - Updating only...\";");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                    Functions.writefile(f, "wipe cache;");
                    Functions.writefile(f, "wipe system;");
                    Functions.writefile(f, "fi;");
//стандартная установка
                    Functions.writefile(f, "if [ \"$(file_getprop \"/tmp/aroma/selectbox.prop\" \"selected.1\")\"  == 2 ]; then");
                    Functions.writefile(f, "ui_print \" - Cleaning System, Caches...\";");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                    Functions.writefile(f, "wipe cache;");
                    Functions.writefile(f, "wipe system;");
                    Functions.writefile(f, "wipe data;");
                    Functions.writefile(f, "fi;");
//чистая установка
                    Functions.writefile(f, "if [ \"$(file_getprop \"/tmp/aroma/selectbox.prop\" \"selected.1\")\" == 3 ]; then");
                    Functions.writefile(f, "ui_print \" - Cleaning System, Cache, Data without media\";");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                    Functions.writefile(f, "wipe cache;");
                    Functions.writefile(f, "wipe system;");
                    Functions.writefile(f, "wipe data;");
                    Functions.writefile(f, "fi;");
                }
            } else {
                if (instance.datamediacheckBox.isSelected()) {
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                    Functions.writefile(f, "ui_print \" - Cleaning System, Cache, Data without media\";");
                    Functions.writefile(f, "wipe cache;");
                    Functions.writefile(f, "wipe system;");
                    Functions.writefile(f, "wipe data;");
                } else {
                    Functions.writefile(f, "ui_print \" - Cleaning System, Caches...\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                    Functions.writefile(f, "wipe cache;");
                    Functions.writefile(f, "wipe system;");
                }
            }

            Functions.writefile(f, "ui_print \"- Installing system...\";");
            setprogress = setprogress + shag;
            Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
            Functions.writefile(f, "package_extract_dir \"system\" \"/system\";");
            Functions.writefile(f, "ui_print \"- Set Symlinks...\";");
            setprogress = setprogress + shag;
            Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
//пишем симлинки
            for (int i = 0; i < symlink_mass.length; i++) {
                if (symlink_mass[i][4].indexOf("busybox", 0) != 0) {
                    Functions.writefile(f, "symlink \"" + symlink_mass[i][4] + "\" \"" + symlink_mass[i][0] + "\";");
                }
            }
//пишем папки
            Functions.writefile(f, "ui_print \"- Set Permissions...\";");
            setprogress = setprogress + shag;
            Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");

            Functions.writefile(f, "set_perm_recursive 0 0 0755 0644 /system;");
            for (int i = 0; i < statfile_folder.length; i++) {
                if (statfile_folder[i][0].indexOf("/vendor", 0) == -1) {
                    if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                        Functions.writefile(f, "set_perm_recursive " + statfile_folder[i][1] + " " + statfile_folder[i][2] + " " + statfile_folder[i][3] + " 0755 " + "\"" + statfile_folder[i][0] + "\";");
                    } else {
                        Functions.writefile(f, "set_perm_recursive " + statfile_folder[i][1] + " " + statfile_folder[i][2] + " " + statfile_folder[i][3] + " 0644 " + "\"" + statfile_folder[i][0] + "\";");
                    }
                } else {
                    if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                        Functions.writefile(f, "set_perm_recursive " + statfile_folder[i][1] + " " + statfile_folder[i][2] + " " + statfile_folder[i][3] + " 0755 " + "\"" + statfile_folder[i][0] + "\";");
                    } else {
                        Functions.writefile(f, "set_perm " + statfile_folder[i][1] + " " + statfile_folder[i][2] + " " + statfile_folder[i][3] + " " + "\"" + statfile_folder[i][0] + "\";");
                    }
                }
            }
//пишем файлы
            for (int i = 0; i < statfile_files.length; i++) {
                Functions.writefile(f, "set_perm " + statfile_files[i][1] + " " + statfile_files[i][2] + " " + statfile_files[i][3] + " " + "\"" + statfile_files[i][0] + "\";");
            }
            //выдаем contexts
            Functions.writefile(f, "cat /file_contexts | grep -e '^/system' | tr -d '\\-\\-' | while read p c; do");
            Functions.writefile(f, "if echo \"$p\" | grep \"(\" >/dev/null; then");
            Functions.writefile(f, "p=`echo \"$p\" | tr -d '(.*)?'`");
            Functions.writefile(f, "chcon -R $c $p");
            Functions.writefile(f, "else");
            Functions.writefile(f, "chcon $c $p");
            Functions.writefile(f, "fi");
            Functions.writefile(f, "done");
            Functions.writefile(f, "chcon u:object_r:runas_exec:s0 /system/bin/run-as");

            Functions.writefile(f, "umount /system;");
//busybox 
            if (instance.busyBoxCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                new File(BusyBoxDir).mkdirs();//создаем папку со всеми подпапками
                if (new File(BusyBoxZip).exists()) {
                    Functions.copyFile(BusyBoxZip, BusyBoxZipTo);//копируем файл
                    Functions.writefile(f, "");
                    Functions.writefile(f, "ui_print \"- Installing BusyBox...\";");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");

                    Functions.writefile(f, "mkdir /tmp/busybox;");
                    Functions.writefile(f, "package_extract_dir META-INF/Mod/busybox /tmp/busybox;");
                    Functions.writefile(f, "unzip \"/tmp/busybox/busybox.zip META-INF/com/google/android/*\" -d /tmp/busybox;");
                    Functions.writefile(f, "sh \"/tmp/busybox/META-INF/com/google/android/update-binary\" dummy 1 /tmp/busybox/busybox.zip;");
                    Functions.writefile(f, "delete_recursive /tmp/busybox;");
                } else {
                    if (new File(busyboxbinary).exists()) {
                        Functions.copyFile(busyboxbinary, busyboxbinaryto);//копируем файл
                        Functions.writefile(f, "");
                        Functions.writefile(f, "ui_print \"- Installing BusyBox...\";");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");

                        Functions.writefile(f, "mount /system;");
                        Functions.writefile(f, "package_extract_file META-INF/Mod/busubox system/xbin/busybox;");
                        Functions.writefile(f, "set_perm 0 0 0755 /system/xbin/busybox;");
                        Functions.writefile(f, "system/xbin/busybox --install -s system/xbin;");
                        Functions.writefile(f, "umount /system;");
                    }
                }

            }
//gapps
            if (instance.GAPPScheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                new File(GappsDir).mkdirs();//создаем папку со всеми подпапками
                Functions.copyFile(GappsZip, GappsZipTo);//копируем файл
                Functions.writefile(f, "");
                Functions.writefile(f, "ui_print \"- Install GAPPS\";");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                Functions.writefile(f, "mkdir /tmp/gapps;");
                Functions.writefile(f, "package_extract_dir META-INF/Mod/gapps /tmp/gapps;");
                Functions.writefile(f, "unzip \"/tmp/gapps/gapps.zip META-INF/com/google/android/*\" -d /tmp/gapps;");
                Functions.writefile(f, "sh \"/tmp/gapps/META-INF/com/google/android/update-binary\" dummy 1 /tmp/gapps/gapps.zip;");
                Functions.writefile(f, "delete_recursive /tmp/gapps;");
            }
//SuperSU
            if (instance.superSuCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                new File(SuperSuDir).mkdirs();//создаем папку со всеми подпапками
                Functions.copyFile(SuperSuZip, SuperSuZipTo);//копируем файл
                Functions.writefile(f, "");
                Functions.writefile(f, "ui_print \"- Install SuperSU\";");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                Functions.writefile(f, "mkdir /tmp/supersu;");
                Functions.writefile(f, "package_extract_dir META-INF/Mod/supersu /tmp/supersu;");
                Functions.writefile(f, "unzip \"/tmp/supersu/supersu.zip META-INF/com/google/android/*\" -d /tmp/supersu;");
                Functions.writefile(f, "sh \"/tmp/supersu/META-INF/com/google/android/update-binary\" dummy 1 /tmp/supersu/supersu.zip;");
                Functions.writefile(f, "delete_recursive /tmp/supersu;");
            }
//init.d
            if (instance.initDCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                new File(initddir).mkdirs();//создаем папку со всеми подпапками
                Functions.copyFile(initdzip, initdzipto);//копируем файл
                Functions.writefile(f, "");
                Functions.writefile(f, "ui_print \"- Install init.d\";");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress " + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ";");
                Functions.writefile(f, "mkdir /tmp/initd;");
                Functions.writefile(f, "package_extract_dir META-INF/Mod/initd /tmp/initd;");
                Functions.writefile(f, "unzip \"/tmp/initd/initd.zip META-INF/com/google/android/*\" -d /tmp/initd;");
                Functions.writefile(f, "sh \"/tmp/initd/META-INF/com/google/android/update-binary\" dummy 1 /tmp/initd/initd.zip;");
                Functions.writefile(f, "delete_recursive /tmp/initd;");
            }

            Functions.writefile(f, "set_progress 1.000000;");
            Functions.writefile(f, "ui_print \"Installation finished!\";");
//Xposed
            if (instance.XposedcheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                new File(XposedDir).mkdirs();//создаем папку со всеми подпапками
                Functions.copyFile(XposedZip, XposedZipTo);//копируем файл
                Functions.copyFile(openrecoveryscript, openrecoveryscriptto);//копируем файл
                Functions.writefile(f, "");
                Functions.writefile(f, "ui_print \"Install Xposed Installer...\";");
                Functions.writefile(f, "mkdir /cache/recovery;");
                Functions.writefile(f, "package_extract_file META-INF/Mod/xposed.zip /cache/recovery/xposed.zip;");
                Functions.writefile(f, "package_extract_file META-INF/Mod/openrecoveryscript /cache/recovery/openrecoveryscript;");
                Functions.writefile(f, "ui_print \"Xposed will be installed after rebooting...\";");
                Functions.writefile(f, "sleep 4;");
                Functions.writefile(f, "reboot recovery;");
            }
            /* <-- выводим в файл что наработали */
// статус/прогресс бар-->
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Создаю boot.sh...");
            } else {
                instance.toolbarLabel.setText("Creating boot.sh...");
            }
            Val = Val + ADD;
            instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--
            //если есть boot.img то копируем
            if (new File(bootimg).exists()) {
                Functions.copyFile(bootimg, bootimgto);//копируем boot.img
            }
            /*--> если выбрана арома*/
            if (instance.aromaInstallerCheckBox.isSelected()) {
                instance.modslist.getModel();
                int columns = instance.modslist.getModel().getColumnCount();
                int rows = instance.modslist.getModel().getRowCount();
                String[][] fromTable = new String[rows][columns];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        fromTable[i][j] = String.valueOf(instance.modslist.getValueAt(instance.modslist.convertRowIndexToModel(i), instance.modslist.convertColumnIndexToModel(j)));
                    }
                }
                for (int i = 0; i < fromTable.length; i++) {
                    if (fromTable[i][0].indexOf(".zip", 0) != -1) {
                        Functions.writefile(f, "");
                        Functions.writefile(f, "if [ \"$(file_getprop \"/tmp/aroma/checkbox.prop\" \"item.1." + (i + 2) + "\")\" == 1 ]; then");
                        Functions.writefile(f, "ui_print \"" + fromTable[i][1].toString() + " installing...\"");
                        Functions.writefile(f, "package_extract_file \"META-INF/Mod/" + instance.foldermods.getText() + "/" + fromTable[i][0].toString() + "\", \"/tmp\";");
                        Functions.writefile(f, "unzip \"/tmp/" + fromTable[i][0].toString() + "\" \"META-INF/com/google/android/*\" -d \"/tmp\");");
                        Functions.writefile(f, "sh \"/tmp/META-INF/com/google/android/update-binary\" dummy 1 \"/tmp/" + fromTable[i][0].toString() + "\";");
                        Functions.writefile(f, "fi;");
                    } else {
                        if (fromTable[i][0].indexOf(".sh", 0) != -1) {
                            Functions.writefile(f, "");
                            Functions.writefile(f, "if [ \"$(file_getprop \"/tmp/aroma/checkbox.prop\" \"item.1." + (i + 2) + "\")\" == 1 ]; then");
                            Functions.writefile(f, "ui_print(\"" + fromTable[i][1].toString() + " installing...\"");
                            Functions.writefile(f, "package_extract_file(\"META-INF/Mod/" + instance.foldermods.getText() + "/" + fromTable[i][0].toString() + "\", \"/tmp/" + fromTable[i][0].toString() + "\");");
                            Functions.writefile(f, "set_perm 0 0 0755 \"/tmp/" + fromTable[i][0].toString() + "\";");
                            Functions.writefile(f, "sh \"/tmp/" + fromTable[i][0].toString() + "\");");
                            Functions.writefile(f, "delete \"/tmp/" + fromTable[i][0].toString() + "\";");
                            Functions.writefile(f, "fi;");
                        }
                    }
                }
            }
            /*создаем бинарник -->*/
            binary.bynar();
            /*<-- создаем бинарник*/
            /*<-- если выбрана арома*/
            //копируем file_contexts тк возможны глюки из за его отсутствия
            if (new File(pathFilecontexts).exists()) {
                Functions.copyFile(pathFilecontexts, dirTmp + System.getProperty("file.separator") + "file_contexts");
            }
            //если галочка на zip стоит то пакуем
            if (instance.zipcheckBox.isSelected()) {
                if (new File(FlashZip).exists()) {
// статус/прогресс бар-->
                    Val = Val + ADD;
                    instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--
                    Functions.deletefile(new File(FlashZip));//удаляем архив если есть
                }
                //Functions.Zipped(dirTmp);//запаковываем в архив файлы/
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
            //подписываем если нужно
            if (instance.signZipCheckBox.isSelected()) {
                Functions.SignZip();
            }

// статус/прогресс бар-->
            instance.progressBar1.setValue(100);
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
// статус/прогресс бар <--
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}