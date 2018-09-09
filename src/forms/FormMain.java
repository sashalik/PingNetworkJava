package forms;


import checkconnection.CheckConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMain extends JFrame {

    // Размеры
    private Point sizePanelEdt = new Point(240,80);
    private Point sizePanelListIp = new Point(150,190);
    private Point sizePanelResult = new Point(230,190);
    private Point sizePanelAction = new Point(240,110);

    // Расположение
    private Point locationPanelEdt = new Point(150,0);
    private Point locationPanelListIp = new Point(0,0);
    private Point locationPanelResult = new Point(390,0);
    private Point locationPanelAction = new Point(150,80);

    //списки
    private JList listIp;
    private JList listResult;

    // Модель списка
    private DefaultListModel modelLispIp = new DefaultListModel();
    private DefaultListModel modelLispResult = new DefaultListModel();

    public void CreateForm()
    {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new JFrame ("Первый Фрейм");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setLayout(null);

        this.getContentPane().add(GetPanelEdtList());
        this.getContentPane().add(GetPanelListIp());
        this.getContentPane().add(GetPanelResult());
        this.getContentPane().add(GetPanelAction());
        this.setPreferredSize(new Dimension(640,430));
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


    // Список Ip-адресов
    private JPanel GetPanelListIp()
    {
        // Основная панель
        JPanel panelLispIp = new JPanel();
        Border b = BorderFactory.createTitledBorder("Список опроса");
        panelLispIp.setBorder(b);
        panelLispIp.setSize(sizePanelListIp.x, sizePanelListIp.y);
        panelLispIp.setLayout(new BorderLayout());
        panelLispIp.setLocation(locationPanelListIp);

        listIp = new JList(modelLispIp);
        listIp.setVisible(true);

        JScrollPane listIpScroll = new JScrollPane(listIp);
        listIpScroll.setPreferredSize(new Dimension(100,100));

        panelLispIp.add(listIpScroll);
        return panelLispIp;
    }

    // панель результата опроса
    private JPanel GetPanelResult()
    {
        // Основная панель
        JPanel panelResult = new JPanel();
        Border b = BorderFactory.createTitledBorder("Результат");
        panelResult.setBorder(b);
        panelResult.setSize(sizePanelResult.x, sizePanelResult.y);
        panelResult.setLayout(new BorderLayout());
        panelResult.setLocation(locationPanelResult);

        listResult = new JList(modelLispResult);
        listResult.setVisible(true);

        JScrollPane listIpScroll = new JScrollPane(listResult);
        listIpScroll.setPreferredSize(new Dimension(100,100));

        panelResult.add(listIpScroll);
        return panelResult;
    }

    // Панель редактирования списка
    private JPanel GetPanelEdtList()
    {
        // Основная панель
        JPanel panelEdtList = new JPanel();
        Border b = BorderFactory.createTitledBorder("Редактирование списка");
        panelEdtList.setBorder(b);
        panelEdtList.setSize(sizePanelEdt.x, sizePanelEdt.y);
        panelEdtList.setLayout(null);
        panelEdtList.setLocation(locationPanelEdt);

        // текстовое поле
        JTextField edtEnter = new JTextField(20);
        edtEnter.setLocation(115,20);
        edtEnter.setSize(116,20);
        edtEnter.setFont(new Font("TimesRoman",Font.BOLD,10));
        panelEdtList.add(edtEnter);

        //Кнопка добавления
        JButton btnAdd = new JButton();
        btnAdd.setText("Добавить");
        btnAdd.setLocation(10,20);
        btnAdd.setSize(85,20);
        btnAdd.setFont(new Font("TimesRoman",Font.BOLD,10));

        // Навесили событие добавления данных в список IP
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelLispIp.addElement(edtEnter.getText());
                edtEnter.setText("");
            }
        });

        panelEdtList.add(btnAdd);

        //Кнопка удаления
        JButton btnDel = new JButton();
        btnDel.setText("Удалить");
        btnDel.setLocation(10,48);
        btnDel.setSize(85,20);
        btnDel.setFont(new Font("TimesRoman",Font.BOLD,10));
        panelEdtList.add(btnDel);

        // Событие удаления элемента из списка
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listIp.getSelectedIndex() != -1)
                {
                    modelLispIp.remove(listIp.getSelectedIndex());
                }
            }
        });

        //Кнопка очистки
        JButton btnClear = new JButton();
        btnClear.setText("Очистить");
        btnClear.setLocation(115,48);
        btnClear.setFont(new Font("TimesRoman",Font.BOLD,10));
        btnClear.setSize(116,20);
        panelEdtList.add(btnClear);

        // Событие очистки списка IP
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelLispIp.clear();
            }
        });

        return panelEdtList;
    }

    // Панель опроса
    private JPanel GetPanelAction()
    {
        JPanel panelAction = new JPanel();
        Border b = BorderFactory.createTitledBorder("Дейсвтия");
        panelAction.setBorder(b);
        panelAction.setSize(sizePanelAction.x, sizePanelAction.y);
        panelAction.setLayout(null);
        panelAction.setLocation(locationPanelAction);

        JButton btnPing = new JButton();
        btnPing.setText("Опросить");
        btnPing.setLocation(10,19);
        btnPing.setFont(new Font("TimesRoman",Font.BOLD,10));
        btnPing.setSize(220,20);
        panelAction.add(btnPing);

        btnPing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelLispResult.clear();
                CheckConnection ccResult = new CheckConnection();
                for(int i = 0; i< modelLispIp.getSize(); i++)
                {
                    modelLispResult.addElement(ccResult.Check(modelLispIp.getElementAt(i).toString()));
                }
            }
        });

        JButton btnPingSend = new JButton();
        btnPingSend.setText("Опросить и отправить");
        btnPingSend.setLocation(10,48);
        btnPingSend.setFont(new Font("TimesRoman",Font.BOLD,10));
        btnPingSend.setSize(220,20);
        panelAction.add(btnPingSend);

        return panelAction;
    }

}

