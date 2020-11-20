import com.sun.security.auth.UnixNumericGroupPrincipal;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generater extends Generator {
    public static void Gen_osn() {
        Functions.writefile(ERRORLOG, "# Gen_osn -->");
        double Val = 0;
        ADD = 100 / 8;
        Val = Val + ADD;
        String filename;
        //сканим папку project -->
        List<File> mss = new ArrayList();
        //ArrayList mss = new ArrayList();
        ArrayList binmss = new ArrayList();
        ArrayList sssmss = new ArrayList();
        File root = new File(projectDir);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File f : list) {
            if (f.isFile()) {
                if (f.getName().toString().contains(".img") || f.getName().toString().contains(".new.dat") || f.getName().toString().contains(".ext4.win")) {
                    mss.add(f.getAbsoluteFile());
                    //mss.add(f.getAbsoluteFile().toString());
                } else {
                    binmss.add(f.getAbsoluteFile().toString());
                }
            }
        }

        Collections.sort(mss, new WindowsLikeComparator());
        //разбиваем на то что будем распаковывать и то что будем лить как есть
        int kk = mss.size();
        for (int i = 0; i < kk; i++) {
            if (mss.get(i).toString().contains("system") || mss.get(i).toString().contains("vendor") || mss.get(i).toString().contains("data") || mss.get(i).toString().contains("cust") || mss.get(i).toString().contains("oem")) {
                sssmss.add(mss.get(i).toString());
                mss.remove(i);
                kk = mss.size();
                i = i - 1;
            } else {
                binmss.add(mss.get(i).toString());
                mss.remove(i);
                kk = mss.size();
                i = i - 1;
            }

        }
/*        ArrayList imgmass = new ArrayList();
        ArrayList datmass = new ArrayList();
        ArrayList brmass = new ArrayList();
        ArrayList winmass = new ArrayList();

        for (int i = 0; i < sssmss.size(); i++) {
            String ras = Functions.getracshirenie(sssmss.get(i).toString(), 1);
            System.out.println(ras);
            if (Functions.getracshirenie(sssmss.get(i).toString(), 1).equals(".img")) {
                imgmass.add(sssmss.get(i).toString());
            } else {
                if (Functions.getracshirenie(sssmss.get(i).toString(), 1).equals(".dat")) {
                    datmass.add(sssmss.get(i).toString());
                } else {
                    if (Functions.getracshirenie(sssmss.get(i).toString(), 1).equals(".br")) {
                        brmass.add(sssmss.get(i).toString());
                    } else {
                        if (Functions.getracshirenie(sssmss.get(i).toString(), 1).equals(".win") || Functions.getracshirenie(sssmss.get(i).toString(), 1).equals(".win000") || Functions.getracshirenie(sssmss.get(i).toString(), 1).equals(".win001")) {
                            winmass.add(sssmss.get(i).toString());
                        }
                    }
                }
            }
        }
*/

        //выкидываем лишний хлам-с
        int lm = sssmss.size();
        //String filename;
        String filename2;
        for (int i = 0; i < lm; i++) {
            filename = sssmss.get(i).toString().substring(sssmss.get(i).toString().lastIndexOf(sep) + 1, sssmss.get(i).toString().indexOf("."));//имя файла
            for (int j = i + 1; j < lm; j++) {
                if (sssmss.get(j).toString().contains(filename)) {
                    //filename2 = sssmss.get(j).toString().substring(sssmss.get(j).toString().lastIndexOf(sep) + 1, sssmss.get(j).toString().indexOf("."));//имя файла
                    //if (filename2.equals(filename)) {
                    sssmss.remove(j);
                    j = j - 1;
                    lm = sssmss.size();
                    // }
                }
            }
        }
        //выкидываем лишний хлам-с
        int ll = binmss.size();
        for (int i = 0; i < ll; i++) {
            if (binmss.get(i).toString().contains(".txt") || binmss.get(i).toString().contains(".log") || binmss.get(i).toString().contains(".prop") || binmss.get(i).toString().contains("file_contexts") || binmss.get(i).toString().contains(".list") || binmss.get(i).toString().contains(".dat") || !binmss.get(i).toString().contains(".")) {
                binmss.remove(i);
                ll = binmss.size();
                i = i - 1;
            }
        }

        String[] projmass = (String[]) sssmss.toArray(new String[sssmss.size()]);//массив с системой и прочими распаковыемыми образами
        String[] binmass = (String[]) binmss.toArray(new String[binmss.size()]);//массив c прочими разделаи для тупой прошивки
        //сканим папку project <--

// подготавливаем файлы, если они рядом с прогой, а не в папке project -->
//project
        if (new File(pathProgram + sep + "boot.img").exists()) {
            Functions.MoveFile(pathProgram + sep + "boot.img", bootimg);
        }
        if (new File(pathProgram + sep + "system.img").exists()) {
            Functions.MoveFile(pathProgram + sep + "system.img", SystemImg);
        }
        if (new File(pathProgram + sep + "system_statfile.txt").exists()) {
            Functions.MoveFile(pathProgram + sep + "system_statfile.txt", pathStatfile);
        }
        if (new File(pathProgram + sep + "file_contexts").exists()) {
            Functions.MoveFile(pathProgram + sep + "file_contexts", pathFilecontexts);
        }
        if (new File(pathProgram + sep + "file_contexts.bin").exists()) {
            Functions.MoveFile(pathProgram + sep + "file_contexts.bin", pathFilecontextsbin);
        }
        if (new File(pathProgram + sep + "system.new.dat").exists()) {
            Functions.MoveFile(pathProgram + sep + "system.new.dat", SystemNewDat);
        }
        if (new File(pathProgram + sep + "system.transfer.list").exists()) {
            Functions.MoveFile(pathProgram + sep + "system.transfer.list", SystemTransferList);
        }
        if (new File(pathProgram + sep + "build.prop").exists()) {
            Functions.MoveFile(pathProgram + sep + "build.prop", buildpropnear);
        }
//addon folder
        if (new File(pathProgram + sep + "busybox.zip").exists()) {
            Functions.MoveFile(pathProgram + sep + "busybox.zip", BusyBoxZip);
        }
        if (new File(pathProgram + sep + "supersu.zip").exists()) {
            Functions.MoveFile(pathProgram + sep + "supersu.zip", SuperSuZip);
        }
        if (new File(pathProgram + sep + "gapps.zip").exists()) {
            Functions.MoveFile(pathProgram + sep + "gapps.zip", GappsZip);
        }
        if (new File(pathProgram + sep + "xposed.zip").exists()) {
            Functions.MoveFile(pathProgram + sep + "xposed.zip", XposedZip);
        }
        if (new File(pathProgram + sep + "openrecoveryscript").exists()) {
            Functions.MoveFile(pathProgram + sep + "openrecoveryscript", openrecoveryscript);
        }
        if (new File(pathProgram + sep + "busybox").exists()) {
            Functions.MoveFile(pathProgram + sep + "busybox", busyboxbinary);
        }
        if (new File(pathProgram + sep + "initd.zip").exists()) {
            Functions.MoveFile(pathProgram + sep + "initd.zip", initdzip);
        }
        if (new File(pathProgram + sep + "firmware-update").exists()) {
            Functions.MoveFile(pathProgram + sep + "firmware-update", firmwareupdate);
        }
        if (new File(pathProgram + sep + "AromaMods").exists()) {
            Functions.MoveFile(pathProgram + sep + "AromaMods", aromamods);
        }
// <-- подготавливаем файлы, если они рядом с прогой, а не в папке project
//создаем папку -->
        if (NAME_OS > 0) {
            if (new File(dirTmp).exists()) {
                Functions.deletefile(new File(dirTmp));//если есть папка tmp - удаляем
            }
            new File(dirScript).mkdirs();//создаем папку со всеми подпапками
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
            new File(dirScript).mkdirs();//создаем папку со всеми подпапками
        }
// <-- создаем папку


// статус/прогресс бар -->
        if (Objects.equals(LANGUAGE, "русский")) {
            instance.toolbarLabel.setText("Создаю config...");
        } else {
            instance.toolbarLabel.setText("Creating config...");
        }

        Val = Val + ADD;
        instance.progressBar1.setValue((int) Val);
        // статус/прогресс бар <--

// статус/прогресс бар-->

        if (Objects.equals(LANGUAGE, "русский")) {
            instance.toolbarLabel.setText("Cоздаю updater-script...");
        } else {
            instance.toolbarLabel.setText("Creating updater-script...");
        }
        Val = Val + ADD;
        instance.progressBar1.setValue((int) Val);
        // статус/прогресс бар <--

        /* выводим в файл что наработали --> */
//выводим updater scrips
        double sch = 10;//счетчик для того сколько будет идти полоска
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
        if (instance.magiskCheckBox.isSelected()) {
            sch = sch + 1;
        }
        double shag = 1 / sch; //шаг на сколько прибавлять полоску
        double setprogress = 0;
        Functions.writefile(UpdaterScript, "# generated by" + NAMEPROGRAMM + ". USG created by blackeangel 4pda.ru");
        Functions.writefile(UpdaterScript, "show_progress(1.000000,0);");
        Functions.writefile(UpdaterScript, "ui_print(\"Installation started\");");
        Functions.writefile(UpdaterScript, "ui_print(\" - Checking config ...\");");
        setprogress = setprogress + shag;
        Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
        Functions.writefile(UpdaterScript, "package_extract_file(\"META-INF/com/google/android/config\", \"/tmp/config\");");
        Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"chmod\", \"777\", \"/tmp/config\");");
        Functions.writefile(UpdaterScript, "run_program(\"/tmp/config\");");

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*ОСНОВНОЙ ЦИКЛ ГЕНЕРИРОВАНИЯ
         * ОЧЕНЬ ГЕМОРОЙНЫЙ ВЫВОД
         * ВСЁ ЧЕРЕЗ ЖОПУ
         * НО ДЛЯ ЛЮБОГО РАЗДЕЛА
         * ЯКОБЫ...
         * */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int ik = 0; ik < projmass.length; ik++) {
            filename = Functions.getfilename(projmass[ik], 0);
            String outdir = dirTmp + sep + filename;
            pathStatfile = projectDir + sep + filename + "_statfile.txt";
// статус/прогресс бар -->
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Открываю " + filename + "_statfile.txt...");
            } else {
                instance.toolbarLabel.setText("Opening " + filename + "_statfile.txt...");
            }
            Val = Val + ADD;
            instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--
//проверим есть ли файл

            if (!new File(pathStatfile).exists()) {
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("" + filename + "_statfile.txt не найден, получаю...");
                } else {
                    instance.toolbarLabel.setText("" + filename + "_statfile.txt not found, macking...");
                }
                Functions.make_statfile(projmass[ik]);
            }

            if (VERSION_ANDROID == 0) {
                if (projmass[ik].contains("system")) {
                    Functions.VersionAndroid();
                }
            }

            /*читаем _statfile в двумерный массив с помощью регулярки -->*/
            String[][] statfilemass;
            int typestatfilemass;
            statfilemass = Functions.parsetxtfile(pathStatfile, "^(.+?)\\s(\\d+)\\s(\\d+)\\s(\\d+)(?:\\s(0x.*?0))?(?:\\s(u:object_r:.+:s0))?(?:\\s(.+?))?$");//statfile от imgextractor.py
            typestatfilemass = 1;
            if (statfilemass[0][0] == null || statfilemass[0][statfilemass[0].length - 2] == null) {
                statfilemass = Functions.parsetxtfile(pathStatfile, "^(.+?)\\s(\\d+)\\s(\\d+)\\s(\\d+)(?:\\s(.+?))?$");//statfile от imgextractor.exe
                typestatfilemass = 2;
                if (statfilemass[0][0] == null) {
                    statfilemass = Functions.parsetxtfile(pathStatfile, "^(.+?)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(?:(.+?))?$");//untar.py
                    typestatfilemass = 2;
                }
            }
            /*читаем system_statfile в двумерный массив с помощью регулярки <--*/
            //System.out.println("name: " + statfilemass[634][0] + ", uid: " + statfilemass[634][1] + ", guid: " + statfilemass[634][2] + ", perm: " + statfilemass[634][3] + ", cap: " + statfilemass[634][4] + ", cont: " + statfilemass[634][5]);
// статус/прогресс бар -->
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Работаю с " + filename + "_statfile.txt...");
            } else {
                instance.toolbarLabel.setText("Working with " + filename + "_statfile.txt...");
            }
            Val = Val + ADD;
            instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--
//прекращаем все действия если кривые права в system_statfile.txt -->
            if (statfilemass[0][0] != null) {
                if (!statfilemass[0][1].equals("0")) {
                    if (!statfilemass[0][2].equals("0")) {
                        if (Objects.equals(LANGUAGE, "русский")) {
                            JOptionPane.showMessageDialog(null, "" + filename + "_statfile.txt неверен или поврежден! Либо неправильный образ!", NAMEPROGRAMM, 0);
                        } else {
                            JOptionPane.showMessageDialog(null, "" + filename + "_statfile.txt uncorreckted!", NAMEPROGRAMM, 0);
                        }
                        instance.toolbarLabel.setText("");
                        instance.progressBar1.setValue(0);
                        return;
                    }
                }
            }

//прекращаем все действия если кривые права в system_statfile.txt <--
            /*перебираем массив проставляем в 3 столбец "0", заменяем сразу system на /system и // на /  и готовим массив симлинов*/
            String[][] symlink_mass = new String[statfilemass.length][2];
            String[][] workstatfilemass = new String[statfilemass.length + 1][statfilemass[0].length - 1];
            if (typestatfilemass > 1) {
                workstatfilemass = new String[statfilemass.length + 1][statfilemass[0].length];
            }
            for (int i = 0; i < statfilemass.length; i++) {
                if (statfilemass[i][0].contains("Miui")) {
                    MIUI = 1;
                }
                if (statfilemass[i][0].contains("/busybox")) {
                    BUSYBOXINSTALLED = 1;
                }
                if (statfilemass[i][3].length() == 3) {
                    statfilemass[i][3] = "0" + statfilemass[i][3];
                }
                statfilemass[i][0] = statfilemass[i][0].replaceAll("" + filename + "/", "/" + filename + "/");/*замена одного на другое*/
                statfilemass[i][0] = statfilemass[i][0].replaceAll("//", "/");
                for (int j = 0; j < statfilemass[0].length - 1; j++) {
                    workstatfilemass[i][j] = statfilemass[i][j];
                }
                if (statfilemass[i][statfilemass[0].length - 1] != null) {
                    symlink_mass[i][0] = statfilemass[i][0];
                    symlink_mass[i][1] = statfilemass[i][statfilemass[0].length - 1];
                    /*for (int j = 0; j <  workstatfilemass[0].length; j++) {
                        workstatfilemass[i][j] = null;
                    }*/
                    //удаляем старые файлы линков -->
                    if (new File(outdir).exists()) {
                        if (NAME_OS > 0) {
                            Functions.deletefile(new File(dirTmp + symlink_mass[i][0].replace("/", "\\")));
                        } else {
                            Functions.deletefile(new File(dirTmp + symlink_mass[i][0]));
                        }
                        //<-- удаляем старые файлы линков
                    }
                }

            }
            symlink_mass = Functions.DelEmptyRowTwoDArray(symlink_mass);//удаляем пустые строки /*<-- Всё, массив симлинков готов*/
            /*делим на папки и файлы -->*/
            //увеличиваем массив на 1 элемент для разделения на файлы и папки
            workstatfilemass[workstatfilemass.length - 1][0] = "/" + filename + "/zzzzz/booooooooooooooook";//заполняем последнюю запись
            /*вот тут вот у нас генерирование и происходит*/
//читаем file_contexts
//работаем с file_contexts
// статус/прогресс бар-->
            if (typestatfilemass > 1) {
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
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("Работаю с file_contexts...");
                } else {
                    instance.toolbarLabel.setText("Working with file_contexts...");
                }
                Val = Val + ADD;
                instance.progressBar1.setValue((int) Val);
// статус/прогресс бар <--

// работаем с file_contexts
                String[][] filecontextsMass = Functions.parsetxtfile(pathFilecontexts, "^(.*?)[\\s-]+(u:object_r:.*?) *$");
                //гоняем цикл для того чтобы выбрать system
                String[][] filecontexts = new String[filecontextsMass.length][2];
                for (int i = 0; i < filecontextsMass.length; i++) {
                    for (int j = 0; j < 2; j++) {
                        //filecontextsMass[i][0].replace("/(system\\/vendor|vendor)","/system/vendor");
                        if (filecontextsMass[i][0].indexOf("#", 0) != 0) {
                            //if (!filecontextsMass[i][0].trim().isEmpty()) {//если строка не пустая
                            if (filecontextsMass[i][0].indexOf("system", 0) != -1 || filecontextsMass[i][0].indexOf("vendor", 0) != -1) {
                                if (!filecontextsMass[i][0].contains("/sys/devices/")) {
                                    if (!filecontextsMass[i][0].contains("/dev/block/")) {
                                        filecontexts[i][j] = filecontextsMass[i][j].replace("\\", "");
                                        // System.out.println(filecontexts[i][j]);
                                    }
                                }
                            }
                        }
                    }
                }
                filecontexts = Functions.DelEmptyRowTwoDArray(filecontexts);
                // статус/прогресс бар-->

                /*вот тут у нас и заканчивается не нужное во многом чтение file_contwxts*/

                for (int i = 0; i < filecontexts.length; i++) {
                    for (int j = 0; j < workstatfilemass.length; j++) {
                        if (workstatfilemass[j][0] != null) {
                            if (workstatfilemass[j][0].compareToIgnoreCase(filecontexts[i][0]) == 0) {
                                workstatfilemass[j][4] = filecontexts[i][1];
                            } else {
                                Pattern pattern = Pattern.compile("^" + filecontexts[i][0] + "$");
                                Matcher matcher = pattern.matcher(workstatfilemass[j][0]);
                                if (matcher.find()) {
                                    workstatfilemass[j][statfilemass[0].length - 1] = filecontexts[i][1];
                                }
                            }
                        }
                    }
                }
            } else {
                for (int j = 0; j < workstatfilemass.length; j++) {
                    if (workstatfilemass[j][4] == null) {
                        workstatfilemass[j][4] = "0x0";
                    }
                }
            }

            String[][] statfile_folder = new String[workstatfilemass.length][workstatfilemass[0].length];//массив папок
            String[][] statfile_files = new String[workstatfilemass.length][workstatfilemass[0].length];//массив файлов

            for (int i = 1; i < workstatfilemass.length - 1; i++) {
                for (int j = 0; j < workstatfilemass[0].length; j++) {
                    if (workstatfilemass[i + 1][0].indexOf(workstatfilemass[i][0] + "/", 0) != -1) {
                        if (workstatfilemass[i][2].compareToIgnoreCase("0") != 0 || workstatfilemass[i][3].compareToIgnoreCase("0755") != 0) {
                            statfile_folder[i][j] = workstatfilemass[i][j];
                        }
                    } else {
                        if (workstatfilemass[i][2].compareToIgnoreCase("0") != 0 || workstatfilemass[i][3].compareToIgnoreCase("0644") != 0) {
                            statfile_files[i][j] = workstatfilemass[i][j];
                        }
                        if (workstatfilemass[i][0].indexOf("" + filename + "/lost+found", 0) != -1 || workstatfilemass[i][0].contains("" + filename + "/app/miuisystem")) {
                            statfile_files[i][0] = null;
                        }
                    }
                }
            }
            //чистка от линков -->
            for (int i = 0; i < symlink_mass.length; i++) {
                for (int j = 0; j < statfile_folder.length; j++) {
                    if (statfile_folder[j][0] != null) {
                        if (symlink_mass[i][0].equals(statfile_folder[j][0])) {
                            for (int k = 0; k < statfile_folder[0].length; k++) {
                                statfile_folder[j][k] = null;
                            }
                        }
                    }
                    if (statfile_files[j][0] != null) {
                        if (symlink_mass[i][0].equals(statfile_files[j][0])) {
                            for (int m = 0; m < statfile_files[0].length; m++) {
                                //System.out.println("j=" + j+" m="+m);
                                statfile_files[j][m] = null;
                            }
                        }
                    }
                }
            }
            //чистка от линков <--
            /*иенно в такой последовательности, иначе, если чистить в общем массиве неправильно разбивается на файлы и папки*/
            statfile_folder = Functions.DelEmptyRowTwoDArray(statfile_folder);
            statfile_files = Functions.DelEmptyRowTwoDArray(statfile_files);
            /*<-- делим на папки и файлы*/

//проверка на версию девайса -->
        /*    if (DEVICE != null || DEVICE2 != null) {
                Functions.writefile(UpdaterScript, "ifelse(is_mounted(\"/system\"), unmount(\"/system\"));");
                Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                Functions.writefile(UpdaterScript, "#assert(getprop(\"ro.product.device\") == \"" + DEVICE + "\" || getprop(\"ro.build.product\") == \"" + DEVICE2 + "\" || getprop(\"ro.product.model\") == \"" + MODEL + "\" || abort(\"E3004: This package is for device: " + DEVICE + "; this device is \" + getprop(\"ro.product.device\") + \".\"););");
            }*/
            Functions.writefile(UpdaterScript, "ifelse(is_mounted(\"/" + filename + "\"), unmount(\"/" + filename + "\"));");
            // Functions.writefile(UpdaterScript, "ifelse(is_mounted(\"/data\"), unmount(\"/data\"));");
            //Functions.writefile(UpdaterScript, "ifelse(is_mounted(\"/cache\"), unmount(\"/cache\"));");

           /* if (MIUI == 1) {
                Functions.writefile(UpdaterScript, "ifelse(is_mounted(\"/cust\"), unmount(\"/cust\"));");
            }*/
            Functions.writefile(UpdaterScript, "ui_print(\" - Mounting /" + filename + "...\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"mount\", \"/" + filename + "\");");

            Functions.writefile(UpdaterScript, "ui_print(\" - Cleaning /" + filename + "...\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"rm\", \"-rf\", \"/" + filename + "\");");
            Functions.writefile(UpdaterScript, "ui_print(\" - Installing " + filename + "...\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "package_extract_dir(\"" + filename + "\", \"/" + filename + "\");");
            Functions.writefile(UpdaterScript, "ui_print(\" - Set Symlinks of /" + filename + " folder...\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
//пишем симлинки
            for (int i = 0; i < symlink_mass.length; i++) {
                //if (!symlink_mass[i][4].contains("busybox")) {
                Functions.writefile(UpdaterScript, "symlink(\"" + symlink_mass[i][1] + "\", \"" + symlink_mass[i][0] + "\");");
                //}
            }

            Functions.writefile(UpdaterScript, "ui_print(\" - Set Permissions of /" + filename + " folder...\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");

            if (VERSION_ANDROID < 19) {
                //пишем папки
                Functions.writefile(UpdaterScript, "set_perm_recursive(0, 0, 0755, 0644, \"/" + filename + "\");");
                for (int i = 0; i < statfile_folder.length; i++) {
                    if (statfile_folder[i][0].indexOf("/vendor", 0) == -1) {
                        if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                            Functions.writefile(UpdaterScript, "set_perm_recursive(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", 0755, " + "\"" + statfile_folder[i][0] + "\");");
                        } else {
                            Functions.writefile(UpdaterScript, "set_perm_recursive(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", 0644, " + "\"" + statfile_folder[i][0] + "\");");
                        }
                    } else {
                        if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                            Functions.writefile(UpdaterScript, "set_perm_recursive(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", 0755, " + "\"" + statfile_folder[i][0] + "\");");
                        } else {
                            Functions.writefile(UpdaterScript, "set_perm(" + statfile_folder[i][1] + ", " + statfile_folder[i][2] + ", " + statfile_folder[i][3] + ", " + "\"" + statfile_folder[i][0] + "\");");
                        }
                    }
                }
                //пишем файлы
                for (int i = 0; i < statfile_files.length; i++) {
                    Functions.writefile(UpdaterScript, "set_perm(" + statfile_files[i][1] + ", " + statfile_files[i][2] + ", " + statfile_files[i][3] + ", " + "\"" + statfile_files[i][0] + "\");");
                }
            } else {//Android 4.4+
                Functions.writefile(UpdaterScript, "set_metadata_recursive(\"/" + filename + "\", \"uid\", 0, \"gid\", 0, \"dmode\", 0755, \"fmode\", 0644, \"capabilities\", 0x0, \"selabel\", \"u:object_r:" + filename + "_file:s0\");");
                if (typestatfilemass > 1) {
                    //пишем папки
                    for (int i = 0; i < statfile_folder.length; i++) {
                        if (statfile_folder[i][0].indexOf("/vendor", 0) == -1) {
                            if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                                Functions.writefile(UpdaterScript, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0755, \"capabilities\", 0x0, \"selabel\", \"" + statfile_folder[i][4] + "\");");
                            } else {
                                Functions.writefile(UpdaterScript, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0644, \"capabilities\", 0x0, \"selabel\", \"" + statfile_folder[i][4] + "\");");
                            }
                        } else {
                            if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                                Functions.writefile(UpdaterScript, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0755, \"capabilities\", 0x0, \"selabel\", \"" + statfile_folder[i][4] + "\");");
                            } else {
                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"mode\", " + statfile_folder[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                            }
                        }
                    }
                    //пишем файлы
                    for (int i = 0; i < statfile_files.length; i++) {
                        if (statfile_files[i][0] != null) {
                            if (statfile_files[i][0].indexOf("bin/") != -1) {
                                if (statfile_files[i][4].compareToIgnoreCase("u:object_r:" + filename + "_file:s0") != 0) {
                                    if (statfile_files[i][0].contains("/run-as")) {
                                        Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0xc0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                    } else {
                                        if (statfile_files[i][0].contains("/wcnss_filter")) {
                                            if (VERSION_ANDROID == 24 || VERSION_ANDROID == 25) {
                                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1000000000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                            } else {
                                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                            }
                                        } else {
                                            if (statfile_files[i][0].contains("/pm-service") || statfile_files[i][0].contains("/imsdatadaemon") || statfile_files[i][0].contains("/ims_rtp_daemon") || statfile_files[i][0].contains("/cnss-daemon")) {
                                                if (VERSION_ANDROID == 24 || VERSION_ANDROID == 25) {
                                                    Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x400, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                } else {
                                                    Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                }
                                            } else {
                                                if (statfile_files[i][0].contains("/" + filename + "/bin/logd")) {
                                                    if (VERSION_ANDROID == 26) {
                                                        Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x440000040, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                    } else {
                                                        Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                    }
                                                } else {
                                                    if (statfile_files[i][0].contains("/" + filename + "/bin/surfaceflinger")) {
                                                        if (VERSION_ANDROID == 25 || VERSION_ANDROID == 26) {
                                                            Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x800000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                        } else {
                                                            Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                        }
                                                    } else {
                                                        if (statfile_files[i][0].contains("/" + filename + "/bin/hostapd")) {
                                                            if (VERSION_ANDROID == 26) {
                                                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x3000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                            } else {
                                                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                            }
                                                        } else {
                                                            if (statfile_files[i][0].contains("/" + filename + "/bin/webview_zygote32") || statfile_files[i][0].contains("/webview_zygote64")) {
                                                                if (VERSION_ANDROID == 26) {
                                                                    Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1c0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                } else {
                                                                    Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                }
                                                            } else {
                                                                if (statfile_files[i][0].contains("/" + filename + "/bin/qmuxd")) {
                                                                    if (VERSION_ANDROID == 26) {
                                                                        Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1000000000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                    } else {
                                                                        Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                    }
                                                                } else {
                                                                    if (statfile_files[i][0].contains("/" + filename + "/bin/hw/android.hardware.bluetooth@1.0-service")) {
                                                                        if (VERSION_ANDROID == 27) {
                                                                            Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x801000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                        } else {
                                                                            Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                        }
                                                                    } else {
                                                                        if (statfile_files[i][0].contains("/" + filename + "/bin/hw/android.hardware.wifi@1.0-service")) {
                                                                            if (VERSION_ANDROID == 27) {
                                                                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x3000, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                            } else {
                                                                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                            }
                                                                        } else {
                                                                            if (statfile_files[i][0].contains("/" + filename + "/bin/cnd")) {
                                                                                if (VERSION_ANDROID == 27) {
                                                                                    Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x1000000400, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                                } else {
                                                                                    Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                                }
                                                                            } else {
                                                                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {// если u:object_r:system_file:s0
                                    if (statfile_files[i][2].compareToIgnoreCase("2000") != 0) {
                                        Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                                    }
                                }
                            } else {//если не bin папка
                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", 0x0, \"selabel\", \"" + statfile_files[i][4] + "\");");
                            }
                        }
                    }
                } else {//python imgextractor
                    //пишем папки
                    for (int i = 0; i < statfile_folder.length; i++) {
                        if (statfile_folder[i][0].indexOf("/vendor", 0) == -1) {
                            if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                                Functions.writefile(UpdaterScript, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0755, \"capabilities\", " + statfile_folder[i][4] + ", \"selabel\", \"" + statfile_folder[i][statfile_folder[0].length - 1] + "\");");
                            } else {
                                Functions.writefile(UpdaterScript, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0644, \"capabilities\", " + statfile_folder[i][4] + ", \"selabel\", \"" + statfile_folder[i][statfile_folder[0].length - 1] + "\");");
                            }
                        } else {
                            if (statfile_folder[i][0].indexOf("/bin", 0) != -1 || statfile_folder[i][0].indexOf("/xbin") != -1) {
                                Functions.writefile(UpdaterScript, "set_metadata_recursive(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"dmode\", " + statfile_folder[i][3] + ", \"fmode\", 0755, \"capabilities\", " + statfile_folder[i][4] + ", \"selabel\", \"" + statfile_folder[i][statfile_folder[0].length - 1] + "\");");
                            } else {
                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_folder[i][0] + "\", \"uid\", " + statfile_folder[i][1] + ", \"gid\", " + statfile_folder[i][2] + ", \"mode\", " + statfile_folder[i][3] + ", \"capabilities\", " + statfile_folder[i][4] + ", \"selabel\", \"" + statfile_folder[i][statfile_folder[0].length - 1] + "\");");
                            }
                        }
                    }
                    //пишем файлы
                    for (int i = 0; i < statfile_files.length; i++) {
                        if (statfile_files[i][0] != null) {
                            if (statfile_files[i][statfile_files[0].length - 1].compareToIgnoreCase("u:object_r:" + filename + "_file:s0") != 0) {
                                Functions.writefile(UpdaterScript, "set_metadata(\"" + statfile_files[i][0] + "\", \"uid\", " + statfile_files[i][1] + ", \"gid\", " + statfile_files[i][2] + ", \"mode\", " + statfile_files[i][3] + ", \"capabilities\", " + statfile_files[i][4] + ", \"selabel\", \"" + statfile_files[i][statfile_files[0].length - 1] + "\");");
                            }
                        }
                    }
                }
            }
            //ставим busybox который есть в прошивке, вдруг он сильно нужен прошивке
            if (projmass[ik].contains("system")) {
                if (BUSYBOXINSTALLED == 1) {
                    if (VERSION_ANDROID < 19) {
                        Functions.writefile(UpdaterScript, "set_perm(0, 0, 0755, \"/" + filename + "/xbin/busybox\");");
                    } else {
                        Functions.writefile(UpdaterScript, "set_metadata(\"/" + filename + "/xbin/busybox\",\"uid\", 0, \"gid\", 0, \"mode\", 0755, \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                    }
                    Functions.writefile(UpdaterScript, "run_program(\"/" + filename + "/xbin/busybox\", \"--install\", \"-s\", \"/" + filename + "/xbin\");");
                }
            }
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"umount\", \"/" + filename + "\");");
            /*Clear Arrays*/
            //statfilemass = new String[0][];
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*КОНЕЦ ГЕМОРОЙНОГО ЦИКЛА
         * НАДЕЮСЬ ВСЁ ПРОШЛО ГЛАДКО
         * ХОТЯ НЕ ПОНЯТНО КАК ЭТО БУДЕТ РАБОТАТЬ
         * ХА-ХА-ХА*/
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //kernel
        for (int jk = 0; jk < binmass.length; jk++) {
            String racshirenie = binmass[jk].substring(binmass[jk].indexOf(binmass[jk].substring(binmass[jk].lastIndexOf(sep) + 1, binmass[jk].length()))).substring(binmass[jk].substring(binmass[jk].indexOf(binmass[jk].substring(binmass[jk].lastIndexOf(sep) + 1, binmass[jk].length()))).indexOf("."), binmass[jk].substring(binmass[jk].indexOf(binmass[jk].substring(binmass[jk].lastIndexOf(sep) + 1, binmass[jk].length()))).length());
            filename = binmass[jk].substring(binmass[jk].indexOf(binmass[jk].substring(binmass[jk].lastIndexOf(sep) + 1, binmass[jk].length()))).substring(0, binmass[jk].substring(binmass[jk].indexOf(binmass[jk].substring(binmass[jk].lastIndexOf(sep) + 1, binmass[jk].length()))).indexOf("."));
            Functions.copyFile(binmass[jk], dirTmp + sep + filename + racshirenie);
            Functions.writefile(UpdaterScript, "ui_print(\" - Flashing " + filename + racshirenie + "...\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "package_extract_file(\"" + filename + racshirenie + "\", file_getprop(\"/tmp/configs\", \"" + filename + "\"));");
        }
        //выводим config
        Functions.extractres("config");
        //если есть boot.img то копируем
        if (new File(bootimg).exists()) {
            Functions.copyFile(bootimg, bootimgto);//копируем boot.img
        }
        //копируем file_contexts тк возможны глюки из за его отсутствия
        if (new File(pathFilecontexts).exists()) {
            Functions.copyFile(pathFilecontexts, dirTmp + sep + "file_contexts");
        }

///если выбрана арома перемещение файлов туда-сюда -->

        if (instance.aromaInstallerCheckBox.isSelected()) {
            new File(dirScript + sep + "aroma").mkdirs();
            new File(modDir).mkdirs();//папка с модами
            Functions.copyDir(aromamods, modDir + sep + instance.foldermods.getText());//копируем моды в папку с именем юзера
            Functions.copyDir(pathBin + sep + "aroma" + sep + "icons", dirScript + sep + "aroma" + sep + "icons");
            Functions.copyDir(pathBin + sep + "aroma" + sep + "ttf", dirScript + sep + "aroma" + sep + "ttf");
            Functions.copyFile(aromabin, aromabinto);
            if (instance.themeCheckBox.isSelected()) {
                new File(aromathemefolderto).mkdirs();
                Functions.copyDir(aromathemefolder + sep + instance.themeslist.getSelectedItem().toString(), aromathemefolderto + sep + instance.themeslist.getSelectedItem().toString());
            }
            aroma_config.gen();
            aromalangeng.englang();
            aromalangrus.ruslang();
            aromachengelog.changelog();

            if (MIUI == 1) {
                Functions.copyFile(updatebinarymiui, dirScript + sep + "update-binary-installer");
            } else {
                if (VERSION_ANDROID < 19) {
                    Functions.copyFile(updatebinary42, dirScript + sep + "update-binary-installer");
                } else {
                    Functions.copyFile(updatebinary71, dirScript + sep + "update-binary-installer");
                }
            }
        } else {
            if (MIUI == 1) {
                Functions.copyFile(updatebinarymiui, updatebinaryto);//копируем файл
            } else {
                if (VERSION_ANDROID < 19) {
                    Functions.copyFile(updatebinary42, updatebinaryto);//копируем файл
                } else {
                    Functions.copyFile(updatebinary71, updatebinaryto);//копируем файл
                }
            }
        }
///<--- если выбрана арома перемещение файлов туда-сюда
        //если это miui
        if (MIUI == 1) {
            //preloader
            if (new File(preloaderimg).exists()) {
                Functions.copyFile(preloaderimg, preloaderimgto);
                Functions.writefile(UpdaterScript, "assert(write_preloader(\"preloader.img\"));");
            }
            //firmware-update
            if (new File(firmwareupdate).exists()) {
                Functions.copyDir(firmwareupdate, dirTmp + sep + "firmware-update");
                ArrayList<String> listfiles = new ArrayList<String>();
                File userdir = new File(firmwareupdate);
                if (userdir.list() == null) return;
                for (String fn : userdir.list()) {
                    listfiles.add(fn);
                }
                String[] firmfolder = listfiles.toArray(new String[0]);
                Functions.writefile(UpdaterScript, "ui_print(\"- Patching firmware images...\");");
                Functions.writefile(UpdaterScript, "ifelse(msm.boot_update(\"main\"), (");
                for (int i = 0; i < firmfolder.length; i++) {
                    if (firmfolder[i].contains("cmnlib64.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/cmnlib64.mbn\", file_getprop(\"/tmp/configs\", \"cmnlib64\"));");
                    }
                    if (firmfolder[i].contains("cmnlib.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/cmnlib.mbn\", file_getprop(\"/tmp/configs\", \"cmnlib\"));");
                    }
                    if (firmfolder[i].contains("rpm.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/rpm.mbn\", file_getprop(\"/tmp/configs\", \"rpm\"));");
                    }
                    if (firmfolder[i].contains("tz.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/tz.mbn\", file_getprop(\"/tmp/configs\", \"tz\"));");
                    }
                    if (firmfolder[i].contains("emmc_appsboot.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/emmc_appsboot.mbn\", file_getprop(\"/tmp/configs\", \"aboot\"));");
                    }
                    if (firmfolder[i].contains("lksecapp.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/lksecapp.mbn\", file_getprop(\"/tmp/configs\", \"lksecapp\"));");
                    }
                    if (firmfolder[i].contains("sbl1.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/sbl1.mbn\", file_getprop(\"/tmp/configs\", \"sbl1\"));");
                    }
                    if (firmfolder[i].contains("devcfg.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/devcfg.mbn\", file_getprop(\"/tmp/configs\", \"devcfg\"));");
                    }
                    if (firmfolder[i].contains("keymaster.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/keymaster.mbn\", file_getprop(\"/tmp/configs\", \"keymaster\"));");
                    }
                }
                Functions.writefile(UpdaterScript, "), \"\");");
                Functions.writefile(UpdaterScript, "ifelse(msm.boot_update(\"backup\"), (");
                for (int i = 0; i < firmfolder.length; i++) {
                    if (firmfolder[i].contains("cmnlib64.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/cmnlib64.mbn\", file_getprop(\"/tmp/configs\", \"cmnlib64\"));");
                    }
                    if (firmfolder[i].contains("cmnlib.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/cmnlib.mbn\", file_getprop(\"/tmp/configs\", \"cmnlib\"));");
                    }
                    if (firmfolder[i].contains("rpm.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/rpm.mbn\", file_getprop(\"/tmp/configs\", \"rpm\"));");
                    }
                    if (firmfolder[i].contains("tz.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/tz.mbn\", file_getprop(\"/tmp/configs\", \"tz\"));");
                    }
                    if (firmfolder[i].contains("emmc_appsboot.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/emmc_appsboot.mbn\", file_getprop(\"/tmp/configs\", \"aboot\"));");
                    }
                    if (firmfolder[i].contains("lksecapp.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/lksecapp.mbn\", file_getprop(\"/tmp/configs\", \"lksecapp\"));");
                    }
                    if (firmfolder[i].contains("sbl1.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/sbl1.mbn\", file_getprop(\"/tmp/configs\", \"sbl1\"));");
                    }
                    if (firmfolder[i].contains("devcfg.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/devcfg.mbn\", file_getprop(\"/tmp/configs\", \"devcfg\"));");
                    }
                    if (firmfolder[i].contains("keymaster.mbn")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/keymaster.mbn\", file_getprop(\"/tmp/configs\", \"keymaster\"));");
                    }
                }
                Functions.writefile(UpdaterScript, "), \"\");");
                Functions.writefile(UpdaterScript, "msm.boot_update(\"finalize\");");
                for (int i = 0; i < firmfolder.length; i++) {
                    if (firmfolder[i].contains("NON-HLOS.bin")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/NON-HLOS.bin\", file_getprop(\"/tmp/configs\", \"modem\"));");
                    }
                    if (firmfolder[i].contains("adspso.bin")) {
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/adspso.bin\", file_getprop(\"/tmp/configs\", \"dsp\"));");
                    }
                    if (firmfolder[i].contains("splash.img")) {
                        Functions.writefile(UpdaterScript, "ui_print(\"- Installation splash.img...\");");
                        Functions.writefile(UpdaterScript, "mount(\"vfat\", \"EMMC\", file_getprop(\"/tmp/configs\", \"splash\"), \"/firmware\");");
                        Functions.writefile(UpdaterScript, "package_extract_file(\"firmware-update/splash.img\", \"/firmware/image/splash.img\");");
                    }
                }
                Functions.writefile(UpdaterScript, "unmount(\"/firmware\");");
            }
            //miui_update
            new File(comDir + sep + "miui").mkdirs();//создаем папку со всеми подпапками
            Functions.copyFile(pathBin + sep + "miui_update", comDir + sep + "miui" + sep + "miui_update");
            Functions.writefile(UpdaterScript, "ui_print(\"- Miui update...\");");
            Functions.writefile(UpdaterScript, "package_extract_file(\"META-INF/com/miui/miui_update\", \"/cache/miui_update\");");
            if (VERSION_ANDROID < 19) {
                Functions.writefile(UpdaterScript, "set_perm(0, 0, 0755, \"/cache/miui_update\");");
            } else {
                Functions.writefile(UpdaterScript, "set_metadata(\"/cache/miui_update\", \"uid\", 0, \"gid\", 0, \"mode\", 0555, \"capabilities\", 0x0);");
            }
            Functions.writefile(UpdaterScript, "run_program(\"/cache/miui_update\");");
            Functions.writefile(UpdaterScript, "delete(\"/cache/miui_update\");");
        }
//busybox
        //если отмечена галочка на форме
        if (instance.busyBoxCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
            new File(BusyBoxDir).mkdirs();//создаем папку со всеми подпапками
            if (new File(BusyBoxZip).exists()) {
                Functions.copyFile(BusyBoxZip, BusyBoxZipTo);//копируем файл

                Functions.writefile(UpdaterScript, "ui_print(\" - Installing BusyBox...\");");
                setprogress = setprogress + shag;
                Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                Functions.writefile(UpdaterScript, "package_extract_dir(\"META-INF/Mod/busybox\", \"/tmp/busybox\");");
                Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/busybox/busybox.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/busybox\");");
                Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/busybox/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/busybox/busybox.zip\");");
                Functions.writefile(UpdaterScript, "delete_recursive(\"/tmp/busybox\");");
            } else {
                if (new File(busyboxbinary).exists()) {
                    Functions.copyFile(busyboxbinary, busyboxbinaryto);//копируем файл

                    Functions.writefile(UpdaterScript, "ui_print(\" - Installing BusyBox...\");");
                    setprogress = setprogress + shag;
                    Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
                    Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"mount\", \"/system\");");
                    Functions.writefile(UpdaterScript, "package_extract_file(\"META-INF/Mod/busubox\", \"/system/xbin/busybox\");");
                    if (VERSION_ANDROID < 19) {
                        Functions.writefile(UpdaterScript, "set_perm(0, 0, 0755, \"/system/xbin/busybox\");");
                    } else {
                        Functions.writefile(UpdaterScript, "set_metadata(\"/system/xbin/busybox\",\"uid\", 0, \"gid\", 0, \"mode\", 0755, \"capabilities\", 0x0, \"selabel\", \"u:object_r:system_file:s0\");");
                    }
                    Functions.writefile(UpdaterScript, "run_program(\"/system/xbin/busybox\", \"--install\", \"-s\", \"/system/xbin\");");
                    Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"umount\", \"/system\");");
                }
            }

        }
//gapps
        if (instance.GAPPScheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
            new File(GappsDir).mkdirs();//создаем папку со всеми подпапками
            Functions.copyFile(GappsZip, GappsZipTo);//копируем файл
            Functions.writefile(UpdaterScript, "ui_print(\" - Install GAPPS\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "package_extract_dir(\"META-INF/Mod/gapps\", \"/tmp/gapps\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/gapps/gapps.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/gapps\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/gapps/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/gapps/gapps.zip\");");
            Functions.writefile(UpdaterScript, "delete_recursive(\"/tmp/gapps\");");
        }
//SuperSU
        if (instance.superSuCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
            new File(SuperSuDir).mkdirs();//создаем папку со всеми подпапками
            Functions.copyFile(SuperSuZip, SuperSuZipTo);//копируем файл
            Functions.writefile(UpdaterScript, "ui_print(\" - Install SuperSU\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "package_extract_dir(\"META-INF/Mod/supersu\", \"/tmp/supersu\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/supersu/supersu.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/supersu\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/supersu/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/supersu/supersu.zip\");");
            Functions.writefile(UpdaterScript, "delete_recursive(\"/tmp/supersu\");");
        }
//Magisk
        if (instance.magiskCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
            new File(MagiskDir).mkdirs();//создаем папку со всеми подпапками
            Functions.copyFile(MagiskZip, MagiskZipTo);//копируем файл
            Functions.writefile(UpdaterScript, "ui_print(\" - Install Magisk\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "package_extract_dir(\"META-INF/Mod/magisk\", \"/tmp/magisk\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/magisk/magisk.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/magisk\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/magisk/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/magisk/magisk.zip\");");
            Functions.writefile(UpdaterScript, "delete_recursive(\"/tmp/magisk\");");
        }
//Xposed
        if (instance.XposedcheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
            new File(XposedDir).mkdirs();//создаем папку со всеми подпапками
            Functions.copyFile(XposedZip, XposedZipTo);//копируем файл
            Functions.copyFile(openrecoveryscript, openrecoveryscriptto);//копируем файл
            Functions.writefile(UpdaterScript, "ui_print(\" - Install Xposed Installer...\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"mkdir\", \"/cache/recovery\");");
            Functions.writefile(UpdaterScript, "package_extract_file(\"META-INF/Mod/xposed/xposed.zip\", \"/cache/recovery/xposed.zip\");");
            Functions.writefile(UpdaterScript, "package_extract_file(\"META-INF/Mod/xposed/openrecoveryscript\", \"/cache/recovery/openrecoveryscript\");");
            Functions.writefile(UpdaterScript, "ui_print(\"Xposed will be installed after rebooting...\");");
            Functions.writefile(UpdaterScript, "sleep(\"4\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/reboot\", \"recovery\");");
        }
//init.d
        if (instance.initDCheckBox.isSelected() && !instance.aromaInstallerCheckBox.isSelected()) {
            new File(initddir).mkdirs();//создаем папку со всеми подпапками
            Functions.copyFile(initdzip, initdzipto);//копируем файл
            Functions.writefile(UpdaterScript, "ui_print(\" - Install init.d\");");
            setprogress = setprogress + shag;
            Functions.writefile(UpdaterScript, "set_progress(" + BigDecimal.valueOf(setprogress).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "000);");
            Functions.writefile(UpdaterScript, "package_extract_dir(\"META-INF/Mod/initd\", \"/tmp/initd\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/initd/initd.zip\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/initd\");");
            Functions.writefile(UpdaterScript, "run_program(\"/sbin/busybox\", \"sh\", \"/tmp/initd/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/initd/initd.zip\");");
            Functions.writefile(UpdaterScript, "delete_recursive(\"/tmp/initd\");");
        }
        Functions.writefile(UpdaterScript, "set_progress(1.000000);");
        Functions.writefile(UpdaterScript, "ui_print(\"Installation finished!\");");
        /* <-- выводим в файл что наработали */
// статус/прогресс бар-->
        //закончили писать, теперь двигаемся туда-сюда

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
                Functions.writefile(UpdaterScript, "");
                Functions.writefile(UpdaterScript, "if file_getprop(\"/tmp/aroma/checkbox.prop\", \"item.1." + (i + 2) + "\") == \"1\" then");
                Functions.generFiles(UpdaterScript, fromTable[i][0].toString(), fromTable[i][1].toString(), instance.foldermods.getText());
                Functions.writefile(UpdaterScript, "endif;");
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
                new File(dirTmp).renameTo(new File(pathProgram + sep + "tmp"));
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
        Functions.writefile(ERRORLOG, "# <-- Gen_osn#");
// статус/прогресс бар <--

//JOptionPane.showMessageDialog(null,"Все!", "Ошибка!",0);//ошибка
//JOptionPane.showMessageDialog(null,"Все!", "Ошибка!",1);//информация
//JOptionPane.showMessageDialog(null,"Все!", "Ошибка!",2);//предупреждение
//JOptionPane.showMessageDialog(null,"Все!", NAMEPROGRAMM,3);//справка

        //System.out.println(pathFilecontexts);
    }
}

