package hello;

public class SimpleApp {

    private int id;
    private String appType;
    private boolean approved;
    private String appName;
    private String firstName;
    private String lastName;
    private String processInstanceId;
    private String eventName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public SimpleApp(String appType, boolean approved, String appName, String firstName, String lastName, String processInstanceId, String eventName) {
        this.appType = appType;
        this.approved = approved;
        this.appName = appName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.processInstanceId = processInstanceId;
        this.eventName = eventName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SimpleApp() {
    }

    @Override
    public String toString() {
        return "SimpleApp{" +
                "id=" + id +
                ", appType='" + appType + '\'' +
                ", approved=" + approved +
                ", appName='" + appName + '\'' +
                '}';
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
