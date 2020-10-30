package backend;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Robot {
    // Robot class member variables
    private int[] eta;
    private String[][] task= new String[5][2];
    private String robotName;
    private String robotType;

    // Constructors for robot class
    public Robot(int[] eta, String[][] task, String robotName, String robotType) {
        this.eta = eta;
        this.task = task;
        this.robotName = robotName;
        this.robotType = robotType;
    }
    public Robot() {

    }
    // Accessors and mutators for robot classes member variables
    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public String getRobotType() {
        return robotType;
    }

    public void setRobotType(String robotType) {
        this.robotType = robotType;
    }

    public int[] getEta() {
        return eta;
    }

    public void setEta(int[] eta) {
        this.eta = eta;
    }

    public String[][] getTask() {
        return task;
    }

    public void setTask(String[][] task) {
        this.task = task;
    }


    // Stores ETA's of the robots
    public void listEta(){
        int[] listEtaO = new int[5];
        System.out.println("listEta sets setEta to: ");
        for (int i=0; i < task.length; i++){
            listEtaO[i]= Integer.parseInt(task[i][1]);
            System.out.println(i+1 +". "+listEtaO[i]);
        }
        setEta(listEtaO);
    }
    // Does the task in a giving millisecond
    public void taskTime (){
        for (int i =0; i<eta.length;i++){
            try{
                System.out.print("Task: " +new Date() + " ");
                System.out.print(task[0][0]+ " " + task[0][1]);
                System.out.println(" ");
                TimeUnit.MILLISECONDS.sleep(eta[i]);
                System.out.println("Finish time: " + new Date());
                deleteTask();
            }

            catch (InterruptedException taskException){
                System.err.format("IOException: %s%n", taskException);
            }

        }
    }
    // Deletes task after completion
    public void deleteTask(){
        for(int i = 0; i<task.length;i++){
            if(task.length==0)
            {
                break;
            }
            else if(task.length-1 == i){
                break;
            }
            else {
                task[i][0] = task[i + 1][0];
                task[i][1] = task[i + 1][1];
            }
        }

    }


}

