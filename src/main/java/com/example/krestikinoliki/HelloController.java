package com.example.krestikinoliki;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private GridPane gridPane;

    private char nowSym = 'x';

    private final char[][] gameField = new char[3][3];

    private boolean isGame = true;

    @FXML
    void BtnClick(ActionEvent event) {
        Button btn = (Button)event.getSource();
        if(!isGame || !Objects.equals(btn.getText(), "")) return;
        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
        gameField[rowIndex][columnIndex] = nowSym;
        btn.setText(String.valueOf(nowSym));
        if(gameField[0][0] == gameField[0][1] && gameField[0][0] == gameField[0][2] && (gameField[0][0] == 'x' || gameField[0][0] == '0')){
            showWinnerDialog(btn.getText());
        } else if(gameField[1][0] == gameField[1][1] && gameField[1][0] == gameField[1][2] && (gameField[1][0] == 'x' || gameField[1][0] == '0')){
            showWinnerDialog(btn.getText());
        } else if(gameField[2][0] == gameField[2][1] && gameField[2][0] == gameField[2][2] && (gameField[2][0] == 'x' || gameField[2][0] == '0')){
            showWinnerDialog(btn.getText());
        } else if(gameField[0][0] == gameField[1][0] && gameField[0][0] == gameField[2][0] && (gameField[0][0] == 'x' || gameField[0][0] == '0')){
            showWinnerDialog(btn.getText());
        } else if(gameField[0][1] == gameField[1][1] && gameField[0][1] == gameField[2][1] && (gameField[0][1] == 'x' || gameField[0][1] == '0')){
            showWinnerDialog(btn.getText());
        } else if(gameField[0][2] == gameField[1][2] && gameField[0][2] == gameField[2][2] && (gameField[0][2] == 'x' || gameField[0][2] == '0')){
            showWinnerDialog(btn.getText());
        } else if(gameField[0][0] == gameField[1][1] && gameField[0][0] == gameField[2][2] && (gameField[0][0] == 'x' || gameField[0][0] == '0')){
            showWinnerDialog(btn.getText());
        } else if(gameField[0][2] == gameField[1][1] && gameField[0][2] == gameField[2][0] && (gameField[0][2] == 'x' || gameField[0][2] == '0')){
            showWinnerDialog(btn.getText());
        } else if(checkForDraw()){
            showDrawDialog();
        }
        nowSym = nowSym == 'x' ? '0' : 'x';
    }

    boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    void showWinnerDialog(String symbol) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "У нас есть победитель " + symbol, ButtonType.OK);
        alert.showAndWait();
        resetGame();
    }

    void showDrawDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ничья!", ButtonType.OK);
        alert.showAndWait();
        resetGame();
    }

    void resetGame() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setText("");
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameField[i][j] = '\0';
            }
        }
        isGame = true;
    }

    @FXML
    void initialize() {
    }
}