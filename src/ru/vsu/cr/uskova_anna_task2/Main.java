package ru.vsu.cr.uskova_anna_task2;


import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void start(){
        LinkedList<Integer> list = new LinkedList<>();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBounds(100, 100, 600, 300);
        jFrame.setResizable(false);

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Ручной ввод: ");
        JTextField textField = new JTextField(30);
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            try {
                String input = textField.getText();
                String[] elements = input.split("[ ,]+");

                getResult(list, panel, elements);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
            }
        });

        JLabel label1 = new JLabel("Ввод из файла: ");
        JTextField textField1 = new JTextField(30);
        JButton button1 = new JButton("OK");
        button1.addActionListener(e -> {
            try {
                String input = textField1.getText();
                Path path = Path.of(input);

                String[] elements = Files.readAllLines(path).get(0).split("[ ,]+");

                getResult(list, panel, elements);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(label1);
        panel.add(textField1);
        panel.add(button1);
        jFrame.add(panel);

        jFrame.setVisible(true);
    }

    private static void getResult(LinkedList<Integer> list, JPanel panel, String[] elements) {
        for(String element: elements){
            list.addLast(Integer.parseInt(element));
        }

        int max = list.max();
        long countMax = list.countMaxElements();

        String answer = "Максимальное число: " + max +
                "\nЧисло вхождений максимального числа: " + countMax;

        JOptionPane.showMessageDialog(panel, answer);
        list.clear();
    }
}
