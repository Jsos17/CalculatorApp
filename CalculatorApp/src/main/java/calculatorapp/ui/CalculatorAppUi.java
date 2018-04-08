
package calculatorapp.ui;

import calculatorapp.domain.CalculatorService;
import calculatorapp.domain.InputHandler;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private int width;
    private int height;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(CalculatorAppUi.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        width = 400;
        height = 400;
        CalculatorService calc = new CalculatorService();
        InputHandler inHandl = new InputHandler(calc);
        
        VBox vbox = new VBox();
        vbox.setPrefSize(width, height);

        GridPane mainGrid = new GridPane();
        TextField expression = new TextField();
        expression.setEditable(false);
        TextField result = new TextField();
        result.setEditable(false);
//        BorderPane borderpane = new BorderPane();
//        borderpane.setPrefSize(width, height);
//        borderpane.setBottom(mainGrid);
//        borderpane.setTop(expression);
        
        vbox.getChildren().add(0, expression);
        vbox.getChildren().add(1, result);
        vbox.getChildren().add(2, mainGrid);
//        mainGrid.setPrefSize(width, height);
//        mainGrid.setGridLinesVisible(true);
        int divider = 5;
        createNumberButtons(divider);
        createMathOperatorButtons(divider);
        createOtherButtons(divider);
        
        mainGrid.addRow(0, numbers.get(7), numbers.get(8), numbers.get(9), otherOperators.get(0), otherOperators.get(1));
        mainGrid.addRow(1, numbers.get(4), numbers.get(5), numbers.get(6), mathOperators.get(0), mathOperators.get(1));
        mainGrid.addRow(2, numbers.get(1), numbers.get(2), numbers.get(3), mathOperators.get(2), mathOperators.get(3));
        mainGrid.addRow(3, numbers.get(0), otherOperators.get(2), mathOperators.get(4), otherOperators.get(4),otherOperators.get(3));
        
        numbers.get(0).setOnMouseClicked((event) -> expression.setText(expression.getText() + 0));
        numbers.get(1).setOnMouseClicked((event) -> expression.setText(expression.getText() + 1));
        numbers.get(2).setOnMouseClicked((event) -> expression.setText(expression.getText() + 2));
        numbers.get(3).setOnMouseClicked((event) -> expression.setText(expression.getText() + 3));
        numbers.get(4).setOnMouseClicked((event) -> expression.setText(expression.getText() + 4));
        numbers.get(5).setOnMouseClicked((event) -> expression.setText(expression.getText() + 5));
        numbers.get(6).setOnMouseClicked((event) -> expression.setText(expression.getText() + 6));
        numbers.get(7).setOnMouseClicked((event) -> expression.setText(expression.getText() + 7));
        numbers.get(8).setOnMouseClicked((event) -> expression.setText(expression.getText() + 8));
        numbers.get(9).setOnMouseClicked((event) -> expression.setText(expression.getText() + 9));
        
        mathOperators.get(0).setOnMouseClicked((event) -> expression.setText(expression.getText() + "*"));
        mathOperators.get(1).setOnMouseClicked((event) -> expression.setText(expression.getText() + "/"));
        mathOperators.get(2).setOnMouseClicked((event) -> expression.setText(expression.getText() + "+"));
        mathOperators.get(3).setOnMouseClicked((event) -> expression.setText(expression.getText() + "-"));
//        mathOperators.get(4).setOnMouseClicked((event) -> expression.setText(expression.getText() + "%"));
//        mathOperators.get(5).setOnMouseClicked((event) -> expression.setText(expression.getText() + "mod"));
        
        otherOperators.get(0).setOnMouseClicked((event) -> {
            expression.setText("");
            result.setText("");
        });
        otherOperators.get(1).setOnMouseClicked((event) -> expression.setText(""));
//        otherOperators.get(2).setOnMouseClicked((event) -> expression.setText((expression.getText() + ".")));
        otherOperators.get(3).setOnMouseClicked((event) -> {
            result.setText("" + inHandl.evaluateExpressionDouble(expression.getText()));
            expression.setText("");
        });
        otherOperators.get(4).setOnMouseClicked((event) ->  expression.setText((expression.getText() + result.getText())));
        
           
        
        Scene scene = new Scene(vbox);
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
        
        setButtonSize(mathOperators, sizeDivider);
    }
    
    private void createOtherButtons(int sizeDivider) {
        otherOperators = new ArrayList<>();
        otherOperators.add(new Button("Delete"));
        otherOperators.add(new Button("Clear"));
        otherOperators.add(new Button("."));
        otherOperators.add(new Button("="));
        otherOperators.add(new Button("Ans"));
        
        setButtonSize(otherOperators, sizeDivider);
    }
}
