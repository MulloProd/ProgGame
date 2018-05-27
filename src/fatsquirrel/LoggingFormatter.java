package fatsquirrel;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggingFormatter extends Formatter {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
    @Override
    public String format(LogRecord record) {
        String result;
        result = df.format(new Date(record.getMillis()));
        result += " - [";
        result += record.getLevel();
        result += "] ";
        result += record.getMessage();
        result += "\n";
        return result;
    }
}
