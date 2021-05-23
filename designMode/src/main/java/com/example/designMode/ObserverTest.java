package com.example.designMode;

import java.util.ArrayList;
import java.util.List;

public class ObserverTest {
    public static void main(String[] args) {

            Event event = new Event();
        Listener1 listener1 = new Listener1();
            event.registObserver(listener1);
        Listener2 task2 = new Listener2();
            event.registObserver(task2);
            event.updateEvent("xxxxx");
            event.unregist(task2);
            event.updateEvent("ccccc");

    }
}
class Event{
    private List<Observer> container = new ArrayList<Observer>();
    public void registObserver(Observer observer){
        container.add(observer);
    }
    //remove
    public void unregist(Observer observer){
        container.remove(observer);
    }

    public void updateEvent(Object object){
        System.out.println("do update...");
        for(Observer it :container){
            it.update(object);
        }
    }
}
interface  Observer{
    void update (Object object);
}
class Listener1 implements Observer{
    @Override
    public void update(Object object) {
        System.out.println("task1 received:"+object);
    }
}class Listener2 implements Observer{
    @Override
    public void update(Object object) {
        System.out.println("task2 received:"+object);
    }
}