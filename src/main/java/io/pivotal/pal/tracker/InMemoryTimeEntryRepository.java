package io.pivotal.pal.tracker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry){

        Long id = currentId++;

        TimeEntry timeEntryWithId = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(),
        timeEntry.getDate(), timeEntry.getHours());

        timeEntries.put(id, timeEntryWithId);
        return timeEntryWithId;
    }

    @Override
    public TimeEntry find(Long id){

        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list(){

        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long Id, TimeEntry timeEntry){
        if(find(Id) == null){
            return null;
        }
        TimeEntry updateTimeEntry = new TimeEntry(Id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
                );

        timeEntries.replace(Id, updateTimeEntry);
        return  updateTimeEntry;
    }

    @Override
    public void delete(Long id){
            timeEntries.remove(id);

    }

}
