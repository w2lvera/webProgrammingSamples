package w2l.inspired.logical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import w2l.inspired.model.DailyLog;

import java.util.List;

import static w2l.inspired.model.CompletionStatus.DONE;
@Component
public class CustomerScoreCalc {
    private final String prefix;
    private final int initialScore;
@Autowired
    public CustomerScoreCalc(String prefix, int initialScore) {
        this.prefix = prefix;
        this.initialScore = initialScore;
    }

   // Unsatisfied dependency expressed through constructor parameter 2: Error creating bean with name 'customerScoreCalc
   // ' defined in file [C:\javatools\apache-tomcat-10.1.50\webapps\simpleDao\WEB-INF\classes\w2l\inspired\logical\CustomerScoreCalc.class]: Unsatisfied dependency expressed through constructor parameter 0: No qualifying bean of type 'java.lang.String' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
    public String getPrefix() {
        return prefix;
    }

    public int calculateScore(List<DailyLog> log){
        int score = initialScore;
        for(DailyLog logEntry: log){
//            switch (logEntry.getStatus()){
//                case DONE -> score += logEntry.getCustomer().getName().startsWith(prefix)?1:0;
//                case FAILED -> score -= logEntry.getEvent().getFee();
//            }
            if(logEntry.getStatus().equals(DONE))score += logEntry.getCustomer().getName().startsWith(prefix)?1:0;
        }

        return score;
    }
}
