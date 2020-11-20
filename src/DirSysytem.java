import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirSysytem extends Generator {

    public static void main() {
        Functions.writefile(ERRORLOG, "DirSysytem Start");
        double Val = 0;
        ADD = 100 / 8;
        Val = Val + ADD;
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
            instance.toolbarLabel.setText("Копирую файлы...");
        } else {
            instance.toolbarLabel.setText("Coping files...");
        }
        Functions.copyDir(projectDir + System.getProperty("file.separator") + "system", SystemDir);//копируем папку systrm в tmp

        if (Objects.equals(LANGUAGE, "русский")) {
            instance.toolbarLabel.setText("Читаю файлы...");
        } else {
            instance.toolbarLabel.setText("Reading files...");
        }
        Functions.writefile(ERRORLOG, "walk Start");
        walk(SystemDir);//читаем папку в временный файл
        Functions.writefile(ERRORLOG, "###");
        try {
//парсим файл в массив -->
            String[][] filemass = Functions.parsetxtfile(pathStatfile, "^(.+?)\\s(\\d+)\\s(\\d+)\\s(\\d+)(?:\\s(.+?))?$");
            Functions.deletefile(new File(sss));
//парсим файл в массив <--
            for (int i = 0; i < filemass.length; i++) {
                filemass[i][0] = filemass[i][0].replace("\\", "/");
                filemass[i][0] = filemass[i][0].replace("system/", "/system/");
                filemass[i][0] = filemass[i][0].replace("//", "/");
            }
//читаем файл исключений в массив -->
			Functions.extractres("exeptions");
            String[][] execute = Functions.parsetxtfile(pathStatfile, "^(.+?)\\s(\\d+)\\s(\\d+)\\s(\\d+)(?:\\s(.+?))?$");
			Functions.deletefile(new File(executefile));
//читаем файл исключений в массив <--
            List<String> symlinks = new ArrayList<>();
            for (int i = 0; i < filemass.length; i++) {
                for (int j = 0; j < execute.length; j++) {
                    if (execute[j][4] != null) {
                        if (!execute[j][4].contains("busybox")) {
                            if (Objects.equals(filemass[i][0].substring(filemass[i][0].lastIndexOf("/") + 1), execute[j][4])) {
                                symlinks.add(execute[j][0] + " " + execute[j][4]);
                            } else {
                                if (execute[j][4].contains("/")) {
                                    if (Objects.equals(filemass[i][0].substring(filemass[i][0].lastIndexOf("/") + 1), execute[j][4].substring(execute[j][4].lastIndexOf("/") + 1))) {
                                        if (Objects.equals(filemass[i][0].substring(0, filemass[i][0].lastIndexOf("/")), execute[j][4].substring(0, execute[j][4].lastIndexOf("/")))) {
                                            symlinks.add(execute[j][0] + " " + execute[j][4]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
/*выдираем линки из toolbox и toybox ======>*/
//toybox -->
            for (int i = 0; i < filemass.length; i++) {
                if (filemass[i][0].contains("/toybox")) {
                    Functions.writefile(ERRORLOG, "toybox -->");
                    File file;
                    if (NAME_OS > 0) {
                        file = new File(dirTmp + System.getProperty("file.separator") + filemass[i][0].replace("/", "\\"));
                        Functions.writefile(ERRORLOG, file.toString());
                    } else {
                        file = new File(dirTmp + System.getProperty("file.separator") + filemass[i][0]);
                        Functions.writefile(ERRORLOG, file.toString());
                    }
                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);
                    String line;

                    while ((line = br.readLine()) != null) {
                        if (line.indexOf("sage:", 0) != -1) {
                            line = line.replaceAll(".+[U|u]sage: ", "");
                            if (line.indexOf(" ", 0) != -1) {
                                line = line.substring(0, line.indexOf(" ", 0));
                            }
                            Functions.writefile(ERRORLOG, line);
                            if (!line.contains("toybox") || !line.contains("usage")) {
                                symlinks.add("/system/bin/" + line + " toybox");
                            }
                        }
                    }
                    br.close();
                }
//<-- toybox
//toolbox -->
                if (filemass[i][0].contains("/toolbox")) {
                    Functions.writefile(ERRORLOG, "toolbox -->");
                    File fle;
                    if (NAME_OS > 0) {
                        fle = new File(dirTmp + System.getProperty("file.separator") + filemass[i][0].replace("/", "\\"));
                        Functions.writefile(ERRORLOG, fle.toString());
                    } else {
                        fle = new File(dirTmp + System.getProperty("file.separator") + filemass[i][0]);
                        Functions.writefile(ERRORLOG, fle.toString());
                    }
                    FileInputStream fs = new FileInputStream(fle);
                    InputStreamReader sr = new InputStreamReader(fs, StandardCharsets.UTF_8);
                    BufferedReader r = new BufferedReader(sr);
                    String lne;

                    while ((lne = r.readLine()) != null) {
                        if (lne.indexOf("sage:", 0) != -1) {
                            lne = lne.replaceAll(".+[U|u]sage: ", "");
                            if (lne.indexOf(" ", 0) != -1) {
                                lne = lne.substring(0, lne.indexOf(" ", 0));
                            }
                            if (lne.indexOf("%s", 0) != 0) {
                                Functions.writefile(ERRORLOG, lne);
                                if (!lne.contains("toolbox") || !lne.contains("usage")) {
                                    symlinks.add("/system/bin/" + lne + " toolbox");
                                }
                            }
                        }
                    }
                    r.close();
                }
            }

//<-- toolbox
 /*выдираем линки из toolbox и toybox <======*/
//заколачиваем симлинки ==>
            String[] tmpmass = symlinks.toArray(new String[0]);
            String[][] symlink = new String[tmpmass.length][2];
            for (int j = 0; j < tmpmass.length; j++) {
                symlink[j][0] = tmpmass[j].substring(0, tmpmass[j].indexOf(" "));
                symlink[j][1] = tmpmass[j].substring(tmpmass[j].indexOf(" ") + 1);
            }
//заколачиваем симлинки <==
/*делим на папки и файлы -->*/
//увеличиваем массив на 1 элемент для разделения на файлы и папки

            String[][] workstatfilemass = new String[filemass.length + 1][5];
            for (int i = 0; i < filemass.length; i++) {
                for (int j = 0; j < 5; j++) {
                    workstatfilemass[i][j] = filemass[i][j];
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
                            if (workstatfilemass[i][0].indexOf("system/lost+found", 0) != -1 || workstatfilemass[i][0].contains("system/app/miuisystem")) {
                                statfile_files[i][0] = null;
                            }
                        }
                    }
                }
            }
            statfile_folder = Functions.DelEmptyRowTwoDArray(statfile_folder);
            statfile_files = Functions.DelEmptyRowTwoDArray(statfile_files);
//========================================================================================================================
//========================================================================================================================
//========================================================================================================================
//========================================================================================================================
            if (VERSION_ANDROID == 0) {
                Functions.VersionAndroid();
            }
            if (VERSION_ANDROID > 19) {
/*<-- делим на папки и файлы*/
//работаем с file_contexts
// статус/прогресс бар-->
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("Открываю file_contexts...");
                } else {
                    instance.toolbarLabel.setText("Opening file_contexts...");
                }
                Val = Val + ADD;
                instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--

                if (!new File(pathFilecontexts).exists()) {
                    if (!new File(pathFilecontextsbin).exists()) {
                        Functions.make_file_contexts();
                    } else {
                        Functions.file_contexts_from_bin();
                    }
                }
// статус/прогресс бар-->
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("Работаю с file_contexts...");
                } else {
                    instance.toolbarLabel.setText("Working with file_contexts...");
                }
                Val = Val + ADD;
                instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--
                String[][] filecontextsMass = Functions.parsetxtfile(pathFilecontexts, "^(.*?)[\\s-]+(u:object_r:.*?) *$");
                //гоняем цикл для того чтобы выбрать system
                String[][] filecontexts = new String[filecontextsMass.length][2];
                for (int i = 0; i < filecontextsMass.length; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (filecontextsMass[i][0].indexOf("#", 0) != 0) {
                            if (filecontextsMass[i][0].indexOf("/system/", 0) != -1) {
                                if (filecontextsMass[i][0].indexOf("/data/", 0) != 0) {
                                    filecontexts[i][j] = filecontextsMass[i][j];
                                }
                            }
                        }
                    }
                }
                filecontexts = Functions.DelEmptyRowTwoDArray(filecontexts);
//фиксим если есть * или [0-9]
                for (int i = 0; i < filecontexts.length; i++) {
                    for (int j = 0; j < statfile_files.length; j++) {
                        if (statfile_files[j][0].compareToIgnoreCase(filecontexts[i][0]) == 0) {
                            statfile_files[j][4] = filecontexts[i][1];
                        } else {
                            if (filecontexts[i][0].indexOf("*", 0) != 0 || filecontexts[i][0].indexOf("[0-9]", 0) != 0) {
                                Pattern pattern = Pattern.compile("^" + filecontexts[i][0] + "$");
                                Matcher matcher = pattern.matcher(statfile_files[j][0]);
                                if (matcher.find()) {
                                    statfile_files[j][4] = filecontexts[i][1];
                                }
                            }
                        }
                    }
                }
//вставляем "u:object_r:system_file:s0" в пустые ячейки
                for (int i = 0; i < statfile_files.length; i++) {
                    if (statfile_files[i][4] == null) {
                        statfile_files[i][4] = "u:object_r:system_file:s0";
                    }
                }
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
                    Functions.copyFile(updatebinary71, dirScript + System.getProperty("file.separator") + "update-binary-installer");
                } else {
                    Functions.copyFile(updatebinary71, updatebinaryto);//копируем файл
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
                Functions.writefile(f, "ifelse(is_mounted(\"/system\"), unmount(\"/system\"));");
                Functions.writefile(f, "ifelse(is_mounted(\"/data\"), unmount(\"/data\"));");
                Functions.writefile(f, "ifelse(is_mounted(\"/cache\"), unmount(\"/cache\"));");
                Functions.writefile(f, "show_progress(1.000000,0);");
                Functions.writefile(f, "ui_print(\"Installation started\");");
                Functions.writefile(f, "ui_print(\" - Mounting...\");");

                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/data\");");

                if (instance.aromaInstallerCheckBox.isSelected()) {
                    if (instance.updateClearCheckBox.isSelected()) {
//обновление
                        Functions.writefile(f, "if file_getprop(\"/tmp/aroma/selectbox.prop\", \"selected.1\") == \"1\" then");
                        Functions.writefile(f, "ui_print(\" - Updating only...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data/dalvik-cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                        Functions.writefile(f, "endif;");
//стандартная установка
                        Functions.writefile(f, "if file_getprop(\"/tmp/aroma/selectbox.prop\", \"selected.1\") == \"2\" then");
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Caches...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data/dalvik-cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                        Functions.writefile(f, "endif;");
//чистая установка
                        Functions.writefile(f, "if file_getprop(\"/tmp/aroma/selectbox.prop\", \"selected.1\") == \"2\" then");
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Cache, Data without media\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data\" );");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                        Functions.writefile(f, "endif;");
                    }
                } else {
                    if (instance.datamediacheckBox.isSelected()) {
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Cache, Data without media\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"chattr\", \"+i\", \"/data/media\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data\" );");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"chattr\", \"-i\", \"/data/media\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                    } else {
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Caches...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data/dalvik-cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                    }
                }
                Functions.writefile(f, "ui_print(\"- Installing system...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                Functions.writefile(f, "package_extract_dir(\"system\", \"/system\");");
                Functions.writefile(f, "ui_print(\"- Set Symlinks...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");

//пишем симлинки
                for (int i = 0; i < symlink.length; i++) {
                    if (!symlink[i][1].contains("busybox")) {
                        Functions.writefile(f, "symlink(\"" + symlink[i][1] + "\", \"" + symlink[i][0] + "\");");
                    }
                }
//пишем папки
                Functions.writefile(f, "ui_print(\"- Set Permissions...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                Functions.writefile(f, "set_metadata_recursive(\"/system\", \"uid\", 0, \"gid\", 0, \"dmode\", 0755, \"fmode\", 0644, \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                for (int i = 0; i < statfile_folder.length; i++) {
                    if (statfile_folder[i][0].indexOf("/vendor", 0) == -1) {
                        if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                            Functions.writefile(f, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0755, \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                        } else {
                            Functions.writefile(f, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0644, \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                        }
                    } else {
                        if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                            Functions.writefile(f, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0755, \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                        } else {
                            Functions.writefile(f, "set_metadata(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"mode\", " + statfile_folder[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                        }
                    }
                }
//пишем файлы
                for (int i = 0; i < statfile_files.length; i++) {
                    if (statfile_files[i][0].indexOf("bin/", 0) != -1) {
                        if (statfile_files[i][4].compareToIgnoreCase("u:object_r:system_file:s0") != 0) {
                            if (statfile_files[i][0].indexOf("/run-as", 0) != -1) {
                                Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0xc0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                            } else {
                                Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                            }
                            if (VERSION_ANDROID == 24) {
                                if (statfile_files[i][0].indexOf("/wcnss_filter", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1000000000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                                if (statfile_files[i][0].indexOf("/pm-service", 0) != -1 || statfile_files[i][0].indexOf("/imsdatadaemon", 0) != -1 || statfile_files[i][0].indexOf("/ims_rtp_daemon", 0) != -1 || statfile_files[i][0].indexOf("/cnss-daemon", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x400, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                            } else {
                                Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                            }
                            if (VERSION_ANDROID == 25) {
                                if (statfile_files[i][0].indexOf("/pm-service", 0) != -1 || statfile_files[i][0].indexOf("/imsdatadaemon", 0) != -1 || statfile_files[i][0].indexOf("/ims_rtp_daemon", 0) != -1 || statfile_files[i][0].indexOf("/cnss-daemon", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x400, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                                if (statfile_files[i][0].indexOf("/system/bin/surfaceflinger", 0) != -1 && VERSION_ANDROID > 23) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x800000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                                if (statfile_files[i][0].indexOf("/wcnss_filter", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1000000000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                            } else {
                                Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                            }
                            if (VERSION_ANDROID == 26) {
                                if (statfile_files[i][0].indexOf("/system/bin/logd", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x440000040, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                                if (statfile_files[i][0].indexOf("/system/bin/hostapd", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x3000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                                if (statfile_files[i][0].indexOf("/webview_zygote32", 0) != -1 || statfile_files[i][0].indexOf("/webview_zygote64", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1c0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                                if (statfile_files[i][0].indexOf("/qmuxd", 0) != -1) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1000000000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                                if (statfile_files[i][0].indexOf("/system/bin/surfaceflinger", 0) != -1 && VERSION_ANDROID > 23) {
                                    Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x800000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                }
                            } else {
                                Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                            }
                        } else {
                            if (statfile_files[i][2].compareToIgnoreCase("2000") != 0) {
                                Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                            }
                        }
                    } else {
                        Functions.writefile(f, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                    }
                }
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
//busybox 
                if (instance.busyBoxCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(BusyBoxDir).mkdirs();//создаем папку со всеми подпапками
                    if (new File(BusyBoxZip).exists()) {
                        Functions.copyFile(BusyBoxZip, BusyBoxZipTo);//копируем файл

                        Functions.writefile(f, "ui_print(\"- Installing BusyBox...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                        Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/busybox\", \"/tmp/busybox\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/busybox/busybox.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/busybox\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/busybox/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/busybox/busybox.zip\");");
                        Functions.writefile(f, "delete_recursive(\"/tmp/busybox\");");
                    } else {
                        if (new File(busyboxbinary).exists()) {
                            Functions.copyFile(busyboxbinary, busyboxbinaryto);//копируем файл

                            Functions.writefile(f, "ui_print(\"- Installing BusyBox...\");");
                            setprogress = setprogress + shag;
                            Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                            Functions.writefile(f, "package_extract_file(\"META-INF/Mod/busubox\", \"/system/xbin/busybox\");");
                            Functions.writefile(f, "set_metadata(\"/system/xbin/busybox\",\"uid\", 0, \"gid\", 0, \"mode\", 0755, \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                            Functions.writefile(f, "run_program(\"/system/xbin/busybox\", \"--install\", \"-s\", \"/system/xbin\");");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        }
                    }

                }
//kernel
                Functions.writefile(f, "ui_print(\"- Unpacking kernel...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                //Functions.writefile(f, "set_progress(0.700000);");
                Functions.writefile(f, "package_extract_file(\"boot.img\", \"/tmp/boot.img\");");
                Functions.writefile(f, "package_extract_file(\"boot.sh\", \"/tmp/boot.sh\");");
                Functions.writefile(f, "set_metadata(\"/tmp/boot.sh\", \"uid\", 0, \"gid\", 0, \"mode\", 0777);");
                Functions.writefile(f, "run_program(\"/tmp/boot.sh\");");
                Functions.writefile(f, "delete(\"/tmp/boot.sh\");");
//gapps
                if (instance.GAPPScheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(GappsDir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(GappsZip, GappsZipTo);//копируем файл
                    Functions.writefile(f, "ui_print(\"- Install GAPPS\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                    Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/gapps\", \"/tmp/gapps\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/gapps/gapps.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/gapps\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/gapps/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/gapps/gapps.zip\");");
                    Functions.writefile(f, "delete_recursive(\"/tmp/gapps\");");
                }
//SuperSU
                if (instance.superSuCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(SuperSuDir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(SuperSuZip, SuperSuZipTo);//копируем файл
                    Functions.writefile(f, "ui_print(\"- Install SuperSU\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                    Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/supersu\", \"/tmp/supersu\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/supersu/supersu.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/supersu\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/supersu/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/supersu/supersu.zip\");");
                    Functions.writefile(f, "delete_recursive(\"/tmp/supersu\");");
                }
                Functions.writefile(f, "set_progress(1.000000);");
                Functions.writefile(f, "ui_print(\"Installation finished!\");");
//Xposed
                if (instance.XposedcheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(XposedDir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(XposedZip, XposedZipTo);//копируем файл
                    Functions.copyFile(openrecoveryscript, openrecoveryscriptto);//копируем файл
                    Functions.writefile(f, "ui_print(\"Install Xposed Installer...\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mkdir\", \"/cache/recovery\");");
                    Functions.writefile(f, "package_extract_file(\"META-INF/Mod/xposed/xposed.zip\", \"/cache/recovery/xposed.zip\");");
                    Functions.writefile(f, "package_extract_file(\"META-INF/Mod/xposed/openrecoveryscript\", \"/cache/recovery/openrecoveryscript\");");
                    Functions.writefile(f, "ui_print(\"Xposed will be installed after rebooting...\");");
                    Functions.writefile(f, "sleep(\"4\");");
                    Functions.writefile(f, "run_program(\"/sbin/reboot\", \"recovery\");");
                }
//init.d
                if (instance.initDCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(initddir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(initdzip, initdzipto);//копируем файл
                    Functions.writefile(f, "ui_print(\"- Install init.d\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                    Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/initd\", \"/tmp/initd\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/initd/initd.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/initd\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/initd/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/initd/initd.zip\");");
                    Functions.writefile(f, "delete_recursive(\"/tmp/initd\");");
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
//выводим boot.sh
                String d = bootsh;
                Functions.writefile(d, "#!/sbin/sh");
                Functions.writefile(d, "if [ -z $BOOTIMAGE ]; then");
                Functions.writefile(d, "for PARTITION in kern-a KERN-A android_boot ANDROID_BOOT kernel KERNEL boot BOOT lnx LNX; do");
                Functions.writefile(d, "BOOTIMAGE=$(readlink /dev/block/by-name/$PARTITION || readlink /dev/block/platform/*/by-name/$PARTITION || readlink /dev/block/platform/*/*/by-name/$PARTITION)");
                Functions.writefile(d, "if [ ! -z $BOOTIMAGE ]; then break; fi");
                Functions.writefile(d, "done");
                Functions.writefile(d, "fi");
                Functions.writefile(d, "dd if=/tmp/boot.img of=$BOOTIMAGE bs=4096");
                Functions.writefile(d, "rm /tmp/boot.img");
//закончили писать, теперь двигаемся туда-сюда

                //если есть boot.img то копируем
                if (new File(bootimg).exists()) {
                    Functions.copyFile(bootimg, bootimgto);//копируем boot.img
                }
                //копируем file_contexts тк возможны глюки из за его отсутствия
                if (new File(pathFilecontexts).exists()) {
                    Functions.copyFile(pathFilecontexts, dirTmp + System.getProperty("file.separator") + "file_contexts");
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
                            Functions.writefile(f, "if file_getprop(\"/tmp/aroma/checkbox.prop\", \"item.1." + (i + 2) + "\") == \"1\" then");
                            Functions.writefile(f, "ui_print(\"" + fromTable[i][1].toString() + " installing...\"");
                            Functions.writefile(f, "package_extract_file(\"META-INF/Mod/" + instance.foldermods.getText() + "/" + fromTable[i][0].toString() + "\", \"/tmp\");");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/" + fromTable[i][0].toString() + "\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp\");");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/" + fromTable[i][0].toString() + "\");");
                            Functions.writefile(f, "endif;");
                        } else {
                            if (fromTable[i][0].indexOf(".sh", 0) != -1) {
                                Functions.writefile(f, "");
                                Functions.writefile(f, "if file_getprop(\"/tmp/aroma/checkbox.prop\", \"item.1." + (i + 2) + "\") == \"1\" then");
                                Functions.writefile(f, "ui_print(\"" + fromTable[i][1].toString() + " installing...\"");
                                Functions.writefile(f, "package_extract_file(\"META-INF/Mod/" + instance.foldermods.getText() + "/" + fromTable[i][0].toString() + "\", \"/tmp/" + fromTable[i][0].toString() + "\");");
                                Functions.writefile(f, "set_metadata(\"/tmp/" + fromTable[i][0].toString() + "\", \"uid\", 0, \"gid\", 0, \"mode\", 0777);");
                                Functions.writefile(f, "run_program(\"/tmp/" + fromTable[i][0].toString() + "\");");
                                Functions.writefile(f, "delete(\"/tmp/" + fromTable[i][0].toString() + "\");");
                                Functions.writefile(f, "endif;");
                            }
                        }
                    }
                }

            /*<-- если выбрана арома*/
                //если галочка на zip стоит то пакуем
                if (instance.zipcheckBox.isSelected()) {
                    if (new File(FlashZip).exists()) {
// статус/прогресс бар-->
                        Val = Val + ADD;
                        instance.progressBar1.setValue((int) Val);
                        //instance.progressBar1.setString("БАРАКУДА");
// статус/прогресс бар <--
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
                return;
            } else {
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
                    Functions.copyFile(updatebinary42, dirScript + System.getProperty("file.separator") + "update-binary-installer");
                } else {
                    Functions.copyFile(updatebinary42, updatebinaryto);//копируем файл
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
                Functions.writefile(f, "ifelse(is_mounted(\"/system\"), unmount(\"/system\"));");
                Functions.writefile(f, "ifelse(is_mounted(\"/data\"), unmount(\"/data\"));");
                Functions.writefile(f, "ifelse(is_mounted(\"/cache\"), unmount(\"/cache\"));");
                Functions.writefile(f, "show_progress(1.000000,0);");
                Functions.writefile(f, "ui_print(\"Installation started\");");
                Functions.writefile(f, "ui_print(\" - Mounting...\");");

                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/data\");");

                if (instance.aromaInstallerCheckBox.isSelected()) {
                    if (instance.updateClearCheckBox.isSelected()) {
//обновление
                        Functions.writefile(f, "if file_getprop(\"/tmp/aroma/selectbox.prop\", \"selected.1\") == \"1\" then");
                        Functions.writefile(f, "ui_print(\" - Updating only...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data/dalvik-cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                        Functions.writefile(f, "endif;");
//стандартная установка
                        Functions.writefile(f, "if file_getprop(\"/tmp/aroma/selectbox.prop\", \"selected.1\") == \"2\" then");
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Caches...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data/dalvik-cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                        Functions.writefile(f, "endif;");
//чистая установка
                        Functions.writefile(f, "if file_getprop(\"/tmp/aroma/selectbox.prop\", \"selected.1\") == \"2\" then");
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Cache, Data without media\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data\" );");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                        Functions.writefile(f, "endif;");
                    }
                } else {
                    if (instance.datamediacheckBox.isSelected()) {
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Cache, Data without media\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"chattr\", \"+i\", \"/data/media\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data\" );");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"chattr\", \"-i\", \"/data/media\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                    } else {
                        Functions.writefile(f, "ui_print(\" - Cleaning System, Caches...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/data/dalvik-cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/cache\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/cache\");");
                    }
                }

                Functions.writefile(f, "ui_print(\"- Installing system...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                Functions.writefile(f, "package_extract_dir(\"system\", \"/system\");");
                Functions.writefile(f, "ui_print(\"- Set Symlinks...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
//пишем симлинки
                for (int i = 0; i < symlink.length; i++) {
                    if (!symlink[i][4].contains("busybox")) {
                        Functions.writefile(f, "symlink(\"" + symlink[i][1] + "\", \"" + symlink[i][0] + "\");");
                    }
                }
//пишем папки
                Functions.writefile(f, "ui_print(\"- Set Permissions...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                Functions.writefile(f, "set_perm_recursive(0, 0, 0755, 0644, \"/system\");");
                for (int i = 0; i < statfile_folder.length; i++) {
                    if (statfile_folder[i][0].indexOf("/vendor", 0) == -1) {
                        if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                            Functions.writefile(f, "set_perm_recursive(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", 0755, " + "\"" + statfile_folder[i][0] + "\");");
                        } else {
                            Functions.writefile(f, "set_perm_recursive(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", 0644, " + "\"" + statfile_folder[i][0] + "\");");
                        }
                    } else {
                        if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                            Functions.writefile(f, "set_perm_recursive(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", 0755, " + "\"" + statfile_folder[i][0] + "\");");
                        } else {
                            Functions.writefile(f, "set_perm(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", " + "\"" + statfile_folder[i][0] + "\");");
                        }
                    }
                }
//пишем файлы
                for (int i = 0; i < statfile_files.length; i++) {
                    Functions.writefile(f, "set_perm(" + statfile_files[i][1] + ", " + statfile_files[i][2] + ", " + statfile_files[i][3] + ", " + "\"" + statfile_files[i][0] + "\");");
                }
                Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
//busybox
                if (instance.busyBoxCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(BusyBoxDir).mkdirs();//создаем папку со всеми подпапками
                    if (new File(BusyBoxZip).exists()) {
                        Functions.copyFile(BusyBoxZip, BusyBoxZipTo);//копируем файл
                        Functions.writefile(f, "ui_print(\"- Installing BusyBox...\");");
                        setprogress = setprogress + shag;
                        Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                        Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/busybox\", \"/tmp/busybox\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/busybox/busybox.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/busybox\");");
                        Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/busybox/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/busybox/busybox.zip\");");
                        Functions.writefile(f, "delete_recursive(\"/tmp/busybox\");");
                    } else {
                        if (new File(busyboxbinary).exists()) {
                            Functions.copyFile(busyboxbinary, busyboxbinaryto);//копируем файл
                            Functions.writefile(f, "ui_print(\"- Installing BusyBox...\");");
                            setprogress = setprogress + shag;
                            Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                            Functions.writefile(f, "package_extract_file(\"META-INF/Mod/busubox\", \"/system/xbin/busybox\");");
                            Functions.writefile(f, "set_perm(0, 0, 0755, \"/system/xbin/busybox\");");
                            Functions.writefile(f, "run_program(\"/system/xbin/busybox\", \"--install\", \"-s\", \"/system/xbin\");");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                        }
                    }

                }
//kernel
                Functions.writefile(f, "ui_print(\"- Unpacking kernel...\");");
                setprogress = setprogress + shag;
                Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                Functions.writefile(f, "set_progress(0.700000);");
                Functions.writefile(f, "package_extract_file(\"boot.img\", \"/tmp/boot.img\");");
                Functions.writefile(f, "package_extract_file(\"boot.sh\", \"/tmp/boot.sh\");");
                Functions.writefile(f, "set_perm(0, 0, 0755, \"/tmp/boot.sh\")");
                Functions.writefile(f, "run_program(\"/tmp/boot.sh\");");
                Functions.writefile(f, "delete(\"/tmp/boot.sh\");");
//gapps
                if (instance.GAPPScheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(GappsDir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(GappsZip, GappsZipTo);//копируем файл
                    Functions.writefile(f, "ui_print(\"- Install GAPPS\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                    Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/gapps\", \"/tmp/gapps\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/gapps/gapps.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/gapps\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/gapps/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/gapps/gapps.zip\");");
                    Functions.writefile(f, "delete_recursive(\"/tmp/gapps\");");
                }
//SuperSU
                if (instance.superSuCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(SuperSuDir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(SuperSuZip, SuperSuZipTo);//копируем файл
                    Functions.writefile(f, "ui_print(\"- Install SuperSU\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                    Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/supersu\", \"/tmp/supersu\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/supersu/supersu.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/supersu\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/supersu/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/supersu/supersu.zip\");");
                    Functions.writefile(f, "delete_recursive(\"/tmp/supersu\");");
                }
//init.d
                if (instance.initDCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(initddir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(initdzip, initdzipto);//копируем файл
                    Functions.writefile(f, "ui_print(\"- Install init.d\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(f, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + ");");
                    Functions.writefile(f, "package_extract_dir(\"META-INF/Mod/initd\", \"/tmp/initd\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/initd/initd.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/initd\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/initd/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/initd/initd.zip\");");
                    Functions.writefile(f, "delete_recursive(\"/tmp/initd\");");
                }
                Functions.writefile(f, "set_progress(1.000000);");
                Functions.writefile(f, "ui_print(\"Installation finished!\");");
//Xposed
                if (instance.XposedcheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
                    new File(XposedDir).mkdirs();//создаем папку со всеми подпапками
                    Functions.copyFile(XposedZip, XposedZipTo);//копируем файл
                    Functions.copyFile(openrecoveryscript, openrecoveryscriptto);//копируем файл
                    Functions.writefile(f, "ui_print(\"Install Xposed Installer...\");");
                    Functions.writefile(f, "run_program(\"/sbin/busybox\", \"mkdir\", \"/cache/recovery\");");
                    Functions.writefile(f, "package_extract_file(\"META-INF/Mod/xposed/xposed.zip\", \"/cache/recovery/xposed.zip\");");
                    Functions.writefile(f, "package_extract_file(\"META-INF/Mod/xposed/openrecoveryscript\", \"/cache/recovery/openrecoveryscript\");");
                    Functions.writefile(f, "ui_print(\"Xposed will be installed after rebooting...\");");
                    Functions.writefile(f, "sleep(\"4\");");
                    Functions.writefile(f, "run_program(\"/sbin/reboot\", \"recovery\");");
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
//выводим boot.sh
                String d = bootsh;
                Functions.writefile(d, "#!/sbin/sh");
                Functions.writefile(d, "if [ -z $BOOTIMAGE ]; then");
                Functions.writefile(d, "for PARTITION in kern-a KERN-A android_boot ANDROID_BOOT kernel KERNEL boot BOOT lnx LNX; do");
                Functions.writefile(d, "BOOTIMAGE=$(readlink /dev/block/by-name/$PARTITION || readlink /dev/block/platform/*/by-name/$PARTITION || readlink /dev/block/platform/*/*/by-name/$PARTITION)");
                Functions.writefile(d, "if [ ! -z $BOOTIMAGE ]; then break; fi");
                Functions.writefile(d, "done");
                Functions.writefile(d, "fi");
                Functions.writefile(d, "dd if=/tmp/boot.img of=$BOOTIMAGE bs=4096");
                Functions.writefile(d, "rm /tmp/boot.img");
//закончили писать, теперь двигаемся туда-сюда
                Functions.copyFile(updatebinary42, updatebinaryto);//копируем файл
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
                            Functions.writefile(f, "if file_getprop(\"/tmp/aroma/checkbox.prop\", \"item.1." + (i + 2) + "\") == \"1\" then");
                            Functions.writefile(f, "ui_print(\"" + fromTable[i][1].toString() + " installing...\"");
                            Functions.writefile(f, "package_extract_file(\"META-INF/Mod/" + instance.foldermods.getText() + "/" + fromTable[i][0].toString() + "\", \"/tmp\");");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/" + fromTable[i][0].toString() + "\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp\");");
                            Functions.writefile(f, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/" + fromTable[i][0].toString() + "\");");
                            Functions.writefile(f, "endif;");
                        } else {
                            if (fromTable[i][0].indexOf(".sh", 0) != -1) {
                                Functions.writefile(f, "");
                                Functions.writefile(f, "if file_getprop(\"/tmp/aroma/checkbox.prop\", \"item.1." + (i + 2) + "\") == \"1\" then");
                                Functions.writefile(f, "ui_print(\"" + fromTable[i][1].toString() + " installing...\"");
                                Functions.writefile(f, "package_extract_file(\"META-INF/Mod/" + instance.foldermods.getText() + "/" + fromTable[i][0].toString() + "\", \"/tmp/" + fromTable[i][0].toString() + "\");");
                                Functions.writefile(f, "set_perm(0, 0, 0755, \"/tmp/" + fromTable[i][0].toString() + "\");");
                                Functions.writefile(f, "run_program(\"/tmp/" + fromTable[i][0].toString() + "\");");
                                Functions.writefile(f, "delete(\"/tmp/" + fromTable[i][0].toString() + "\");");
                                Functions.writefile(f, "endif;");
                            }
                        }
                    }
                }

            /*<-- если выбрана арома*/
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
                return;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        Functions.writefile(ERRORLOG, "# <-- DirSystem");
    }

    public static void walk(String path) {

        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
                //mss.add(f.getAbsoluteFile().toString().substring(f.getAbsoluteFile().toString().indexOf(pathProgram, 0) + (pathProgram.length() + 1)) + " 0 0 0755");
                Functions.writefile(sss, f.getAbsoluteFile().toString().substring(f.getAbsoluteFile().toString().indexOf(dirTmp, 0) + (dirTmp.length() + 1)) + " 0 0 0755");
            } else {
                Functions.writefile(sss, f.getAbsoluteFile().toString().substring(f.getAbsoluteFile().toString().indexOf(dirTmp, 0) + (dirTmp.length() + 1)) + " 0 0 0644");
                // mss.add(f.getAbsoluteFile().toString().substring(f.getAbsoluteFile().toString().indexOf(pathProgram, 0) + (pathProgram.length() + 1)) + " 0 0 0644");
            }
        }
    }


}
