import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static java.nio.file.Files.readAllLines;

public class Generator extends JFrame {
    protected static Generator instance;
    public JButton button5;
    public JButton button6;
    public JButton button7;
    public JButton Exit;
    public JPanel rootPanel;
    public JButton UnixButton;
    public JButton Pravo;
    public JButton Help;
    public JButton button4;
    public JButton button9;
    public JCheckBox zipcheckBox;
    public JCheckBox datamediacheckBox;
    public JCheckBox busyBoxCheckBox;
    public JCheckBox superSuCheckBox;
    public JCheckBox GAPPScheckBox;
    public JCheckBox XposedcheckBox;
    public JCheckBox signZipCheckBox;
    public JProgressBar progressBar1;
    public JPanel vspomogat;
    public JPanel generate;
    public JPanel tools;
    public JPanel options;
    public JToolBar toolbar;
    public JLabel toolbarLabel;
    public JButton SignZip;
    public JComboBox comboBox1;
    public JLabel yandex;
    public JLabel paypal;
    public JLabel PDA;
    public JLabel Thx;
    public JLabel qiwi;
    public JTabbedPane pages;
    public JButton mackeStatfilefromTarButton;
    public JTextField textField1;
    public JButton calcButton;
    public JTextField textField2;
    public JPanel Calc;
    public JButton deodexedScriptButton;
    public JButton textfileButton;
    public JCheckBox aromaInstallerCheckBox;
    public JTabbedPane AromaConf;
    public JCheckBox chengeLogCheckBox;
    public JTable modslist;
    public JTextArea ChengelogTextArea;
    public JFormattedTextField nameFormattedTextField;
    public JFormattedTextField versionFormattedTextField;
    public JFormattedTextField authorFormattedTextField;
    public JFormattedTextField deviceFormattedTextField;
    public JFormattedTextField dateFormattedTextField;
    public JPanel Aroma;
    public JPanel General;
    public JPanel Mods;
    public JScrollPane ChalgelogScrol;
    public JCheckBox SelectLang;
    public JButton boldButton;
    public JButton CenterButton;
    public JButton underlineButton;
    public JButton italicButton;
    public JButton LeftButton;
    public JButton RightButton;
    public JCheckBox themeCheckBox;
    public JComboBox themeslist;
    public JButton ColorButton;
    public JFormattedTextField foldermods;
    public JCheckBox updateClearCheckBox;
    public JCheckBox initDCheckBox;
    private JButton button1;
    public JCheckBox magiskCheckBox;
    public JButton AromaGener;
    private JButton toolcreatearchive;
    private JButton delgaapsmiserveces;
    public JCheckBox updateCheckBox;

    //наши чекбоксы
    ///глобалки для всяких нужностей ===>
    public static int NAME_OS;//наименование операционки
    public static double ADD = 0;//глобальная переменная добавления к процентам
    public static String LANGUAGE = Locale.getDefault().getDisplayLanguage();//получаем язык системы
    public static String sep = System.getProperty("file.separator");
    public static int VERSION_ANDROID = 0;
    public static int VENDOR = 0;
    /*получаем дату текущую ==>*/
    //   static Date date = new Date();
    //   static SimpleDateFormat Formatfordate = new SimpleDateFormat("yyyyMMdd");
    /*получаем дату текущую <==*/
//	public static String BUILD = Formatfordate.format(date).toString();
    public static String BUILD = "20190101";
    public static String NAMEPROGRAMM = "Updater Script Generator v0.18b build" + BUILD;
    public static Integer MIUI = 0;
    public static Integer BUSYBOXINSTALLED = 0;
    public static String DEVICE;
    public static String DEVICE2;
    public static String MODEL;
    public static String BRAND;
    //папки и файлы -->

    //основная папка будет
    //public static String pathProgram = new File(Generator.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
    //тестовая папка
    //public static String pathProgram = System.getProperty("user.dir");//папка с прогой для винды или домашняя папка для линя
    public static String pathProgram = new java.io.File("").getAbsolutePath();
    public static String dirTmp = pathProgram + sep + ".tmp"; //Windows
    public static String outputDir = pathProgram + sep + "output";
    public static String addonDir = pathProgram + sep + "addon";
    public static String projectDir = pathProgram + sep + "project";
    public static String metainfDir = dirTmp + sep + "META-INF";
    public static String comDir = metainfDir + sep + "com";
    public static String googleDir = comDir + sep + "google";
    public static String dirScript = googleDir + sep + "android";
    public static String modDir = metainfDir + sep + "Mod";
    //project
    public static String pathStatfile = projectDir + sep + "system_statfile.txt";
    public static String pathFilecontexts = projectDir + sep + "file_contexts";
    public static String pathFilecontextsbin = projectDir + sep + "file_contexts.bin";
    public static String bootimg = projectDir + sep + "boot.img";
    public static String SystemImg = projectDir + sep + "system.img";
    public static String SystemNewDat = projectDir + sep + "system.new.dat";
    public static String SystemNewDatBr = projectDir + sep + "system.new.dat.br";
    public static String SystemTransferList = projectDir + sep + "system.transfer.list";
    public static String VendorStatfile = projectDir + sep + "vendor_statfile.txt";
    public static String VendorImg = projectDir + sep + "vendor.img";
    public static String VendorNewDat = projectDir + sep + "vendor.new.dat";
    public static String VendorNewDatBr = projectDir + sep + "vendor.new.dat.br";
    public static String VendorTransferList = projectDir + sep + "vendor.transfer.list";
    public static String tmpvendorstatfile = dirTmp + sep + "vendor_statfile.txt";
    public static String buildpropnear = projectDir + sep + "build.prop";
    public static String preloaderimg = projectDir + sep + "preloader.img";
    public static String nonplatFilecontexts = projectDir + sep + "nonplat_file_contexts";
    public static String platFilecontexts = projectDir + sep + "plat_file_contexts";

    //addon folder
    public static String BusyBoxZip = addonDir + sep + "busybox.zip";
    public static String SuperSuZip = addonDir + sep + "supersu.zip";
    public static String MagiskZip = addonDir + sep + "magisk.zip";
    public static String GappsZip = addonDir + sep + "gapps.zip";
    public static String XposedZip = addonDir + sep + "xposed.zip";
    public static String openrecoveryscript = addonDir + sep + "openrecoveryscript";
    public static String busyboxbinary = addonDir + sep + "busybox";
    public static String initdzip = addonDir + sep + "initd.zip";
    public static String firmwareupdate = addonDir + sep + "firmware-update";
    public static String aromamods = addonDir + sep + "AromaMods";
    //copy to
    public static String BusyBoxZipTo = modDir + sep + "busybox" + sep + "busybox.zip";
    public static String initdzipto = modDir + sep + "initd" + sep + "initd.zip";
    public static String SuperSuZipTo = modDir + sep + "supersu" + sep + "supersu.zip";
    public static String MagiskZipTo = modDir + sep + "magisk" + sep + "magisk.zip";
    public static String GappsZipTo = modDir + sep + "gapps" + sep + "gapps.zip";
    public static String XposedZipTo = modDir + sep + "xposed" + sep + "xposed.zip";
    public static String openrecoveryscriptto = modDir + sep + "xposed" + sep + "openrecoveryscript";
    public static String busyboxbinaryto = modDir + sep + "busybox" + sep + "busybox";
    public static String bootimgto = dirTmp + sep + "boot.img";
    public static String aromabinto = dirScript + sep + "update-binary";
    public static String aromathemefolderto = dirScript + sep + "aroma" + sep + "themes";
    public static String preloaderimgto = dirTmp + sep + "preloader.img";
    //dirs
    public static String XposedDir = modDir + sep + "xposed";
    public static String SuperSuDir = modDir + sep + "supersu";
    public static String MagiskDir = modDir + sep + "magisk";
    public static String BusyBoxDir = modDir + sep + "busybox";
    public static String GappsDir = modDir + sep + "gapps";
    public static String initddir = modDir + sep + "initd";
    //bin folder
    public static String pathBin = pathProgram + sep + "bin";// папка bin
    public static String updatebinary71 = pathBin + sep + "update-binary71";
    public static String updatebinary42 = pathBin + sep + "update-binary42";
    public static String simgToimg = pathBin + sep + "simg2img";
    public static String sdat2imgexe = pathBin + sep + "sdat2img.exe";
    public static String sdat2imgpy = pathBin + sep + "sdat2img.py";
    public static String sdat2imgbat = pathProgram + sep + "sdat2img.bat";
    public static String ImgExtractor = pathBin + sep + "ImgExtractor.exe";
    public static String imgextractor = pathBin + sep + "imgextractor.py";
    public static String filecont = pathBin + sep + "filecont.exe";
    public static String sefcontext_parser = pathBin + sep + "sefcontext_parser";
    public static String sefcontext_compile = pathBin + sep + "sefcontext_compile";
    public static String key1 = pathBin + sep + "testkey.x509.pem";
    public static String key2 = pathBin + sep + "testkey.pk8";
    public static String executefile = pathBin + sep + "exeptions";
    public static String aromabin = pathBin + sep + "update-binarya";
    public static String aromathemefolder = pathBin + sep + "aroma" + sep + "themes";
    public static String updatebinarymiui = pathBin + sep + "update-binarymiui";
    public static String brotli = pathBin + sep + "brotli.exe";
    public static String brotlilin = pathBin + sep + "brotli";
    public static String pythonexe = pathBin + sep + "python3" + sep + "python.exe";
    //script folder
    public static String bootsh = dirScript + sep + "config";
    public static String UpdaterScript = dirScript + sep + "updater-script";
    public static String updatebinaryto = dirScript + sep + "update-binary";

    public static String FlashZip = outputDir + sep + "Flash.zip";
    public static String tmpzip = pathProgram + sep + "tmp.zip";
    public static String tmpziplin = pathProgram + sep + ".tmp.zip";
    //system folder
    public static String SystemDir = dirTmp + sep + "system";
    public static String VendorDir = dirTmp + sep + "vendor";
    public static String recoveryfrombootp = SystemDir + sep + "recovery-from-boot.p";
    public static String journal = SystemDir + sep + ".journal";
    public static String lostfound = SystemDir + sep + "lost+found";
    public static String buildprop = SystemDir + sep + "build.prop";

    //help scripts
    public static String unpacksystemsh = pathProgram + sep + ".unpack_system.sh";
    public static String filecontextsbat = pathProgram + sep + "file_contexts.bat";
    public static String unpackbootsh = pathProgram + sep + ".unpack_boot.sh";

    public static String zipsign = FlashZip + "_sign.zip";
    public static String ERRORLOG = pathProgram + sep + "error.log";
    public static String sss = pathProgram + sep + "tmpsys.txt";
/*     public static String
    public static String
*/
    //папки и файлы <--

    ///глобалки для всяких нужностей <===
    public Generator() {
        $$$setupUI$$$();
        UnixButton.setEnabled(false);
        Pravo.setEnabled(false);

        if (Objects.equals(LANGUAGE, "русский")) {
            button5.setText("<html><div align=center>Генерировать");//задать имя кнопке
            button5.setToolTipText("Генерировать скрипт по образам прошивки: .img, .new.dat,.win, .win000");
            UnixButton.setText("<html><div align=center>Генерировать Unix-Shell скрипт");
            UnixButton.setToolTipText("Генерировать Shell скрипт по образам прошивки");
            Pravo.setText("<html><div align=center>Генерировать скрипт по папке system");
            Help.setText("Справка");
            Exit.setText("Выход");
            button4.setText("<html><div align=center>Карта блоков из MT67xx_Android_scatter");
            button6.setText("<html><div align=center>Конвертировать file_contexts.bin в file_contexts");
            button7.setText("<html><div align=center>Конвертировать file_contexts в file_contexts.bin");
            button9.setText("<html><div align=center>Получить file_contexts из boot.img");
            zipcheckBox.setText("Запаковать в .zip");
            zipcheckBox.setToolTipText("Запаковать готовый скрипт в архив");
            datamediacheckBox.setText("Очистка data/media");
            datamediacheckBox.setToolTipText("<html>Добавит в код очистку data без /data/media." +
                    "<br>Ставить при наличии такой папки у себя на телефоне." +
                    "<br>в противном случае удалит все что есть на внутренней памяти." +
                    "<br>Работает не стабильно");
            toolbarLabel.setText("Готов!");
            signZipCheckBox.setText("Подписать .zip");
            SignZip.setText("Подписать .zip");
            tools.setBorder(BorderFactory.createTitledBorder("Инструменты"));
            generate.setBorder(BorderFactory.createTitledBorder("Генератор"));
            options.setBorder(BorderFactory.createTitledBorder("Настройки"));
            mackeStatfilefromTarButton.setText("<html><div align=center>Получить system_statfile.txt из бэкапа TWRP");
            pages.setTitleAt(1, "Инструменты");
            pages.setTitleAt(0, "Генератор");
            Calc.setBorder(BorderFactory.createTitledBorder("Калькулятор разрешений"));
            calcButton.setText("Перевести");
            deodexedScriptButton.setText("<html><div align=center>Сделать deodex скрипт");
            textfileButton.setText("<html><div align=center>Пересчитать права в текстовом файле");
            aromaInstallerCheckBox.setToolTipText("<html>Aroma Installer - это графический установщик патчей и прошивок" +
                    "<br>одной из его особенностей является возможность писать на разных языках, делать выбор того или иного компонента" +
                    "<br>с поддержкой тем и прочих прелестей. Если бы автор не забросил этот проект, " +
                    "<br>то за ним было б будущее в связке с shell скриптами.");
            chengeLogCheckBox.setToolTipText("Список изменений мода/прошивки");
            themeCheckBox.setToolTipText("Выберите тему справа");
            SelectLang.setToolTipText("Добавляет меню выбора языка русский/английский");
            updateClearCheckBox.setToolTipText("<html>Добавляет меню выбора типа установки:" +
                    "<br>- Чистая" +
                    "<br>- Обновление" +
                    "<br>- Стандартная");
            modslist.setToolTipText("<html>Список файлов файлов дополнительных модифиукаций с их описанием" +
                    "<br>Описание заполняется в ручную");
            nameFormattedTextField.setToolTipText("Название мода");
            authorFormattedTextField.setToolTipText("Автор мода");
            versionFormattedTextField.setToolTipText("Версия мода");
            dateFormattedTextField.setToolTipText("Дата мода");
            deviceFormattedTextField.setToolTipText("Название аппарата/модель и прочее");
            foldermods.setToolTipText("Имя папки с модами, куда все будет складываться");
            boldButton.setToolTipText("Сделать текст жирным в списке изменений");
            italicButton.setToolTipText("Сделать текст курсивным в списке изменений");
            underlineButton.setToolTipText("Сделать текст подчеркнутым в списке изменений");
            CenterButton.setToolTipText("Выровнять текст по центру в списке изменений");
            LeftButton.setToolTipText("Выровнять текст по левому краю в списке изменений");
            RightButton.setToolTipText("Выровнять текст по правому краю в списке изменений");
            ColorButton.setToolTipText("Выбрать цвет текста в списке изменений");
            comboBox1.setToolTipText("Версия Andoid. В режиме Auto - определяется автоматически.");
            toolcreatearchive.setToolTipText("Запаковать папку tmp");
            toolcreatearchive.setText("Создать архив");
        } else {
            button5.setText("<html><div align=center>Generate");//задать имя кнопке
            UnixButton.setText("<html><div align=center>Generate Unix-Shell");
            Pravo.setText("<html><div align=center>Generate from folder /system");
            Help.setText("Help");
            Exit.setText("Exit");
            button4.setText("<html><div align=center>Map blocks from MT67xx_Android_scatter");
            button6.setText("<html><div align=center>Convert file_contexts.bin in file_contexts");
            button7.setText("<html><div align=center>Convert file_contexts in file_contexts.bin");
            button9.setText("<html><div align=center>Make file_contexts from boot.img");
            zipcheckBox.setText("Pack in .zip");
            datamediacheckBox.setText("Clear data/media");
            toolbarLabel.setText("Ready!");
            signZipCheckBox.setText("Sign .zip");
            SignZip.setText("Sign .zip");
            mackeStatfilefromTarButton.setText("<html><div align=center>Make system_statfile.txt from backup TWRP");
            deodexedScriptButton.setText("<html><div align=center>Make Deodexed script");
            textfileButton.setText("<html><div align=center>Recalc rules in txt file");
            Calc.setBorder(BorderFactory.createTitledBorder("Rules calculator"));
            calcButton.setText("Recalc");
        }
        textField1.setText("-rwxr-xr--");//вываливаем для примера в калькулятор
        //String impfile = SystemNewDatBr.substring(SystemNewDatBr.indexOf(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1, SystemNewDatBr.length())));
        //System.out.println(impfile.substring(impfile.indexOf("."), impfile.length()));
        //System.out.println(impfile.substring(0, impfile.indexOf(".")));
        //String racsh = SystemNewDatBr.substring(SystemNewDatBr.indexOf(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1, SystemNewDatBr.length()))).substring(SystemNewDatBr.substring(SystemNewDatBr.indexOf(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1, SystemNewDatBr.length()))).indexOf("."), SystemNewDatBr.substring(SystemNewDatBr.indexOf(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1, SystemNewDatBr.length()))).length());
        //String filename = SystemNewDatBr.substring(SystemNewDatBr.indexOf(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1, SystemNewDatBr.length()))).substring(0, SystemNewDatBr.substring(SystemNewDatBr.indexOf(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1, SystemNewDatBr.length()))).indexOf("."));
        //System.out.println("file " + filename + " rash " + racsh);
        //System.out.println(SystemNewDatBr.indexOf(SystemNewDatBr.substring(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1,SystemNewDatBr.length()).indexOf(("."))))));
        //System.out.println(SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1, SystemNewDatBr.substring(SystemNewDatBr.lastIndexOf(sep) + 1,SystemNewDatBr.length()).indexOf(".")));//имя файла, если есть пути
        //System.out.println(unpacksystemsh.substring(unpacksystemsh.lastIndexOf("."), unpacksystemsh.length()));//расширение файла
        //System.out.println(unpacksystemsh.substring(unpacksystemsh.lastIndexOf(sep) + 1, unpacksystemsh.lastIndexOf(".")));//имя файла, если есть пути
        //String booo = "boot.img";
        //System.out.println(booo.substring(0, booo.lastIndexOf(".")));//имя файла, если нет путей
        //String[] extfile = unpacksystemsh.split("\\.");
        //String filename = extfile[0];
        //String racshirenie = extfile[1];


        //прогресс бар
        progressBar1.setStringPainted(true);//показываем проценты на баре
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);
        comboBox1.setSelectedItem("Auto");
//==================================================================================================================
/// блок заполнения всех данных тех что используются везде  ===>
//==================================================================================================================
        ///проверяем какая система установлена и пишем в глобальную переменную
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            NAME_OS = 1;
        } else {
            if (System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0) {
            }
            NAME_OS = 0;
        }
//создаем пустые папки -->
        if (!new File(outputDir).exists()) {
            new File(outputDir).mkdirs();
        }
        if (!new File(projectDir).exists()) {
            new File(projectDir).mkdirs();
        }
        if (!new File(addonDir).exists()) {
            new File(addonDir).mkdirs();
        }
        if (!new File(pathBin).exists()) {
            if (Objects.equals(LANGUAGE, "русский")) {
                JOptionPane.showMessageDialog(null, "Не найдена папка bin!", NAMEPROGRAMM, 0);
            } else {
                JOptionPane.showMessageDialog(null, "Not found bin folder!", NAMEPROGRAMM, 0);
            }
            System.exit(0);
        }
//создаем пустые папки <--
        //задаем права папке bin и папку tmp
        if (NAME_OS < 1) {
            //dirTmp = pathProgram + sep + ".tmp";
            ProcessBuilder pb = new ProcessBuilder("chmod -R 755 " + pathBin);//задаем права папке
            File outputFile = new File("log.log");
            File errorFile = new File("log.log");
            pb.redirectOutput(outputFile);
            pb.redirectError(errorFile);
            Process process = null;
            try {
                process = pb.start();
                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                }
            } catch (IOException e) {
            }
        }
        if (new File(ERRORLOG).exists()) {
            Functions.deletefile(new File(ERRORLOG));//если есть ERRORLOG
        }

        Date now = new Date();
        Functions.writefile(ERRORLOG, NAMEPROGRAMM);
        Functions.writefile(ERRORLOG, now.toString());
        Functions.writefile(ERRORLOG, "");
        Functions.writefile(ERRORLOG, "pathProgram " + pathProgram);
//==================================================================================================================
/// блок заполнения всех данных тех что используются везде  <===
//==================================================================================================================
        if (new File(BusyBoxZip).exists()) {
            busyBoxCheckBox.setEnabled(true);
        } else {
            busyBoxCheckBox.setEnabled(false);
        }
        if (new File(SuperSuZip).exists()) {
            superSuCheckBox.setEnabled(true);
        } else {
            superSuCheckBox.setEnabled(false);
        }
        if (new File(MagiskZip).exists()) {
            magiskCheckBox.setEnabled(true);
        } else {
            magiskCheckBox.setEnabled(false);
        }

        if (new File(GappsZip).exists()) {
            GAPPScheckBox.setEnabled(true);
        } else {
            GAPPScheckBox.setEnabled(false);
        }
        if (new File(XposedZip).exists()) {
            XposedcheckBox.setEnabled(true);
        } else {
            XposedcheckBox.setEnabled(false);
        }
        if (new File(initdzip).exists()) {
            initDCheckBox.setEnabled(true);
        } else {
            initDCheckBox.setEnabled(false);
        }

        setContentPane(rootPanel);
        /*отключаем всю Арому ==>*/
        AromaConf.setEnabled(false);
        ChengelogTextArea.setEnabled(false);
        chengeLogCheckBox.setEnabled(false);
        nameFormattedTextField.setEnabled(false);
        versionFormattedTextField.setEnabled(false);
        authorFormattedTextField.setEnabled(false);
        deviceFormattedTextField.setEnabled(false);
        dateFormattedTextField.setEnabled(false);
        SelectLang.setEnabled(false);
        boldButton.setEnabled(false);
        CenterButton.setEnabled(false);
        italicButton.setEnabled(false);
        underlineButton.setEnabled(false);
        RightButton.setEnabled(false);
        LeftButton.setEnabled(false);
        themeCheckBox.setEnabled(false);
        themeslist.setEnabled(false);
        ColorButton.setEnabled(false);
        foldermods.setEnabled(false);
        updateClearCheckBox.setEnabled(false);
        /*отключаем всю Арому <==*/

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//закрыть при нажатии на крестик
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//ничего не делать при нажатии на крестик

        setSize(750, 450);//размер окна проги

        pack();

        setResizable(false);//запретить изменять размер окна

        //setAlwaysOnTop(true);//поверх окон
        setTitle(NAMEPROGRAMM);//имя проги

        setLocationRelativeTo(null);//по центру экрана

        setVisible(true);//отображать прогу

//реакция кнопок на нажатия
        UnixButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "UnixShell pressed");
                Runnable r = new Runnable() {
                    public void run() {
                        UnixButton.setEnabled(false);//выкл. клавишу
                        Unixscript.script();
                        UnixButton.setEnabled(true);//вкл. клавишу
                    }
                };
                new Thread(r).start();
                Functions.writefile(ERRORLOG, "UnixShell pressed ending");
            }
        });
        button5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "Generater pressed");
                Runnable r = new Runnable() {
                    public void run() {
                        button5.setEnabled(false);//выкл. клавишу
                        Generater.Gen_osn();
                        button5.setEnabled(true);//вкл. клавишу
                    }
                };
                new Thread(r).start();
                Functions.writefile(ERRORLOG, "Generater pressed ending");
            }
        });
        Help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "Help pressed");
                Runnable r = new Runnable() {
                    public void run() {
                        if (Objects.equals(LANGUAGE, "русский")) {
                            HelpRus.main();
                        } else {

                        }
                    }
                };
                new Thread(r).start();
                Functions.writefile(ERRORLOG, "Help pressed ending");
            }
        });
        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//закрыть программу при нажатии на кнопку выход
            }
        });
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "6 button");
                button6.setEnabled(false);//выкл. клавишу
                Functions.file_contexts_from_bin();
                button6.setEnabled(true);//вкл. клавишу
                Functions.writefile(ERRORLOG, "6 button ending");
            }
        });
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "7 button");
                button7.setEnabled(false);//выкл. клавишу
                Functions.file_contexts_to_bin();
                button7.setEnabled(true);//вкл. клавишу
                Functions.writefile(ERRORLOG, "7 button ending");
            }
        });
        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button9.setEnabled(false);//выкл. клавишу
                Functions.make_file_contexts();
                button9.setEnabled(true);//вкл. клавишу
            }
        });

        SignZip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "SignZip button");
                Runnable r = new Runnable() {
                    public void run() {
                        Functions.SignZipFree();
                    }
                };
                new Thread(r).start();
                Functions.writefile(ERRORLOG, "SignZip button ending");
            }
        });
        yandex.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Functions.openWebpage(URI.create("https://money.yandex.ru/to/410011128605653"));
            }
        });
        PDA.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Functions.openWebpage(URI.create("http://4pda.ru/forum/index.php?showtopic=205817&view=findpost&p=53519511"));
            }
        });
        paypal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Functions.writefile(ERRORLOG, "Go to URL paypal");
                Functions.openWebpage(URI.create("https://www.paypal.me/blackeangel"));
                Functions.writefile(ERRORLOG, "GO to URL paypal ending");
            }
        });

        mackeStatfilefromTarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "Generate statfile from TWRP");
                Runnable r = new Runnable() {
                    public void run() {
                        mackeStatfilefromTarButton.setEnabled(false);//выкл. клавишу
                        JFileChooser fileopen = new JFileChooser(projectDir);
                        String win = null;
                        fileopen.setFileFilter(new ExtFileFilter("win", "*.ext4.win"));//крутой фильтр по расширению
                        int ret = fileopen.showDialog(null, "Открыть файл");
                        if (ret == JFileChooser.APPROVE_OPTION) {
                            File file = fileopen.getSelectedFile();
                            if (file.getName().indexOf(".ext4.win", 0) != -1) {
                                win = file.getAbsolutePath().toString();
                            }
                        }
                        Functions.MackeStatfilefromTar(win);
                        mackeStatfilefromTarButton.setEnabled(true);//вкл. клавишу
                    }
                };
                new Thread(r).start();
                Functions.writefile(ERRORLOG, "Generate statfile from TWRP ending");
            }
        });
        //калькулятор
        calcButton.setMnemonic(KeyEvent.VK_L);
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "Calcculator pressed");
                if (textField1.getText().length() == 10 || textField1.getText().length() == 9) {
                    textField2.setText(Functions.getDigits(textField1.getText()));
                }
                Functions.writefile(ERRORLOG, "Calcculator ending");
            }
        });

        deodexedScriptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runnable r = new Runnable() {
                    public void run() {
                        deodexedScriptButton.setEnabled(false);//выкл. клавишу
                        deodexedscript.deodexgenerat();
                        deodexedScriptButton.setEnabled(true);//вкл. клавишу
                    }
                };
                new Thread(r).start();
            }
        });
        textfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.RecalcRules();
            }
        });

        aromaInstallerCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (aromaInstallerCheckBox.isSelected()) {
                    AromaConf.setEnabled(true);
                    chengeLogCheckBox.setEnabled(true);
                    nameFormattedTextField.setEnabled(true);
                    versionFormattedTextField.setEnabled(true);
                    authorFormattedTextField.setEnabled(true);
                    deviceFormattedTextField.setEnabled(true);
                    dateFormattedTextField.setEnabled(true);
                    SelectLang.setEnabled(true);
                    themeCheckBox.setEnabled(true);
                    foldermods.setEnabled(true);
                    updateClearCheckBox.setEnabled(true);
                    if (!new File(aromamods).exists()) {
                        new File(aromamods).mkdirs();//создаем tmp папку со всеми подпапками
                    }
                    if (superSuCheckBox.isSelected()) {
                        if (new File(SuperSuZip).exists()) {
                            Functions.copyFile(SuperSuZip, aromamods + sep + "supersu.zip");
                        }

                    }
                    if (busyBoxCheckBox.isSelected()) {
                        if (new File(BusyBoxZip).exists()) {
                            Functions.copyFile(BusyBoxZip, aromamods + sep + "busybox.zip");
                        }
                    }
                    if (XposedcheckBox.isSelected()) {
                        if (new File(XposedZip).exists()) {
                            Functions.copyFile(XposedZip, aromamods + sep + "xposed.zip");
                        }
                    }
                    if (initDCheckBox.isSelected()) {
                        if (new File(initdzip).exists()) {
                            Functions.copyFile(initdzip, aromamods + sep + "initd.zip");
                        }
                    }

                    if (GAPPScheckBox.isSelected()) {
                        if (new File(BusyBoxZip).exists()) {
                            Functions.copyFile(GappsZip, aromamods + sep + "gapps.zip");
                        }
                    }

                    //таблица -->
                    DefaultTableModel model;
                    model = new DefaultTableModel() {

                        public boolean isCellEditable(int row, int column) {
                            if (column == 0) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                    };
                    model.addColumn("Файл");
                    model.addColumn("Название");
                    //получаем список файлов
                    File Dir = new File(aromamods);
                    for (String fn : Dir.list()) {
                        //Добавим строку с данными
                        if (fn.indexOf("supersu", 0) != -1) {
                            String[] data = {fn, "SuperSu"};
                            model.addRow(data);
                        } else {
                            if (fn.indexOf("initd", 0) != -1) {
                                String[] data = {fn, "init.d"};
                                model.addRow(data);
                            } else {
                                if (fn.indexOf("xposed", 0) != -1) {
                                    String[] data = {fn, "Xposed Installer"};
                                    model.addRow(data);
                                } else {
                                    if (fn.indexOf("busybox", 0) != -1) {
                                        String[] data = {fn, "BusyBox"};
                                        model.addRow(data);
                                    } else {
                                        if (fn.indexOf("gapps", 0) != -1) {
                                            String[] data = {fn, "Google Apps"};
                                            model.addRow(data);
                                        } else {
                                            if (fn.indexOf("magisk", 0) != -1) {
                                                String[] data = {fn, "Magisk"};
                                                model.addRow(data);
                                            } else {
                                                String[] data = {fn, ""};
                                                model.addRow(data);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    modslist.setModel(model);
                    // таблица <--
                    // список тем из папки -->
                    DefaultComboBoxModel thememodel = new DefaultComboBoxModel();
                    File listok = new File(pathBin + sep + "aroma" + sep + "themes");
                    for (String fol : listok.list()) {
                        thememodel.addElement(fol);
                    }
                    themeslist.setModel(thememodel);
//список тем из папки <--
                } else {
                    AromaConf.setEnabled(false);
                    chengeLogCheckBox.setEnabled(false);
                    nameFormattedTextField.setEnabled(false);
                    versionFormattedTextField.setEnabled(false);
                    authorFormattedTextField.setEnabled(false);
                    deviceFormattedTextField.setEnabled(false);
                    dateFormattedTextField.setEnabled(false);
                    SelectLang.setEnabled(false);
                    themeCheckBox.setEnabled(false);
                    themeslist.setEnabled(false);
                    ChengelogTextArea.setEnabled(false);
                    boldButton.setEnabled(false);
                    CenterButton.setEnabled(false);
                    italicButton.setEnabled(false);
                    underlineButton.setEnabled(false);
                    RightButton.setEnabled(false);
                    LeftButton.setEnabled(false);
                    ColorButton.setEnabled(false);
                    foldermods.setEnabled(false);
                    updateClearCheckBox.setEnabled(false);
                }
            }
        });
        chengeLogCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (chengeLogCheckBox.isSelected()) {
                    ChengelogTextArea.setEnabled(true);
                    boldButton.setEnabled(true);
                    CenterButton.setEnabled(true);
                    italicButton.setEnabled(true);
                    underlineButton.setEnabled(true);
                    RightButton.setEnabled(true);
                    LeftButton.setEnabled(true);
                    ColorButton.setEnabled(true);
                } else {
                    ChengelogTextArea.setEnabled(false);
                    boldButton.setEnabled(false);
                    CenterButton.setEnabled(false);
                    italicButton.setEnabled(false);
                    underlineButton.setEnabled(false);
                    RightButton.setEnabled(false);
                    LeftButton.setEnabled(false);
                    ColorButton.setEnabled(false);
                }
            }
        });

        SelectLang.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (SelectLang.isSelected()) {
                    new File(dirScript + sep + "aroma" + sep + "langs").mkdirs();
                }
            }
        });
        boldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ChengelogTextArea.getSelectedText() != null) {
                    ChengelogTextArea.replaceSelection("<b>" + ChengelogTextArea.getSelectedText() + "</b>");
                }
            }
        });
        CenterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ChengelogTextArea.getSelectedText() != null) {
                    ChengelogTextArea.replaceSelection("<@center>" + ChengelogTextArea.getSelectedText() + "</@>");
                }
            }
        });
        italicButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ChengelogTextArea.getSelectedText() != null) {
                    ChengelogTextArea.replaceSelection("<i>" + ChengelogTextArea.getSelectedText() + "</i>");
                }
            }
        });
        underlineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ChengelogTextArea.getSelectedText() != null) {
                    ChengelogTextArea.replaceSelection("<u>" + ChengelogTextArea.getSelectedText() + "</u>");
                }
            }
        });
        LeftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ChengelogTextArea.getSelectedText() != null) {
                    ChengelogTextArea.replaceSelection("<@left>" + ChengelogTextArea.getSelectedText() + "</@>");
                }
            }
        });
        RightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ChengelogTextArea.getSelectedText() != null) {
                    ChengelogTextArea.replaceSelection("<@right>" + ChengelogTextArea.getSelectedText() + "</@>");
                }
            }
        });
        themeCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (themeCheckBox.isSelected()) {
                    new File(dirScript + sep + "aroma" + sep + "themes").mkdirs();
                    themeslist.setEnabled(true);
                } else {
                    themeslist.setEnabled(false);
                }
            }
        });

        ColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JColorChooser clr = new JColorChooser();
                if (ChengelogTextArea.getSelectedText() != null) {
                    Color color = clr.showDialog(null, "Choose Color",
                            getBackground());
                    ChengelogTextArea.replaceSelection("<" + Functions.convertColorToHexadeimal(color) + ">" + ChengelogTextArea.getSelectedText() + "</#>");
                }
            }
        });
        updateClearCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (updateClearCheckBox.isSelected()) {
                    datamediacheckBox.setSelected(false);
                }
            }
        });

        datamediacheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (datamediacheckBox.isSelected()) {
                    updateClearCheckBox.setSelected(false);
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blocks.block();
            }
        });
        Pravo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Functions.writefile(ERRORLOG, "Generated from folder pressed");
                Runnable r = new Runnable() {
                    public void run() {
                        Pravo.setEnabled(false);//выкл. клавишу
                        DirSysytem.main();
                        Pravo.setEnabled(true);//вкл. клавишу
                    }
                };
                new Thread(r).start();
                Functions.writefile(ERRORLOG, "Generated from folder pressed ending");
            }
        });
        magiskCheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (superSuCheckBox.isSelected()) {
                    superSuCheckBox.setSelected(false);
                }
            }
        });
        AromaGener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AromaGenerator.main();
            }
        });
        superSuCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (magiskCheckBox.isSelected()) {
                    magiskCheckBox.setSelected(false);
                }
            }
        });

        toolcreatearchive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runnable r = new Runnable() {
                    public void run() {
                        toolcreatearchive.setEnabled(false);//выкл. клавишу
                        if (new File(dirTmp).exists()) {
                                Functions.Zipping();
                        }
                        toolcreatearchive.setEnabled(true);//вкл. клавишу
                    }
                };
                new Thread(r).start();
            }
        });
        delgaapsmiserveces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delgaapsmi.main();
            }
        });
    }


    public static void main(String[] args) {
        instance = new Generator();
    }

    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 7, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setAutoscrolls(true);
        rootPanel.setDoubleBuffered(false);
        rootPanel.setEnabled(true);
        rootPanel.setFocusCycleRoot(false);
        rootPanel.setFocusTraversalPolicyProvider(false);
        rootPanel.setFocusable(false);
        rootPanel.setInheritsPopupMenu(false);
        rootPanel.setMaximumSize(new Dimension(750, 500));
        rootPanel.setMinimumSize(new Dimension(750, 500));
        rootPanel.setName("");
        rootPanel.setOpaque(false);
        rootPanel.setPreferredSize(new Dimension(750, 500));
        rootPanel.setRequestFocusEnabled(false);
        rootPanel.setToolTipText("");
        rootPanel.setVerifyInputWhenFocusTarget(false);
        rootPanel.setVisible(true);
        toolbar = new JToolBar();
        toolbar.setInheritsPopupMenu(false);
        toolbar.setName("");
        toolbar.setRollover(false);
        toolbar.setToolTipText("");
        toolbar.putClientProperty("JToolBar.isRollover", Boolean.FALSE);
        rootPanel.add(toolbar, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        toolbar.setBorder(BorderFactory.createTitledBorder(""));
        toolbarLabel = new JLabel();
        toolbarLabel.setAlignmentX(0.5f);
        toolbarLabel.setFocusable(false);
        toolbarLabel.setText("1321321312");
        toolbar.add(toolbarLabel);
        pages = new JTabbedPane();
        rootPanel.add(pages, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        generate = new JPanel();
        generate.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(5, 5, 5, 5), -1, -1));
        generate.setRequestFocusEnabled(true);
        pages.addTab("Generator", generate);
        generate.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Generate", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(generate.getFont().getName(), Font.BOLD | Font.ITALIC, generate.getFont().getSize()), new Color(-6740480)));
        button5 = new JButton();
        button5.setAlignmentY(0.0f);
        button5.setAutoscrolls(false);
        button5.setDoubleBuffered(false);
        button5.setFocusCycleRoot(false);
        button5.setFocusPainted(false);
        button5.setHorizontalAlignment(0);
        button5.setHorizontalTextPosition(0);
        button5.setIconTextGap(1);
        button5.setLabel("gener");
        button5.setMargin(new Insets(1, 1, 1, 1));
        button5.setText("gener");
        button5.putClientProperty("hideActionText", Boolean.FALSE);
        button5.putClientProperty("html.disable", Boolean.FALSE);
        generate.add(button5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 60), new Dimension(150, 60), new Dimension(150, 60), 0, false));
        options = new JPanel();
        options.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        options.setToolTipText("");
        generate.add(options, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(220, -1), 0, false));
        options.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Options", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(options.getFont().getName(), Font.BOLD | Font.ITALIC, options.getFont().getSize()), new Color(-6740480)));
        datamediacheckBox = new JCheckBox();
        datamediacheckBox.setLabel("data/media");
        datamediacheckBox.setText("data/media");
        options.add(datamediacheckBox, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, 24), null, 0, false));
        busyBoxCheckBox = new JCheckBox();
        busyBoxCheckBox.setActionCommand("BusyИox");
        busyBoxCheckBox.setEnabled(true);
        busyBoxCheckBox.setLabel("BusyBox");
        busyBoxCheckBox.setText("BusyBox");
        options.add(busyBoxCheckBox, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, 24), null, 0, false));
        GAPPScheckBox = new JCheckBox();
        GAPPScheckBox.setLabel("GAPPS");
        GAPPScheckBox.setText("GAPPS");
        options.add(GAPPScheckBox, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, 24), null, 0, false));
        XposedcheckBox = new JCheckBox();
        XposedcheckBox.setLabel("Xposed");
        XposedcheckBox.setText("Xposed");
        options.add(XposedcheckBox, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, 24), null, 0, false));
        zipcheckBox = new JCheckBox();
        zipcheckBox.setLabel("zip");
        zipcheckBox.setSelected(true);
        zipcheckBox.setText("zip");
        options.add(zipcheckBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        superSuCheckBox = new JCheckBox();
        superSuCheckBox.setActionCommand("SuperSu");
        superSuCheckBox.setText("SuperSu");
        options.add(superSuCheckBox, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, 32), null, 0, false));
        signZipCheckBox = new JCheckBox();
        signZipCheckBox.setText("Sign zip");
        options.add(signZipCheckBox, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(120, -1), 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Auto");
        defaultComboBoxModel1.addElement("Android 4.2");
        defaultComboBoxModel1.addElement("Android 4.4-6.0");
        defaultComboBoxModel1.addElement("Android 7.0");
        defaultComboBoxModel1.addElement("Android 7.1");
        defaultComboBoxModel1.addElement("Android 8.0");
        comboBox1.setModel(defaultComboBoxModel1);
        options.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(88, -1), new Dimension(88, -1), 0, false));
        initDCheckBox = new JCheckBox();
        initDCheckBox.setText("init.d");
        options.add(initDCheckBox, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Pravo = new JButton();
        Pravo.setEnabled(true);
        Pravo.setHorizontalTextPosition(0);
        Pravo.setLabel("system");
        Pravo.setMargin(new Insets(1, 1, 1, 1));
        Pravo.setText("system");
        generate.add(Pravo, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        Aroma = new JPanel();
        Aroma.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        generate.add(Aroma, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        aromaInstallerCheckBox = new JCheckBox();
        aromaInstallerCheckBox.setText("Aroma Installer");
        Aroma.add(aromaInstallerCheckBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AromaConf = new JTabbedPane();
        Aroma.add(AromaConf, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        General = new JPanel();
        General.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 6, new Insets(0, 0, 0, 0), -1, -1));
        AromaConf.addTab("General", General);
        chengeLogCheckBox = new JCheckBox();
        chengeLogCheckBox.setText("Chenge Log");
        General.add(chengeLogCheckBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
        ChalgelogScrol = new JScrollPane();
        ChalgelogScrol.setAlignmentX(0.0f);
        ChalgelogScrol.setAlignmentY(0.0f);
        ChalgelogScrol.setAutoscrolls(true);
        ChalgelogScrol.setInheritsPopupMenu(false);
        General.add(ChalgelogScrol, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 7, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ChengelogTextArea = new JTextArea();
        ChengelogTextArea.setTabSize(9);
        ChengelogTextArea.setText("");
        ChalgelogScrol.setViewportView(ChengelogTextArea);
        nameFormattedTextField = new JFormattedTextField();
        nameFormattedTextField.setText("Name");
        General.add(nameFormattedTextField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(65, -1), new Dimension(65, -1), 0, false));
        versionFormattedTextField = new JFormattedTextField();
        versionFormattedTextField.setText("Version");
        General.add(versionFormattedTextField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(65, -1), new Dimension(65, -1), 0, false));
        CenterButton = new JButton();
        CenterButton.setHorizontalTextPosition(0);
        CenterButton.setText("C");
        General.add(CenterButton, new com.intellij.uiDesigner.core.GridConstraints(8, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(45, 20), new Dimension(45, 20), new Dimension(45, 20), 0, false));
        underlineButton = new JButton();
        underlineButton.setFont(new Font(underlineButton.getFont().getName(), underlineButton.getFont().getStyle(), underlineButton.getFont().getSize()));
        underlineButton.setHorizontalTextPosition(0);
        underlineButton.setText("U");
        General.add(underlineButton, new com.intellij.uiDesigner.core.GridConstraints(8, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(45, 20), new Dimension(45, 20), new Dimension(45, 20), 0, false));
        LeftButton = new JButton();
        LeftButton.setHorizontalTextPosition(0);
        LeftButton.setText("L");
        General.add(LeftButton, new com.intellij.uiDesigner.core.GridConstraints(8, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(45, 20), new Dimension(45, 20), new Dimension(45, 20), 0, false));
        themeCheckBox = new JCheckBox();
        themeCheckBox.setText("Theme");
        General.add(themeCheckBox, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        themeslist = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        themeslist.setModel(defaultComboBoxModel2);
        General.add(themeslist, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boldButton = new JButton();
        boldButton.setFont(new Font(boldButton.getFont().getName(), Font.BOLD, boldButton.getFont().getSize()));
        boldButton.setHorizontalTextPosition(0);
        boldButton.setText("B");
        General.add(boldButton, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(45, 20), new Dimension(45, 20), new Dimension(45, 20), 0, false));
        italicButton = new JButton();
        italicButton.setFont(new Font(italicButton.getFont().getName(), Font.ITALIC, italicButton.getFont().getSize()));
        italicButton.setHorizontalTextPosition(0);
        italicButton.setText("I");
        General.add(italicButton, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(45, 20), new Dimension(45, 20), new Dimension(45, 20), 0, false));
        RightButton = new JButton();
        RightButton.setHorizontalTextPosition(0);
        RightButton.setText("R");
        General.add(RightButton, new com.intellij.uiDesigner.core.GridConstraints(9, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(45, 20), new Dimension(45, 20), new Dimension(45, 20), 0, false));
        ColorButton = new JButton();
        ColorButton.setHorizontalTextPosition(0);
        ColorButton.setText("Color");
        General.add(ColorButton, new com.intellij.uiDesigner.core.GridConstraints(9, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(65, 20), new Dimension(65, 20), new Dimension(65, 20), 0, false));
        deviceFormattedTextField = new JFormattedTextField();
        deviceFormattedTextField.setText("Device");
        General.add(deviceFormattedTextField, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(65, -1), new Dimension(65, -1), 0, false));
        foldermods = new JFormattedTextField();
        foldermods.setFont(new Font(foldermods.getFont().getName(), foldermods.getFont().getStyle(), 10));
        foldermods.setText("Name folder Mods");
        General.add(foldermods, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(90, -1), new Dimension(90, -1), new Dimension(90, -1), 0, false));
        SelectLang = new JCheckBox();
        SelectLang.setText("Lang (rus/eng)");
        General.add(SelectLang, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(110, -1), new Dimension(110, -1), new Dimension(110, -1), 0, false));
        authorFormattedTextField = new JFormattedTextField();
        authorFormattedTextField.setText("Author");
        General.add(authorFormattedTextField, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(65, -1), new Dimension(65, -1), 0, false));
        dateFormattedTextField = new JFormattedTextField();
        dateFormattedTextField.setText("Date");
        General.add(dateFormattedTextField, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(65, -1), new Dimension(65, -1), 0, false));
        updateClearCheckBox = new JCheckBox();
        updateClearCheckBox.setText("Update/Clear");
        General.add(updateClearCheckBox, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Mods = new JPanel();
        Mods.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        AromaConf.addTab("Mods", null, Mods, "");
        final JScrollPane scrollPane1 = new JScrollPane();
        Mods.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        modslist = new JTable();
        scrollPane1.setViewportView(modslist);
        UnixButton = new JButton();
        UnixButton.setEnabled(true);
        UnixButton.setHorizontalTextPosition(0);
        UnixButton.setLabel("Unix");
        UnixButton.setMargin(new Insets(1, 1, 1, 1));
        UnixButton.setText("Unix");
        generate.add(UnixButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 60), new Dimension(150, 60), new Dimension(150, 60), 0, false));
        vspomogat = new JPanel();
        vspomogat.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pages.addTab("Tools", vspomogat);
        tools = new JPanel();
        tools.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        vspomogat.add(tools, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tools.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Tools", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(tools.getFont().getName(), Font.BOLD | Font.ITALIC, tools.getFont().getSize()), new Color(-6740480)));
        button4 = new JButton();
        button4.setEnabled(true);
        button4.setHorizontalTextPosition(0);
        button4.setLabel("block");
        button4.setMargin(new Insets(1, 1, 1, 1));
        button4.setText("block");
        tools.add(button4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        button7 = new JButton();
        button7.setAlignmentY(0.0f);
        button7.setHideActionText(false);
        button7.setHorizontalTextPosition(0);
        button7.setLabel("contexts");
        button7.setMargin(new Insets(1, 1, 1, 1));
        button7.setText("contexts");
        button7.putClientProperty("hideActionText", Boolean.FALSE);
        button7.putClientProperty("html.disable", Boolean.FALSE);
        tools.add(button7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        SignZip = new JButton();
        SignZip.setText("SignZip");
        tools.add(SignZip, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, 60), new Dimension(150, 60), new Dimension(150, 60), 0, false));
        mackeStatfilefromTarButton = new JButton();
        mackeStatfilefromTarButton.setText("MackeStatfilefromTar");
        mackeStatfilefromTarButton.setVerifyInputWhenFocusTarget(true);
        mackeStatfilefromTarButton.setVisible(true);
        tools.add(mackeStatfilefromTarButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        Calc = new JPanel();
        Calc.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tools.add(Calc, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Calc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Calculator"));
        calcButton = new JButton();
        calcButton.setText("Calc");
        Calc.add(calcButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        Calc.add(textField2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField1 = new JTextField();
        Calc.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        tools.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        button6 = new JButton();
        button6.setHorizontalTextPosition(0);
        button6.setMargin(new Insets(1, 1, 1, 1));
        button6.setText("Convert file_contexts.bin");
        tools.add(button6, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        button9 = new JButton();
        button9.setHorizontalTextPosition(0);
        button9.setMargin(new Insets(1, 1, 1, 1));
        button9.setText("Convert file_contexts");
        tools.add(button9, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        deodexedScriptButton = new JButton();
        deodexedScriptButton.setEnabled(true);
        deodexedScriptButton.setHorizontalTextPosition(0);
        deodexedScriptButton.setLabel("deodexed script");
        deodexedScriptButton.setMargin(new Insets(1, 1, 1, 1));
        deodexedScriptButton.setText("deodexed script");
        tools.add(deodexedScriptButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        textfileButton = new JButton();
        textfileButton.setHorizontalTextPosition(0);
        textfileButton.setLabel("Recalk Rules txt file");
        textfileButton.setMargin(new Insets(1, 1, 1, 1));
        textfileButton.setText("Recalk Rules txt file");
        tools.add(textfileButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 60), new Dimension(150, 60), 0, false));
        Exit = new JButton();
        Exit.setHorizontalTextPosition(0);
        Exit.setLabel("Exit");
        Exit.setMargin(new Insets(1, 1, 1, 1));
        Exit.setText("Exit");
        rootPanel.add(Exit, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(60, 20), new Dimension(60, 20), new Dimension(60, 20), 0, false));
        PDA = new JLabel();
        PDA.setFont(new Font(PDA.getFont().getName(), Font.BOLD | Font.ITALIC, PDA.getFont().getSize()));
        PDA.setForeground(new Color(-16775169));
        PDA.setHorizontalAlignment(11);
        PDA.setText("blackeangel 4PDA.RU");
        rootPanel.add(PDA, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Help = new JButton();
        Help.setHorizontalTextPosition(0);
        Help.setLabel("Help");
        Help.setMargin(new Insets(1, 1, 1, 1));
        Help.setText("Help");
        rootPanel.add(Help, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(60, 20), new Dimension(60, 20), new Dimension(60, 20), 0, false));
        Thx = new JLabel();
        Thx.setName("");
        Thx.setOpaque(false);
        Thx.setText("Поблагодарить:");
        rootPanel.add(Thx, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 33), null, 0, false));
        yandex = new JLabel();
        yandex.setFont(new Font(yandex.getFont().getName(), Font.BOLD | Font.ITALIC, yandex.getFont().getSize()));
        yandex.setForeground(new Color(-16775169));
        yandex.setText("Yandex.Money");
        rootPanel.add(yandex, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 33), null, 0, false));
        paypal = new JLabel();
        paypal.setFont(new Font(paypal.getFont().getName(), Font.BOLD | Font.ITALIC, paypal.getFont().getSize()));
        paypal.setForeground(new Color(-16775169));
        paypal.setText("PayPal   ");
        rootPanel.add(paypal, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(47, 33), null, 0, false));
        qiwi = new JLabel();
        qiwi.setText("QIWI: +79524760250");
        rootPanel.add(qiwi, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(126, 33), null, 0, false));
        progressBar1 = new JProgressBar();
        rootPanel.add(progressBar1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}

