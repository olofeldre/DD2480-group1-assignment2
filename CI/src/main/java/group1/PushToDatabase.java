package group1;
class PushToDatabase
{
    
    PushToDatabase(String json, Time time, boolean compiles, boolean passesTests)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    }

}