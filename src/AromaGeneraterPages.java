import java.io.File;
import java.util.Objects;

public class AromaGeneraterPages extends AromaGenerator {
    public static void main() {
        String F = aromafolder + System.getProperty("file.separator") + "pages";
        String D = aromafolder + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "ru.lang";
        String E = aromafolder + System.getProperty("file.separator") + "langs" + System.getProperty("file.separator") + "en.lang";
        if (instance1.SelectLang.isSelected()) {
            aromageneratorruslang.partOne();
            aromageneratorlangeng.partOne();
        } else {
            if (Objects.equals(Generator.LANGUAGE, "русский")) {
                aromageneratorruslang.partOne();
            } else {
                aromageneratorlangeng.partOne();
            }
        }
        //page1. --->
        instance1.table2.getModel();//получили содержимое модели таблицы
        int rowstable2 = instance1.table2.getModel().getRowCount();//кол-во строк
        int columnstable2 = instance1.table2.getModel().getColumnCount();//кол-во столбцов
        instance1.table9.getModel();//получили содержимое модели таблицы
        int rowstable9 = instance1.table9.getModel().getRowCount();//кол-во строк
        int columnstable9 = instance1.table9.getModel().getColumnCount();//кол-во столбцов
        if (!Objects.equals(instance1.textField1.getText(), "") || !Objects.equals(instance1.textField8.getText(), "") || !Objects.equals(instance1.textField9.getText(), "") || !Objects.equals(instance1.textField13.getText(), "")) {
            if (rowstable2 > 0 || rowstable9 > 0) {
                Functions.writefile(F, "form(");
                Functions.writefile(F, "\"<~page1.title>\",");//название окна
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page1.title=" + instance1.textField1.getText());
                    Functions.writefile(E, "page1.title=" + instance1.textField1.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page1.title=" + instance1.textField1.getText());
                    } else {
                        Functions.writefile(E, "page1.title=" + instance1.textField1.getText());
                    }
                }
                Functions.writefile(F, "\"<~page1.optitle>\",");//описание окна
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page1.optitle=" + instance1.textField13.getText());
                    Functions.writefile(E, "page1.optitle=" + instance1.textField13.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page1.optitle=" + instance1.textField13.getText());
                    } else {
                        Functions.writefile(E, "page1.optitle=" + instance1.textField13.getText());
                    }
                }
                Functions.writefile(F, "\"@default\",");
                Functions.writefile(F, "\"page1.prop\",");

                if (rowstable2 > 0) {
                    //заполняем массив -->
                    String[][] arraytable2 = new String[rowstable2][columnstable2];//масив таблицы
                    for (int i = 0; i < rowstable2; i++) {
                        for (int j = 0; j < columnstable2; j++) {
                            arraytable2[i][j] = String.valueOf(instance1.table2.getValueAt(instance1.table2.convertRowIndexToModel(i), instance1.table2.convertColumnIndexToModel(j)));
                        }
                    }
                    //<-- заполняем массив
                    //копируем только нужные нам файлы, что в таблице -->
                    for (int i = 0; i < arraytable2.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable2[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable2[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable2[i][0]);//копируем моды в папку с именем юзера
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable2[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable2[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable2[i][0]);
                            }
                        }
                    }
                    //копируем только нужные нам файлы, что в таблице <--
                    Functions.writefile(F, "\"group1\",\"<~page1.group1>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page1.group1=" + instance1.textField8.getText());
                        Functions.writefile(E, "page1.group1=" + instance1.textField8.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page1.group1=" + instance1.textField8.getText());
                        } else {
                            Functions.writefile(E, "page1.group1=" + instance1.textField8.getText());
                        }
                    }
                    for (int i = 0; i < arraytable2.length; i++) {
                        if (i == arraytable2.length - 1) {
                            if (Objects.equals(arraytable2[i][3], "1")) {
                                Functions.writefile(F, "\"page1.checkbox1." + (i + 1) + "\", \"<~page1.checkbox1." + (i + 1) + ">\", \"<~page1.optcheck1." + (i + 1) + ">\", \"check\"");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable2[i][0].toString(), arraytable2[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page1.selectbox1." + (i + 1) + "\", \"<~page1.selectbox1." + (i + 1) + ">\", \"<~page1.optselect1." + (i + 1) + ">\", \"select\"");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable2[i][0].toString(), arraytable2[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable2[i][3], "1")) {
                                Functions.writefile(F, "\"page1.checkbox1." + (i + 1) + "\", \"<~page1.checkbox1." + (i + 1) + ">\", \"<~page1.optcheck1." + (i + 1) + ">\", \"check\",");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.checkbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optcheck1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable2[i][0].toString(), arraytable2[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page1.selectbox1." + (i + 1) + "\", \"<~page1.selectbox1." + (i + 1) + ">\", \"<~page1.optselect1." + (i + 1) + ">\", \"select\",");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.selectbox1." + (i + 1) + "=" + arraytable2[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optselect1." + (i + 1) + "=" + arraytable2[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable2[i][0].toString(), arraytable2[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                if (rowstable9 > 0) {
                    //заполняем массив -->
                    String[][] arraytable9 = new String[rowstable9][columnstable9];//масив таблицы
                    for (int i = 0; i < rowstable9; i++) {
                        for (int j = 0; j < columnstable9; j++) {
                            arraytable9[i][j] = String.valueOf(instance1.table9.getValueAt(instance1.table9.convertRowIndexToModel(i), instance1.table9.convertColumnIndexToModel(j)));
                        }
                    }
                    //<-- заполняем массив
                    //копируем только нужные нам файлы, что в таблице -->
                    for (int i = 0; i < arraytable9.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable9[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable9[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable9[i][0]);//копируем моды в папку с именем юзера
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable9[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable9[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable9[i][0]);
                            }
                        }
                    }
                    //копируем только нужные нам файлы, что в таблице <--
                    Functions.writefile(F, "\"group2\",\"<~page1.group2>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page1.group1=" + instance1.textField9.getText());
                        Functions.writefile(E, "page1.group1=" + instance1.textField9.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page1.group2=" + instance1.textField8.getText());
                        } else {
                            Functions.writefile(E, "page1.group2=" + instance1.textField9.getText());
                        }
                    }
                    for (int i = 0; i < arraytable9.length; i++) {
                        if (i == arraytable9.length - 1) {
                            if (Objects.equals(arraytable9[i][3], "1")) {
                                Functions.writefile(F, "\"page1.checkbox2." + (i + 1) + "\", \"<~page1.checkbox2." + (i + 1) + ">\", \"<~page1.optcheck2." + (i + 1) + ">\", \"check\"");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable9[i][0].toString(), arraytable9[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page1.selectbox2." + (i + 1) + "\", \"<~page1.selectbox2." + (i + 1) + ">\", \"<~page1.optselect2." + (i + 1) + ">\", \"select\"");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable9[i][0].toString(), arraytable9[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable9[i][3], "1")) {
                                Functions.writefile(F, "\"page1.checkbox2." + (i + 1) + "\", \"<~page1.checkbox2." + (i + 1) + ">\", \"<~page1.optcheck2." + (i + 1) + ">\", \"check\",");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.checkbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optcheck2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable9[i][0].toString(), arraytable9[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page1.selectbox2." + (i + 1) + "\", \"<~page1.selectbox2." + (i + 1) + ">\", \"<~page1.optselect2." + (i + 1) + ">\", \"select\",");
                                //языки -->
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(D, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    Functions.writefile(E, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                    Functions.writefile(E, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(D, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page1.selectbox2." + (i + 1) + "=" + arraytable9[i][1].replace("null", ""));
                                        Functions.writefile(E, "page1.optselect2." + (i + 1) + "=" + arraytable9[i][2].replace("null", ""));
                                    }
                                }
                                //языки <--
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page1.prop\", \"page1.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable9[i][0].toString(), arraytable9[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                Functions.writefile(F, ");");
            }
        }
//<-- page1
//page2 --->
        instance1.table3.getModel();//получили содержимое модели таблицы
        int rowstable3 = instance1.table3.getModel().getRowCount();//кол-во строк
        int columnstable3 = instance1.table3.getModel().getColumnCount();//кол-во столбцов
        instance1.table8.getModel();//получили содержимое модели таблицы
        int rowstable8 = instance1.table8.getModel().getRowCount();//кол-во строк
        int columnstable8 = instance1.table8.getModel().getColumnCount();//кол-во столбцов
        if (!Objects.equals(instance1.textField2.getText(), "") || !Objects.equals(instance1.textField6.getText(), "") || !Objects.equals(instance1.textField7.getText(), "") || !Objects.equals(instance1.textField14.getText(), "")) {
            if (rowstable3 > 0 || rowstable8 > 0) {
                Functions.writefile(F, "form(");
                Functions.writefile(F, "\"<~page2.title>\",");
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page2.title=" + instance1.textField2.getText());
                    Functions.writefile(E, "page2.title=" + instance1.textField2.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page2.title=" + instance1.textField2.getText());
                    } else {
                        Functions.writefile(E, "page2.title=" + instance1.textField2.getText());
                    }
                }
                Functions.writefile(F, "\"<~page2.optitle>\",");
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page2.optitle=" + instance1.textField14.getText());
                    Functions.writefile(E, "page2.optitle=" + instance1.textField14.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page2.optitle=" + instance1.textField14.getText());
                    } else {
                        Functions.writefile(E, "page2.optitle=" + instance1.textField14.getText());
                    }
                }
                Functions.writefile(F, "\"@default\",");
                Functions.writefile(F, "\"page2.prop\",");
                if (rowstable3 > 0) {
                    String[][] arraytable3 = new String[rowstable3][columnstable3];
                    for (int i = 0; i < rowstable3; i++) {
                        for (int j = 0; j < columnstable3; j++) {
                            arraytable3[i][j] = String.valueOf(instance1.table3.getValueAt(instance1.table3.convertRowIndexToModel(i), instance1.table3.convertColumnIndexToModel(j)));
                        }
                    }
                    for (int i = 0; i < arraytable3.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable3[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable3[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable3[i][0]);//копируем моды в папку с именем юзера
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable3[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable3[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable3[i][0]);
                            }
                        }
                    }
                    Functions.writefile(F, "\"group1\",\"<~page2.group1>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page2.group1=" + instance1.textField6.getText());
                        Functions.writefile(E, "page2.group1=" + instance1.textField6.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page2.group1=" + instance1.textField6.getText());
                        } else {
                            Functions.writefile(E, "page2.group1=" + instance1.textField6.getText());
                        }
                    }
                    for (int i = 0; i < arraytable3.length; i++) {
                        if (i == arraytable3.length - 1) {
                            if (Objects.equals(arraytable3[i][3], "1")) {
                                Functions.writefile(F, "\"page2.checkbox1." + (i + 1) + "\", \"<~page2.checkbox1." + (i + 1) + ">\", \"<~page2.optcheck1." + (i + 1) + ">\", \"check\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable3[i][0].toString(), arraytable3[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page2.selectbox1." + (i + 1) + "\", \"<~page2.selectbox1." + (i + 1) + ">\", \"<~page2.optselect1." + (i + 1) + ">\", \"select\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable3[i][0].toString(), arraytable3[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable3[i][3], "1")) {
                                Functions.writefile(F, "\"page2.checkbox1." + (i + 1) + "\", \"<~page2.checkbox1." + (i + 1) + ">\", \"<~page2.optcheck1." + (i + 1) + ">\", \"check\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.checkbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optcheck1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable3[i][0].toString(), arraytable3[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page2.selectbox1." + (i + 1) + "\", \"<~page2.selectbox1." + (i + 1) + ">\", \"<~page2.optselect1." + (i + 1) + ">\", \"select\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.selectbox1." + (i + 1) + "=" + arraytable3[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optselect1." + (i + 1) + "=" + arraytable3[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable3[i][0].toString(), arraytable3[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                if (rowstable8 > 0) {
                    String[][] arraytable8 = new String[rowstable8][columnstable8];
                    for (int i = 0; i < rowstable8; i++) {
                        for (int j = 0; j < columnstable8; j++) {
                            arraytable8[i][j] = String.valueOf(instance1.table8.getValueAt(instance1.table8.convertRowIndexToModel(i), instance1.table8.convertColumnIndexToModel(j)));
                        }
                    }
                    for (int i = 0; i < arraytable8.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable8[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable8[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable8[i][0]);//копируем моды в папку с именем юзера
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable8[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable8[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable8[i][0]);
                            }
                        }
                    }
                    Functions.writefile(F, "\"group2\",\"<~page2.group2>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page2.group1=" + instance1.textField7.getText());
                        Functions.writefile(E, "page2.group1=" + instance1.textField7.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page2.group2=" + instance1.textField6.getText());
                        } else {
                            Functions.writefile(E, "page2.group2=" + instance1.textField7.getText());
                        }
                    }
                    for (int i = 0; i < arraytable8.length; i++) {
                        if (i == arraytable8.length - 1) {
                            if (Objects.equals(arraytable8[i][3], "1")) {
                                Functions.writefile(F, "\"page2.checkbox2." + (i + 1) + "\", \"<~page2.checkbox2." + (i + 1) + ">\", \"<~page2.optcheck2." + (i + 1) + ">\", \"check\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable8[i][0].toString(), arraytable8[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page2.selectbox2." + (i + 1) + "\", \"<~page2.selectbox2." + (i + 1) + ">\", \"<~page2.optselect2." + (i + 1) + ">\", \"select\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable8[i][0].toString(), arraytable8[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable8[i][3], "1")) {
                                Functions.writefile(F, "\"page2.checkbox2." + (i + 1) + "\", \"<~page2.checkbox2." + (i + 1) + ">\", \"<~page2.optcheck2." + (i + 1) + ">\", \"check\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.checkbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optcheck2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable8[i][0].toString(), arraytable8[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page2.selectbox2." + (i + 1) + "\", \"<~page2.selectbox2." + (i + 1) + ">\", \"<~page2.optselect2." + (i + 1) + ">\", \"select\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(D, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    Functions.writefile(E, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                    Functions.writefile(E, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(D, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page2.selectbox2." + (i + 1) + "=" + arraytable8[i][1].replace("null", ""));
                                        Functions.writefile(E, "page2.optselect2." + (i + 1) + "=" + arraytable8[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page2.prop\", \"page2.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable8[i][0].toString(), arraytable8[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                Functions.writefile(F, ");");
            }
        }
//<-- page2
//page3 --->
        instance1.table4.getModel();//получили содержимое модели таблицы
        int rowstable4 = instance1.table4.getModel().getRowCount();//кол-во строк
        int columnstable4 = instance1.table4.getModel().getColumnCount();//кол-во столбцов
        instance1.table7.getModel();//получили содержимое модели таблицы
        int rowstable7 = instance1.table7.getModel().getRowCount();//кол-во строк
        int columnstable7 = instance1.table7.getModel().getColumnCount();//кол-во столбцов
        if (!Objects.equals(instance1.textField3.getText(), "") || !Objects.equals(instance1.textField4.getText(), "") || !Objects.equals(instance1.textField5.getText(), "") || !Objects.equals(instance1.textField15.getText(), "")) {
            if (rowstable4 > 0 || rowstable7 > 0) {
                Functions.writefile(F, "form(");
                Functions.writefile(F, "\"<~page3.title>\",");
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page3.title=" + instance1.textField3.getText());
                    Functions.writefile(E, "page3.title=" + instance1.textField3.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page3.title=" + instance1.textField3.getText());
                    } else {
                        Functions.writefile(E, "page3.title=" + instance1.textField3.getText());
                    }
                }
                Functions.writefile(F, "\"<~page3.optitle>\",");
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page3.optitle=" + instance1.textField15.getText());
                    Functions.writefile(E, "page3.optitle=" + instance1.textField15.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page3.optitle=" + instance1.textField15.getText());
                    } else {
                        Functions.writefile(E, "page3.optitle=" + instance1.textField15.getText());
                    }
                }
                Functions.writefile(F, "\"@default\",");
                Functions.writefile(F, "\"page3.prop\",");
                if (rowstable4 > 0) {
                    String[][] arraytable4 = new String[rowstable4][columnstable4];
                    for (int i = 0; i < rowstable4; i++) {
                        for (int j = 0; j < columnstable4; j++) {
                            arraytable4[i][j] = String.valueOf(instance1.table4.getValueAt(instance1.table4.convertRowIndexToModel(i), instance1.table4.convertColumnIndexToModel(j)));
                        }
                    }
                    for (int i = 0; i < arraytable4.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable4[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable4[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable4[i][0]);//копируем моды в папку с именем юзера
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable4[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable4[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable4[i][0]);
                            }
                        }
                    }
                    Functions.writefile(F, "\"group1\",\"<~page3.group1>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page3.group1=" + instance1.textField4.getText());
                        Functions.writefile(E, "page3.group1=" + instance1.textField4.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page3.group1=" + instance1.textField4.getText());
                        } else {
                            Functions.writefile(E, "page3.group1=" + instance1.textField4.getText());
                        }
                    }
                    for (int i = 0; i < arraytable4.length; i++) {
                        if (i == arraytable4.length - 1) {
                            if (Objects.equals(arraytable4[i][3], "1")) {
                                Functions.writefile(F, "\"page3.checkbox1." + (i + 1) + "\", \"<~page3.checkbox1." + (i + 1) + ">\", \"<~page3.optcheck1." + (i + 1) + ">\", \"check\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable4[i][0].toString(), arraytable4[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page3.selectbox1." + (i + 1) + "\", \"<~page3.selectbox1." + (i + 1) + ">\", \"<~page3.optselect1." + (i + 1) + ">\", \"select\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable4[i][0].toString(), arraytable4[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable4[i][3], "1")) {
                                Functions.writefile(F, "\"page3.checkbox1." + (i + 1) + "\", \"<~page3.checkbox1." + (i + 1) + ">\", \"<~page3.optcheck1." + (i + 1) + ">\", \"check\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.checkbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optcheck1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable4[i][0].toString(), arraytable4[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page3.selectbox1." + (i + 1) + "\", \"<~page3.selectbox1." + (i + 1) + ">\", \"<~page3.optselect1." + (i + 1) + ">\", \"select\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.selectbox1." + (i + 1) + "=" + arraytable4[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optselect1." + (i + 1) + "=" + arraytable4[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable4[i][0].toString(), arraytable4[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                if (rowstable7 > 0) {
                    String[][] arraytable7 = new String[rowstable7][columnstable7];
                    for (int i = 0; i < rowstable7; i++) {
                        for (int j = 0; j < columnstable7; j++) {
                            arraytable7[i][j] = String.valueOf(instance1.table7.getValueAt(instance1.table7.convertRowIndexToModel(i), instance1.table7.convertColumnIndexToModel(j)));
                        }
                    }
                    for (int i = 0; i < arraytable7.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable7[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable7[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable7[i][0]);
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable7[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable7[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable7[i][0]);
                            }
                        }
                    }
                    Functions.writefile(F, "\"group2\",\"<~page3.group2>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page3.group1=" + instance1.textField5.getText());
                        Functions.writefile(E, "page3.group1=" + instance1.textField5.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page3.group2=" + instance1.textField4.getText());
                        } else {
                            Functions.writefile(E, "page3.group2=" + instance1.textField5.getText());
                        }
                    }
                    for (int i = 0; i < arraytable7.length; i++) {
                        if (i == arraytable7.length - 1) {
                            if (Objects.equals(arraytable7[i][3], "1")) {
                                Functions.writefile(F, "\"page3.checkbox2." + (i + 1) + "\", \"<~page3.checkbox2." + (i + 1) + ">\", \"<~page3.optcheck2." + (i + 1) + ">\", \"check\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable7[i][0].toString(), arraytable7[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page3.selectbox2." + (i + 1) + "\", \"<~page3.selectbox2." + (i + 1) + ">\", \"<~page3.optselect2." + (i + 1) + ">\", \"select\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable7[i][0].toString(), arraytable7[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable7[i][3], "1")) {
                                Functions.writefile(F, "\"page3.checkbox2." + (i + 1) + "\", \"<~page3.checkbox2." + (i + 1) + ">\", \"<~page3.optcheck2." + (i + 1) + ">\", \"check\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.checkbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optcheck2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable7[i][0].toString(), arraytable7[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page3.selectbox2." + (i + 1) + "\", \"<~page3.selectbox2." + (i + 1) + ">\", \"<~page3.optselect2." + (i + 1) + ">\", \"select\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(D, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    Functions.writefile(E, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                    Functions.writefile(E, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(D, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page3.selectbox2." + (i + 1) + "=" + arraytable7[i][1].replace("null", ""));
                                        Functions.writefile(E, "page3.optselect2." + (i + 1) + "=" + arraytable7[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page3.prop\", \"page3.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable7[i][0].toString(), arraytable7[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                Functions.writefile(F, ");");
            }
        }
//<-- page3
//page4 --->
        instance1.table5.getModel();//получили содержимое модели таблицы
        int rowstable5 = instance1.table5.getModel().getRowCount();//кол-во строк
        int columnstable5 = instance1.table5.getModel().getColumnCount();//кол-во столбцов
        instance1.table6.getModel();//получили содержимое модели таблицы
        int rowstable6 = instance1.table6.getModel().getRowCount();//кол-во строк
        int columnstable6 = instance1.table6.getModel().getColumnCount();//кол-во столбцов
        if (!Objects.equals(instance1.textField12.getText(), "") || !Objects.equals(instance1.textField10.getText(), "") || !Objects.equals(instance1.textField11.getText(), "") || !Objects.equals(instance1.textField16.getText(), "")) {
            if (rowstable5 > 0 || rowstable6 > 0) {
                Functions.writefile(F, "form(");
                Functions.writefile(F, "\"<~page4.title>\",");
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page4.title=" + instance1.textField12.getText());
                    Functions.writefile(E, "page4.title=" + instance1.textField12.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page4.title=" + instance1.textField12.getText());
                    } else {
                        Functions.writefile(E, "page4.title=" + instance1.textField12.getText());
                    }
                }
                Functions.writefile(F, "\"<~page4.optitle>\",");
                if (instance1.SelectLang.isSelected()) {
                    Functions.writefile(D, "page4.optitle=" + instance1.textField16.getText());
                    Functions.writefile(E, "page4.optitle=" + instance1.textField16.getText());
                } else {
                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                        Functions.writefile(D, "page4.optitle=" + instance1.textField16.getText());
                    } else {
                        Functions.writefile(E, "page4.optitle=" + instance1.textField16.getText());
                    }
                }
                Functions.writefile(F, "\"@default\",");
                Functions.writefile(F, "\"page4.prop\",");
                if (rowstable5 > 0) {
                    String[][] arraytable5 = new String[rowstable5][columnstable5];
                    for (int i = 0; i < rowstable5; i++) {
                        for (int j = 0; j < columnstable5; j++) {
                            arraytable5[i][j] = String.valueOf(instance1.table5.getValueAt(instance1.table5.convertRowIndexToModel(i), instance1.table5.convertColumnIndexToModel(j)));
                        }
                    }
                    for (int i = 0; i < arraytable5.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable5[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable5[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable5[i][0]);//копируем моды в папку с именем юзера
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable5[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable5[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable5[i][0]);
                            }
                        }
                    }
                    Functions.writefile(F, "\"group1\",\"<~page4.group1>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page4.group1=" + instance1.textField10.getText());
                        Functions.writefile(E, "page4.group1=" + instance1.textField10.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page4.group1=" + instance1.textField10.getText());
                        } else {
                            Functions.writefile(E, "page4.group1=" + instance1.textField10.getText());
                        }
                    }
                    for (int i = 0; i < arraytable5.length; i++) {
                        if (i == arraytable5.length - 1) {
                            if (Objects.equals(arraytable5[i][3], "1")) {
                                Functions.writefile(F, "\"page4.checkbox1." + (i + 1) + "\", \"<~page4.checkbox1." + (i + 1) + ">\", \"<~page4.optcheck1." + (i + 1) + ">\", \"check\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable5[i][0].toString(), arraytable5[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page4.selectbox1." + (i + 1) + "\", \"<~page4.selectbox1." + (i + 1) + ">\", \"<~page4.optselect1." + (i + 1) + ">\", \"select\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable5[i][0].toString(), arraytable5[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable5[i][3], "1")) {
                                Functions.writefile(F, "\"page4.checkbox1." + (i + 1) + "\", \"<~page4.checkbox1." + (i + 1) + ">\", \"<~page4.optcheck1." + (i + 1) + ">\", \"check\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.checkbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optcheck1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.checkbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable5[i][0].toString(), arraytable5[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page4.selectbox1." + (i + 1) + "\", \"<~page4.selectbox1." + (i + 1) + ">\", \"<~page4.optselect1." + (i + 1) + ">\", \"select\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.selectbox1." + (i + 1) + "=" + arraytable5[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optselect1." + (i + 1) + "=" + arraytable5[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.selectbox1." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable5[i][0].toString(), arraytable5[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                if (rowstable6 > 0) {
                    String[][] arraytable6 = new String[rowstable6][columnstable6];
                    for (int i = 0; i < rowstable6; i++) {
                        for (int j = 0; j < columnstable6; j++) {
                            arraytable6[i][j] = String.valueOf(instance1.table6.getValueAt(instance1.table6.convertRowIndexToModel(i), instance1.table6.convertColumnIndexToModel(j)));
                        }
                    }
                    for (int i = 0; i < arraytable6.length; i++) {
                        if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable6[i][0]).isFile()) {
                            Functions.copyFile(Generator.aromamods + System.getProperty("file.separator") + arraytable6[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable6[i][0]);
                        } else {
                            if (new File(Generator.aromamods + System.getProperty("file.separator") + arraytable6[i][0]).isDirectory()) {
                                Functions.copyDir(Generator.aromamods + System.getProperty("file.separator") + arraytable6[i][0], Generator.modDir + System.getProperty("file.separator") + instance1.foldermods.getText() + System.getProperty("file.separator") + arraytable6[i][0]);
                            }
                        }
                    }
                    Functions.writefile(F, "\"group2\",\"<~page4.group2>\",\"\",\"group\",");
                    if (instance1.SelectLang.isSelected()) {
                        Functions.writefile(D, "page4.group1=" + instance1.textField11.getText());
                        Functions.writefile(E, "page4.group1=" + instance1.textField11.getText());
                    } else {
                        if (Objects.equals(Generator.LANGUAGE, "русский")) {
                            Functions.writefile(D, "page4.group2=" + instance1.textField10.getText());
                        } else {
                            Functions.writefile(E, "page4.group2=" + instance1.textField11.getText());
                        }
                    }
                    for (int i = 0; i < arraytable6.length; i++) {
                        if (i == arraytable6.length - 1) {
                            if (Objects.equals(arraytable6[i][3], "1")) {
                                Functions.writefile(F, "\"page4.checkbox2." + (i + 1) + "\", \"<~page4.checkbox2." + (i + 1) + ">\", \"<~page4.optcheck2." + (i + 1) + ">\", \"check\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable6[i][0].toString(), arraytable6[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page4.selectbox2." + (i + 1) + "\", \"<~page4.selectbox2." + (i + 1) + ">\", \"<~page4.optselect2." + (i + 1) + ">\", \"select\"");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable6[i][0].toString(), arraytable6[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        } else {
                            if (Objects.equals(arraytable6[i][3], "1")) {
                                Functions.writefile(F, "\"page4.checkbox2." + (i + 1) + "\", \"<~page4.checkbox2." + (i + 1) + ">\", \"<~page4.optcheck2." + (i + 1) + ">\", \"check\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.checkbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optcheck2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.checkbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable6[i][0].toString(), arraytable6[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            } else {
                                Functions.writefile(F, "\"page4.selectbox2." + (i + 1) + "\", \"<~page4.selectbox2." + (i + 1) + ">\", \"<~page4.optselect2." + (i + 1) + ">\", \"select\",");
                                if (instance1.SelectLang.isSelected()) {
                                    Functions.writefile(D, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(D, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    Functions.writefile(E, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                    Functions.writefile(E, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                } else {
                                    if (Objects.equals(Generator.LANGUAGE, "русский")) {
                                        Functions.writefile(D, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(D, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    } else {
                                        Functions.writefile(E, "page4.selectbox2." + (i + 1) + "=" + arraytable6[i][1].replace("null", ""));
                                        Functions.writefile(E, "page4.optselect2." + (i + 1) + "=" + arraytable6[i][2].replace("null", ""));
                                    }
                                }
                                Functions.writefile(Generator.UpdaterScript, "");
                                Functions.writefile(Generator.UpdaterScript, "if file_getprop(\"/tmp/aroma/page4.prop\", \"page4.selectbox2." + (i + 1) + "\") == \"1\" then");
                                Functions.generFiles(Generator.UpdaterScript, arraytable6[i][0].toString(), arraytable6[i][1].toString(), instance1.foldermods.getText());
                                Functions.writefile(Generator.UpdaterScript, "endif;");
                            }
                        }
                    }
                }
                Functions.writefile(F, ");");
            }
        }
//<-- page4
        if (instance1.SelectLang.isSelected()) {
            aromageneratorruslang.partTwo();
            aromageneratorlangeng.partTwo();
        } else {
            if (Objects.equals(Generator.LANGUAGE, "русский")) {
                aromageneratorruslang.partTwo();
            } else {
                aromageneratorlangeng.partTwo();
            }
        }
    }
}