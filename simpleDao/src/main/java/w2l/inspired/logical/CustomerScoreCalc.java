package w2l.inspired.logical;

import w2l.inspired.model.DailyLog;

import java.util.List;

import static w2l.inspired.model.CompletionStatus.DONE;

public class CustomerScoreCalc {
    private final String prefix;
    private final int initialScore;

    public CustomerScoreCalc(String prefix, int initialScore) {
        this.prefix = prefix;
        this.initialScore = initialScore;
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
