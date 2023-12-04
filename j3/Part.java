package j3;

public class Part {
    int line;
    int startIndex;
    int endIndex;
    int value;
    boolean isConnected;
    public Part (int line, int startIndex, int endIndex, int value) {
        this.line = line;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.value = value;
        this.isConnected = false;
    }
}