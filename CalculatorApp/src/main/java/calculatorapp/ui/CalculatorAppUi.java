package calculatorapp.ui;

import calculatorapp.dao.ExpressionDao;
import calculatorapp.dao.MathDatabase;
import calculatorapp.logic.CalculatorService;
import calculatorapp.logic.Expression;
import calculatorapp.logic.ExpressionEvaluator;
import calculatorapp.logic.ExpressionMemory;
import calculatorapp.logic.InputParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
 *
 * It provides calculator functionality and database functionality alongside it.
 *
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
    private Button sqrt;
    private Button sin;
    private Button cos;
    private Button tan;
    private Button ln;
    private Button log;
    private Button delete;
    private Button clear;
    private Button dot;
    private Button equalsSign;
    private Button answer;
    private Button leftBracket;
    private Button rightBracket;
    private Button absValue;
    private Button signMinus;
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
    private Label expressionCount;
    private Button copyDbExpression;
    private Button getAllSavedExpressions;
    private Label retrievalStatus;

    private TextField search;
    private Label searchHelp;
    private Button searchExpression;
    private Label searchStatus;
    private Button copyMatch;
    private Button deleteExpression;
    private Label deleteStatus;

    private TextField input;
    private TextField formula;
    private TextField result;
    private TextField instruction;

    private Button yesButton;
    private Button noButton;

    private CalculatorService calc;
    private InputParser inputParser;
    private ExpressionEvaluator exprEval;
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
     * Creates a database if it does not already exist, by establishing a
     * connection to a SQL database.
     *
     * The name of the database is retrieved from a file config.properties which
     * the user must create.
     *
     * If the configuration file is not present, then default database is
     * created.
     *
     * @throws Exception if an exception occurs
     */
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        String databaseAddress = "";
        try {
            properties.load(new FileInputStream("config.properties"));
            databaseAddress = properties.getProperty("mathDatabase");
        } catch (IOException e) {
            System.out.println("config.properties missing, default name mathAlternative.db is used");
            try {
                File mathDBFile = new File("mathAlternative.db");
                mathDBFile.createNewFile();
                databaseAddress = mathDBFile.getCanonicalPath();
                System.out.println("File mathAlternative.db created at " + mathDBFile.getCanonicalPath());
            } catch (IOException ioe) {
                System.out.println("File creation failed");
                System.out.println("Database functionality is absent");
            }
        }

        MathDatabase math = new MathDatabase("jdbc:sqlite:" + databaseAddress);
        try (Connection conn = math.getConnection()) {
            math.initDatabase();
            System.out.println("Database initialization/connection success");
        } catch (SQLException e) {
            System.out.println("Database initialization/connection failure ");
        }

        eDao = new ExpressionDao(math);
        calc = new CalculatorService();
        inputParser = new InputParser();
        exprEval = new ExpressionEvaluator(calc, inputParser);
        exprMem = new ExpressionMemory(exprEval, 10);
    }

    /**
     * The standard method for the gui.
     *
     * Everything related to the graphical user interface happens in this
     * method, or is initiated by this method.
     *
     * @param stage the stage to be shown
     * @throws Exception if an exception occurs
     */
    @Override
    public void start(Stage stage) throws Exception {
        width = 500;
        height = 500;

        createAllButtons();
        int divider = 8;
        setButtonSize(mainGridButtons, divider);
        setButtonSize(auxGridButtons, divider);

        GridPane mainGrid = new GridPane();
        GridPane auxGrid = new GridPane();
        createGrids(mainGrid, auxGrid);

        ListView<Expression> databaseMatchesList = new ListView();
        ListProperty<Expression> dbMatchesListProperty = new SimpleListProperty<>();
        databaseMatchesList.itemsProperty().bind(dbMatchesListProperty);

        ListView<String> memoryList = new ListView<>();
        ListProperty<String> memListProperty = new SimpleListProperty<>();
        memoryList.itemsProperty().bind(memListProperty);

        ListView<Expression> databaseList = new ListView();
        ListProperty<Expression> dbListProperty = new SimpleListProperty<>();
        databaseList.itemsProperty().bind(dbListProperty);

        int rowHeight = 30;

        VBox vBoxSearch = new VBox();
        createVBoxSearch(vBoxSearch);
        VBox vBoxLeft = new VBox();
        createVBoxLeft(vBoxLeft, rowHeight, auxGrid, vBoxSearch);
        VBox vBoxCenter = new VBox();
        createVBoxCenter(vBoxCenter, rowHeight, mainGrid, databaseMatchesList);
        createSetMemoryLimitSlider();
        VBox vBoxRight = new VBox();
        createVBoxRight(vBoxRight, memoryList);
        VBox vBoxRightest = new VBox();
        createVBoxRightest(vBoxRightest, databaseList);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(vBoxLeft, vBoxCenter, vBoxRight, vBoxRightest);
        hbox.setSpacing(20);

        numbers.get(0).setOnMouseClicked((event) -> input.appendText("0"));
        numbers.get(1).setOnMouseClicked((event) -> input.appendText("1"));
        numbers.get(2).setOnMouseClicked((event) -> input.appendText("2"));
        numbers.get(3).setOnMouseClicked((event) -> input.appendText("3"));
        numbers.get(4).setOnMouseClicked((event) -> input.appendText("4"));
        numbers.get(5).setOnMouseClicked((event) -> input.appendText("5"));
        numbers.get(6).setOnMouseClicked((event) -> input.appendText("6"));
        numbers.get(7).setOnMouseClicked((event) -> input.appendText("7"));
        numbers.get(8).setOnMouseClicked((event) -> input.appendText("8"));
        numbers.get(9).setOnMouseClicked((event) -> input.appendText("9"));

        multiply.setOnMouseClicked((event) -> input.appendText("*"));
        divide.setOnMouseClicked((event) -> input.appendText("/"));
        plus.setOnMouseClicked((event) -> input.appendText("+"));
        minus.setOnMouseClicked((event) -> input.appendText("-"));
        exponent.setOnMouseClicked((event) -> input.appendText("^"));

        sqrt.setOnMouseClicked((event) -> input.appendText("sqrt("));
        log.setOnMouseClicked((event) -> input.appendText("log("));
        ln.setOnMouseClicked((event) -> input.appendText("ln("));
        sin.setOnMouseClicked((event) -> input.appendText("sin("));
        cos.setOnMouseClicked((event) -> input.appendText("cos("));
        tan.setOnMouseClicked((event) -> input.appendText("tan("));
        absValue.setOnMouseClicked((event) -> input.appendText("abs("));
        signMinus.setOnMouseClicked((event) -> input.appendText("neg("));
        percent.setOnMouseClicked((event) -> input.appendText("%("));

        delete.setOnMouseClicked((event) -> {
            int inputLength = input.getLength();
            if (inputLength > 0) {
                input.deleteText(inputLength - 1, inputLength);
            }
        });

        clear.setOnMouseClicked((event) -> {
            input.clear();
            formula.clear();
            result.clear();
            instruction.clear();
        });

        dot.setOnMouseClicked((event) -> input.appendText((".")));

        equalsSign.setOnMouseClicked((event) -> {
            Double res = exprEval.expressionEvaluation(input.getText());
            if (res.equals(Double.NaN)) {
                instruction.setText("Is not a valid expression, press delete to fix the expression and try again");
            } else if (Double.isInfinite(res)) {
                instruction.setText("The expression is too long");
            } else {
                formula.setText(input.getText());
                exprMem.addToMemory(input.getText());
                memListProperty.set(FXCollections.observableArrayList(exprMem.getMemExpressionsArrayList()));
                input.clear();
                result.setText(Double.toString(res));
                instruction.clear();
            }
        });

        answer.setOnMouseClicked((event) -> {
            String res = result.getText();
            if (!res.equals("")) {
                if (res.charAt(0) == '-' && res.length() >= 2) {
                    input.appendText("neg(" + res.substring(1) + ")");
                } else {
                    input.appendText(res);
                }
            }
        });

        leftBracket.setOnMouseClicked((event) -> input.appendText("("));
        rightBracket.setOnMouseClicked((event) -> input.appendText(")"));

        setMemoryLimit.setOnMouseClicked((event) -> {
            exprMem.setMemoryLimit((int) memLimitSlider.getValue());
            currentMemLimit.setText("The memory limit is: " + exprMem.getMemoryLimit());
            memListProperty.set(FXCollections.observableArrayList(exprMem.getMemExpressionsArrayList()));
        });

        clearMemory.setOnMouseClicked((event) -> {
            exprMem.clearMemory();
            memListProperty.set(FXCollections.observableArrayList(exprMem.getMemExpressionsArrayList()));
        });

        copyMemExpression.setOnMouseClicked((event) -> {
            int memSelectionIndex = memoryList.getSelectionModel().selectedIndexProperty().get();
            if (memSelectionIndex >= 0 && memSelectionIndex < memListProperty.size()) {
                input.appendText(memListProperty.get(memSelectionIndex));
            }
        });

        copyDbExpression.setOnMouseClicked((event) -> {
            int selectedIdx = databaseList.getSelectionModel().selectedIndexProperty().get();
            if (selectedIdx >= 0 && selectedIdx < dbListProperty.size()) {
                input.appendText(dbListProperty.get(selectedIdx).getExpression());
            }
        });

        saveExpression.setOnMouseClicked((event) -> {
            int memSelectionIndex = memoryList.getSelectionModel().selectedIndexProperty().get();
            if (memSelectionIndex >= 0 && memSelectionIndex < memListProperty.size()) {
                try {
                    eDao.save(memListProperty.get(memSelectionIndex));
                    saveStatus.setText("Save completed successfully");
                    saveStatus.setTextFill(Color.GREEN);
                    countExpressions();
                    dbListProperty.set(FXCollections.observableArrayList(eDao.findAll()));
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
            if (!memListProperty.isEmpty()) {
                try {
                    eDao.saveAll(exprMem.getMemExpressionsArrayList());
                    saveAllStatus.setText("All saves completed successfully");
                    saveAllStatus.setTextFill(Color.GREEN);
                    countExpressions();
                    dbListProperty.set(FXCollections.observableArrayList(eDao.findAll()));
                } catch (SQLException e) {
                    saveAllStatus.setText("Save all failed");
                    saveAllStatus.setTextFill(Color.RED);
                }

                delayedClear(saveAllStatus);
            }
        });

        deleteExpression.setOnMouseClicked((event) -> {
            int selectedIdx = databaseList.getSelectionModel().selectedIndexProperty().get();
            if (selectedIdx >= 0 && selectedIdx < dbListProperty.size()) {
                try {
                    eDao.delete(dbListProperty.get(selectedIdx).getId());
                    dbListProperty.set(FXCollections.observableArrayList(eDao.findAll()));
                    deleteStatus.setText("Delete successful");
                    deleteStatus.setTextFill(Color.GREEN);
                    countExpressions();
                } catch (SQLException e) {
                    deleteStatus.setText("Delete failed");
                    deleteStatus.setTextFill(Color.RED);
                }

                delayedClear(deleteStatus);
            }
        });

        searchExpression.setOnMouseClicked((event) -> {
            String partialExpression = search.getText();
            if (!partialExpression.equals("")) {
                try {
                    dbMatchesListProperty.set(FXCollections.observableArrayList(eDao.findMatches(partialExpression)));
                    searchStatus.setText("Search completed");
                    searchStatus.setTextFill(Color.GREEN);
                } catch (SQLException e) {
                    searchStatus.setText("Search failed");
                    searchStatus.setTextFill(Color.RED);
                }

                delayedClear(searchStatus);
            }
        });

        copyMatch.setOnMouseClicked((event) -> {
            int selectedIdx = databaseMatchesList.getSelectionModel().selectedIndexProperty().get();
            if (selectedIdx >= 0 && selectedIdx < dbMatchesListProperty.size()) {
                input.appendText(dbMatchesListProperty.get(selectedIdx).getExpression());
            }
        });

        Scene calculatorScene = new Scene(hbox);
        stage.setTitle("CalculatorApp");
        stage.setScene(calculatorScene);
        stage.show();

        GridPane exitGrid = new GridPane();
        Label unsavedProgress = new Label("Upon exit, all unsaved progress will be lost");
        Label exitQuestion = new Label("Are you sure you want to close the program?");
        exitGrid.add(unsavedProgress, 0, 0);
        exitGrid.add(exitQuestion, 0, 1);
        exitGrid.add(yesButton, 0, 2);
        exitGrid.add(noButton, 1, 2);
        exitGrid.setVgap(10);

        Scene exitScene = new Scene(exitGrid);
        Stage exitStage = new Stage();
        exitStage.setScene(exitScene);

        stage.setOnCloseRequest((event) -> {
            event.consume();
            exitStage.show();
        });

        exitStage.setOnCloseRequest((event) -> {
            event.consume();
        });

        noButton.setOnMouseClicked((event) -> {
            exitStage.close();
        });

        yesButton.setOnMouseClicked((event) -> {
            stage.close();
            exitStage.close();
        });
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

    private void createAllButtons() {
        mainGridButtons = new ArrayList<>();
        auxGridButtons = new ArrayList<>();
        createNumberButtons();
        createMathOperatorButtons();
        createFunctionButtons();
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

        mainGridButtons.add(plus);
        mainGridButtons.add(minus);
        mainGridButtons.add(multiply);
        mainGridButtons.add(divide);

        auxGridButtons.add(exponent);
    }

    private void createFunctionButtons() {
        sqrt = new Button("sqrt()");
        sin = new Button("sin()");
        cos = new Button("cos()");
        tan = new Button("tan()");
        ln = new Button("ln()");
        log = new Button("log()");
        absValue = new Button("abs()");
        signMinus = new Button("neg()");
        percent = new Button("%()");

        mainGridButtons.add(percent);

        auxGridButtons.add(sqrt);
        auxGridButtons.add(sin);
        auxGridButtons.add(cos);
        auxGridButtons.add(tan);
        auxGridButtons.add(ln);
        auxGridButtons.add(log);
        auxGridButtons.add(absValue);
        auxGridButtons.add(signMinus);
    }

    private void createOtherButtons() {
        delete = new Button("Delete");
        clear = new Button("Clear");
        dot = new Button(".");
        equalsSign = new Button("=");
        answer = new Button("Ans");
        leftBracket = new Button("(");
        rightBracket = new Button(")");

        mainGridButtons.add(delete);
        mainGridButtons.add(clear);
        mainGridButtons.add(dot);
        mainGridButtons.add(equalsSign);
        mainGridButtons.add(answer);

        auxGridButtons.add(leftBracket);
        auxGridButtons.add(rightBracket);
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
        expressionCount = new Label("");
        countExpressions();
        copyDbExpression = new Button("Copy the selected database expression to input");
        getAllSavedExpressions = new Button("Retrieve all saved expressions");
        retrievalStatus = new Label("");
        deleteExpression = new Button("Delete the selected expression from the database");
        deleteStatus = new Label("");

        search = new TextField();
        searchHelp = new Label("Type below to search:");
        searchExpression = new Button("Search for an expression");
        searchStatus = new Label("");
        copyMatch = new Button("Copy the selected match to input");
    }

    private void countExpressions() {
        try {
            expressionCount.setText("There are " + eDao.countExpressionsInDatabase() + " expressions in the database");
            expressionCount.setTextFill(Color.BLUE);
        } catch (SQLException e) {
            expressionCount.setText("Expressions in the database could not be counted");
        }
    }

    private void createProgramFunctionalityButtons() {
        yesButton = new Button("Yes");
        noButton = new Button("No");
    }

    private void createGrids(GridPane mainGrid, GridPane auxGrid) {
        mainGrid.addRow(0, numbers.get(7), numbers.get(8), numbers.get(9), delete, clear);
        mainGrid.addRow(1, numbers.get(4), numbers.get(5), numbers.get(6), multiply, divide);
        mainGrid.addRow(2, numbers.get(1), numbers.get(2), numbers.get(3), plus, minus);
        mainGrid.addRow(3, numbers.get(0), dot, percent, answer, equalsSign);

        auxGrid.addColumn(0, exponent, sin, sqrt, absValue);
        auxGrid.addColumn(1, leftBracket, cos, ln, signMinus);
        auxGrid.addColumn(2, rightBracket, tan, log);
    }

    private void createVBoxSearch(VBox vBoxSearch) {
        vBoxSearch.setSpacing(5);
        vBoxSearch.getChildren().add(0, searchHelp);
        vBoxSearch.getChildren().add(1, search);
        vBoxSearch.getChildren().add(2, searchExpression);
        vBoxSearch.getChildren().add(3, searchStatus);
        vBoxSearch.getChildren().add(4, copyMatch);
    }

    private void createVBoxLeft(VBox vBoxLeft, int height, GridPane auxGrid, VBox vBoxSearch) {
        Label inputLabel = new Label("Input: ");
        inputLabel.setPrefHeight(height);
        Label exprLabel = new Label("Expression:");
        exprLabel.setPrefHeight(height);
        Label resLabel = new Label("Result: ");
        resLabel.setPrefHeight(height);
        Label instrLabel = new Label("Instructions: ");
        instrLabel.setPrefHeight(height);

        vBoxLeft.setSpacing(5);
        vBoxLeft.getChildren().add(0, inputLabel);
        vBoxLeft.getChildren().add(1, exprLabel);
        vBoxLeft.getChildren().add(2, resLabel);
        vBoxLeft.getChildren().add(3, instrLabel);
        vBoxLeft.getChildren().add(4, auxGrid);
        vBoxLeft.getChildren().add(5, vBoxSearch);
    }

    private void createVBoxCenter(VBox vBoxCenter, int h2, GridPane mainGrid, ListView<Expression> databaseMatchesList) {
        input = new TextField();
        input.setPrefHeight(h2);
        input.setEditable(false);
        formula = new TextField();
        formula.setPrefHeight(h2);
        formula.setEditable(false);
        result = new TextField();
        result.setPrefHeight(h2);
        result.setEditable(false);
        instruction = new TextField();
        instruction.setPrefHeight(h2);
        instruction.setEditable(false);

        vBoxCenter.setSpacing(5);
        vBoxCenter.getChildren().add(0, input);
        vBoxCenter.getChildren().add(1, formula);
        vBoxCenter.getChildren().add(2, result);
        vBoxCenter.getChildren().add(3, instruction);
        vBoxCenter.getChildren().add(4, mainGrid);
        vBoxCenter.getChildren().add(5, new Label("Search results:"));
        vBoxCenter.getChildren().add(6, databaseMatchesList);
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

    private void createVBoxRight(VBox vBoxRight, ListView<String> memoryList) {
        vBoxRight.setSpacing(5);
        vBoxRight.getChildren().add(0, currentMemLimit);
        vBoxRight.getChildren().add(1, setMemoryLabel);
        vBoxRight.getChildren().add(2, memLimitSlider);
        vBoxRight.getChildren().add(3, setMemoryLimit);
        vBoxRight.getChildren().add(4, new Label("Memory: Recently used"));
        vBoxRight.getChildren().add(5, clearMemory);
        vBoxRight.getChildren().add(6, saveAllExpressions);
        vBoxRight.getChildren().add(7, saveAllStatus);
        vBoxRight.getChildren().add(8, saveExpression);
        vBoxRight.getChildren().add(9, saveStatus);
        vBoxRight.getChildren().add(10, copyMemExpression);
        vBoxRight.getChildren().add(11, memoryList);
    }

    private void createVBoxRightest(VBox vBoxRightest, ListView<Expression> databaseList) {
        vBoxRightest.setSpacing(5);
        vBoxRightest.getChildren().add(0, databaseLabel);
        vBoxRightest.getChildren().add(1, expressionCount);
        vBoxRightest.getChildren().add(2, getAllSavedExpressions);
        vBoxRightest.getChildren().add(3, retrievalStatus);
        vBoxRightest.getChildren().add(4, copyDbExpression);
        vBoxRightest.getChildren().add(5, deleteExpression);
        vBoxRightest.getChildren().add(6, deleteStatus);
        vBoxRightest.getChildren().add(7, databaseList);
    }
}
