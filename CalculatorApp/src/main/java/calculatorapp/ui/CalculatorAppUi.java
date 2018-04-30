package calculatorapp.ui;

import calculatorapp.dao.ExpressionDao;
import calculatorapp.dao.MathDatabase;
import calculatorapp.logic.CalculatorService;
import calculatorapp.logic.Expression;
import calculatorapp.logic.ExpressionMemory;
import calculatorapp.logic.InputParser;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the graphical user interface for the CalculatorApp. 
 * @author jpssilve
 */
public class CalculatorAppUi extends Application {

    private ArrayList<Button> numbers;
    private Button plus;
    private Button minus;
    private Button multiply;
    private Button divide;
    private Button exponent;
    private Button percent;
    private Button modulo;
    private Button delete;
    private Button clear;
    private Button dot;
    private Button equalsSign;
    private Button answer;
    private Button leftBracket;
    private Button rightBracket;
    private Button absValue;
    private ArrayList<Button> mainGridButtons;
    private ArrayList<Button> auxGridButtons;

    private Button setMemoryLimit;
    private Button clearMemory;
    private Button copyMemExpression;
    private Button saveExpression;
    private Label saveStatus;
    private Button saveAllExpressions;
    private Label saveAllStatus;
    private Label currentMemLimit;
    private Label setMemoryLabel;
    private Slider memLimitSlider;

    private Label databaseLabel;
    private Button copyDbExpression;
    private Button getAllSavedExpressions;
    private Label retrievalStatus;
    private Button searchExpression;
    private Button deleteExpression;
    private Label deleteStatus;

    private Button closeButton;
    private Button yesButton;
    private Button noButton;

    private CalculatorService calc;
    private InputParser inputParser;
    private ExpressionMemory exprMem;
    private ExpressionDao eDao;
    private int width;
    private int height;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(CalculatorAppUi.class);
    }

    /**
     * Creates a database if it does not already exist, by establishing a connection
     * to a SQLite database. 
     * 
     * The name of the database is retrieved from a file config.properties which the user must create.
     * @throws Exception 
     */
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String databaseAddress = properties.getProperty("mathDatabase");
        MathDatabase math = new MathDatabase("jdbc:sqlite:" + databaseAddress);

        try (Connection conn = math.getConnection()) {
            math.initDatabase();
            System.out.println("Database initialization/connection success");
        } catch (SQLException e) {
            System.out.println("Database initialization/connection failure " + e.getMessage());
        }

        eDao = new ExpressionDao(math);
        calc = new CalculatorService();
        inputParser = new InputParser(calc);
        exprMem = new ExpressionMemory(inputParser, 10);
    }

    @Override
    public void start(Stage stage) throws Exception {
        width = 500;
        height = 500;

        createAllButtons();
        int divider = 7;
        setButtonSize(mainGridButtons, divider);
        setButtonSize(auxGridButtons, divider);

        GridPane mainGrid = new GridPane();
        mainGrid.addRow(0, numbers.get(7), numbers.get(8), numbers.get(9), delete, clear);
        mainGrid.addRow(1, numbers.get(4), numbers.get(5), numbers.get(6), multiply, divide);
        mainGrid.addRow(2, numbers.get(1), numbers.get(2), numbers.get(3), plus, minus);
        mainGrid.addRow(3, numbers.get(0), dot, percent, answer, equalsSign);

        GridPane auxGrid = new GridPane();
        auxGrid.addColumn(0, exponent, modulo);
        auxGrid.addColumn(1, leftBracket, rightBracket);
        auxGrid.addColumn(2, absValue);

        int h2 = 30;
        Label inputLabel = new Label("Input: ");
        inputLabel.setPrefHeight(h2);
        Label exprLabel = new Label("Expression:");
        exprLabel.setPrefHeight(h2);
        Label resLabel = new Label("Result: ");
        resLabel.setPrefHeight(h2);
        Label instrLabel = new Label("Instructions: ");
        instrLabel.setPrefHeight(h2);

        VBox vBoxLeft = new VBox();
        vBoxLeft.getChildren().add(0, inputLabel);
        vBoxLeft.getChildren().add(1, exprLabel);
        vBoxLeft.getChildren().add(2, resLabel);
        vBoxLeft.getChildren().add(3, instrLabel);
        vBoxLeft.getChildren().add(4, auxGrid);

        TextField input = new TextField();
        input.setPrefHeight(h2);
        input.setEditable(false);
        TextField formula = new TextField();
        formula.setPrefHeight(h2);
        formula.setEditable(false);
        TextField result = new TextField();
        result.setPrefHeight(h2);
        result.setEditable(false);
        TextField instruction = new TextField();
        instruction.setPrefHeight(h2);
        instruction.setEditable(false);

        VBox vBoxCenter = new VBox();
        vBoxCenter.setPrefSize(width, height);
        vBoxCenter.getChildren().add(0, input);
        vBoxCenter.getChildren().add(1, formula);
        vBoxCenter.getChildren().add(2, result);
        vBoxCenter.getChildren().add(3, instruction);
        vBoxCenter.getChildren().add(4, mainGrid);

        Label recentExpressions = new Label("Memory: Recently used");
        recentExpressions.setPrefHeight(h2);

        ListView<String> memoryList = new ListView<>();
        ListProperty<String> listProperty = new SimpleListProperty<>();
        memoryList.itemsProperty().bind(listProperty);
        listProperty.set(FXCollections.observableArrayList(exprMem.getMemExpressionsArrayList()));

        createSetMemoryLimitSlider();
        VBox vBoxRight = new VBox();
        vBoxRight.setSpacing(5);
        vBoxRight.getChildren().add(0, currentMemLimit);
        vBoxRight.getChildren().add(1, setMemoryLabel);
        vBoxRight.getChildren().add(2, memLimitSlider);
        vBoxRight.getChildren().add(3, setMemoryLimit);
        vBoxRight.getChildren().add(4, recentExpressions);
        vBoxRight.getChildren().add(5, clearMemory);
        vBoxRight.getChildren().add(6, saveAllExpressions);
        vBoxRight.getChildren().add(7, saveAllStatus);
        vBoxRight.getChildren().add(8, saveExpression);
        vBoxRight.getChildren().add(9, saveStatus);
        vBoxRight.getChildren().add(10, copyMemExpression);
        vBoxRight.getChildren().add(11, memoryList);

        ListView<Expression> databaseList = new ListView();
        ListProperty<Expression> dbListProperty = new SimpleListProperty<>();
        databaseList.itemsProperty().bind(dbListProperty);

        VBox vBoxRightest = new VBox();
        vBoxRightest.setSpacing(10);
        vBoxRightest.getChildren().add(0, databaseLabel);
        vBoxRightest.getChildren().add(1, getAllSavedExpressions);
        vBoxRightest.getChildren().add(2, retrievalStatus);
        vBoxRightest.getChildren().add(3, copyDbExpression);
        vBoxRightest.getChildren().add(4, deleteExpression);
        vBoxRightest.getChildren().add(5, deleteStatus);
        vBoxRightest.getChildren().add(6, databaseList);
        
        ListView<Expression> databaseMatchesList = new ListView();
        ListProperty<Expression> matchesListProperty = new SimpleListProperty<>();
        databaseMatchesList.itemsProperty().bind(matchesListProperty);
        
        VBox vBoxSearch = new VBox();
        vBoxSearch.getChildren().add(0, searchExpression);
        vBoxSearch.getChildren().add(1, databaseMatchesList);
        
//        vBoxLeft.getChildren().add(5, vBoxSearch);
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(vBoxLeft, vBoxCenter, vBoxRight, vBoxRightest);

        numbers.get(0).setOnMouseClicked((event) -> input.setText(input.getText() + "0"));
        numbers.get(1).setOnMouseClicked((event) -> input.setText(input.getText() + "1"));
        numbers.get(2).setOnMouseClicked((event) -> input.setText(input.getText() + "2"));
        numbers.get(3).setOnMouseClicked((event) -> input.setText(input.getText() + "3"));
        numbers.get(4).setOnMouseClicked((event) -> input.setText(input.getText() + "4"));
        numbers.get(5).setOnMouseClicked((event) -> input.setText(input.getText() + "5"));
        numbers.get(6).setOnMouseClicked((event) -> input.setText(input.getText() + "6"));
        numbers.get(7).setOnMouseClicked((event) -> input.setText(input.getText() + "7"));
        numbers.get(8).setOnMouseClicked((event) -> input.setText(input.getText() + "8"));
        numbers.get(9).setOnMouseClicked((event) -> input.setText(input.getText() + "9"));

        multiply.setOnMouseClicked((event) -> input.setText(input.getText() + "*"));
        divide.setOnMouseClicked((event) -> input.setText(input.getText() + "/"));
        plus.setOnMouseClicked((event) -> input.setText(input.getText() + "+"));
        minus.setOnMouseClicked((event) -> input.setText(input.getText() + "-"));
        exponent.setOnMouseClicked((event) -> input.setText(input.getText() + "^"));

//        percent.setOnMouseClicked((event) -> input.setText(input.getText() + "%"));
//        modulo.setOnMouseClicked((event) -> input.setText(input.getText() + "mod"));
        delete.setOnMouseClicked((event) -> {
            String s = input.getText();
            if (s.length() != 0) {
                input.setText(input.getText().substring(0, input.getText().length() - 1));
            }
        });
        clear.setOnMouseClicked((event) -> {
            input.setText("");
            formula.setText("");
            result.setText("");
            instruction.setText("");
        });
        dot.setOnMouseClicked((event) -> input.setText((input.getText() + ".")));
        equalsSign.setOnMouseClicked((event) -> {
            Double res = inputParser.expressionEvaluation(input.getText());
            if (res.equals(Double.NaN)) {
                instruction.setText("Is not a valid expression, press delete to fix the expression and try again");
            } else if (Double.isInfinite(res)) {
                instruction.setText("The expression is too long");
            } else {
                formula.setText(input.getText());
                exprMem.addToMemory(input.getText());
                listProperty.set(FXCollections.observableArrayList(exprMem.getMemExpressionsArrayList()));
                input.setText("");
                result.setText("" + res);
                instruction.setText("");
            }
        });
        answer.setOnMouseClicked((event) -> input.setText((input.getText() + result.getText())));
        leftBracket.setOnMouseClicked((event) -> input.setText((input.getText() + "(")));
        rightBracket.setOnMouseClicked((event) -> input.setText((input.getText() + ")")));

        setMemoryLimit.setOnMouseClicked((event) -> {
            exprMem.setMemoryLimit((int) memLimitSlider.getValue());
            currentMemLimit.setText("The memory limit is: " + exprMem.getMemoryLimit());
            listProperty.set(FXCollections.observableArrayList(exprMem.getMemExpressionsArrayList()));
        });
        
        clearMemory.setOnMouseClicked((event) -> {
            exprMem.clearMemory();
            listProperty.set(FXCollections.observableArrayList(exprMem.getMemExpressionsArrayList()));
        });

        copyMemExpression.setOnMouseClicked((event) -> {
            int memSelectionIndex = memoryList.getSelectionModel().selectedIndexProperty().get();
            if (memSelectionIndex >= 0 && !listProperty.isEmpty()) {
                input.setText(input.getText() + listProperty.get(memSelectionIndex));
            }
        });

        copyDbExpression.setOnMouseClicked((event) -> {
            int selectedIdx = databaseList.getSelectionModel().selectedIndexProperty().get();
            if (selectedIdx >= 0 && !dbListProperty.isEmpty()) {
                input.setText(input.getText() + dbListProperty.get(selectedIdx).getExpression());
            }
        });

        saveExpression.setOnMouseClicked((event) -> {
            int memSelectionIndex = memoryList.getSelectionModel().selectedIndexProperty().get();
            if (memSelectionIndex >= 0 && !listProperty.isEmpty()) {
                try {
                    eDao.save(listProperty.get(memSelectionIndex));
                    saveStatus.setText("Save completed successfully");
                    saveStatus.setTextFill(Color.GREEN);
                } catch (SQLException e) {
                    saveStatus.setText("Save failed");
                    saveStatus.setTextFill(Color.RED);
                }
                
                delayedClear(saveStatus);
            }

        });

        getAllSavedExpressions.setOnMouseClicked((event) -> {
            try {
                dbListProperty.set(FXCollections.observableArrayList(eDao.findAll()));
                retrievalStatus.setText("All expressions retrieved successfully");
                retrievalStatus.setTextFill(Color.GREEN);
            } catch (SQLException e) {
                retrievalStatus.setText("Retrieval failed");
                retrievalStatus.setTextFill(Color.RED);
            }
            
            delayedClear(retrievalStatus);
        });

        saveAllExpressions.setOnMouseClicked((event) -> {
            if (!listProperty.isEmpty()) {
                try {
                    eDao.saveAll(exprMem.getMemExpressionsArrayList());
                    saveAllStatus.setText("All saves completed successfully");
                    saveAllStatus.setTextFill(Color.GREEN);
                } catch (SQLException e) {
                    saveAllStatus.setText("Save all failed");
                    saveAllStatus.setTextFill(Color.RED);
                }
                
                delayedClear(saveAllStatus);
            } 
        });

        deleteExpression.setOnMouseClicked((event) -> {
            int selectedIdx = databaseList.getSelectionModel().selectedIndexProperty().get();
            if (selectedIdx >= 0 && dbListProperty.size() > selectedIdx) {
                try {
                    eDao.delete(dbListProperty.get(selectedIdx).getId());
                    dbListProperty.set(FXCollections.observableArrayList(eDao.findAll()));
                    deleteStatus.setText("Delete successful");
                    deleteStatus.setTextFill(Color.GREEN);
                } catch (SQLException e) {
                    deleteStatus.setText("Delete failed");
                    deleteStatus.setTextFill(Color.RED);
                }
                
                delayedClear(deleteStatus);
            }
        });
        
        searchExpression.setOnMouseClicked((event) -> {
            String partialExpression = "";
            if (!partialExpression.equals("")) {
                try {
                    matchesListProperty.set(FXCollections.observableArrayList(eDao.findMatches(partialExpression)));
                } catch (SQLException e) {

                }
            }
            
        });

        Scene scene = new Scene(hbox);
        stage.setTitle("CalculatorApp");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Closing the CalculatorApp...");
    }

    private void delayedClear(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10.0), ev -> {
            label.setText("");
            label.setTextFill(Color.BLACK);
        }));
        timeline.play();
    }

    private void createSetMemoryLimitSlider() {
        currentMemLimit = new Label("The memory limit is: " + exprMem.getMemoryLimit());
        setMemoryLabel = new Label("Set memory limit using the slider");
        memLimitSlider = new Slider();
        memLimitSlider.setMin(0);
        memLimitSlider.setMax(25);
        memLimitSlider.setValue(10);
        memLimitSlider.setShowTickLabels(true);
        memLimitSlider.setShowTickMarks(true);
        memLimitSlider.setSnapToTicks(true);
        memLimitSlider.setMajorTickUnit(2);
    }

    private void createAllButtons() {
        mainGridButtons = new ArrayList<>();
        auxGridButtons = new ArrayList<>();
        createNumberButtons();
        createMathOperatorButtons();
        createOtherButtons();
        createMemoryANdDataBaseButtons();
        createProgramFunctionalityButtons();
    }

    private void setButtonSize(ArrayList<Button> buttons, int divider) {
        buttons.forEach(b -> b.setPrefSize(width / divider, height / divider));
    }

    private void createNumberButtons() {
        numbers = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numbers.add(new Button("" + i));
        }
        mainGridButtons.addAll(numbers);
    }

    private void createMathOperatorButtons() {
        plus = new Button("+");
        minus = new Button("-");
        multiply = new Button("*");
        divide = new Button("/");
        exponent = new Button("^");
        percent = new Button("%");
        modulo = new Button("mod");

        mainGridButtons.add(plus);
        mainGridButtons.add(minus);
        mainGridButtons.add(multiply);
        mainGridButtons.add(divide);
        mainGridButtons.add(percent);

        auxGridButtons.add(modulo);
        auxGridButtons.add(exponent);
    }

    private void createOtherButtons() {
        delete = new Button("Delete");
        clear = new Button("Clear");
        dot = new Button(".");
        equalsSign = new Button("=");
        answer = new Button("Ans");
        leftBracket = new Button("(");
        rightBracket = new Button(")");
        absValue = new Button("abs");

        mainGridButtons.add(delete);
        mainGridButtons.add(clear);
        mainGridButtons.add(dot);
        mainGridButtons.add(equalsSign);
        mainGridButtons.add(answer);

        auxGridButtons.add(leftBracket);
        auxGridButtons.add(rightBracket);
        auxGridButtons.add(absValue);
    }

    private void createMemoryANdDataBaseButtons() {
        setMemoryLimit = new Button("Set memory limit");
        clearMemory = new Button("Clear memory");
        copyMemExpression = new Button("Copy the selected memory expression to input");
        saveExpression = new Button("Save the selected expression");
        saveAllExpressions = new Button("Save all expressions in memory");
        saveStatus = new Label("");
        saveAllStatus = new Label("");

        databaseLabel = new Label("Expressions in the database:");
        copyDbExpression = new Button("Copy selected database expression to input");
        getAllSavedExpressions = new Button("Retrieve all saved expressions");
        retrievalStatus = new Label("");
        searchExpression = new Button("Search for an expression");
        deleteExpression = new Button("Delete the selected expression from database");
        deleteStatus = new Label("");
    }

    private void createProgramFunctionalityButtons() {
        closeButton = new Button("Close the application");
        yesButton = new Button("Yes");
        noButton = new Button("No");
    }
}
