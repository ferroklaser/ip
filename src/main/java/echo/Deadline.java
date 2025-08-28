package echo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toDataString() {
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return "D | " + super.toDataString() + " | " + this.by.format(storageFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
