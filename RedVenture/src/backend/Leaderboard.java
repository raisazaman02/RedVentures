package backend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
    // Member variables of class leaderboard
    private int[] rScore;
    private String rType;
    private String rName;
    private int totalScore;

    // Accessors and mutators for the member variables
    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int[] getrScore() {
        return rScore;
    }

    public void setrScore(int[] rScore) {
        this.rScore = rScore;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    // Read from the output file
    public void printLeaderBoard() throws IOException {
        File file = new File("LeaderBoard.txt");
        if (!file.exists()) {
            file.createNewFile();

        }
        BufferedReader scoreData = new BufferedReader(new FileReader(file));
        String robotScore;
        while ((robotScore = scoreData.readLine()) != null) {
            String firstPlace = Files.readAllLines(Paths.get("LeaderBoard.txt")).get(1);
            System.out.println("First Place is " + firstPlace);
            System.out.println(robotScore);
        }

    }

    // Write to the output file
    public void writeToLeaderboard(ArrayList<Leaderboard> robotScoreData) {
        ArrayList<Integer> sortedScore = sortScore(robotScoreData);
        try {
            File f1 = new File("LeaderBoard.txt");
            if (!f1.exists()) {
                f1.createNewFile();

            }
            FileWriter fileWriter = new FileWriter(f1.getName(), true);
            PrintWriter info = new PrintWriter(fileWriter);
            ArrayList<String> nameBot = new ArrayList<>();
            for (int i = sortedScore.size() - 1; i >= 0; i--) {
                int temp = sortedScore.get(i);
                for (int j = 0; j < robotScoreData.size(); j++) {
                    if (temp == robotScoreData.get(j).getTotalScore()) {
                        nameBot.add(robotScoreData.get(j).getrName());
                        fileWriter.write(robotScoreData.get(j).getrName() + " " + robotScoreData.get(j).getrType()
                                + " " + robotScoreData.get(j).getTotalScore());
                        info.println(" ");
                    }
                }

            }
            System.out.println("Output file successful stored data.");
            fileWriter.close();

        } catch (IOException exception) {
            System.out.println("Error: output file didn't store data.");
            exception.printStackTrace();
        }
    }

    // Sort robot scores
    public ArrayList<Integer> sortScore(ArrayList<Leaderboard> robotScoreData) {
        ArrayList<Integer> sort = new ArrayList<>();
        for (int i = 0; i < robotScoreData.size(); i++) {
            sort.add(robotScoreData.get(i).getTotalScore());
        }
        Collections.sort(sort);
        System.out.println("Sort Score: " + sort);
        return sort;
    }

    // Calculating the robots score from the eta
    public void scoreBoard() {

        int sum = 0;
        for (int i = 0; i < getrScore().length; i++) {
            sum = sum + getrScore()[i];
        }
        int score = sum / 1000;
        setTotalScore(score);

    }
}
