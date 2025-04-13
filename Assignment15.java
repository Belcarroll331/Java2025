# Create the JavaFX 5x5 TicTacToe game source code and zip it
from zipfile import ZipFile
import os

# Java code for 5x5 TicTacToe game using JavaFX
tictactoe_code = """
package tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TicTacToe5x5 extends Application {
    private static final int SIZE = 5;
    private static final int CELL_SIZE = 100;
    private char[][] board = new char[SIZE][SIZE];
    private boolean xTurn = true;
    private boolean gameEnded = false;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                StackPane cell = createCell(row, col);
                grid.add(cell, col, row);
            }
        }

        Scene scene = new Scene(grid, CELL_SIZE * SIZE, CELL_SIZE * SIZE);
        primaryStage.setTitle("TicTacToe 5x5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane createCell(int row, int col) {
        Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);

        Text text = new Text();
        text.setFont(Font.font(36));

        StackPane stack = new StackPane(rect, text);
        stack.setOnMouseClicked(e -> {
            if (!gameEnded && board[row][col] == '\\0') {
                board[row][col] = xTurn ? 'X' : 'O';
                text.setText(String.valueOf(board[row][col]));
                if (checkWin(row, col)) {
                    showWinAlert(board[row][col]);
                    gameEnded = true;
                } else {
                    xTurn = !xTurn;
                }
            }
        });

        return stack;
    }

    private boolean checkWin(int row, int col) {
        char symbol = board[row][col];
        return checkDirection(row, col, 1, 0, symbol) // Horizontal
            || checkDirection(row, col, 0, 1, symbol) // Vertical
            || checkDirection(row, col, 1, 1, symbol) // Diagonal \
            || checkDirection(row, col, 1, -1, symbol); // Diagonal /
    }

    private boolean checkDirection(int row, int col, int dx, int dy, char symbol) {
        int count = 1;

        for (int i = 1; i < 5; i++) {
            int r = row + i * dx, c = col + i * dy;
            if (r < 0 || r >= SIZE || c < 0 || c >= SIZE || board[r][c] != symbol) break;
            count++;
        }

        for (int i = 1; i < 5; i++) {
            int r = row - i * dx, c = col - i * dy;
            if (r < 0 || r >= SIZE || c < 0 || c >= SIZE || board[r][c] != symbol) break;
            count++;
        }

        return count >= 5;
    }

    private void showWinAlert(char winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Winner: " + winner);
        alert.setContentText("Congratulations!");
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
"""

# Directory setup
project_dir = "/mnt/data/TicTacToe5x5"
os.makedirs(f"{project_dir}/tictactoe", exist_ok=True)

# Save the Java file
java_file_path = f"{project_dir}/tictactoe/TicTacToe5x5.java"
with open(java_file_path, "w") as f:
    f.write(tictactoe_code.strip())

# Create a ZIP file
zip_file_path = "/mnt/data/TicTacToe5x5.zip"
with ZipFile(zip_file_path, 'w') as zipf:
    for foldername, subfolders, filenames in os.walk(project_dir):
        for filename in filenames:
            filepath = os.path.join(foldername, filename)
            arcname = os.path.relpath(filepath, project_dir)
            zipf.write(filepath, arcname)

zip_file_path
