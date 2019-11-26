package output.Game;

import java.time.LocalDateTime;
import java.util.Date;

//Interceptor- Context
public class EventContextObj {
    public LocalDateTime currentDateTime;
    public String user;
    public String gameName;

    public EventContextObj(LocalDateTime currentDateTime, String user, String gameName){
        this.currentDateTime=currentDateTime;
        this.user = user;
        this.gameName = gameName;
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public String getUser(){
        return user;
    }

    public String getGameName(){
        return gameName;
    }
}
