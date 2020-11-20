import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.util.Vector;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.util.*;
import java.util.List;
import javax.activation.*;
import javax.swing.text.JTextComponent;

public class AromaGenerator extends JFrame {
    protected static AromaGenerator instance1;
    public JPanel panel1;
    public JButton closeButton;
    public JButton Gener;
    public JTable table1;
    public JTabbedPane pages;
    public JButton Exit;
    public JCheckBox datamediacheckBox;
    public JPanel generate;
    public JPanel tools;
    public JPanel options;
    public JLabel toolbarLabel;
    public JTextField textField2;
    public JTextArea ChengelogTextArea;
    public JFormattedTextField nameFormattedTextField;
    public JFormattedTextField versionFormattedTextField;
    public JFormattedTextField authorFormattedTextField;
    public JFormattedTextField deviceFormattedTextField;
    public JFormattedTextField dateFormattedTextField;
    public JPanel General;
    public JPanel changelogs;
    public JPanel page1;
    public JPanel page2;
    public JPanel page3;
    public JPanel page4;
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
    public JTextField textField1;
    public JTextField textField3;
    public JTextField textField10;
    public JTextField textField11;
    public JTable table6;
    public JTable table5;
    public JTextField textField12;
    public JTextField textField4;
    public JTable table4;
    public JTextField textField5;
    public JTable table7;
    public JTextField textField6;
    public JTable table3;
    public JTextField textField7;
    public JTable table8;
    public JTextField textField8;
    public JTable table2;
    public JTextField textField9;
    public JTable table9;
    public JPanel LeftPanel;
    public JTextField textField13;
    public JTextField textField14;
    public JTextField textField15;
    public JTextField textField16;
    public final TransferHandler handler = new TableRowTransferHandler();

    public static String pathProgram = System.getProperty("user.dir");//папка с прогой для винды или домашняя папка для линя
    public static String dirTmp = pathProgram + System.getProperty("file.separator") + ".tmp"; //Windows
    public static String outputDir = pathProgram + System.getProperty("file.separator") + "output";
    public static String addonDir = pathProgram + System.getProperty("file.separator") + "addon";
    public static String projectDir = pathProgram + System.getProperty("file.separator") + "project";
    public static String metainfDir = dirTmp + System.getProperty("file.separator") + "META-INF";
    public static String comDir = metainfDir + System.getProperty("file.separator") + "com";
    public static String googleDir = comDir + System.getProperty("file.separator") + "google";
    public static String dirScript = googleDir + System.getProperty("file.separator") + "android";
    public static String modDir = metainfDir + System.getProperty("file.separator") + "Mod";
    public static String pathBin = pathProgram + System.getProperty("file.separator") + "bin";// папка bin
    public static String aromamods = addonDir + System.getProperty("file.separator") + "AromaMods";
    public static String aromabinto = dirScript + System.getProperty("file.separator") + "update-binary";
    public static String aromathemefolderto = dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "themes";
    public static String aromabin = pathBin + System.getProperty("file.separator") + "update-binarya";
    public static String aromathemefolder = pathBin + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "themes";
    public static String aromafolder = dirScript + System.getProperty("file.separator") + "aroma";
    public static String updatebinary71 = pathBin + System.getProperty("file.separator") + "update-binary71";
    public static String FlashZip = pathProgram + System.getProperty("file.separator") + "Flash.zip";

    public AromaGenerator() {
        $$$setupUI$$$();
        setContentPane(panel1);
        themeslist.setEnabled(false);
        setTitle("Aroma Generator");//имя проги
        setSize(900, 520);//размер окна проги
        setResizable(false);//запретить изменять размер окна
        if (Objects.equals(Generator.LANGUAGE, "русский")) {
            ChengelogTextArea.setToolTipText("Список изменений мода/прошивки");
            themeCheckBox.setToolTipText("Выберите тему справа");
            SelectLang.setToolTipText("Добавляет меню выбора языка русский/английский");
            updateClearCheckBox.setToolTipText("<html>Добавляет меню выбора типа установки:" +
                    "<br>- Чистая" +
                    "<br>- Обновление" +
                    "<br>- Стандартная");
            nameFormattedTextField.setToolTipText("Название мода");
            authorFormattedTextField.setToolTipText("Автор мода");
            versionFormattedTextField.setToolTipText("Версия мода");
            dateFormattedTextField.setToolTipText("Дата мода");
            deviceFormattedTextField.setToolTipText("Название аппарата/модель и прочее");
            foldermods.setToolTipText("Имя папки с модами, куда все будет складываться");
            boldButton.setToolTipText("Сделать текст жирным");
            italicButton.setToolTipText("Сделать текст курсивным");
            underlineButton.setToolTipText("Сделать текст подчеркнутым");
            CenterButton.setToolTipText("Выровнять текст по центру");
            LeftButton.setToolTipText("Выровнять текст по левому краю");
            RightButton.setToolTipText("Выровнять текст по правому краю");
            ColorButton.setToolTipText("Выбрать цвет текста");
            //страница1
            textField1.setToolTipText("Название окна");
            textField13.setToolTipText("Описание окна");
            textField8.setToolTipText("Заголовок группы 1");
            textField9.setToolTipText("Заголовок группы 2");
            //страница2
            textField2.setToolTipText("Название окна");
            textField14.setToolTipText("Описание окна");
            textField6.setToolTipText("Заголовок группы 1");
            textField7.setToolTipText("Заголовок группы 2");
            //страница3
            textField3.setToolTipText("Название окна");
            textField15.setToolTipText("Описание окна");
            textField4.setToolTipText("Заголовок группы 1");
            textField5.setToolTipText("Заголовок группы 2");
            //страница4
            textField12.setToolTipText("Название окна");
            textField16.setToolTipText("Описание окна");
            textField10.setToolTipText("Заголовок группы 1");
            textField11.setToolTipText("Заголовок группы 2");
            //список файлов в папке
            table1.setToolTipText("Перечень файлов в папке addon/AromaMods");
        }
        setVisible(true);//отображать прогу
        //setAlwaysOnTop(true);//поверхб окон
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//закрыть при нажатии на крестик


        themeCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (themeCheckBox.isSelected()) {
                    new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "themes").mkdirs();
                    themeslist.setEnabled(true);
                } else {
                    themeslist.setEnabled(false);
                }
            }
        });


        Gener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        SelectLang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (SelectLang.isSelected()) {
                    new File(dirScript + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "langs").mkdirs();
                }
            }
        });


        boldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() != null) {
                    ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).replaceSelection("<b>" + ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() + "</b>");
                }
            }
        });
        italicButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() != null) {
                    ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).replaceSelection("<i>" + ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() + "</i>");
                }
            }
        });
        underlineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() != null) {
                    ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).replaceSelection("<u>" + ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() + "</u>");
                }
            }
        });

        CenterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() != null) {
                    ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).replaceSelection("<@center>" + ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() + "</@>");
                }
            }
        });

        LeftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() != null) {
                    ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).replaceSelection("<@left>" + ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() + "</@>");
                }
            }
        });
        RightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() != null) {
                    ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).replaceSelection("<@right>" + ((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() + "</@>");
                }
            }
        });

        ColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JColorChooser clr = new JColorChooser();
                if (((JTextComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()).getSelectedText() != null) {
                    JTextComponent a = (JTextComponent)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();//запомним в переменную где что выделяли
                    Color color = clr.showDialog(null, "Choose Color",
                            getBackground());
                    a.replaceSelection("<" + Functions.convertColorToHexadeimal(color) + ">" + a.getSelectedText() + "</#>");
                }
            }
        });

        // список тем из папки -->
        DefaultComboBoxModel thememodel = new DefaultComboBoxModel();
        File listok = new File(pathBin + System.getProperty("file.separator") + "aroma" + System.getProperty("file.separator") + "themes");
        for (String fol : listok.list()) {
            thememodel.addElement(fol);
        }
        themeslist.setModel(thememodel);
//список тем из папки <--
        table1.setDragEnabled(true);
        table1.setDropMode(DropMode.INSERT);
        table1.setRowSelectionAllowed(true);
        table1.setFillsViewportHeight(true);
        table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.setTransferHandler(handler);
        table1.setAutoCreateRowSorter(true);

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
        table1.setModel(model);
//список файлов папки <--

        //table2
        table2.setDragEnabled(true);
        table2.setDropMode(DropMode.INSERT);
        table2.setRowSelectionAllowed(true);
        table2.setFillsViewportHeight(true);
        table2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table2.setTransferHandler(handler);
        table2.setAutoCreateRowSorter(true);
        DefaultTableModel model2;
        model2 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model2.addColumn("Файл");
        model2.addColumn("Название");
        model2.addColumn("Описание");
        model2.addColumn("Тип");
        table2.setModel(model2);

        //table3
        table3.setDragEnabled(true);
        table3.setDropMode(DropMode.INSERT);
        table3.setRowSelectionAllowed(true);
        table3.setFillsViewportHeight(true);
        table3.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table3.setTransferHandler(handler);
        table3.setAutoCreateRowSorter(true);
        DefaultTableModel model3;
        model3 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model3.addColumn("Файл");
        model3.addColumn("Название");
        model3.addColumn("Описание");
        model3.addColumn("Тип");
        table3.setModel(model3);

        //table4
        table4.setDragEnabled(true);
        table4.setDropMode(DropMode.INSERT);
        table4.setRowSelectionAllowed(true);
        table4.setFillsViewportHeight(true);
        table4.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table4.setTransferHandler(handler);
        table4.setAutoCreateRowSorter(true);
        DefaultTableModel model4;
        model4 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model4.addColumn("Файл");
        model4.addColumn("Название");
        model4.addColumn("Описание");
        model4.addColumn("Тип");
        table4.setModel(model4);

        //table5
        table5.setDragEnabled(true);
        table5.setDropMode(DropMode.INSERT);
        table5.setRowSelectionAllowed(true);
        table5.setFillsViewportHeight(true);
        table5.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table5.setTransferHandler(handler);
        table5.setAutoCreateRowSorter(true);
        DefaultTableModel model5;
        model5 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model5.addColumn("Файл");
        model5.addColumn("Название");
        model5.addColumn("Описание");
        model5.addColumn("Тип");
        table5.setModel(model5);

        //table6
        table6.setDragEnabled(true);
        table6.setDropMode(DropMode.INSERT);
        table6.setRowSelectionAllowed(true);
        table6.setFillsViewportHeight(true);
        table6.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table6.setTransferHandler(handler);
        table6.setAutoCreateRowSorter(true);
        DefaultTableModel model6;
        model6 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model6.addColumn("Файл");
        model6.addColumn("Название");
        model6.addColumn("Описание");
        model6.addColumn("Тип");
        table6.setModel(model6);

        //table7
        table7.setDragEnabled(true);
        table7.setDropMode(DropMode.INSERT);
        table7.setRowSelectionAllowed(true);
        table7.setFillsViewportHeight(true);
        table7.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table7.setTransferHandler(handler);
        table7.setAutoCreateRowSorter(true);
        DefaultTableModel model7;
        model7 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model7.addColumn("Файл");
        model7.addColumn("Название");
        model7.addColumn("Описание");
        model7.addColumn("Тип");
        table7.setModel(model7);

        //table8
        table8.setDragEnabled(true);
        table8.setDropMode(DropMode.INSERT);
        table8.setRowSelectionAllowed(true);
        table8.setFillsViewportHeight(true);
        table8.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table8.setTransferHandler(handler);
        table8.setAutoCreateRowSorter(true);
        DefaultTableModel model8;
        model8 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model8.addColumn("Файл");
        model8.addColumn("Название");
        model8.addColumn("Описание");
        model8.addColumn("Тип");
        table8.setModel(model8);

        //table9
        table9.setDragEnabled(true);
        table9.setDropMode(DropMode.INSERT);
        table9.setRowSelectionAllowed(true);
        table9.setFillsViewportHeight(true);
        table9.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table9.setTransferHandler(handler);
        table9.setAutoCreateRowSorter(true);
        DefaultTableModel model9;
        model9 = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        model9.addColumn("Файл");
        model9.addColumn("Название");
        model9.addColumn("Описание");
        model9.addColumn("Тип");
        table9.setModel(model9);

        Gener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AromaGenerater.Gener();
            }
        });
    }

    public static void main() {
        instance1 = new AromaGenerator();
    }

    public void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        table1 = new JTable();
        panel1.add(table1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
        table2 = new JTable();
        panel1.add(table2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
        Gener = new JButton();
        Gener.setText("Generated");
        panel1.add(Gener, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        closeButton = new JButton();
        closeButton.setText("Close");
        panel1.add(closeButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}

class TableRowTransferHandler extends TransferHandler {
    public final DataFlavor localObjectFlavor;
    public int[] indices;
    public int addIndex = -1; //Location where items were added
    public int addCount; //Number of items added.
    public JComponent source;

    protected TableRowTransferHandler() {
        super();
        localObjectFlavor = new ActivationDataFlavor(Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        source = c;
        JTable table = (JTable) c;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Object> list = new ArrayList<>();
        indices = table.getSelectedRows();
        for (int i : indices) {
            list.add(model.getDataVector().get(i));
        }
        Object[] transferedObjects = list.toArray();
        return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        JTable table = (JTable) info.getComponent();
        boolean isDropable = info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
        //XXX bug?
        table.setCursor(isDropable ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
        return isDropable;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE; //TransferHandler.COPY_OR_MOVE;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!canImport(info)) {
            return false;
        }
        TransferHandler.DropLocation tdl = info.getDropLocation();
        if (!(tdl instanceof JTable.DropLocation)) {
            return false;
        }
        JTable.DropLocation dl = (JTable.DropLocation) tdl;
        JTable target = (JTable) info.getComponent();
        DefaultTableModel model = (DefaultTableModel) target.getModel();
        int index = dl.getRow();
        //boolean insert = dl.isInsert();
        int max = model.getRowCount();
        if (index < 0 || index > max) {
            index = max;
        }
        addIndex = index;
        target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        try {
            Object[] values = (Object[]) info.getTransferable().getTransferData(localObjectFlavor);
            if (Objects.equals(source, target)) {
                addCount = values.length;
            }
            for (int i = 0; i < values.length; i++) {
                int idx = index++;
                model.insertRow(idx, (Vector) values[i]);
                target.getSelectionModel().addSelectionInterval(idx, idx);
            }
            return true;
        } catch (UnsupportedFlavorException | IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == TransferHandler.MOVE);
    }

    public void cleanup(JComponent c, boolean remove) {
        if (remove && indices != null) {
            c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            DefaultTableModel model = (DefaultTableModel) ((JTable) c).getModel();
            if (addCount > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] >= addIndex) {
                        indices[i] += addCount;
                    }
                }
            }
            for (int i = indices.length - 1; i >= 0; i--) {
                model.removeRow(indices[i]);
            }
        }
        indices = null;
        addCount = 0;
        addIndex = -1;
    }
}