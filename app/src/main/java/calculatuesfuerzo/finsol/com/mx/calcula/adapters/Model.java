package calculatuesfuerzo.finsol.com.mx.calcula.adapters;

import com.google.firebase.database.DataSnapshot;

public class Model {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(DataSnapshot dataSnapshot) {
        this.key = dataSnapshot.getKey();
    }

    public void setKeyDesa(String key) {
        this.key = key;
    }
}
