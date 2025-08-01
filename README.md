# 🏹 Lost Ark Hero Upgrader

An immersive Java Swing application demonstrating the **Decorator Design Pattern** by allowing players to dynamically enhance their Lost Ark heroes with multiple power-ups at runtime. Customize your hero’s abilities, apply modular upgrades, and see real-time changes in stats and sprites!

---

## 🌟 Features

* 🎨 **Java Swing GUI** with dark fantasy theme
* 🔍 **Decorator Pattern**: stack multiple upgrades (Fire Enchantment, Armor Upgrade, Speed Boost, Magic Shield) without altering base classes
* 🦸‍♂️ **Selectable Heroes**: Warrior, Archer, Mage
* ⚡ **Dynamic Stat Calculation**: base power + all applied decorators
* 🖼️ **Custom Hero Sprites** change on each upgrade (NEW!)
* 📐 Extensible: add new hero types or upgrades by writing new decorator classes

---

## 📸 Screenshot Gallery

| Default Hero Selection | After Fire Enchantment | Full Power-Up Stack |
|------------------------|------------------------|----------------------|
| <img src="https://github.com/user-attachments/assets/57a958d2-fe3a-4449-8458-357d66490d5c" width="400"/> | <img src="https://github.com/user-attachments/assets/1bdf52d2-483b-427d-9314-6301d4609849" width="400"/> | <img src="https://github.com/user-attachments/assets/6980963a-d02a-4d13-b741-053043acc3ae" width="400"/> |

---

## 🧩 Decorator Pattern Overview

Each upgrade is implemented as a **Decorator** wrapping a `Character` object. This allows combining any number of enhancements in any order at runtime:

```
Character warrior = new Warrior();
Character buffed = new FireEnchantment(new ArmorUpgrade(new SpeedBoost(warrior)));
System.out.println(buffed.getDescription()); // "Warrior with Speed Boost with Armor Upgrade with Fire Enchantment"
System.out.println(buffed.getPower());       // 100 + 20 + 40 + 30 = 190
```

### UML Diagram

> Paste your generated PlantUML diagram below:

<img width="1496" height="441" alt="UML" src="https://github.com/user-attachments/assets/561aa06e-1a7d-4f61-94a7-cc439c2d4240" />

---

## 📂 Project Structure

```bash
Lost-Ark-Hero-Upgrade-Decorator-Pattern/
├── src/main/java/com/tharindu/rpg/
│   └── RPGDecorator.java
├── Resources/
│   ├── warrior_base.png
│   ├── warrior_fire.png
│   ├── warrior_shield.png
│   ├── warrior_speed.png
│   ├── warrior_magic.png
│   ├── archer_base.png
│   ├── archer_fire.png
│   ├── archer_shield.png
│   ├── archer_speed.png
│   ├── archer_magic.png
│   ├── mage_base.png
│   ├── mage_fire.png
│   ├── mage_shield.png
│   ├── mage_speed.png
│   └── mage_full.png
└── README.md
```
---

## 🚀 How to Run

1. **Clone repo**:

   ```bash
   git clone https://github.com/Tharindu714/Lost-Ark-Hero-Upgrade-Decorator-Pattern.git
   cd Lost-Ark-Hero-Upgrade-Decorator-Pattern
   ```
2. **Compile & package** with Maven:

   ```bash
   mvn clean package
   ```
3. **Run** the GUI:

   ```bash
   java -jar target/hero-upgrader-1.0.jar
   ```

> Requires Java 11 or later.

---

## ⚙️ Extending the System

* **New Hero**: implement `Character`, define base power and description.
* **New Upgrade**: extend `CharacterDecorator`, override `getDescription()` and `getPower()`.

---

## 📝 License

MIT License. Pull requests and contributions welcome!

---

*Github: [https://github.com/Tharindu714/Lost-Ark-Hero-Upgrade-Decorator-Pattern](https://github.com/Tharindu714/Lost-Ark-Hero-Upgrade-Decorator-Pattern)*
