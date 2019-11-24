package output.Game;

import java.util.ArrayList;

//Interceptor- Dispatcher
public class EventLoggingDispatcher implements IEventInterceptor {
    ArrayList<IEventInterceptor> interceptors = new ArrayList<IEventInterceptor>();
    @Override
    public void preEvent(EventContextObj context) {
        IEventInterceptor interceptor = interceptors.get(interceptors.size()-1);
        //preMarshall
        interceptor.preEvent(context);

    }

    @Override
    public void postEvent(EventContextObj context) {
        IEventInterceptor interceptor = interceptors.get(interceptors.size()-1);
        //postMarshall
        interceptor.postEvent(context);
    }

    public void addEventLogInterceptors(IEventInterceptor interceptor) {
        interceptors.add(interceptor);
        System.out.println("New interceptor has been added");
    }
}
