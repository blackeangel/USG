import java.util.Objects;

public class aroma_config extends Generator {
    public static void gen() {
        if (instance.aromaInstallerCheckBox.isSelected()) {
            String F = dirScript + System.getProperty("file.separator") + "aroma-config";
            Functions.writefile(F, "ini_set(\"rom_name\", \"" + instance.nameFormattedTextField.getText() + "\");");
            Functions.writefile(F, "ini_set(\"rom_version\", \"" + instance.versionFormattedTextField.getText() + "\");");
            Functions.writefile(F, "ini_set(\"rom_date\", \"" + instance.dateFormattedTextField.getText() + "\");");
            Functions.writefile(F, "ini_set(\"rom_author\", \"" + instance.authorFormattedTextField.getText() + "\");");
            Functions.writefile(F, "ini_set(\"rom_device\", \"" + instance.deviceFormattedTextField.getText() + "\");");
            Functions.writefile(F, "calibrate(\"0.9718\",\"4\",\"1.0176\",\"-9\",\"yes\");");
            Functions.writefile(F, "fontresload( \"0\", \"ttf/DroidSans.ttf\", \"12\" );");
            Functions.writefile(F, "fontresload( \"1\", \"ttf/DroidSans.ttf\", \"18\" );");
            if (instance.themeCheckBox.isSelected()) {
                Functions.writefile(F, "theme(\"" + instance.themeslist.getSelectedItem().toString() + "\");");
            }
            if (instance.SelectLang.isSelected()) {
                Functions.writefile(F, "menubox(\"Language\", \"Choose language.\\nВыберите язык.\",");
                Functions.writefile(F, "\"@confirm\",");
                Functions.writefile(F, "\"language.prop\",");
                Functions.writefile(F, "\"English\", \"\", \"@english\",");
                Functions.writefile(F, "\"Русский\", \"\", \"@russian\");");
                Functions.writefile(F, "if prop(\"language.prop\", \"selected\") == \"1\" then");
                Functions.writefile(F, "    loadlang(\"langs/en.lang\");");
                Functions.writefile(F, "else");
                Functions.writefile(F, "    loadlang(\"langs/ru.lang\");");
                Functions.writefile(F, "endif;");
            } else {
                if (Objects.equals(LANGUAGE, "русский")) {
                    Functions.writefile(F, "loadlang(\"langs/ru.lang\");");
                } else {
                    Functions.writefile(F, "loadlang(\"langs/en.lang\");");
                }
            }
            if (instance.SelectLang.isSelected() && instance.chengeLogCheckBox.isSelected()) {
                Functions.writefile(F, "textbox(\"<~changelog>\",");
                Functions.writefile(F, "\"<~changelog1>\",");
                Functions.writefile(F, "\"@update\",");
                Functions.writefile(F, "if prop(\"language.prop\",\"selected\")==\"1\" then");
                Functions.writefile(F, "    resread(\"changelogs/en.txt\");");
                Functions.writefile(F, "else");
                Functions.writefile(F, "    resread(\"changelogs/ru.txt\");");
                Functions.writefile(F, "endif;");
            } else {
                if (!instance.SelectLang.isSelected() && instance.chengeLogCheckBox.isSelected()) {
                    if (Objects.equals(LANGUAGE, "русский")) {
                        Functions.writefile(F, "textbox(\"<~changelog>\", \"<~changelog1>\", \"@update\", resread(\"changelogs/ru.txt\"));");
                    } else {
                        Functions.writefile(F, "textbox(\"<~changelog>\", \"<~changelog1>\", \"@update\", resread(\"changelogs/en.txt\"));");
                    }
                }
            }
            ///=================================
            Functions.writefile(F, "");
            Functions.writefile(F, "checkbox(\"<~checkbox>\",");
            Functions.writefile(F, "\"<~checkbox1.1>\",");
            Functions.writefile(F, "\"@customize\",");
            Functions.writefile(F, "\"checkbox.prop\",");
            instance.modslist.getModel();
            int rows = instance.modslist.getModel().getRowCount();
            for (int i = 0; i < rows - 1; i++) {
                Functions.writefile(F, "\"<~checkbox1." + (i + 2) + ">\", \"\" , 0,");
            }
            Functions.writefile(F, "\"<~checkbox1." + (rows + 1) + ">\", \"\" , 0);");
            Functions.writefile(F, "");

//выбор режима установки чистая или обновление  -->
            if (instance.updateClearCheckBox.isSelected()) {
                Functions.writefile(F, "selectbox(\"<~selectbox>\",");
                Functions.writefile(F, "\"<~selectbox1.1>\",");
                Functions.writefile(F, "\"@customize\",");
                Functions.writefile(F, "\"selectbox.prop\",");
                Functions.writefile(F, "\"<~selectbox2.1>\", \"<~selectbox3.1>\", 1,");
                Functions.writefile(F, "\"<~selectbox2.2>\", \"<~selectbox3.2>\", 0,");
                Functions.writefile(F, "\"<~selectbox2.3>\", \"<~selectbox3.3>\", 0);");
            }
//выбор режима установки чистая или обновление  <--
///==================================
            ///концовка -->
            Functions.writefile(F, "ini_set(\"text_next\", \"<~text_install>\");");
            Functions.writefile(F, "");
            Functions.writefile(F, "viewbox(\"<~ayr>\",");
            Functions.writefile(F, "\"<~ayr1>\",");
            Functions.writefile(F, "\"@info\");");
            Functions.writefile(F, "");
            Functions.writefile(F, "confirm(\"<~confirm>\",");
            Functions.writefile(F, "\"<~confirm1.1>\",");
            Functions.writefile(F, "\"@confirm\",");
            Functions.writefile(F, "\"<~confirm2.1>\",");
            Functions.writefile(F, "\"<~confirm3.1>\");");
            Functions.writefile(F, "");
            Functions.writefile(F, "ini_set(\"text_next\", \"<~text_next1>\");");
            Functions.writefile(F, "install(\"<~install>\",");
            Functions.writefile(F, "\"<b>\"+ ini_get(\"rom_name\") +\"<~install1.1>\" + ");
            Functions.writefile(F, "\"<~install1.2>\",");
            Functions.writefile(F, "\"@install\",");
            Functions.writefile(F, "\"<~install2.1>\" + ini_get(\"rom_name\") + \"<~install2.2>\");");
            Functions.writefile(F, "");
            Functions.writefile(F, "ini_set(\"text_next\", \"<~text_finish>\");");
            Functions.writefile(F, "");
            Functions.writefile(F, "checkviewbox(\"<~reboot>\",");
            Functions.writefile(F, "\"<~reboot1.1>\",");
            Functions.writefile(F, "\"@welcome\",");
            Functions.writefile(F, "\"<~reboot2.1>\", \"1\", \"reboot\");");
            Functions.writefile(F, "");
            Functions.writefile(F, "if getvar(\"reboot\")==\"1\" then");
            Functions.writefile(F, "reboot(\"onfinish\");");
            Functions.writefile(F, "endif;");
            //<-- концовка

        }
    }

}

class aromageneratorconfig extends AromaGenerator {
    /*++++++++++++++===================================++++++++++++++++++++++++++++++++++*/
    public static void AromaGeneratorConfig() {
        String F = dirScript + System.getProperty("file.separator") + "aroma-config";
        Functions.writefile(F, "ini_set(\"rom_name\", \"" + instance1.nameFormattedTextField.getText() + "\");");
        Functions.writefile(F, "ini_set(\"rom_version\", \"" + instance1.versionFormattedTextField.getText() + "\");");
        Functions.writefile(F, "ini_set(\"rom_date\", \"" + instance1.dateFormattedTextField.getText() + "\");");
        Functions.writefile(F, "ini_set(\"rom_author\", \"" + instance1.authorFormattedTextField.getText() + "\");");
        Functions.writefile(F, "ini_set(\"rom_device\", \"" + instance1.deviceFormattedTextField.getText() + "\");");
        Functions.writefile(F, "calibrate(\"0.9718\",\"4\",\"1.0176\",\"-9\",\"yes\");");
        Functions.writefile(F, "fontresload( \"0\", \"ttf/DroidSans.ttf\", \"12\" );");
        Functions.writefile(F, "fontresload( \"1\", \"ttf/DroidSans.ttf\", \"18\" );");
        if (instance1.themeCheckBox.isSelected()) {
            Functions.writefile(F, "theme(\"" + instance1.themeslist.getSelectedItem().toString() + "\");");
        }
        if (instance1.SelectLang.isSelected()) {
            Functions.writefile(F, "menubox(\"Language\", \"Choose language.\\nВыберите язык.\",");
            Functions.writefile(F, "\"@confirm\",");
            Functions.writefile(F, "\"language.prop\",");
            Functions.writefile(F, "\"English\", \"\", \"@english\",");
            Functions.writefile(F, "\"Русский\", \"\", \"@russian\");");
            Functions.writefile(F, "if prop(\"language.prop\", \"selected\") == \"1\" then");
            Functions.writefile(F, "    loadlang(\"langs/en.lang\");");
            Functions.writefile(F, "else");
            Functions.writefile(F, "    loadlang(\"langs/ru.lang\");");
            Functions.writefile(F, "endif;");
        } else {
            if (Objects.equals(Generator.LANGUAGE, "русский")) {
                Functions.writefile(F, "loadlang(\"langs/ru.lang\");");
            } else {
                Functions.writefile(F, "loadlang(\"langs/en.lang\");");
            }
        }
        if (!Objects.equals(instance1.ChengelogTextArea.getText(), "")) {
            if (instance1.SelectLang.isSelected()) {
                Functions.writefile(F, "textbox(\"<~changelog>\", \"<~changelog1>\", \"@update\",");
                Functions.writefile(F, "if prop(\"language.prop\",\"selected\")==\"1\" then");
                Functions.writefile(F, "    resread(\"changelogs/en.txt\")");
                Functions.writefile(F, "else");
                Functions.writefile(F, "    resread(\"changelogs/ru.txt\")");
                Functions.writefile(F, "endif);");
            } else {
                if (Objects.equals(Generator.LANGUAGE, "русский")) {
                    Functions.writefile(F, "textbox(\"<~changelog>\", \"<~changelog1>\", \"@update\", resread(\"changelogs/ru.txt\"));");
                } else {
                    Functions.writefile(F, "textbox(\"<~changelog>\", \"<~changelog1>\", \"@update\", resread(\"changelogs/en.txt\"));");
                }
            }
        }
        ///=================================
       /* Functions.writefile(F, "");
        Functions.writefile(F, "checkbox(\"<~checkbox>\",");
        Functions.writefile(F, "\"<~checkbox1.1>\",");
        Functions.writefile(F, "\"@customize\",");
        Functions.writefile(F, "\"checkbox.prop\",");*/

        //table2
        AromaGeneraterPages.main();//основное заполнение

//выбор режима установки чистая или обновление  -->
        if (instance1.updateClearCheckBox.isSelected()) {
            Functions.writefile(F, "selectbox(\"<~selectbox>\",");
            Functions.writefile(F, "\"<~selectbox1.1>\",");
            Functions.writefile(F, "\"@customize\",");
            Functions.writefile(F, "\"selectbox.prop\",");
            Functions.writefile(F, "\"<~selectbox2.1>\", \"<~selectbox3.1>\", 1,");
            Functions.writefile(F, "\"<~selectbox2.2>\", \"<~selectbox3.2>\", 0,");
            Functions.writefile(F, "\"<~selectbox2.3>\", \"<~selectbox3.3>\", 0);");
        }
//выбор режима установки чистая или обновление  <--
///==================================

        Functions.writefile(F, "include(\"pages\");");//подключаем наши страницы

        ///концовка -->
        Functions.writefile(F, "ini_set(\"text_next\", \"<~text_install>\");");
        Functions.writefile(F, "");
        Functions.writefile(F, "viewbox(\"<~ayr>\",");
        Functions.writefile(F, "\"<~ayr1>\",");
        Functions.writefile(F, "\"@info\");");
        Functions.writefile(F, "");
        Functions.writefile(F, "confirm(\"<~confirm>\", \"<~confirm1.1>\", \"@confirm\", \"<~confirm2.1>\", \"<~confirm3.1>\");");
        Functions.writefile(F, "");
        Functions.writefile(F, "ini_set(\"text_next\", \"<~text_next1>\");");
        Functions.writefile(F, "install(\"<~install>\",");
        Functions.writefile(F, "\"<b>\" + ini_get(\"rom_name\") +\"<~install1.1>\"+");
        Functions.writefile(F, "\"<~install1.2>\",");
        Functions.writefile(F, "\"@install\",");
        Functions.writefile(F, "\"<~install2.1>\" + ini_get(\"rom_name\") + \"<~install2.2>\");");
        Functions.writefile(F, "");
        Functions.writefile(F, "ini_set(\"text_next\", \"<~text_finish>\");");
        Functions.writefile(F, "");
        Functions.writefile(F, "checkviewbox(\"<~reboot>\",");
        Functions.writefile(F, "\"<~reboot1.1>\",");
        Functions.writefile(F, "\"@welcome\",");
        Functions.writefile(F, "\"<~reboot2.1>\", \"0\", \"reboot\");");
        Functions.writefile(F, "");
        Functions.writefile(F, "if getvar(\"reboot\")==\"1\" then");
        Functions.writefile(F, "reboot(\"onfinish\");");
        Functions.writefile(F, "endif;");
        //<-- концовка

    }
}