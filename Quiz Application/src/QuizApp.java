
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    String[][] questions = {
            {"Which language runs in a web browser?", "Java", "C", "Python", "JavaScript", "JavaScript"},
            {"What does CPU stand for?", "Central Process Unit", "Central Processing Unit", "Computer Personal Unit", "Central Processor Unit", "Central Processing Unit"},
            {"Who invented Java?", "Dennis Ritchie", "James Gosling", "Bjarne Stroustrup", "Guido van Rossum", "James Gosling"}
    };

    int index = 0;
    int score = 0;
    JLabel questionLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup bg;
    JButton nextButton;

    public QuizApp() {
        setTitle("🧠 Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel();
        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();
        bg = new ButtonGroup();
        bg.add(opt1);
        bg.add(opt2);
        bg.add(opt3);
        bg.add(opt4);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        add(questionLabel);
        add(opt1);
        add(opt2);
        add(opt3);
        add(opt4);
        add(nextButton);

        loadQuestion();
    }

    void loadQuestion() {
        bg.clearSelection();
        if (index < questions.length) {
            questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
            opt1.setText(questions[index][1]);
            opt2.setText(questions[index][2]);
            opt3.setText(questions[index][3]);
            opt4.setText(questions[index][4]);
        } else {
            showResult();
        }
    }

    void showResult() {
        JOptionPane.showMessageDialog(this, "Your Score: " + score + "/" + questions.length);
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e) {
        if (index < questions.length) {
            String selected = null;
            if (opt1.isSelected()) selected = opt1.getText();
            else if (opt2.isSelected()) selected = opt2.getText();
            else if (opt3.isSelected()) selected = opt3.getText();
            else if (opt4.isSelected()) selected = opt4.getText();

            if (selected != null && selected.equals(questions[index][5])) {
                score++;
            }
            index++;
            loadQuestion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp().setVisible(true));
    }
}
