package Observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Subscriber> listSubscriber = new ArrayList<Subscriber>();
    
    public void addSubscriber(Subscriber s){
        listSubscriber.add(s);
    }
    public void removeSubscriber(Subscriber s){
        listSubscriber.remove(s);
    }
    public void notifySubscribers(){
        for (Subscriber subscriber : listSubscriber) {
            subscriber.update();
        }
    }
}
