package web.brick;

import io.micronaut.websocket.WebSocketSession;

public class User {
    private WebSocketSession session;
    private int id;
    private long timeJoined;
    private long timeElapsed;

    public User(int id, WebSocketSession session) {
        this.id = id;
        this.timeJoined = System.currentTimeMillis();
        this.session = session;
    }

    public void syncTime(long time) {
        TimeSyncMessage message = new TimeSyncMessage(timeElapsed, time);
        session.sendSync(message);
    }

    public long update(long timeNow) {
        timeElapsed = timeNow - timeJoined;
        return timeElapsed;
    }

    public long getTimeSinceJoined() {
        return System.currentTimeMillis() - timeJoined;
    }

    @Override 
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).id == this.id;
        }
        return false;
    }

}