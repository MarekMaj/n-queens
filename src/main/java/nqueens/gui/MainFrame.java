package nqueens.gui;

import nqueens.command.StartCommand;
import nqueens.command.Command;
import nqueens.command.FinishCommand;
import nqueens.factory.AlgorithmEnum;
import nqueens.factory.HeuristicEnum;

import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JSpinner insertBoardSize = new JSpinner();
    private JCheckBox showBoard = new JCheckBox("Pokaż planszę");
    private JCheckBox showAlgorithmSteps = new JCheckBox("Pokaż kroki");
    private JLabel algorithmStepsField = new JLabel("");
    private JLabel algorithmExecutionTimeField = new JLabel("");
    private JComboBox chooseAlgorithm = new JComboBox();
    private JComboBox chooseHeuristic = new JComboBox();

    public MainFrame() {
        initialize();

        JPanel mainPanel = createMainPanel();
        contentPane.add(mainPanel, BorderLayout.NORTH);

        mainPanel.add(createNorthPanel());
        mainPanel.add(createButtonPanel());

    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 5));
        return mainPanel;
    }

    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(0, 2, 10, 10));

        northPanel.add(createOptionPanel());
        northPanel.add(createResultsPanel());
        return northPanel;
    }

    private JPanel createOptionPanel() {
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(0, 2, 5, 10));

        JLabel label = new JLabel("Wybierz algorytm:");
        optionPanel.add(label);

        chooseAlgorithm.setModel(new DefaultComboBoxModel(AlgorithmEnum.values()));
        optionPanel.add(chooseAlgorithm);

        JLabel label_1 = new JLabel("Wybierz heurystykę:");
        optionPanel.add(label_1);

        chooseHeuristic.setModel(new DefaultComboBoxModel(HeuristicEnum.values()));
        optionPanel.add(chooseHeuristic);

        JLabel label_2 = new JLabel("Rozmiar szachownicy:");
        optionPanel.add(label_2);

        insertBoardSize.setModel(new SpinnerNumberModel(10, 1, 1000, 1));
        optionPanel.add(insertBoardSize);

        optionPanel.add(showBoard);

        initializeShowAlgorithmSteps();
        optionPanel.add(showAlgorithmSteps);
        return optionPanel;
    }

    private void initializeShowAlgorithmSteps() {
        showAlgorithmSteps.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showBoard.setEnabled(!showAlgorithmSteps.isSelected());
                if (showAlgorithmSteps.isSelected()) {
                    showBoard.setSelected(true);
                }
            }
        });
        showAlgorithmSteps.setVisible(false);   //TODO zaimplementwac
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);

        buttonPanel.add(createFinishButton());
        buttonPanel.add(createStartButton());
        return buttonPanel;
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Rozpocznij");
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                StartCommand startCommand = new StartCommand((AlgorithmEnum) chooseAlgorithm.getSelectedItem(),
                        (HeuristicEnum) chooseHeuristic.getSelectedItem(),
                        (Integer) insertBoardSize.getValue());

                startCommand.addAlgorithmExecutionTimeField(algorithmExecutionTimeField).
                        addAlgorithmStepsField(algorithmStepsField).
                        addShowBoard(showBoard.isSelected());

                startCommand.execute();
            }
        });
        startButton.setBounds(263, 12, 126, 28);
        return startButton;
    }

    private JButton createFinishButton() {
        JButton finishButton = new JButton("Zakończ");
        finishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Command command = new FinishCommand();
                command.execute();
            }
        });
        finishButton.setBounds(270, 66, 112, 28);
        return finishButton;
    }

    private JPanel createResultsPanel() {
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(0, 2, 7, 0));

        JLabel algorithmStepsLabel = new JLabel("Ilość kroków:");
        resultsPanel.add(algorithmStepsLabel);

        resultsPanel.add(algorithmStepsField);

        JLabel algorithmExecutionTimeLabel = new JLabel("Czas wykonania [ms]:");
        resultsPanel.add(algorithmExecutionTimeLabel);

        resultsPanel.add(algorithmExecutionTimeField);
        return resultsPanel;
    }

    private void initialize() {
        setTitle("Problem n-hetmanów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 663, 315);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);
    }

}
