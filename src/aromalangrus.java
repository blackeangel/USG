import javax.swing.table.DefaultTableModel;
import java.io.File;

public class aromalangrus extends Generator {
    public static void ruslang() {
        if (instance.SelectLang.isSelected()) {
            if (!new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "langs").exists()) {
                new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "langs").mkdirs();//создаем папку со всеми подпапками
            }
            String f=dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "ru.lang";
            //начало файла
            Functions.writefile(f, "text_ok=OK");
            Functions.writefile(f, "text_next=Далее");
            Functions.writefile(f, "text_back=Назад");
            Functions.writefile(f, "text_yes=Да");
            Functions.writefile(f, "text_no=Нет");
            Functions.writefile(f, "text_about=О прошивке");
            Functions.writefile(f, "text_calibrating=Калибровка");
            Functions.writefile(f, "text_quit=Покинуть установку");
            Functions.writefile(f, "text_quit_msg=Вы действительно хотите покинуть установку?");
            Functions.writefile(f, "text_install=Установить");
            Functions.writefile(f, "text_next1=Далее");
            Functions.writefile(f, "text_finish=Закончить");
            Functions.writefile(f, "themes=Темы");
            Functions.writefile(f, "themes1.1=Выберите тему");
            Functions.writefile(f, "themes2.1=Без темы");
            Functions.writefile(f, "themes2.2=ICS");
            Functions.writefile(f, "themes2.3=MIUI");
            Functions.writefile(f, "themes2.4=MIUI v4");
            Functions.writefile(f, "themes2.5=Sense");
            Functions.writefile(f, "themes3.1=Стандартная тема AROMA Installer");
            Functions.writefile(f, "themes3.2=Ice Cream Sandvich тема");
            Functions.writefile(f, "themes3.3=MIUI тема");
            Functions.writefile(f, "themes3.4=MIUI v4 тема");
            Functions.writefile(f, "themes3.5=Sense тема");
            Functions.writefile(f, "welcome=Добро пожаловать");
            Functions.writefile(f, "welcome1=Прошивка < b >");
            Functions.writefile(f, "welcome2=</b> v");
            Functions.writefile(f, "welcome3=для");
            Functions.writefile(f, "welcome4=от <b>");
            Functions.writefile(f, "welcome5=</b>.\\");
            Functions.writefile(f, " Дата:");
            Functions.writefile(f, "welcome6=.\\");
            Functions.writefile(f, "Нажмите Далее для продолжения.");
            Functions.writefile(f, "agreebox=Соглашение");
            Functions.writefile(f, "agreebox1.1=Отказ от ответственности");
            Functions.writefile(f, "agreebox2.1=Согласен.");
            Functions.writefile(f, "agreebox3.1=Вы должны согласиться, чтобы продолжить");
            Functions.writefile(f, "type=Выбор телефона");
            Functions.writefile(f, "type1.1=Перед началом установки выберите телефон:");
            Functions.writefile(f, "type2.1=");
            Functions.writefile(f, "type2.2=");
            Functions.writefile(f, "type2.3=");
            Functions.writefile(f, "type3.2=");
            Functions.writefile(f, "changelog=Изменения");
            Functions.writefile(f, "changelog1=Список изменений");
            Functions.writefile(f, "checkbox=Выбор компонентов");
            Functions.writefile(f, "checkbox1.1=Установка дополнительных компонентов");
//начало файла --<
/*тут должно быть генерируемое содержимое
=============================================================
*/
//читаем в массив из таблицы -->
            instance.modslist.getModel();
            int columns=instance.modslist.getModel().getColumnCount();
            int rows=instance.modslist.getModel().getRowCount();
            String[][] fromTable=new String[rows][columns];
            for (int i=0; i < rows; i++) {
                for (int j=0; j < columns; j++) {
                    fromTable[i][j]=String.valueOf(instance.modslist.getValueAt(instance.modslist.convertRowIndexToModel(i), instance.modslist.convertColumnIndexToModel(j)));
                }
            }
            for (int i=0; i < fromTable.length; i++) {
                Functions.writefile(f, "checkbox1." + (i + 2) + "=" + fromTable[i][1]);
            }
//читаем в массив из таблицы <--
/*концовка файла*/
            Functions.writefile(f, "selectbox=Тип установки");
            Functions.writefile(f, "selectbox1.1=Выбирете тип установки:");
            Functions.writefile(f, "selectbox2.1=Обновление");
            Functions.writefile(f, "selectbox2.2=Стандартная");
            Functions.writefile(f, "selectbox2.3=Чистая");
            Functions.writefile(f, "selectbox3.1=Очистка только Cache & Dalvik-cache");
            Functions.writefile(f, "selectbox3.2=Очистка System, Cache, Dalvik-cache");
            Functions.writefile(f, "selectbox3.3=Очистка Data, System, Cache, Dalvik-cache");

            Functions.writefile(f, "demo=Демо");
            Functions.writefile(f, "demo1.1=Что вы хотите посмотреть ?");
            Functions.writefile(f, "demo2.1=Установщик");
            Functions.writefile(f, "demo2.2=Другое");
            Functions.writefile(f, "demo3.1=Устанавливает прошивки, патчи, ядра...");
            Functions.writefile(f, "demo3.2=Системная информация, вычисления и т.д.");
            Functions.writefile(f, "sys.title1=Информация о системе");
            Functions.writefile(f, "sys.title2=Информация о памяти");
            Functions.writefile(f, "sysinfo=Устройство");
            Functions.writefile(f, "sysinfo1=Здесь некоторая информация о вашем устройстве.");
            Functions.writefile(f, "back=Возврат");
            Functions.writefile(f, "back1.1=Вы можете вернуться на один из предыдущих экранов.");
            Functions.writefile(f, "back2.1=Не возвращаться");
            Functions.writefile(f, "back2.2=Вернуться на прошлый экран");
            Functions.writefile(f, "calculations=Вычисления");
            Functions.writefile(f, "iif=Сравнение");
            Functions.writefile(f, "iif1=Верно");
            Functions.writefile(f, "iif2=Неверно");
            Functions.writefile(f, "alert=Установка");
            Functions.writefile(f, "alert1.1=Теперь перейдем к установщику.");
            Functions.writefile(f, "alert2.1=Хорошо");
            Functions.writefile(f, "ayr=Вы готовы ?");
            Functions.writefile(f, "ayr1=Нажмите Установить, чтобы продолжить.");
            Functions.writefile(f, "confirm=Подтверждение");
            Functions.writefile(f, "confirm1.1=Начать прошивку ?");
            Functions.writefile(f, "confirm2.1=Да");
            Functions.writefile(f, "confirm3.1=Нет");
            Functions.writefile(f, "install=Установка");
            Functions.writefile(f, "install1.1=</b> устанавливается.\\");
            Functions.writefile(f, "\\");
            Functions.writefile(f, "install1.2=Пожалуйста, подождите...");
            Functions.writefile(f, "install2.1=Мастер установки успешно установил<b>");
            Functions.writefile(f, "install2.2=</b>.Нажмите Далее, чтобы закончить.");
            Functions.writefile(f, "reboot=Перезагрузка");
            Functions.writefile(f, "reboot1.1=Хотите ли вы перезагрузить устройство после выхода?");
            Functions.writefile(f, "reboot2.1=Перезагрузить устройство");
/*концовка файла*/
        }
    }
}

/*+++++++++++++++++++++++++++++=================================++++++++++++++++++++++++++++++++++++++++++*/
class aromageneratorruslang extends AromaGenerator {

    public static void partOne() {
        String f=aromafolder + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "ru.lang";

        Functions.writefile(f, "text_ok=OK");
        Functions.writefile(f, "text_next=Далее");
        Functions.writefile(f, "text_back=Назад");
        Functions.writefile(f, "text_yes=Да");
        Functions.writefile(f, "text_no=Нет");
        Functions.writefile(f, "text_about=О прошивке");
        Functions.writefile(f, "text_calibrating=Калибровка");
        Functions.writefile(f, "text_quit=Покинуть установку");
        Functions.writefile(f, "text_quit_msg=Вы действительно хотите покинуть установку?");
        Functions.writefile(f, "text_install=Установить");
        Functions.writefile(f, "text_next1=Далее");
        Functions.writefile(f, "text_finish=Закончить");
        Functions.writefile(f, "themes=Темы");
        Functions.writefile(f, "themes1.1=Выберите тему");
        Functions.writefile(f, "themes2.1=Без темы");
        Functions.writefile(f, "themes2.2=ICS");
        Functions.writefile(f, "themes2.3=MIUI");
        Functions.writefile(f, "themes2.4=MIUI v4");
        Functions.writefile(f, "themes2.5=Sense");
        Functions.writefile(f, "themes3.1=Стандартная тема AROMA Installer");
        Functions.writefile(f, "themes3.2=Ice Cream Sandvich тема");
        Functions.writefile(f, "themes3.3=MIUI тема");
        Functions.writefile(f, "themes3.4=MIUI v4 тема");
        Functions.writefile(f, "themes3.5=Sense тема");
        Functions.writefile(f, "welcome=Добро пожаловать");
        Functions.writefile(f, "welcome1=Прошивка <b>");
        Functions.writefile(f, "welcome2=</b> v");
        Functions.writefile(f, "welcome3=для");
        Functions.writefile(f, "welcome4=от <b>");
        Functions.writefile(f, "welcome5=</b>.\\");
        Functions.writefile(f, " Дата:");
        Functions.writefile(f, "welcome6=.\\");
        Functions.writefile(f, "Нажмите Далее для продолжения.");
        Functions.writefile(f, "agreebox=Соглашение");
        Functions.writefile(f, "agreebox1.1=Отказ от ответственности");
        Functions.writefile(f, "agreebox2.1=Согласен.");
        Functions.writefile(f, "agreebox3.1=Вы должны согласиться, чтобы продолжить");
        Functions.writefile(f, "type=Выбор телефона");
        Functions.writefile(f, "type1.1=Перед началом установки выберите телефон:");
        Functions.writefile(f, "type2.1=");
        Functions.writefile(f, "type2.2=");
        Functions.writefile(f, "type2.3=");
        Functions.writefile(f, "type3.2=");
        Functions.writefile(f, "changelog=Изменения");
        Functions.writefile(f, "changelog1=Список изменений");
    }

    public static void partTwo() {
        String f=aromafolder + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "ru.lang";
        Functions.writefile(f, "demo=Демо");
        Functions.writefile(f, "demo1.1=Что вы хотите посмотреть ?");
        Functions.writefile(f, "demo2.1=Установщик");
        Functions.writefile(f, "demo2.2=Другое");
        Functions.writefile(f, "demo3.1=Устанавливает прошивки, патчи, ядра...");
        Functions.writefile(f, "demo3.2=Системная информация, вычисления и т.д.");
        Functions.writefile(f, "sys.title1=Информация о системе");
        Functions.writefile(f, "sys.title2=Информация о памяти");
        Functions.writefile(f, "sysinfo=Устройство");
        Functions.writefile(f, "sysinfo1=Здесь некоторая информация о вашем устройстве.");
        Functions.writefile(f, "back=Возврат");
        Functions.writefile(f, "back1.1=Вы можете вернуться на один из предыдущих экранов.");
        Functions.writefile(f, "back2.1=Не возвращаться");
        Functions.writefile(f, "back2.2=Вернуться на прошлый экран");
        Functions.writefile(f, "calculations=Вычисления");
        Functions.writefile(f, "iif=Сравнение");
        Functions.writefile(f, "iif1=Верно");
        Functions.writefile(f, "iif2=Неверно");
        Functions.writefile(f, "alert=Установка");
        Functions.writefile(f, "alert1.1=Теперь перейдем к установщику.");
        Functions.writefile(f, "alert2.1=Хорошо");
        Functions.writefile(f, "ayr=Вы готовы ?");
        Functions.writefile(f, "ayr1=Нажмите Установить, чтобы продолжить.");
        Functions.writefile(f, "confirm=Подтверждение");
        Functions.writefile(f, "confirm1.1=Начать прошивку ?");
        Functions.writefile(f, "confirm2.1=Да");
        Functions.writefile(f, "confirm3.1=Нет");
        Functions.writefile(f, "install=Установка");
		Functions.writefile(f, "install1.1=</b> устанавливается.\\");
        Functions.writefile(f, "\\");
        Functions.writefile(f, "install1.2=Пожалуйста, подождите...");
        Functions.writefile(f, "install2.1=Мастер установки успешно установил <b>");
        Functions.writefile(f, "install2.2=</b>.Нажмите Далее, чтобы закончить.");
        Functions.writefile(f, "reboot=Перезагрузка");
        Functions.writefile(f, "reboot1.1=Хотите ли вы перезагрузить устройство после выхода?");
        Functions.writefile(f, "reboot2.1=Перезагрузить устройство");
/*концовка файла*/
    }
}

