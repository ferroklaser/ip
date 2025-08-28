package echo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toDataString() {
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return "E | " + super.toDataString() + " | " + this.from.format(storageFormatter) + " | " + this.to.format(storageFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
