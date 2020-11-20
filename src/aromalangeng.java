import java.io.File;

public class aromalangeng extends Generator {
    public static void englang() {
        if (instance.SelectLang.isSelected()) {
            if (!new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "langs").exists()) {
                new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "langs").mkdirs();//создаем папку со всеми подпапками
            }
            String f = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "en.lang";
            //начало файла
            Functions.writefile(f, "text_ok=OK");
            Functions.writefile(f, "text_next=Next >");
            Functions.writefile(f, "text_back=< Back");
            Functions.writefile(f, "text_yes=Yes");
            Functions.writefile(f, "text_no=No");
            Functions.writefile(f, "text_about=About");
            Functions.writefile(f, "text_calibrating=Calibrating Tools");
            Functions.writefile(f, "text_quit=Quit Installation");
            Functions.writefile(f, "text_quit_msg=Are you sure to quit the installer?");
            Functions.writefile(f, "text_install=Install");
            Functions.writefile(f, "text_next1=Next >");
            Functions.writefile(f, "text_finish=Finish");
            Functions.writefile(f, "themes=Themes");
            Functions.writefile(f, "themes1.1=Choose theme thet you want");
            Functions.writefile(f, "themes2.1=Generic");
            Functions.writefile(f, "themes2.2=ICS");
            Functions.writefile(f, "themes2.3=MIUI");
            Functions.writefile(f, "themes2.4=MIUI v4");
            Functions.writefile(f, "themes2.5=Sense");
            Functions.writefile(f, "themes3.1=Unthemed AROMA Installer");
            Functions.writefile(f, "themes3.2=Ice Cream Sandvich theme");
            Functions.writefile(f, "themes3.3=MIUI theme");
            Functions.writefile(f, "themes3.4=MIUI v4 theme");
            Functions.writefile(f, "themes3.5=Sense theme");
            Functions.writefile(f, "welcome=Welcome");
            Functions.writefile(f, "welcome1=It's a <b>");
            Functions.writefile(f, "welcome2=</b> v");
            Functions.writefile(f, "welcome3=for");
            Functions.writefile(f, "welcome4=by <b>");
            Functions.writefile(f, "welcome5=</b>.\\");
            Functions.writefile(f, "Date:");
            Functions.writefile(f, "welcome6=.\\");
            Functions.writefile(f, "Tap Next to continiue.");
            Functions.writefile(f, "agreebox=Agreebox");
            Functions.writefile(f, "agreebox1.1=You should agree with it.");
            Functions.writefile(f, "agreebox2.1=Yes, I agree.");
            Functions.writefile(f, "agreebox3.1=You can't continiue while you disagree.");
            Functions.writefile(f, "changelog=Changelog");
            Functions.writefile(f, "changelog1=There're all changes.");
            Functions.writefile(f, "textdialog=Textdialog");
            Functions.writefile(f, "textdialog1=Text on button");
            Functions.writefile(f, "type=Type of Installation");
            Functions.writefile(f, "type1.1=Choose type of installation.");
            Functions.writefile(f, "type2.1=1");
            Functions.writefile(f, "type2.2=2");
            Functions.writefile(f, "type3.1=First type");
            Functions.writefile(f, "type3.2=Second type");
            Functions.writefile(f, "checkbox=Checkbox");
            Functions.writefile(f, "checkbox1.1=Make your choices.");
//начало файла --<
/*тут должно быть генерируемое содержимое
=============================================================
*/
            Functions.writefile(f, "");
            Functions.writefile(f, "checkbox(\"<~checkbox>\",");
            Functions.writefile(f, "\"<~checkbox1.1>\",");
            Functions.writefile(f, "\"@customize\",");
            Functions.writefile(f, "\"checkbox.prop\",");
            instance.modslist.getModel();
            int rows = instance.modslist.getModel().getRowCount();
            for (int i = 0; i < rows - 1; i++) {
                Functions.writefile(f, "\"<~checkbox1." + (i + 2) + ">\", \"\" , 0,");
            }
            Functions.writefile(f, "\"<~checkbox1." + (rows + 1) + ">\", \"\" , 0);");
            Functions.writefile(f, "");
/*концовка файла*/
            Functions.writefile(f, "selectbox = Type installation");
            Functions.writefile(f, "selectbox1.1 = Select type installation:");
            Functions.writefile(f, "selectbox2.1 = Update");
            Functions.writefile(f, "selectbox2.2 = Standart");
            Functions.writefile(f, "selectbox2.3 = Clear");
            Functions.writefile(f, "selectbox3.1 = Clear only Cache & Dalvik-cache");
            Functions.writefile(f, "selectbox3.2 = Clear System, Cache, Dalvik-cache");
            Functions.writefile(f, "selectbox3.3 = Clear Data, System, Cache, Dalvik-cache");

            Functions.writefile(f, "demo=Demo");
            Functions.writefile(f, "demo1.1=What do you want to see?");
            Functions.writefile(f, "demo2.1=Installer demo");
            Functions.writefile(f, "demo2.2=Installs something");
            Functions.writefile(f, "demo3.1=See other demos first");
            Functions.writefile(f, "demo3.2=Sysinfo, calculating etc.");
            Functions.writefile(f, "sys.title1=System Information");
            Functions.writefile(f, "sys.title2=Memory Information");
            Functions.writefile(f, "sysinfo=Device Information");
            Functions.writefile(f, "sysinfo1=There's some information about your device.");
            Functions.writefile(f, "back=Back demo");
            Functions.writefile(f, "back1.1=You can return to one of previous screens");
            Functions.writefile(f, "back2.1=Don't return");
            Functions.writefile(f, "back2.2=Return to the last screen");
            Functions.writefile(f, "calculations=Calculations");
            Functions.writefile(f, "iif=Inline If");
            Functions.writefile(f, "iif1=True");
            Functions.writefile(f, "iif2=False");
            Functions.writefile(f, "alert=Installation Demo");
            Functions.writefile(f, "alert1.1=Now we're going to installation demo.");
            Functions.writefile(f, "alert2.1=Good");
            Functions.writefile(f, "ayr=Are you ready?");
            Functions.writefile(f, "ayr1=Tap Install to continiue.");
            Functions.writefile(f, "confirm=Confirm");
            Functions.writefile(f, "confirm1.1=Choose Yes or No.");
            Functions.writefile(f, "confirm2.1=Yes text");
            Functions.writefile(f, "confirm3.1=No text");
            Functions.writefile(f, "install=Install");
            Functions.writefile(f, "install1.1=</b> are installing.\\");
            Functions.writefile(f, "\\");
            Functions.writefile(f, "install1.2=Please wait...");
            Functions.writefile(f, "install2.1=The installation wizard has successfully installed <b>");
            Functions.writefile(f, "install2.2=</b>. Press Next to finish.");
            Functions.writefile(f, "reboot=Reboot");
            Functions.writefile(f, "reboot1.1=Are you want to reboot device after finish?");
            Functions.writefile(f, "reboot2.1=Reboot device");
/*концовка файла*/
        }
    }
}

/*++++++++++++++++++++++++++++++++++++==================================+++++++++++++++++++++++++++++*/
class aromageneratorlangeng extends AromaGenerator {



    public static void partOne() {
        String f = aromafolder + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "en.lang";
        //начало файла
        Functions.writefile(f, "text_ok=OK");
        Functions.writefile(f, "text_next=Next >");
        Functions.writefile(f, "text_back=< Back");
        Functions.writefile(f, "text_yes=Yes");
        Functions.writefile(f, "text_no=No");
        Functions.writefile(f, "text_about=About");
        Functions.writefile(f, "text_calibrating=Calibrating Tools");
        Functions.writefile(f, "text_quit=Quit Installation");
        Functions.writefile(f, "text_quit_msg=Are you sure to quit the installer?");
        Functions.writefile(f, "text_install=Install");
        Functions.writefile(f, "text_next1=Next >");
        Functions.writefile(f, "text_finish=Finish");
        Functions.writefile(f, "themes=Themes");
        Functions.writefile(f, "themes1.1=Choose theme thet you want");
        Functions.writefile(f, "themes2.1=Generic");
        Functions.writefile(f, "themes2.2=ICS");
        Functions.writefile(f, "themes2.3=MIUI");
        Functions.writefile(f, "themes2.4=MIUI v4");
        Functions.writefile(f, "themes2.5=Sense");
        Functions.writefile(f, "themes3.1=Unthemed AROMA Installer");
        Functions.writefile(f, "themes3.2=Ice Cream Sandvich theme");
        Functions.writefile(f, "themes3.3=MIUI theme");
        Functions.writefile(f, "themes3.4=MIUI v4 theme");
        Functions.writefile(f, "themes3.5=Sense theme");
        Functions.writefile(f, "welcome=Welcome");
        Functions.writefile(f, "welcome1=It's a <b>");
        Functions.writefile(f, "welcome2=</b> v");
        Functions.writefile(f, "welcome3=for");
        Functions.writefile(f, "welcome4=by <b>");
        Functions.writefile(f, "welcome5=</b>.\\");
        Functions.writefile(f, "Date:");
        Functions.writefile(f, "welcome6=.\\");
        Functions.writefile(f, "Tap Next to continiue.");
        Functions.writefile(f, "agreebox=Agreebox");
        Functions.writefile(f, "agreebox1.1=You should agree with it.");
        Functions.writefile(f, "agreebox2.1=Yes, I agree.");
        Functions.writefile(f, "agreebox3.1=You can't continiue while you disagree.");
        Functions.writefile(f, "changelog=Changelog");
        Functions.writefile(f, "changelog1=There're all changes.");
        Functions.writefile(f, "textdialog=Textdialog");
        Functions.writefile(f, "textdialog1=Text on button");
        Functions.writefile(f, "type=Type of Installation");
        Functions.writefile(f, "type1.1=Choose type of installation.");
        Functions.writefile(f, "type2.1=1");
        Functions.writefile(f, "type2.2=2");
        Functions.writefile(f, "type3.1=First type");
        Functions.writefile(f, "type3.2=Second type");
    }

    public static void partTwo() {
        String f = aromafolder + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "en.lang";
        Functions.writefile(f, "demo=Demo");
        Functions.writefile(f, "demo1.1=What do you want to see?");
        Functions.writefile(f, "demo2.1=Installer demo");
        Functions.writefile(f, "demo2.2=Installs something");
        Functions.writefile(f, "demo3.1=See other demos first");
        Functions.writefile(f, "demo3.2=Sysinfo, calculating etc.");
        Functions.writefile(f, "sys.title1=System Information");
        Functions.writefile(f, "sys.title2=Memory Information");
        Functions.writefile(f, "sysinfo=Device Information");
        Functions.writefile(f, "sysinfo1=There's some information about your device.");
        Functions.writefile(f, "back=Back demo");
        Functions.writefile(f, "back1.1=You can return to one of previous screens");
        Functions.writefile(f, "back2.1=Don't return");
        Functions.writefile(f, "back2.2=Return to the last screen");
        Functions.writefile(f, "calculations=Calculations");
        Functions.writefile(f, "iif=Inline If");
        Functions.writefile(f, "iif1=True");
        Functions.writefile(f, "iif2=False");
        Functions.writefile(f, "alert=Installation Demo");
        Functions.writefile(f, "alert1.1=Now we're going to installation demo.");
        Functions.writefile(f, "alert2.1=Good");
        Functions.writefile(f, "ayr=Are you ready?");
        Functions.writefile(f, "ayr1=Tap Install to continiue.");
        Functions.writefile(f, "confirm=Confirm");
        Functions.writefile(f, "confirm1.1=Choose Yes or No.");
        Functions.writefile(f, "confirm2.1=Yes text");
        Functions.writefile(f, "confirm3.1=No text");
		Functions.writefile(f, "");
        Functions.writefile(f, "install=Install");
        Functions.writefile(f, "install1.1=</b> are installing.\\");
        Functions.writefile(f, "\\");
		Functions.writefile(f, "");
        Functions.writefile(f, "install1.2=Please wait...");
        Functions.writefile(f, "install2.1=The installation wizard has successfully installed <b>");
        Functions.writefile(f, "install2.2=</b>. Press Next to finish.");
		Functions.writefile(f, "");
        Functions.writefile(f, "reboot=Reboot");
        Functions.writefile(f, "reboot1.1=Are you want to reboot device after finish?");
        Functions.writefile(f, "reboot2.1=Reboot device");
/*концовка файла*/
    }
}
