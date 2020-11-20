import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shlyapin_pa on 08.06.17.
 */
public class HelpRus extends Generator {
    public JTextArea textArea1;
    public JPanel panel1;
    public JButton closeButton;

    public HelpRus() {
        $$$setupUI$$$();
        setContentPane(panel1);
        setVisible(true);//отображать прогу
        setSize(750, 400);//размер окна проги
        setResizable(false);//запретить изменять размер окна
        //setAlwaysOnTop(true);//поверхб окон
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//закрыть при нажатии на крестик
        textArea1.getAutoscrolls();
        textArea1.setEditable(false);
        if (LANGUAGE == "русский") {
            closeButton.setText("Закрыть");
        } else {
            closeButton.setText("Close");
        }
        textArea1.setText(
                "                                  Назначение\n" +

                        "Программа создана для облегчении жизни портировщиков и всех ковырятелей прошивок\n" +
                        "Создает готовый архив .zip для прошивки ВСЕЙ прошивки через кастомное Recovery TWRP\n" +
                        "\n" +
                        "                                  Возможности\n" +
                        "\n" +
                        "1. Создание скрипта на основе system.img (system.new.dat) и boot.img файлов\n" +
                        "2. Создание Unix-Shell скрипта на основе system.img (system.new.dat) и boot.img файлов\n" +
                        "3. Создание скрипта на основе папки system\n" +
                        "4. Конвертирование file_contexts.bin в file_contexts в текстовом виде\n" +
                        "5. Создание карты блоков для процеесоров МТК67хх на основе scatter.txt (для прошивки через FlashTool) файла\n" +
                        "6. Получение file_contexts из boot.img в текстовом виде\n" +
                        "7. Создание скрипта на основе file_contexts (file_contexts.bin) и system_statfile.txt\n" +
                        "\n" +
                        "                                  Использование\n" +
                        "\n" +
                        "                                   ВНИМАНИЕ!\n" +
                        "ПРОГРАММА ДОЛЖНА НАХОДИТЬСЯ НА ДИСКЕ С СИСТЕМОЙ!\n" +
                        "ПУТИ НЕ ДОЛЖНЫ СОДЕРЖАТЬ РУССКИЕ БУКВЫ, ПРОБЕЛЫ, ДЛИНЫЕ ИМЕНА!\n" +
                        "\n" +
                        "1. Создание скрипта на основе system.img (system.new.dat) и boot.img файлов:\n" +
                        "1.1. Рядом с программой кладем system.img (или system.new.dat и system.transfer.list) и boot.img файлов\n" +
                        "1.2. Если необходим SuperSu, BusyBox, GAPPS, Xposed:\n" +
                        "1.2.1. Переименовываем в supersu.zip, busybox.zip, gapps.zip, xposed.zip соответственно\n" +
                        "1.2.2. Копируем их в папку рядом с программой\n" +
                        "Можно копировать по одному архиву, а можно все разом, все зависит от того, что хотите получить.\n" +
                        "BusyBox возможно в двух вариантах: бинарным файлом или же установочным архивом основанном на shell скрипте\n" +
                        "В случае бинарного файла - переименовать в busybox\n" +
                        "1.2.3. Отмечаем галочками нужное нам.\n" +
                        "1.3. В зависимости от того какой скрипт хотим получить выбираем: \"Генерировать\" или \"Генерировать Unix-Shell скрипт\"\n" +
                        "1.4. Ожидать завершения работы программы.\n" +
                        "Готовый архив Flash.zip должен появиться рядом с программой\n" +
                        "\n" +
                        "                            ВНИМАНИЕ!\n" +
                        "В СКРИПТЕ ЕСТЬ ОЧИСТКА РАЗДЕЛА /data БЕЗ data/media (ВНУТРЕННЯЯ ПАМЯТЬ ТЕЛЕФОНА),\n" +
                        "ЕСЛИ ПУТЬ К НЕЙ ОТЛИЧАЕТСЯ - СМЕНИТЕ НА СВОЙ, ЛИБО УДАЛИТЕ ЭТИ СТРОКИ.\n" +
                        "В ПРОТИВНОМ СЛУЧАЕ - ВСЕ ДАННЫЕ БУДУТ ПОТЕРЯНЫ!\n" +
                        "\n" +
                        "2. Создание скрипта на основе папки system и boot.img:\n" +
                        "2.1. Рядом с программой кладем папку system и boot.img\n" +
                        "2.2. Если необходим SuperSu, BusyBox, GAPPS, Xposed:\n" +
                        "2.2.1. Переименовываем в supersu.zip, busybox.zip, gapps.zip, xposed.zip соответственно\n" +
                        "2.2.2. Копируем их в папку рядом с программой\n" +
                        "Можно копировать по одному архиву, а можно все разом, все зависит от того, что хотите получить.\n" +
                        "BusyBox возможно в двух вариантах: бинарным файлом или же установочным архивом основанном на shell скрипте\n" +
                        "В случае бинарного файла - переименовать в busybox\n" +
                        "2.2.3. Отмечаем галочками нужное нам.\n" +
                        "2.3. Выбираем: \"Генерировать скрипт по папке system\"\n" +
                        "2.4. Ожидать завершения работы программы.\n" +
                        "Готовый архив Flash.zip должен появиться рядом с программой\n" +
                        "\n" +
                        "                            ВНИМАНИЕ!" +
                        "В СКРИПТЕ ЕСТЬ ОЧИСТКА РАЗДЕЛА /data БЕЗ data/media (ВНУТРЕННЯЯ ПАМЯТЬ ТЕЛЕФОНА).\n" +
                        "ЕСЛИ ПУТЬ К НЕЙ ОТЛИЧАЕТСЯ - СМЕНИТЕ НА СВОЙ, ЛИБО УДАЛИТЕ ЭТИ СТРОКИ.\n" +
                        "В ПРОТИВНОМ СЛУЧАЕ - ВСЕ ДАННЫЕ БУДУТ ПОТЕРЯНЫ!\n" +
                        "\n" +
                        "3. Карта блоков из MT67xx_Android_scatter:\n" +
                        "3.1. Рядом с программой кладем MT67xx_Android_scatter\n" +
                        "3.2. Выбираем: \"Карта блоков из MT67xx_Android_scatter\"\n" +
                        "3.3. Ожидать завершения работы программы.\n" +
                        "Готовый файл blocks.txt должен появиться рядом с программой\n" +
                        "\n" +
                        "4. Конвертировать file_contexts.bin в file_contexts в текстовом виде:\n" +
                        "4.1. Рядом с программой кладем file_contexts.bin\n" +
                        "4.2. Выбираем: \"Конвертировать file_contexts.bin в file_contexts\"\n" +
                        "4.3. Ожидать завершения работы программы.\n" +
                        "Готовый файл file_contexts должен появиться рядом с программой\n" +
                        "\n" +
                        "5. Получить file_contexts из boot.img в текстовом виде:\n" +
                        "5.1. Рядом с программой кладем boot.img\n" +
                        "5.2. Выбираем: \"Получить file_contexts из boot.img\"\n" +
                        "5.3. Ожидать завершения работы программы.\n" +
                        "Готовый файл file_contexts должен появиться рядом с программой\n" +
                        "\n" +
                        "Самый точный скрипт получается на основе образов прошивок, примерно 85-90% то что запустится прошивка\n" +
                        "Самый неудачный скрипт получается на основе папки прошивки, примерно 30-60% то что запустится прошивка"


        );
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public static void main() {
        new HelpRus();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textArea1 = new JTextArea();
        scrollPane1.setViewportView(textArea1);
        closeButton = new JButton();
        closeButton.setText("Close");
        panel1.add(closeButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
