import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.security.Principal;

import static java.util.stream.Collectors.joining;

public class Functions extends Generator {
    private static String Zip;
    private List<String> fileList = new ArrayList<>();
    private String packFilePath;
    private String packDirectoryPath;

    public static boolean isDirEmpty(final Path directory) throws IOException {
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }
    public static String[][] DelEmptyRowTwoDArray(String[][] mass) {
        int k = 0;
        int m;
        for (int i = 0; i < mass.length; i++) {
            m = 0;
            for (int j = 0; j < mass[0].length; j++) {
                if (mass[i][j] == null) {
                    m = m + 1;
                }
            }
            if (m != mass[0].length) {
                k = k + 1;
            }
//старый рабочий без проблем код -->
           /*if (mass[i][0] != null) {
                k = k + 1;
            }*/
//старый рабочий без проблем код <--
        }
        int n = 0;
        String[][] new_mass = new String[k][mass[0].length];
        for (int i = 0; i < mass.length; i++) {
            if (mass[i][0] != null) {
                for (int j = 0; j < mass[0].length; j++) {
                    new_mass[n][j] = mass[i][j];
                }
                n++;
            }
        }
        return new_mass;
    }

    public static void writefile(String FileName, String text) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(FileName, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(text + "\n");
        writer.close();
    }

    public static void deletefile(File path) {
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                if (f.isDirectory()) deletefile(f);
                else f.delete();
            }
        }
        path.delete();
    }

    public static boolean copyDir(String src, String dst) {
        final File srcFile = new File(src);
        final File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isDirectory() && !dstFile.exists()) {
            dstFile.mkdir();
            File nextSrcFile;
            String nextSrcFilename, nextDstFilename;
            for (String filename : srcFile.list()) {
                nextSrcFilename = srcFile.getAbsolutePath()
                        + File.separator + filename;
                nextDstFilename = dstFile.getAbsolutePath()
                        + File.separator + filename;
                nextSrcFile = new File(nextSrcFilename);
                if (nextSrcFile.isDirectory()) {
                    copyDir(nextSrcFilename, nextDstFilename);
                } else {
                    copyFile(nextSrcFilename, nextDstFilename);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean copyFile(String src, String dst) {
        final File srcFile = new File(src);
        final File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isFile() && !dstFile.exists()) {
            try (InputStream in = new FileInputStream(srcFile);
                 OutputStream out = new FileOutputStream(dstFile)) {
                byte[] buffer = new byte[1024];
                int bytes;
                while ((bytes = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytes);
                }
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean MoveFile(String input, String output) {
        copyFile(input, output);
        deletefile(new File(input));
        if (new File(output).exists() == true && new File(output).exists() == false) {
            return true;
        } else {
            return false;
        }
    }

    public static void Zipping() {
        String time = getDateTime();
        if (Objects.equals(LANGUAGE, "русский")) {
            instance.toolbarLabel.setText("Создаю архив...");
        } else {
            instance.toolbarLabel.setText("Creating archive...");
        }
        if (NAME_OS > 0) {
            Functions.runprogram(pythonexe, "-m", "zipfile", "-c", outputDir + sep + "Flash_" + time + ".zip", dirTmp + "/.");
        } else {
            Functions.runprogram("python3", "-m", "zipfile", "-c", outputDir + sep + "Flash_" + time + ".zip", dirTmp + "/.");
        }
        if (new File(outputDir + sep + "Flash_" + time + ".zip").exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Запаковка завершена!");
            } else {
                instance.toolbarLabel.setText("Archiving complete!");
            }
        }
    }

    public static void ExtractImg(String fileimg) {
        writefile(ERRORLOG, "# ExtractSystemImg -->");

        String filename = getfilename(fileimg, 1);
        String racshirenie = getracshirenie(fileimg, 1);
        String tmpsystemstatfile = dirTmp + sep + filename + "_statfile.txt";
        pathStatfile = projectDir + sep + filename + "_statfile.txt";

        if (Objects.equals(LANGUAGE, "русский")) {
            instance.toolbarLabel.setText("Распаковка " + filename + racshirenie + "...");
        } else {
            instance.toolbarLabel.setText("Extracting " + filename + racshirenie + "...");
        }
        Functions.extractres("ext4.py");
        Functions.runprogrampy("imgextractor.py", fileimg, dirTmp + sep + filename, "","","");
        Functions.deletefile(new File(pathBin + sep + "ext4.py"));

        /*Проверки...*/
        int exitdir = 0;
        /*File root = new File(dirTmp);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File f : list) {
            if (f.isDirectory()) {
                if (f.getAbsolutePath().toString().equalsIgnoreCase(dirTmp + sep + filename)) {
                    int count = f.list().length;
                    if (count > 0) {
                        exitdir = 1;
                        return;
                    }
                }
            }
        }
*/
        Path path = Paths.get(dirTmp);
        try {
            if(!isDirEmpty(path)){
                exitdir = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exitdir == 0) {
            if (NAME_OS > 0) {
                Functions.runprogram(ImgExtractor, fileimg, dirTmp + sep + filename, "", "", "");
                if (new File(recoveryfrombootp).exists()) {
                    deletefile(new File(recoveryfrombootp));
                }
                if (new File(journal).exists()) {
                    deletefile(new File(journal));
                }
                if (new File(lostfound).exists()) {
                    deletefile(new File(lostfound));
                }
            } else {
                Functions.runprogram(simgToimg, fileimg, "", "", "", "");
                Functions.writefile(unpacksystemsh, "#!/bin/bash");
                Functions.writefile(unpacksystemsh, "locdir=$PWD");
                Functions.writefile(unpacksystemsh, "mkdir $locdir/" + filename);
                Functions.writefile(unpacksystemsh, "mv $locdir/project/" + filename + racshirenie + " $locdir/project/" + filename + "simg.img");
                Functions.writefile(unpacksystemsh, "$locdir/bin/simg2img $locdir/project/" + filename + "simg.img $locdir/project/" + filename + racshirenie);
                Functions.writefile(unpacksystemsh, "filesize=$(stat -c%s \"$locdir/project/" + filename + racshirenie + "\")");
                Functions.writefile(unpacksystemsh, "if [ \"$filesize\" -gt \"100000\" ]");
                Functions.writefile(unpacksystemsh, "then");
                Functions.writefile(unpacksystemsh, "    sudo mount -t ext4 -o rw,loop $locdir/project/" + filename + racshirenie + " $locdir/" + filename);
                Functions.writefile(unpacksystemsh, "else");
                Functions.writefile(unpacksystemsh, "    rm -f $locdir/project/" + filename + racshirenie);
                Functions.writefile(unpacksystemsh, "    mv $locdir/project/" + filename + "simg.img $locdir/project/" + filename + racshirenie);
                Functions.writefile(unpacksystemsh, "    sudo mount -t ext4 -o rw,loop $locdir/project/" + filename + racshirenie + " $locdir/" + filename);
                Functions.writefile(unpacksystemsh, "fi");
                Functions.writefile(unpacksystemsh, "sudo find $locdir/" + filename + " | xargs stat -c '%N %u %g %a' | awk '! /->/ { print $0 }' | awk -F\"$locdir\" '{ print $2 }' > $locdir/project/" + filename + "_statfile.txt");
                Functions.writefile(unpacksystemsh, "sudo find $locdir/" + filename + " | xargs stat -c '%N %u %g %a' | awk '/->/ { print $1 \" \" $4 \" \" $5 \" \" $6 \" \" $3}' | awk -F\"$locdir\" '{ print $2 }'  >> $locdir/project/" + filename + "_statfile.txt");
                Functions.writefile(unpacksystemsh, "sed -i \"s/[/]//;s/'//g;1,2d;s/[«]//g;s/[»]//g\" $locdir/project/" + filename + "_statfile.txt");
                Functions.writefile(unpacksystemsh, "cat $locdir/project/" + filename + "_statfile.txt | sort > $locdir/project/" + filename + "_statfile2.txt");
                Functions.writefile(unpacksystemsh, "mv $locdir/project/" + filename + "_statfile2.txt $locdir/project/" + filename + "_statfile.txt");
                Functions.writefile(unpacksystemsh, "mkdir $locdir/.tmp/" + filename);
                Functions.writefile(unpacksystemsh, "cp -R $locdir/" + filename + "/* $locdir/.tmp/" + filename);
                Functions.writefile(unpacksystemsh, "sudo umount $locdir/" + filename);
                Functions.writefile(unpacksystemsh, "rm -rf $locdir/" + filename);
                if (new File(unpacksystemsh).exists()) {
                    Functions.runprogram("chmod", Integer.toString(755), unpacksystemsh, "", "", "");
                    Functions.runprogram(unpacksystemsh, "", "", "", "", "");
                    deletefile(new File(unpacksystemsh));
                }
            }
        }
        if (new File(tmpsystemstatfile).exists()) {
            MoveFile(tmpsystemstatfile, pathStatfile);
        }
        if (filename.equalsIgnoreCase("system")) {
            copyFile(buildprop, buildpropnear);
            VersionAndroid();//получаем версию андроида
            writefile(ERRORLOG, "# <-- ExtractSystemImg");
        }
    }

    public static void make_statfile(String inpfile) {
        writefile(ERRORLOG, "# make_system_statfile -->");
        String filename = getfilename(inpfile, 0);
        String racshirenie = getracshirenie(inpfile, 0);
        if (Objects.equals(racshirenie, ".img")) {
            ExtractImg(inpfile);
        } else {
            if (Objects.equals(racshirenie, ".new.dat")) {
                String transferlist = projectDir + sep + filename + ".transfer.list";
                String fileimg = projectDir + sep + filename + ".img";
                if (new File(transferlist).exists()) {
                    if (Objects.equals(LANGUAGE, "русский")) {
                        instance.toolbarLabel.setText("Конвертирование " + filename + ".new.dat...");
                    } else {
                        instance.toolbarLabel.setText("Extracting " + filename + ".new.dat...");
                    }
                    Functions.runprogrampy("sdat2img.py", transferlist, inpfile, fileimg,"","");
                    ExtractImg(fileimg);
                } else {
                    if (Objects.equals(LANGUAGE, "русский")) {
                        JOptionPane.showMessageDialog(null, transferlist + " не найден!", NAMEPROGRAMM, 0);
                    } else {
                        JOptionPane.showMessageDialog(null, transferlist + " not found!", NAMEPROGRAMM, 0);
                    }
                    return;
                }
            } else {
                if (Objects.equals(racshirenie, ".new.dat.br")) {
                    String newdat = projectDir + sep + filename + ".new.dat";
                    if (Objects.equals(LANGUAGE, "русский")) {
                        instance.toolbarLabel.setText("Конвертирование " + filename + ".new.dat.br в " + filename + ".new.dat...");
                    } else {
                        instance.toolbarLabel.setText("Converting " + filename + ".new.dat.br to " + filename + ".new.dat...");
                    }
                    if (NAME_OS > 0) {
                        Functions.extractres("_brotli.pyd");
                        //Functions.runprogram(brotli, "--decompress", "--in", inpfile, "--out", newdat);
                    } else {
                        Functions.extractres("_brotli");
                        //Functions.runprogram(brotlilin, "--decompress", "--in", inpfile, "--out", newdat);
                    }
                    Functions.runprogrampy("bro.py", "-d","-i", inpfile, "--output", newdat);
                    Functions.deletefile(new File(pathBin + sep + "_brotli.so"));
                    Functions.deletefile(new File(pathBin + sep + "_brotli.pyd"));
                    if (new File(newdat).exists()) {
                        make_statfile(newdat);
                    }
                } else {
                    if (new File(projectDir + sep + filename + ".ext4.win").exists() || new File(projectDir + sep + filename + ".ext4.win000").exists()) {
                        MackeStatfilefromTar(inpfile);
                        if (Objects.equals(filename, "system")) {
                            copyFile(buildprop, buildpropnear);
                            VersionAndroid();
                        }
                    } else {
                        /*if (Objects.equals(LANGUAGE, "русский")) {
                            JOptionPane.showMessageDialog(null, "Не найден следующй файл: system.new.dat или system.new.dat.br или system.img или system.ext4.win(win000)!", NAMEPROGRAMM, 0);
                        } else {
                            JOptionPane.showMessageDialog(null, "Not found next file: system.new.dat or system.new.dat.br or system.img or system.ext4.win(win000)!", NAMEPROGRAMM, 0);
                        }*/
                        return;
                    }
                }
            }

        }
        writefile(ERRORLOG, "# <-- make_system_statfile");
    }

    public static void VersionAndroid() { //получаем версию андроида
        writefile(ERRORLOG, "# Version API -->");
        String build = null;
        if (instance.comboBox1.getSelectedItem() == "Android 4.2") {
            VERSION_ANDROID = 17;//"Android 4.2"
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
        }
        if (instance.comboBox1.getSelectedItem() == "Android 4.4-6.0") {
            VERSION_ANDROID = 19;//"Android 4.4-6.0"
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
        }
        if (instance.comboBox1.getSelectedItem() == "Android 7.0") {
            VERSION_ANDROID = 24;//Android 7.0
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
        }
        if (instance.comboBox1.getSelectedItem() == "Android 7.1") {
            VERSION_ANDROID = 25;//Android 7.1
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
        }
        if (instance.comboBox1.getSelectedItem() == "Android 8.0") {
            VERSION_ANDROID = 26;//Android 8.0
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
        }
        if (instance.comboBox1.getSelectedItem() == "Android 8.1") {
            VERSION_ANDROID = 27;//Android 8.1
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
        }
        if (instance.comboBox1.getSelectedItem() == "Android 9.0") {
            VERSION_ANDROID = 28;//Android 9.0
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
        }
        if (instance.comboBox1.getSelectedItem() == "Auto") {
            writefile(ERRORLOG, "Selection is " + instance.comboBox1.getSelectedItem());
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Получакем версию API...");
            } else {
                instance.toolbarLabel.setText("Taking version API...");
            }
            //try {
            if (new File(buildprop).exists()) {
                build = buildprop;
            } else {
                if (new File(buildpropnear).exists()) {
                    build = buildpropnear;
                }
            }
            if (build != null) {
                writefile(ERRORLOG, "build.prop " + build);
                //читаем файл построчно, ищем значение
                String[][] buildpropmass = new String[0][];
                buildpropmass = Functions.parsetxtfile(build, "^(.+?)=(.+?)$");
                buildpropmass = DelEmptyRowTwoDArray(buildpropmass);
                for (int i = 0; i < buildpropmass.length; i++) {
                    if (buildpropmass[i][0].indexOf("ro.build.version.sdk", 0) != -1) {
                        VERSION_ANDROID = Integer.parseInt(buildpropmass[i][1].trim());
                    }
                    if (buildpropmass[i][0].indexOf("ro.product.model", 0) != -1) {
                        MODEL = buildpropmass[i][1].trim();//модель аппарата
                    }
                    if (buildpropmass[i][0].indexOf("ro.product.brand", 0) != -1) {
                        BRAND = buildpropmass[i][1].trim();//бренд аппарата
                    }
                    if (buildpropmass[i][0].indexOf("ro.product.device", 0) != -1) {
                        DEVICE = buildpropmass[i][1].trim();//divice
                    }
                    if (buildpropmass[i][0].indexOf("ro.build.product", 0) != -1) {
                        DEVICE2 = buildpropmass[i][1].trim();//divice2
                    }
                    if (buildpropmass[i][0].indexOf(".miui.", 0) != -1 || buildpropmass[i][0].indexOf("-miui-", 0) != -1) {
                        MIUI = 1;//если есть, то это прошивка miui
                    }
                }
                /*читаем system_statfile в двумерный массив с помощью регулярки <--*/
            } else {
                VERSION_ANDROID = 19;
            }
            writefile(ERRORLOG, "Version API: " + String.valueOf(VERSION_ANDROID));
            writefile(ERRORLOG, "MODEL: " + MODEL);
            writefile(ERRORLOG, "BRAND: " + BRAND);
            writefile(ERRORLOG, "DEVICE: " + DEVICE);
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Версия API " + VERSION_ANDROID);
            } else {
                instance.toolbarLabel.setText("Version API " + VERSION_ANDROID);
            }
            writefile(ERRORLOG, "# <-- Version API");
        }
    }

    public static void make_file_contexts() {

        writefile(ERRORLOG, "# make_file_contexts -->");
        if (new File(projectDir + sep + "boot.emmc.win").exists() && !new File(bootimg).exists()) {
            new File(projectDir + sep + "boot.emmc.win").renameTo(new File(bootimg));
        }
        if (new File(projectDir + sep + "boot-sign.img").exists() && !new File(bootimg).exists()) {
            Functions.runprogrampy("sign2img.py", projectDir + sep + "boot-sign.img", "", "","","");
        }
        if (!new File(bootimg).exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                JOptionPane.showMessageDialog(null, "boot.img не найден!", NAMEPROGRAMM, 0);
            } else {
                JOptionPane.showMessageDialog(null, "boot.img not found!", NAMEPROGRAMM, 0);
            }
            instance.progressBar1.setValue(0);
            instance.toolbarLabel.setText("");
            return;
        }
        Functions.extractres("unpackbootimg.py");
        if (NAME_OS > 0) {
            if (new File(filecontextsbat).exists()) {
                deletefile(new File(filecontextsbat));
            }
            Functions.extractres("file_contexts.bat");
//делаем скрытым
            if (new File(filecontextsbat).exists()) {
                Path path = Paths.get(filecontextsbat);
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("Распаковка boot.img...");
                } else {
                    instance.toolbarLabel.setText("Extracting boot.img...");
                }
                try {
                    Files.setAttribute(path, "dos:hidden", true);
                    Functions.runprogram("cmd", "/c", filecontextsbat, "", "", "");
                    deletefile(new File(filecontextsbat));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (new File(unpackbootsh).exists()) {
                deletefile(new File(unpackbootsh));
            }
            if (new File(unpackbootsh).exists()) {
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("Распаковка boot.img...");
                } else {
                    instance.toolbarLabel.setText("Extracting boot.img...");
                }
                Functions.extractres(".unpack_boot.sh");
                Functions.runprogram("chmod", Integer.toString(755), unpackbootsh, "", "", "");
                Functions.runprogram(unpackbootsh, "", "", "", "", "");
                deletefile(new File(unpackbootsh));
            }
        }
        if (new File(pathFilecontextsbin).exists()) {
            file_contexts_from_bin();
        } else {
            if (new File(nonplatFilecontexts).exists() && new File(platFilecontexts).exists()) {
                try {
                    allinone(pathFilecontexts, new String[]{nonplatFilecontexts, platFilecontexts});
                } catch (IOException e) {
                }
                deletefile(new File(nonplatFilecontexts));
                deletefile(new File(platFilecontexts));
            }
        }
        Functions.deletefile(new File(pathBin + sep + "unpackbootimg.py"));
        if (new File(pathFilecontexts).exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("file_contexts получен!");
            } else {
                instance.toolbarLabel.setText("file_contexts macked!");
            }
        } else {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Не удалось получить file_contexts!");
            } else {
                instance.toolbarLabel.setText("Can't macking file_contexts!");
            }
            return;
        }
        writefile(ERRORLOG, "# <-- make_file_contexts");
    }

    public static void file_contexts_from_bin() {
        writefile(ERRORLOG, "# file_contexts_from_bin -->");
        if (new File(pathFilecontextsbin).exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Конвертирую file_contexts.bin в текстовый формат...");
            } else {
                instance.toolbarLabel.setText("Converting file_contexts.bin to text format...");
            }
            Functions.runprogrampy("sefcontext_parser.py", pathFilecontextsbin, "-o", pathFilecontexts,"","");
            if (new File(pathFilecontexts).exists()) {
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("file_contexts получен!");
                } else {
                    instance.toolbarLabel.setText("file_contexts is taking!");
                }
            }
        } else {
            if (Objects.equals(LANGUAGE, "русский")) {
                JOptionPane.showMessageDialog(null, "file_contexts.bin не найден!", NAMEPROGRAMM, 0);
            } else {
                JOptionPane.showMessageDialog(null, "file_contexts.bin not found!", NAMEPROGRAMM, 0);
            }
            instance.progressBar1.setValue(0);
            instance.toolbarLabel.setText("");
            return;
        }
        writefile(ERRORLOG, "# <-- file_contexts_from_bin");
    }

    public static void file_contexts_to_bin() {
        writefile(ERRORLOG, "# file_contexts_to_bin -->");
        if (new File(pathFilecontexts).exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Конвертирую file_contexts в file_contexts.bin...");
            } else {
                instance.toolbarLabel.setText("Converting file_contexts to file_contexts.bin...");
            }
            if (NAME_OS > 0) {
                Functions.runprogram(filecont, pathFilecontexts, "-o", pathFilecontextsbin, "", "");
            } else {
                Functions.runprogram(sefcontext_compile, "-o", pathFilecontextsbin, pathFilecontexts, "", "");
            }
            if (new File(pathFilecontextsbin).exists()) {
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("file_contexts.bin получен!");
                } else {
                    instance.toolbarLabel.setText("file_contexts.bin is taking!");
                }
            }
        } else {
            if (Objects.equals(LANGUAGE, "русский")) {
                JOptionPane.showMessageDialog(null, "file_contexts не найден!", NAMEPROGRAMM, 0);
            } else {
                JOptionPane.showMessageDialog(null, "file_contexts not found!", NAMEPROGRAMM, 0);
            }
            instance.progressBar1.setValue(0);
            instance.toolbarLabel.setText("");
            return;
        }
        writefile(ERRORLOG, "# <-- file_contexts_to_bin");
    }

    public static void SignZip() {//подписываем тестовым ключом
        writefile(ERRORLOG, "# SignZip -->");
        if (new File(FlashZip).exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Подписываю...");
            } else {
                instance.toolbarLabel.setText("Signing...");
            }
            SignApk.main(new String[]{key1, key2, FlashZip, zipsign});
            deletefile(new File(FlashZip));
            (new File(zipsign)).renameTo(new File(FlashZip));
        }
        writefile(ERRORLOG, "# <-- SignZip");
    }

    public static void SignZipFree() {
        writefile(ERRORLOG, "# SignZipFree -->");
        /*Далее в классе где создаете jFileChooser:
jFileChooser.setFileFilter(new ExtFileFilter("txt", "*.txt Текстовые файлы"));
*/
        JFileChooser fileopen = new JFileChooser(projectDir);
        fileopen.setFileFilter(new ExtFileFilter("zip", "*.zip"));//крутой фильтр по расширению
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            if (file.getName().indexOf(".zip", 0) != -1) {
                Zip = file.getAbsolutePath();
            } else {
                if (Objects.equals(LANGUAGE, "русский")) {
                    JOptionPane.showMessageDialog(null, "Это не .zip архив!", NAMEPROGRAMM, 0);
                } else {
                    JOptionPane.showMessageDialog(null, "It's not archive .zip!", NAMEPROGRAMM, 0);
                }
                return;
            }
        } else {
            return;
        }
        String zipsigned = Zip + "_sign.zip";
        writefile(ERRORLOG, Zip);
        if (new File(Zip).exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Подписываю...");
            } else {
                instance.toolbarLabel.setText("Signing...");
            }
            SignApk.main(new String[]{key1, key2, Zip, zipsigned});
            deletefile(new File(Zip));
            (new File(zipsigned)).renameTo(new File(Zip));
            if (new File(Zip).exists()) {
                if (Objects.equals(LANGUAGE, "русский")) {
                    instance.toolbarLabel.setText("Готово!");
                } else {
                    instance.toolbarLabel.setText("Done!");
                }
            }
        }
        writefile(ERRORLOG, "# <-- SignZipFree");
    }

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDigits(String s) {
        if (!s.matches("^.*([r|-][w|-][x|-]){3}$")) {
            throw new IllegalArgumentException("Achtung!");
        }
        Function<String, String> rwxMapper = rwx -> String.valueOf(Integer.parseInt(rwx.replaceAll("[rwx]", "1").replaceAll("-", "0"), 2));
        return Arrays.stream(s.substring(s.length() - 9).split("(?<=\\G...)")).map(rwxMapper).collect(joining());
    }

    public static void MackeStatfilefromTar(String filewin) {
        writefile(ERRORLOG, "# MackeStatfilefromTar -->");
        String filename = getfilename(filewin, 0);
        if (new File(projectDir + sep + filename + ".ext4.win").exists() || new File(projectDir + sep + filename + ".ext4.win000").exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText("Получаю данные из бэкапа...");
            } else {
                instance.toolbarLabel.setText("Taking info from backup...");
            }
            if (getracshirenie(filewin, 0).equals(".ext4.win")) {
                if (new File(filewin).exists()) {
                    Functions.runprogrampy("untar.py", filewin, dirTmp + sep + filename, "","","");
                }
            } else {
                if (filewin.contains(".ext4.win00")) {
                    if (new File(projectDir + sep + filename + ".ext4.win000").exists()) {
                        Functions.runprogrampy("untar.py", projectDir + sep + filename + ".ext4.win000", dirTmp + sep + filename, "","","");
                    }
                    if (new File(projectDir + sep + filename + ".ext4.win001").exists()) {
                        Functions.runprogrampy("untar.py", projectDir + sep + filename + ".ext4.win001", dirTmp + sep + filename, "","","");
                    }
                    if (new File(projectDir + sep + filename + ".ext4.win002").exists()) {
                        Functions.runprogrampy("untar.py", projectDir + sep + filename + ".ext4.win002", dirTmp + sep + filename, "","","");
                    }
                    if (new File(projectDir + sep + filename + ".ext4.win003").exists()) {
                        Functions.runprogrampy("untar.py", projectDir + sep + filename + ".ext4.win003", dirTmp + sep + filename, "","","");
                    }
                    if (new File(projectDir + sep + filename + ".ext4.win004").exists()) {
                        Functions.runprogrampy("untar.py", projectDir + sep + filename + ".ext4.win004", dirTmp + sep + filename, "","","");
                    }
                    if (new File(projectDir + sep + filename + ".ext4.win005").exists()) {
                        Functions.runprogrampy("untar.py", projectDir + sep + filename + ".ext4.win005", dirTmp + sep + filename, "","","");
                    }
                }
            }
        }
        if (new File(dirTmp + sep + filename + "_statfile.txt").exists()) {
            //Convertlogtar2statfile(filename);
            MoveFile(dirTmp + sep + filename + "_statfile.txt", projectDir + sep + filename + "_statfile.txt");
            //deletefile(new File(projectDir + sep + filename + "_logtar.txt"));
        }
        if (new File(projectDir + sep + filename + "_statfile.txt").exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                instance.toolbarLabel.setText(filename + "_statfile.txt получен!");
            } else {
                instance.toolbarLabel.setText(filename + "_statfile.txt taked!");
            }
        }
        writefile(ERRORLOG, "# <-- MackeStatfilefromTar");
    }

    //пересчет прав в любом текстовом файле -->
    public static void RecalcRules() {
        writefile(ERRORLOG, "# RecalcRules -->");
        String InputTextFile;
        JFileChooser fileopen = new JFileChooser(projectDir);
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            InputTextFile = file.getAbsolutePath();
        } else {
            return;
        }
        writefile(ERRORLOG, InputTextFile);
        String OutTextFile = InputTextFile + "_new";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(InputTextFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        Pattern pattern = Pattern.compile("[-|l|s|d|b|c|p]([r|-][w|-][x|-]){3}");
        try {
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    line = line.replaceAll(matcher.group(), getDigits(matcher.group().replace("s", "x")));
                    writefile(OutTextFile, line);
                } else {
                    writefile(OutTextFile, line);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        writefile(ERRORLOG, "# <-- RecalcRules");
    }
//пересчет прав в любом текстовом файле <--

    //копирование папки с темами
    public static void CopyThemes() {
        if (instance.aromaInstallerCheckBox.isSelected() && instance.themeCheckBox.isSelected()) {
            copyDir(pathBin + sep + "aroma" + sep + "themes" + sep + instance.themeslist.getSelectedItem().toString(), dirScript + sep + "aroma" + sep + "themes" + sep + instance.themeslist.getSelectedItem().toString());
        }
    }

    //конвертирование цвета RGB в Hex
    public static String convertColorToHexadeimal(Color color) {
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if (hex.length() < 6) {
            if (hex.length() == 5)
                hex = "0" + hex;
            if (hex.length() == 4)
                hex = "00" + hex;
            if (hex.length() == 3)
                hex = "000" + hex;
        }
        hex = "#" + hex;
        return hex;
    }

    public static String[][] parsetxtfile(String filename, String patern) {
        String[][] mass = new String[0][];
        try {
            BufferedReader reader = null;
            ArrayList<String> result = new ArrayList<String>();
            Pattern pattern = Pattern.compile(patern);
            try {
                reader = new BufferedReader(new FileReader(filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line;
            int max = 1;
            while ((line = reader.readLine()) != null) {
                result.add(line);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    if (max < matcher.groupCount()) {
                        max = matcher.groupCount();
                    }
                }
            }
            reader.close();
            mass = new String[result.toArray().length][max];
            for (int i = 0; i < result.toArray().length; i++) {
                Matcher matcher = pattern.matcher(result.get(i).toString());
                if (matcher.find()) {
                    for (int j = 1; j <= matcher.groupCount(); ++j) {
                        mass[i][j - 1] = matcher.group(j);
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return mass;
    }

    public static void generFiles(String FileWrite, String WhatWrite, String WhatWrite2, String FolderName) {
        //использование:
        //WhatWrite - arraytable2[i][0] - нулевой столбец таблицы с названием файла
        //WhatWrite2 - arraytable2[i][1] - первый столбец таблицы с названием
        //FolderName - instance1.foldermods.getText() - папка с модами
        //FileWrite - куда записываем - updater-script
        if (new File(Generator.aromamods + sep + WhatWrite).isFile()) {
            if (WhatWrite.indexOf(".zip", 0) != -1) {
                Functions.writefile(FileWrite, "   ui_print(\"" + WhatWrite2 + " installing...\");");
                Functions.writefile(FileWrite, "   run_program(\"/sbin/busybox\",\"mkdir\", \"/tmp/" + WhatWrite.substring(0, WhatWrite.indexOf(".zip")) + "\");");
                Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", \"/tmp/" + WhatWrite.substring(0, WhatWrite.indexOf(".zip")) + "/" + WhatWrite + "\");");
                Functions.writefile(FileWrite, "   run_program(\"/sbin/busybox\", \"unzip\", \"/tmp/" + WhatWrite.substring(0, WhatWrite.indexOf(".zip")) + "/" + WhatWrite + "\", \"META-INF/com/google/android/*\", \"-d\", \"/tmp/" + WhatWrite.substring(0, WhatWrite.indexOf(".zip")) + "\");");
                Functions.writefile(FileWrite, "   run_program(\"/sbin/busybox\", \"sh\", \"/tmp/" + WhatWrite.substring(0, WhatWrite.indexOf(".zip")) + "/META-INF/com/google/android/update-binary\", \"dummy\", \"1\", \"/tmp/" + WhatWrite.substring(0, WhatWrite.indexOf(".zip")) + "/" + WhatWrite + "\");");
                Functions.writefile(FileWrite, "   delete_recursive(\"/tmp/" + WhatWrite.substring(0, WhatWrite.indexOf(".zip")) + "\");");
            }
            if (WhatWrite.indexOf(".sh", 0) != -1) {
                Functions.writefile(FileWrite, "   ui_print(\"" + WhatWrite2 + " installing...\");");
                Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", \"/tmp/" + WhatWrite + "\");");
                Functions.writefile(FileWrite, "   set_metadata(\"/tmp/" + WhatWrite + "\", \"uid\", 0, \"gid\", 0, \"mode\", 0777);");
                Functions.writefile(FileWrite, "   run_program(\"/tmp/" + WhatWrite + "\");");
                Functions.writefile(FileWrite, "   delete(\"/tmp/" + WhatWrite + "\");");
            }
            if (WhatWrite.indexOf(".img", 0) != -1) {
                Functions.writefile(FileWrite, "   ui_print(\"" + WhatWrite2 + " installing...\");");
                if (WhatWrite.indexOf("boot", 0) != -1) {
                    Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", file_getprop(\"/tmp/configs\", \"boot\"));");
                }
                if (WhatWrite.indexOf("recovery", 0) != -1) {
                    Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", file_getprop(\"/tmp/configs\", \"recovery\"));");
                }
                if (WhatWrite.indexOf("system", 0) != -1) {
                    Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", file_getprop(\"/tmp/configs\", \"system\"));");
                }
                if (WhatWrite.indexOf("splash", 0) != -1) {
                    Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", file_getprop(\"/tmp/configs\", \"splash\"));");
                }
                if (WhatWrite.indexOf("NON-HLOS", 0) != -1) {
                    Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", file_getprop(\"/tmp/configs\", \"modem\"));");
                }
            }
            if (WhatWrite.indexOf(".bin", 0) != -1) {
                Functions.writefile(FileWrite, "   ui_print(\"" + WhatWrite2 + " installing...\");");
                if (WhatWrite.indexOf("NON-HLOS", 0) != -1) {
                    Functions.writefile(FileWrite, "   package_extract_file(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", file_getprop(\"/tmp/configs\", \"modem\"));");
                }
            }
        } else {
            if (new File(Generator.aromamods + sep + WhatWrite).isDirectory()) {
                if (WhatWrite.indexOf("system", 0) != -1) {
                    Functions.writefile(FileWrite, "   ui_print(\"" + WhatWrite2 + " installing...\");");
                    Functions.writefile(FileWrite, "   package_extract_dir(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", \"/system\");");
                }
                if (WhatWrite.indexOf("data", 0) != -1) {
                    Functions.writefile(FileWrite, "   ui_print(\"" + WhatWrite2 + " installing...\");");
                    Functions.writefile(FileWrite, "   package_extract_dir(\"META-INF/Mod/" + FolderName + "/" + WhatWrite + "\", \"/data\");");
                }
            }
        }
    }

    //получаем текущее время+дата
    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //объединение файлов в один
    public static void allinone(String OutFile, String[] InFiles) throws IOException {
        File file1 = null;

        if (!new File(OutFile).exists()) {
            file1 = new File(OutFile);
        }
        //Открываем файл для записи
        try (BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(file1, true))) {
            int k;
            for (int i = 0; i < InFiles.length; i++) {
                File file2 = new File(InFiles[i]);
                //Открываем файл для считывания
                BufferedInputStream bufRead = new BufferedInputStream(new FileInputStream(file2));
                while ((k = bufRead.read()) != -1) {
                    bufOut.write(k);
                }
                bufOut.flush();    // Принудительно выталкиваем данные с буфера
                bufRead.close();  //
            }
            bufOut.close();// Закрываем выходной файл
        }
    }

    public static String getfilename(String fileimg, int last) {
        String filename = null;
        if (fileimg.contains(sep)) {
            if (last == 1) {
                filename = fileimg.substring(fileimg.lastIndexOf(sep) + 1, fileimg.lastIndexOf("."));//имя файла, если есть пути
            } else {
                filename = fileimg.substring(fileimg.lastIndexOf(sep) + 1, fileimg.indexOf("."));//имя файла, если есть пути
            }
        } else {
            if (last == 1) {
                filename = fileimg.substring(0, fileimg.lastIndexOf("."));//имя файла, если есть пути
            } else {
                filename = fileimg.substring(0, fileimg.indexOf("."));//имя файла, если есть пути
            }
        }
        return filename;
    }

    public static String getracshirenie(String fileimg, int last) {
        String racshirenie = null;
        if (fileimg.contains(sep)) {
            if (last == 1) {
                racshirenie = fileimg.substring(fileimg.lastIndexOf("."), fileimg.length());//расширение файла
            } else {
                racshirenie = fileimg.substring(fileimg.indexOf("."), fileimg.length());//расширение файла
            }
        } else {
            if (last == 1) {
                racshirenie = fileimg.substring(fileimg.lastIndexOf("."), fileimg.length());//расширение файла
            } else {
                racshirenie = fileimg.substring(fileimg.indexOf("."), fileimg.length());//расширение файла
            }
        }
        return racshirenie;
    }

    public static void extractres(String resname) {
        try {
            InputStream in = Generator.class.getClassLoader().getResourceAsStream("res/" + resname);
            FileOutputStream fop = null;
            if (resname.contains(".sh") || resname.contains(".bat")) {
                fop = new FileOutputStream(new File(Generator.pathProgram + sep + resname));
            } else {
                if (resname.contains("config")) {
                    fop = new FileOutputStream(new File(Generator.dirScript + sep + resname));
                } else {
                    fop = new FileOutputStream(new File(Generator.pathBin + sep + resname));
                }
            }
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                fop.write(bytes, 0, read);
            }
            in.close();
            fop.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runprogrampy(String nameprogpy, String param1, String param2, String param3,String param4,String param5) {
        Functions.extractres(nameprogpy);
        try {
            ProcessBuilder pb = null;
            if (NAME_OS > 0) {
                pb = new ProcessBuilder(pythonexe, pathBin + sep + nameprogpy, param1, param2, param3,param4,param5);
            } else {
                pb = new ProcessBuilder("python3", pathBin + sep + nameprogpy, param1, param2, param3,param4,param5);
            }
            File outputFile = new File("log.log");
            File errorFile = new File("log.log");
            pb.redirectOutput(outputFile);
            pb.redirectError(errorFile);
            Process process = pb.start();
            process.waitFor();
            deletefile(new File(Generator.pathBin + sep + nameprogpy));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runprogram(String nameprog, String param1, String param2, String param3, String param4, String param5) {
        try {
            ProcessBuilder pb = new ProcessBuilder(nameprog, param1, param2, param3, param4, param5);
            File outputFile = new File("log.log");
            File errorFile = new File("log.log");
            pb.redirectOutput(outputFile);
            pb.redirectError(errorFile);
            Process process = pb.start();
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WindowsLikeComparator implements Comparator<File> {

    //Regexp to make the 3 part split of the filename.
    private static final Pattern splitPattern = Pattern.compile("^(.*?)(\\d*)(?:\\.([^.]*))?$");

    @Override
    public int compare(File o1, File o2) {
        SplitteFileName data1 = getSplittedFileName(o1);
        SplitteFileName data2 = getSplittedFileName(o2);

        //Compare the namepart caseinsensitive.
        int result = data1.name.compareToIgnoreCase(data2.name);
        //If name is equal, then compare by number
        if (result == 0) {
            result = data1.number.compareTo(data2.number);
        }
        //If numbers are equal then compare by length text of number. This
        //is valid because it differs only by heading zeros. Longer comes
        //first.
        if (result == 0) {
            result = -Integer.compare(data1.numberText.length(), data2.numberText.length());
        }
        //If all above is equal, compare by ext.
        if (result == 0) {
            result = data1.ext.compareTo(data2.ext);
        }
        return result;
    }

    private SplitteFileName getSplittedFileName(File f) {
        Matcher matcher = splitPattern.matcher(f.getName());
        if (matcher.matches()) {
            return new SplitteFileName(matcher.group(1), matcher.group(2), matcher.group(3));
        } else {
            return new SplitteFileName(f.getName(), null, null);
        }
    }

    static class SplitteFileName {

        String name;
        Long number;
        String numberText;
        String ext;

        public SplitteFileName(String name, String numberText, String ext) {
            this.name = name;
            if ("".equals(numberText)) {
                this.number = -1L;
            } else {
                this.number = Long.valueOf(numberText);
            }

            this.numberText = numberText;
            this.ext = ext;
        }
    }
}