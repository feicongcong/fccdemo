package com.example.designMode;

public class ChainOfResp {

    public static void main(String[] args){
        Request request = new Request(true, true);
        Handler handler = new Utf8Handler();
        Handler handler1 = new LogHandler();
        handler.next=handler1;
        handler.process(request);
    }
}
class Request{
    public Boolean isUtf8;
    public Boolean isLog;

    public Request(Boolean isUtf8, Boolean isLog) {
        this.isUtf8 = isUtf8;
        this.isLog = isLog;
    }
}
abstract class Handler{
    Handler next;
    public Handler(Handler next) {
        this.next = next;
    }

    public Handler() {
    }

    abstract boolean process(Request request);
}
class Utf8Handler extends Handler{
    public Utf8Handler(Handler next) {
        super(next);
    }

    public Utf8Handler() {
    }

    @Override
    boolean process(Request request) {
        if(true == request.isUtf8){
            if(null!=next){
                next.process(request);
            }
            return true;
        }else{
            return false;
        }
    }
}
class LogHandler extends Handler{
    public LogHandler(Handler next) {
        super(next);
    }

    public LogHandler() {
    }

    @Override
    boolean process(Request request) {
        if(true == request.isLog){
            if(null!=next){
                next.process(request);
            }
            return true;
        }else{
            return false;
        }
    }
}