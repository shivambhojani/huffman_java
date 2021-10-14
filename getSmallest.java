import java.util.HashMap;
import java.util.Map;

public class getSmallest {

    public String getSmallest(Map< String, huffmanNode> map)
    {
        //
        Map<String, huffmanNode> newmap = new HashMap<String, huffmanNode>();
        newmap = map;
        Map.Entry<String, huffmanNode> min = null;
        for (Map.Entry<String, huffmanNode> entry : newmap.entrySet()){
            if (min == null || min.getValue().freq> entry.getValue().freq) {
                min = entry;
            }
        }
        return min.getKey();
    }

    }
