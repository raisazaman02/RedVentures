package backend;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Application header
        System.out.println("Welcome to Bot-o-Mat");
        System.out.println(" ");
        // Variable initialization in main
        int min = 0;
        String[][] taskList = {
                {"do the the dishes","1000"},
                {"sweep the house","3000"},
                {"do the laundry","10000"},
                {"take out the recycling","4000"},
                {"make a sammich","7000"},
                {"mow the lawn","20000"},
                {"rake the leaves", "18000"},
                {"give the dog a bath","14500"},
                {"bake some cookies","8000"},
                {"wash the car","20000"}
        };
        // Array of task to list of tasks
        ArrayList<Task> task = new ArrayList<>();
        for (int i = 0; i < taskList.length; i++){
            Task newTask = new Task(taskList[i][0],Integer.parseInt(taskList[i][1]));
            task.add(newTask);
        }
        // Main menu is called
        menu ();
        // User input main menu option or exit to stop
        Scanner scannerO = new Scanner(System.in);
        System.out.println("What option would you like (Type exit to stop): ");
        String menuSelection = scannerO.nextLine();
        // Function called to execute the option selected
        menuOption(menuSelection, task, min,task.size());
        while (!menuSelection.equals("exit")){
            menu ();
            System.out.println("What option would you like (Type exit to stop): ");
            menuSelection = scannerO.nextLine();
            menuOption(menuSelection, task, min,task.size());
        }
    }
    // Function that contains the main menu selection
    static void menuOption(String menuSelection, ArrayList<Task> task, int min, int max) throws IOException {
        //Declaring Objects
        Scanner scannerO = new Scanner(System.in);
        Leaderboard score = new Leaderboard();
        // User option outcomes
        switch (menuSelection) {
            case "1":
                // Add a new robot option
                newRobot(task,min, max);
                break;
            case "2":
                // List of Tasks and etas
                System.out.println("List of tasks and time it takes to complete given task: ");
                for(int i=0; i < task.size();i++){
                    System.out.println(task.get(i).getDescription());
                    System.out.println(task.get(i).getEtaTask());
                }
                break;
            case "3":
                // Add a new task to the list
                System.out.println("Enter a new task: ");
                String robotTask = scannerO.nextLine();
                addNewTask(robotTask,task);
                break;
            case "4":
                // Print leader board
                score.printLeaderBoard();
                break;
            case "exit":
                // End program
                System.out.println("Thank you for using out program.");
                System.out.println("End of Bot-o-Mat");
                break;
            default:
                // wrong input
                System.out.println("Error: Invalid statement.");
        }
    }
    // Add task function
    public static void addNewTask(String robotTask,ArrayList<Task> task){
        int newEta = ((int)(Math.random()*((30000-1000)))+1000);
        Task newTask = new Task(robotTask,newEta);
        task.add(newTask);
    }
    // Choose five random task index
    public static int[] fiveTask(double min, double max){
        int[] indexList = new int[5];
        System.out.println("fiveTask returns before sorting: ");
        for (int i =0; i<5; i++){
            int temp= (int) ((int)(Math.random()*((max-min)))+min);
            if(temp < max && temp > min) {
                indexList[i]=temp;
                System.out.print(indexList[i]);
            }
            else{
                System.out.println("Error: Index out of bound");
            }

        }
        System.out.println(" ");
        insertionSort(indexList);
        System.out.println("fiveTask returns after sorting: ");
        for (int j =0; j<5; j++) {
            System.out.print(indexList[j]);
        }
        System.out.println(" ");
        return indexList;
    }
    // Sort the five random task index
    public static void insertionSort(int taskIndex[])
    {
        int temp;
        int j;
        for (int i = 1; i < taskIndex.length; i++)
        {
            temp = taskIndex[i];
            j = i - 1;
            while (j >= 0 && taskIndex[j] > temp)
            {
                taskIndex[j + 1] = taskIndex[j];
                j = j - 1;
            }
            taskIndex[j + 1] = temp;
        }
    }
    // Take the sorted index and located the task on the task list
    public static String[][] assignTask (ArrayList <Task> taskList,int[] taskInd){
        String[][] fiveTasks = new String[5][2];
        System.out.println("Robot task list: ");
        for (int i = 0;i<taskInd.length;i++){
            fiveTasks[i][0] = taskList.get(taskInd[i]).getDescription();
            fiveTasks[i][1] = String.valueOf(taskList.get(taskInd[i]).getEtaTask());
            System.out.println(fiveTasks[i][0]);
            System.out.println(fiveTasks[i][1]);
        }
        return fiveTasks;
    }
    // Main menu
    public static void menu (){
        System.out.println("1. Create a new robot");
        System.out.println("2. Print the tasks");
        System.out.println("3. Add tasks");
        System.out.println("4. Print the leaderboard");

    }
    // Create new robot function
    public static void newRobot(ArrayList <Task> task, int min, int max) {
        // Prompt the user for robot type and robot name
        ArrayList<String> nameList = new ArrayList<>();
        Scanner scannerO = new Scanner(System.in);
        System.out.println("Enter robot name(separate using ,): ");
        String robotName = scannerO.nextLine();
        while(nameList.contains(robotName)){
            System.out.println("Enter robot name(separate using ,): ");
            robotName = scannerO.nextLine();
        }
        nameList.add(robotName);
        robotName = robotName.replaceAll("\\s", "");
        String [] nameArr = robotName.split(",");
        System.out.println("Unipedal, Bipedal, Quadrupedal, Arachnid, Radial, Aeronautical");
        System.out.println("Enter robot type(separate using ,): ");
        String robotType = scannerO.nextLine();
        robotType= robotType.toLowerCase();
        robotType = robotType.replaceAll("\\s", "");
        String [] typeArr = robotType.split(",");
        ArrayList<Leaderboard> robotScoreData = new ArrayList<>();
        Leaderboard score = new Leaderboard();
        // Switch case to assign the proper task to the robots
        for (int i = 0; i < typeArr.length; i++){
            int [] taskInd = fiveTask(min,task.size());
            switch (typeArr[i]) {
                case "unipedal":
                    Unipedal unipedalO = new Unipedal();
                    unipedalO.setRobotName(nameArr[i]);
                    unipedalO.setRobotType(typeArr[i]);
                    System.out.println("Your " +unipedalO.getRobotType()+ " "+ unipedalO.getRobotName() + " is ready.");
                    unipedalO.setTask(assignTask (task,taskInd));
                    unipedalO.listEta();
                    unipedalO.taskTime();
                    score.setrName(unipedalO.getRobotName());
                    score.setrType(unipedalO.getRobotType());
                    score.setrScore(unipedalO.getEta());
                    robotScoreData.add(score);
                    score.scoreBoard();
                    break;
                case "bipedal":
                    Bipedal bipedalO = new Bipedal();
                    bipedalO.setRobotName(nameArr[i]);
                    bipedalO.setRobotType(typeArr[i]);
                    System.out.println("Your " +bipedalO.getRobotType()+ " "+ bipedalO.getRobotName() + " is ready.");
                    bipedalO.setTask(assignTask (task,taskInd));
                    bipedalO.listEta();
                    bipedalO.taskTime();
                    score = new Leaderboard();
                    score.setrName(bipedalO.getRobotName());
                    score.setrType(bipedalO.getRobotType());
                    score.setrScore(bipedalO.getEta());
                    robotScoreData.add(score);
                    score.scoreBoard();
                    break;
                case "quadrupedal":
                    Quadrupedal quadrupedalO = new Quadrupedal();
                    quadrupedalO.setRobotName(nameArr[i]);
                    quadrupedalO.setRobotType(typeArr[i]);
                    System.out.println("Your " +quadrupedalO.getRobotType()+ " "+ quadrupedalO.getRobotName() + " is ready.");
                    quadrupedalO.setTask(assignTask (task,fiveTask(min,max)));
                    quadrupedalO.listEta();
                    quadrupedalO.taskTime();
                    score = new Leaderboard();
                    score.setrName(quadrupedalO.getRobotName());
                    score.setrType(quadrupedalO.getRobotType());
                    score.setrScore(quadrupedalO.getEta());
                    robotScoreData.add(score);
                    score.scoreBoard();
                    break;
                case "arachnid":
                    Arachnid arachnidO = new Arachnid();
                    arachnidO.setRobotName(nameArr[i]);
                    arachnidO.setRobotType(typeArr[i]);
                    System.out.println("Your " +arachnidO.getRobotType()+ " "+ arachnidO.getRobotName() + " is ready.");
                    arachnidO.setTask(assignTask (task,taskInd));
                    arachnidO.listEta();
                    arachnidO.taskTime();
                    score = new Leaderboard();
                    score.setrName(arachnidO.getRobotName());
                    score.setrType(arachnidO.getRobotType());
                    score.setrScore(arachnidO.getEta());
                    robotScoreData.add(score);
                    score.scoreBoard();
                    break;
                case "radial":
                    Radial radialO = new Radial();
                    radialO.setRobotName(nameArr[i]);
                    radialO.setRobotType(typeArr[i]);
                    System.out.println("Your " +radialO.getRobotType()+ " "+ radialO.getRobotName() + " is ready.");
                    radialO.setTask(assignTask (task,taskInd));
                    radialO.listEta();
                    radialO.taskTime();
                    score = new Leaderboard();
                    score.setrName(radialO.getRobotName());
                    score.setrType(radialO.getRobotType());
                    score.setrScore(radialO.getEta());
                    robotScoreData.add(score);
                    score.scoreBoard();
                    break;
                case "aeronautical":
                    Aeronautical aeronauticalO = new Aeronautical();
                    aeronauticalO.setRobotName(nameArr[i]);
                    aeronauticalO.setRobotType(typeArr[i]);
                    System.out.println("Your " +aeronauticalO.getRobotType()+ " "+ aeronauticalO.getRobotName() + " is ready.");
                    aeronauticalO.setTask(assignTask (task,taskInd));
                    aeronauticalO.listEta();
                    aeronauticalO.taskTime();
                    score = new Leaderboard();
                    score.setrName(aeronauticalO.getRobotName());
                    score.setrType(aeronauticalO.getRobotType());
                    score.setrScore(aeronauticalO.getEta());
                    robotScoreData.add(score);
                    score.scoreBoard();
                    break;
                default:
                    System.out.println("Error: Invalid statement.");
            }
        }
        // Store score in output file
        Leaderboard data = new Leaderboard();
        data.writeToLeaderboard(robotScoreData);

    }

}


