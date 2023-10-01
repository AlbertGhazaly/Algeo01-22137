 import java.io.*;
 
 public class MultiOutputStream extends OutputStream {
    private final OutputStream[] outputStreams;

    public MultiOutputStream(OutputStream... outputStreams) {
        this.outputStreams = outputStreams;
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream os : outputStreams) {
            os.write(b);
        }
    }
}