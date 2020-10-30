package backend;

public class Task {
    // Task class member variable
    private String description;
    int etaTask;

    // Task class constructor
    public Task(String description, int etaTask) {
        this.description = description;
        this.etaTask = etaTask;
    }

    // Accessors and mutators for the member variable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtaTask() {
        return etaTask;
    }

    public void setEtaTask(int etaTask) {
        this.etaTask = etaTask;
    }


}
