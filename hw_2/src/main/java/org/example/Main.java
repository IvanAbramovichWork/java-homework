package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        frame.setTitle("Coffee calculator");
        frame.setSize(300, 400);
        frame.setLocation(500, 700);
        JComboBox<String> comboWithType = new JComboBox<>(new String[]{"Выберите тип кофе", "Латте", "Капучино", "Американо", "Раф"});
        JComboBox<String> comboWithSize = new JComboBox<>(new String[]{"Выберите размер чашки", "Маленький", "Средний", "Большой"});
        JCheckBox sugar = new JCheckBox("Сахар");
        JCheckBox milk = new JCheckBox("Молоко");
        JButton calc = new JButton("Рассчитать стоимость кофе");
        JLabel label = new JLabel("Стоимость кофе: ");
        calc.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sum = 0;
                switch (String.valueOf(comboWithType.getSelectedItem())) {
                    case "Латте":
                        sum += 140;
                        break;
                    case "Капучино":
                        sum += 130;
                        break;
                    case "Американо":
                        sum += 120;
                        break;
                    case "Раф":
                        sum += 150;
                        break;
                }
                switch (String.valueOf(comboWithSize.getSelectedItem())) {
                    case "Средний":
                        sum *= 1.2;
                        break;
                    case  "Большой":
                        sum *= 1.4;
                        break;
                    case "Маленький":
                        sum *= 1.6;
                        break;
                }
                if (sugar.isSelected()) {
                    sum += 5;
                }
                if (milk.isSelected()) {
                    sum += 15;
                }
                label.setText("Стоимость кофе: " + (int)sum);
            }
        });
        frame.add(comboWithType);
        frame.add(comboWithSize);
        frame.add(sugar);
        frame.add(milk);
        frame.add(calc);
        frame.add(label);
        frame.setVisible(true);
    }


}
