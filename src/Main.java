import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

        String difficulty = selectDifficulty(); // Difficulty selection

        String url = "https://www.nytimes.com/puzzles/sudoku/" + difficulty;

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);

        int[][] sudokuBoard = getBoard(driver); // The Sudoku board is getting from the NY Times

        Solver.solveBoard(sudokuBoard); // The Sudoku board solving

        setBoard(driver, sudokuBoard); // The Sudoku board is set to the NY Times.

    }

    private static void setBoard(WebDriver driver, int[][] sudokuBoard) {

        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++, index++) {
                String xpath = "//div[@data-cell='" + index + "']"; // Xpath for cell

                WebElement cell = driver.findElement(By.xpath(xpath));

                String value = cell.getAttribute("aria-label");  // Value is getting from "aria-label" attiribute

                Actions actions = new Actions(driver);

                if (value.equals("empty")) {
                    actions.moveToElement(cell).click().perform();
                    actions.sendKeys(String.valueOf(sudokuBoard[i][j])).perform(); // Setting values to empty cells
                }
            }
        }
    }

    private static int[][] getBoard(WebDriver driver) {
        int[][] sudokuBoard = new int[9][9];

        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++, index++) {
                String xpath = "//div[@data-cell='" + index + "']"; // Xpath for cell

                WebElement cell = driver.findElement(By.xpath(xpath));

                String value = cell.getAttribute("aria-label"); // Value is getting from "aria-label" attiribute

                if (value.equals("empty"))
                    sudokuBoard[i][j] = 0;
                else
                    sudokuBoard[i][j] = Integer.parseInt(value);
            }
        }
        return sudokuBoard;
    }

    private static String selectDifficulty() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("1-Easy\n2-Medium\n3-Hard\nSelect Difficulty: ");
        String difficulty = scanner.nextLine();

        if (difficulty.equals("1") || difficulty.equalsIgnoreCase("easy"))
            return "easy";

        else if (difficulty.equals("2") || difficulty.equalsIgnoreCase("medium"))
            return "medium";

        else if (difficulty.equals("3") || difficulty.equalsIgnoreCase("hard"))
            return "hard";

        return null;
    }


}



