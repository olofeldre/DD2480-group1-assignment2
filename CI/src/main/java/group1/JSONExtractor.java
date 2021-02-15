package group1;
import org.json.JSONObject;

class JSONExtractor
{
    private String branch;
    private String commit;
    public JSONExtractor(String json)
    {
        JSONObject parsed = new JSONObject(json);
        commit = parsed.getString("after");
        String ref = parsed.getString("ref");
        String[] refs = ref.split("/");
        branch = refs[refs.length - 1]; 
    }
    public String getBranch()
    {
        return branch;
    }

    public String getCommit()
    {
        return commit;
    }
}
