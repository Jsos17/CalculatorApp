
package calculatorapp.ui;

import calculatorapp.domain.CalculatorService;
import calculatorapp.domain.InputHandler;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jpssilve
 */
public class CalculatorAppUi extends Application {
    private ArrayList<Button> numbers;
    private ArrayList<Button> mathOperators;
    private ArrayList<Button> otherOperators;
    private CalculatorService calc;
    private InputHandler inputHandlr;
    private int width;
    private int height;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(CalculatorAppUi.class);
    }
    
    @Override
    public void init() throws Exception {
        calc = new CalculatorService();
        inputHandlr = new InputHandler(calc);
    }

    @Override
    public void start(Stage stage) throws Exception {
        width = 500;
        height = 500;
        
        int h2 = 30;
        
        VBox vBoxLeft = new VBox();
        Label inputLabel = new Label("Input: ");
        inputLabel.setPrefHeight(h2);
        Label exprLabel = new Label("Expression:");
        exprLabel.setPrefHeight(h2);
        Label resLabel = new Label("Result: ");
        resLabel.setPrefHeight(h2);
        Label instrLabel = new Label("Instructions: ");
        instrLabel.setPrefHeight(h2);
        vBoxLeft.getChildren().add(0, inputLabel);
        vBoxLeft.getChildren().add(1, exprLabel);
        vBoxLeft.getChildren().add(2, resLabel);
        vBoxLeft.getChildren().add(3, instrLabel);
        
        VBox vBoxRight = new VBox();
        vBoxRight.setPrefSize(width, height);

        GridPane mainGrid = new GridPane();
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
        
        GridPane auxGrid = new GridPane();
        vBoxLeft.getChildren().add(4, auxGrid);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(vBoxLeft, vBoxRight);
        
        vBoxRight.getChildren().add(0, input);
        vBoxRight.getChildren().add(1, formula);
        vBoxRight.getChildren().add(2, result);
        vBoxRight.getChildren().add(3, instruction);
        vBoxRight.getChildren().add(4, mainGrid);

        int divider = 7;
        createNumberButtons(divider);
        createMathOperatorButtons(divider);
        createOtherButtons(divider);
        
        auxGrid.addColumn(0, mathOperators.get(5), mathOperators.get(6), mathOperators.get(7));
        auxGrid.addColumn(1, otherOperators.get(5), otherOperators.get(6));
        auxGrid.addColumn(2, otherOperators.get(7));
        
        mainGrid.addRow(0, numbers.get(7), numbers.get(8), numbers.get(9), otherOperators.get(0), otherOperators.get(1));
        mainGrid.addRow(1, numbers.get(4), numbers.get(5), numbers.get(6), mathOperators.get(0), mathOperators.get(1));
        mainGrid.addRow(2, numbers.get(1), numbers.get(2), numbers.get(3), mathOperators.get(2), mathOperators.get(3));
        mainGrid.addRow(3, numbers.get(0), otherOperators.get(2), mathOperators.get(4), otherOperators.get(4),otherOperators.get(3));
        
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
        
        mathOperators.get(0).setOnMouseClicked((event) -> input.setText(input.getText() + "*"));
        mathOperators.get(1).setOnMouseClicked((event) -> input.setText(input.getText() + "/"));
        mathOperators.get(2).setOnMouseClicked((event) -> input.setText(input.getText() + "+"));
        mathOperators.get(3).setOnMouseClicked((event) -> input.setText(input.getText() + "-"));
//        mathOperators.get(4).setOnMouseClicked((event) -> expression.setText(expression.getText() + "%"));
//        mathOperators.get(5).setOnMouseClicked((event) -> expression.setText(expression.getText() + "mod"));
        
        otherOperators.get(0).setOnMouseClicked((event) -> {
            String s = input.getText();
            if (s.length() != 0) {input.setText(input.getText().substring(0, input.getText().length()-1));}
        });
        otherOperators.get(1).setOnMouseClicked((event) -> {
            input.setText("");
            formula.setText("");
            result.setText("");
            instruction.setText("");
        });
        otherOperators.get(2).setOnMouseClicked((event) -> input.setText((input.getText() + ".")));
        otherOperators.get(3).setOnMouseClicked((event) -> {
            Double res = inputHandlr.expressionEvaluation(input.getText());
            if (res.equals(Double.NaN)) {
                instruction.setText("Is not a valid expression, press delete to fix the expression and try again");
            } else {
                formula.setText(input.getText());
                input.setText("");
                result.setText("" + res);
                instruction.setText("");
            }
            
        });
        otherOperators.get(4).setOnMouseClicked((event) ->  input.setText((input.getText() + result.getText())));
        otherOperators.get(5).setOnMouseClicked((event) ->  input.setText((input.getText() + "(")));
        otherOperators.get(6).setOnMouseClicked((event) ->  input.setText((input.getText() + ")")));
        
        
        Scene scene = new Scene(hbox);
        stage.setTitle("CalculatorApp");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Closing the CalculatorApp...");
    }
    
    
    private void setButtonSize(ArrayList<Button> buttons, int divider) {
        buttons.forEach(b -> b.setPrefSize(width / divider, height / divider));
    }
    
    private void createNumberButtons(int sizeDivider) {
        numbers = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numbers.add(new Button("" + i));
        }
        setButtonSize(numbers,  sizeDivider);
    }
    
    private void createMathOperatorButtons(int sizeDivider) {
        mathOperators = new ArrayList<>();
        mathOperators.add(new Button("*"));
        mathOperators.add(new Button("/"));
        mathOperators.add(new Button("+"));
        mathOperators.add(new Button("-"));
        mathOperators.add(new Button("%"));
        mathOperators.add(new Button("mod"));
        mathOperators.add(new Button("x^2"));
        mathOperators.add(new Button("x^y"));
        
        setButtonSize(mathOperators, sizeDivider);
    }
    
    private void createOtherButtons(int sizeDivider) {
        otherOperators = new ArrayList<>();
        otherOperators.add(new Button("Delete"));
        otherOperators.add(new Button("Clear"));
        otherOperators.add(new Button("."));
        otherOperators.add(new Button("="));
        otherOperators.add(new Button("Ans"));
        otherOperators.add(new Button("("));
        otherOperators.add(new Button(")"));
        otherOperators.add(new Button("Abs"));
        
        setButtonSize(otherOperators, sizeDivider);
    }
}
