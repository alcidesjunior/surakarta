package Service;

public class CommunicationModel {
    private String communicationType;
    private String message;
    private String moveTo;
    private String dot;


    public String getCommunicationType() {
        return communicationType;
    }

    public String getMessage() {
        return message;
    }

    public String getMoveTo() {
        return moveTo;
    }

    public String getDot() {
        return dot;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMoveTo(String moveTo) {
        this.moveTo = moveTo;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }
}
