package w2l.inspired.logical;

import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.util.List;

public interface DailyLogProcessor {

    List<DailyLog> getLog() throws IOException;
    void reWriteLog(List<DailyLog> log) throws IOException;
}
