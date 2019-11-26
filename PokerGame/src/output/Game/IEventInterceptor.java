package output.Game;


//Interceptor- Interceptor interface
public interface IEventInterceptor {
     void preEvent(EventContextObj context);
     void postEvent(EventContextObj context);
}
