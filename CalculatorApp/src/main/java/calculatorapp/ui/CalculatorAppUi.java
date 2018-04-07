
package calculatorapp.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

        BorderPane borderpane = new BorderPane();
        borderpane.setTop(new TextArea(""));
        borderpane.setPrefSize(width, height);
        GridPane mainGrid = new GridPane();
        borderpane.setBottom(mainGrid);
//        mainGrid.setPrefSize(width, height);
//        mainGrid.setGridLinesVisible(true);
        
        createNumberButtons(6);
        createMathOperatorButtons(6);
        createOtherButtons(6);
        
        mainGrid.addRow(0, numbers.get(7), numbers.get(8), numbers.get(9), otherOperators.get(0), otherOperators.get(1));
        mainGrid.addRow(1, numbers.get(4), numbers.get(5), numbers.get(6), mathOperators.get(0), mathOperators.get(1));
        mainGrid.addRow(2, numbers.get(1), numbers.get(2), numbers.get(3), mathOperators.get(2), mathOperators.get(3));
        mainGrid.addRow(3, numbers.get(0), otherOperators.get(2), mathOperators.get(4), mathOperators.get(5), otherOperators.get(3));
        
        
        
        Scene scene = new Scene(borderpane);
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
