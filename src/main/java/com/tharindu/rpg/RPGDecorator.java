package com.tharindu.rpg;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

interface Character {
    String getDescription();

    int getPower();
}

// File: BaseCharacter.java
abstract class BaseCharacter implements Character {
    protected String description = "Unknown Character";

    @Override
    public String getDescription() {
        return description;
    }
}

class Warrior extends BaseCharacter {
    public Warrior() {
        description = "\uD83C\uDFC7 Warrior";
    }

    @Override
    public int getPower() {
        return 100;
    }
}

class Archer extends BaseCharacter {
    public Archer() {
        description = "\uD83C\uDFF9 Archer";
    }

    @Override
    public int getPower() {
        return 80;
    }
}

class Mage extends BaseCharacter {
    public Mage() {
        description = "\uD83E\uDDDD️ Mage";
    }

    @Override
    public int getPower() {
        return 70;
    }
}

abstract class CharacterDecorator implements Character {
    protected Character character;

    public CharacterDecorator(Character character) {
        this.character = character;
    }

    @Override
    public String getDescription() {
        return character.getDescription();
    }

    @Override
    public int getPower() {
        return character.getPower();
    }
}

class FireEnchantment extends CharacterDecorator {
    public FireEnchantment(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return character.getDescription() + "\nFire Enhanced \uD83D\uDD25";
    }

    @Override
    public int getPower() {
        return character.getPower() + 30;
    }
}

class ArmorUpgrade extends CharacterDecorator {
    public ArmorUpgrade(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return character.getDescription() + "\nArmor Upgraded \uD83D\uDEE1";
    }

    @Override
    public int getPower() {
        return character.getPower() + 40;
    }
}

class SpeedBoost extends CharacterDecorator {
    public SpeedBoost(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return character.getDescription() + "\nSpeed Boosted \uD83D\uDCA5";
    }

    @Override
    public int getPower() {
        return character.getPower() + 20;
    }
}

class MagicShield extends CharacterDecorator {
    public MagicShield(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return character.getDescription() + "\nMagic Shield Equipped \uD83D\uDD2E";
    }

    @Override
    public int getPower() {
        return character.getPower() + 50;
    }
}

class BackgroundPanel extends JPanel {
    private final Image background;

    public BackgroundPanel(Image background) {
        this.background = background;
        setLayout(new GridBagLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}

public class RPGDecorator extends JFrame {
    private JComboBox<String> typeCombo;
    private JCheckBox fireCB, armorCB, speedCB, shieldCB;
    private JTextArea outputArea;
    private JPanel imagePanel;

    public RPGDecorator() {
        setTitle("Character Customizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
    }

    private void initComponents() {
        // --- background setup (unchanged) ---
        URL imageUrl = getClass().getClassLoader().getResource("background.jpg");
        if (imageUrl == null) {
            System.err.println("Error: background.jpg not found in resources");
            System.exit(1);
        }
        Image bg = new ImageIcon(imageUrl).getImage();
        BackgroundPanel content = new BackgroundPanel(bg);
        content.setLayout(new GridBagLayout());
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 25, 15, 25);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // --- Title ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel title = new JLabel("⚔️ Lost Ark Character Customizer");
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        content.add(title, gbc);

        {
            // make the label + combo into one FlowLayout panel
            JLabel typeLabel = new JLabel("Character Type:");
            typeLabel.setFont(typeLabel.getFont().deriveFont(20f));
            typeLabel.setForeground(Color.LIGHT_GRAY);

            typeCombo = new JComboBox<>(new String[]{"Warrior", "Archer", "Mage"});
            typeCombo.setFont(typeCombo.getFont().deriveFont(20f));

            JPanel typeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
            typeRow.setOpaque(false);
            typeRow.add(typeLabel);
            typeRow.add(typeCombo);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;               // span two columns if you like
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.weightx = 0;
            content.add(typeRow, gbc);
        }

        {
            JLabel upLabel = new JLabel("Upgrades:");
            upLabel.setFont(upLabel.getFont().deriveFont(20f));
            upLabel.setForeground(Color.LIGHT_GRAY);

            JPanel ups = new JPanel(new GridLayout(4, 1, 8, 0));
            ups.setOpaque(false);
            fireCB = createCB("🔥 Fire Enchantment");
            armorCB = createCB("🛡️ Armor Upgrade");
            speedCB = createCB("💥 Speed Boost");
            shieldCB = createCB("🔮 Magic Shield");
            ups.add(fireCB);
            ups.add(armorCB);
            ups.add(speedCB);
            ups.add(shieldCB);

            // now wrap label + ups panel
            JPanel upRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
            upRow.setOpaque(false);
            upRow.add(upLabel);
            upRow.add(ups);

            gbc.gridy = 2;
            gbc.gridwidth = 2;
            content.add(upRow, gbc);
        }
        // reset grid width if you need other components below
        gbc.gridwidth = 1;

        // --- Create button ---
        JButton btn = new JButton("Power Up Hero!");
        btn.setFont(btn.getFont().deriveFont(24f));
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(btn, gbc);

        // --- Output text area (now pinned to col=1) ---
        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.GREEN);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        content.add(scroll, gbc);

        // --- Image panel on the left column (col=0,row=4) ---
        imagePanel = new JPanel(new GridLayout(1, 1, 10, 10));
        imagePanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        content.add(imagePanel, gbc);

        // --- wire up ---
        btn.addActionListener(e -> generateCharacter());
    }

    private JCheckBox createCB(String txt) {
        JCheckBox cb = new JCheckBox(txt);
        cb.setFont(cb.getFont().deriveFont(18f));
        cb.setForeground(Color.WHITE);
        cb.setOpaque(false);
        return cb;
    }

    private void generateCharacter() {
        // --- same decorator logic ---
        Character ch;
        switch ((String) Objects.requireNonNull(typeCombo.getSelectedItem())) {
            case "Archer":
                ch = new Archer();
                break;
            case "Mage":
                ch = new Mage();
                break;
            default:
                ch = new Warrior();
        }
        if (fireCB.isSelected()) ch = new FireEnchantment(ch);
        if (armorCB.isSelected()) ch = new ArmorUpgrade(ch);
        if (speedCB.isSelected()) ch = new SpeedBoost(ch);
        if (shieldCB.isSelected()) ch = new MagicShield(ch);

        outputArea.setText(ch.getDescription() + "\nTotal Power: " + ch.getPower());

        // --- rebuild images on the left ---
        imagePanel.removeAll();
        List<String> ups = new ArrayList<>();
        if (fireCB.isSelected()) ups.add("fire");
        if (armorCB.isSelected()) ups.add("armor");
        if (speedCB.isSelected()) ups.add("speed");
        if (shieldCB.isSelected()) ups.add("shield");
        if (ups.isEmpty()) ups.add("base");  // fallback

        imagePanel.setLayout(new GridLayout(1, ups.size(), 10, 10));
        String type = ((String) typeCombo.getSelectedItem()).toLowerCase();
        for (String u : ups) {
            String fn = type + "_" + u + ".png";
            URL url = getClass().getClassLoader().getResource(fn);
            JLabel lbl;
            if (url != null) {
                Image img = new ImageIcon(url).getImage()
                        .getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lbl = new JLabel(new ImageIcon(img));
            } else {
                lbl = new JLabel("No image");
                lbl.setForeground(Color.WHITE);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setPreferredSize(new Dimension(150, 150));
            }
            imagePanel.add(lbl);
        }

        imagePanel.revalidate();
        imagePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RPGDecorator().setVisible(true));
    }
}