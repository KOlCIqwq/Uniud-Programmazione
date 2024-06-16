package esami.es22_21;

public class FrameStack {
    private SList<Frame> frames;

    public FrameStack() {
        frames = new SList<Frame>();
    }

    public boolean empty() {
        return frames.isNull();
    }

    public void push( Frame frame ) {
        frames.cons(frame);
    }

    public Frame peek() {
        if (empty()){
            return null;
        }
        Frame first = frames.car();
        return first;
    }

    public Frame pop() {
        if (empty()){
            return null;
        }
        Frame first = frames.car();
        frames = frames.cdr();
        return first;
    }

}
