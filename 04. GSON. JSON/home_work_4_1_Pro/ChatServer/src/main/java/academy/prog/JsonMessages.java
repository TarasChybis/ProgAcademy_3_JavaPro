package academy.prog;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list = new ArrayList<>();

    public JsonMessages(List<Message> sourceList, int fromIndex, String name) {
        for (int i = fromIndex; i < sourceList.size(); i++) {
            if(sourceList.get(i).getTo().equals(name) || sourceList.get(i).getTo().equals("All") || sourceList.get(i).getFrom().equals(name))  {

                list.add(sourceList.get(i));
            }
        }
    }
}
