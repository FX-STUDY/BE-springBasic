package week7.seob.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import week7.seob.common.MyLogger;

@Service
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;

    @Autowired
    public LogDemoService(ObjectProvider<MyLogger> myLogger) {
        this.myLoggerProvider = myLogger;
    }

    public void logic(String id) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
