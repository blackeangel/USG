import java.io.File;
import java.util.Objects;

public class aromachengelog extends Generator {
    public static void changelog() {
        if (instance.chengeLogCheckBox.isSelected()) {
            new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs").mkdirs();
            if (instance.SelectLang.isSelected()) {
                String fr = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "ru.txt";
                Functions.writefile(fr, instance.ChengelogTextArea.getText());
                String fe = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "en.txt";
                Functions.writefile(fe, instance.ChengelogTextArea.getText());
            } else {
                if (Objects.equals(LANGUAGE, "русский")) {
                    String fr = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "ru.txt";
                    Functions.writefile(fr, instance.ChengelogTextArea.getText());
                } else {
                    String fe = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "en.txt";
                    Functions.writefile(fe, instance.ChengelogTextArea.getText());
                }
            }
        }
    }
}
/*+++++++++++++++++=========================================+++++++++++++++++++++++++++++++++*/
class aromageneratorchangelog extends AromaGenerator {
	public static void AromaGeneratorChangeLog() {
        if (!Objects.equals(instance1.ChengelogTextArea.getText(), "")) {
            new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs").mkdirs();
            if (instance1.SelectLang.isSelected()) {
                String fr = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "ru.txt";
                Functions.writefile(fr, instance1.ChengelogTextArea.getText());
                String fe = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "en.txt";
                Functions.writefile(fe, instance1.ChengelogTextArea.getText());
            } else {
                if (Objects.equals(Generator.LANGUAGE, "русский")) {
                    String fr = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "ru.txt";
                    Functions.writefile(fr, instance1.ChengelogTextArea.getText());
                } else {
                    String fe = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "changelogs" + System.getProperty("file.separator") + "en.txt";
                    Functions.writefile(fe, instance1.ChengelogTextArea.getText());
                }
            }
        }
    }
}