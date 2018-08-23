package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import calculatuesfuerzo.finsol.com.mx.calcula.adapters.Model;

public class Provider<V extends Model> {

    private static final String SLASH = "/";
    private static final String NODE_RED_OPERATIVA = "red_operativa";
    private static final String NODE_DIVISIONALES = "divisionales";
    private static final String NODE_REGIONALES = "regionales";
    private static final String NODE_SUCURSALES = "gerentes";
    public static final String NODE_USUARIO_LOGIN = "usuarios_login/";
    private static final String NODE_PARAM_STRING = "%s";
    public static final String NODE_ASESORES = "asesores/";

    private static final String DINAMIC_DIVISIONAL = NODE_DIVISIONALES + SLASH + NODE_PARAM_STRING;
    private static final String DINAMIC_REGIONAL = NODE_REGIONALES + SLASH + NODE_PARAM_STRING;
    private static final String DINAMIC_SUCURSAL = NODE_SUCURSALES + SLASH + NODE_PARAM_STRING;
    private static final String DINAMIC_ASESOR = NODE_ASESORES + NODE_PARAM_STRING;

    public static String getDinamicDivisional(String divisional) {
        return String.format(DINAMIC_DIVISIONAL, divisional);
    }

    public static String getDinamicRegional(String regional) {
        return String.format(DINAMIC_REGIONAL, regional);
    }

    public static String getDinamicSucursal(String sucursal) {
        return String.format(DINAMIC_SUCURSAL, sucursal);
    }

    public static String getDinamicAsesor(String asesor) {
        return String.format(DINAMIC_ASESOR, asesor);
    }

    public static final String PATH_RED_OPERATIVA = NODE_RED_OPERATIVA;

    private static final FirebaseDatabase mDatabase;

    static{
        mDatabase = FirebaseDatabase.getInstance();
    }

    public static DatabaseReference addValueEventListener(String firebasePath, boolean keepSynced, ValueEventListener valueEventListener) {
        DatabaseReference ref = mDatabase.getReference(firebasePath);
        ref.keepSynced(keepSynced);
        ref.addValueEventListener(valueEventListener);
        return ref;
    }

    public static DatabaseReference addChildEventListener(String firebasePath, boolean keepSynced, ChildEventListener childEventListener) {
        DatabaseReference ref = mDatabase.getReference(firebasePath);
        ref.keepSynced(keepSynced);
        ref.addChildEventListener(childEventListener);
        return ref;
    }
    public static DatabaseReference addListenerForSingleValueEvent(String firebasePath, boolean keepSynced, ValueEventListener valueEventListener) {
        DatabaseReference ref = mDatabase.getReference(firebasePath);
        ref.keepSynced(keepSynced);
        ref.addListenerForSingleValueEvent(valueEventListener);
        return ref;
    }

    public static void updateValue(String path, Object value){
        mDatabase.getReference(path).setValue(value);
    }

    public static String pushValue(String path, Object value){
        DatabaseReference ref = mDatabase.getReference(path).push();
        ref.setValue(value);
        return ref.getKey();
    }

    public static void guardarFirebase(String path, Object value){
        DatabaseReference ref = mDatabase.getReference(path).push();
        ref.setValue(value);

    }

    <T extends Model> T parseDatasnapshot(DataSnapshot dataSnapshot, Class<T> type) {
        if(dataSnapshot.getValue()!=null){
            T t = dataSnapshot.getValue(type);
            t.setKey(dataSnapshot);
            return t;
        }else{
            return null;
        }
    }

    private ModelValueEventListener<V> mModelValueEventListener;
    private Class<V> mType;

    public Provider(Class<V> type) {
        this.mType = type;
    }

    public DatabaseReference addGenericValueEventListener(String node, ModelValueEventListener<V> modelValueEventListener){
        this.mModelValueEventListener = modelValueEventListener;
        return Provider.addValueEventListener(node, false, new ValueEventListenerImpl());
    }

    class ValueEventListenerImpl implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            V v = parseDatasnapshot(dataSnapshot, mType);
            mModelValueEventListener.onDataChange(v);
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
            mModelValueEventListener.onCancelled(databaseError);
        }
    }

    public interface ModelChildEventListener<T>{
        void onChildAdded(T t);
        void onChildChanged(T t);
        void onChildRemoved(T t);
        void onChildMoved(T t);
        void onCancelled(DatabaseError databaseError);
    }

    public interface ModelValueEventListener<T>{
        void onDataChange(T t);
        void onCancelled(DatabaseError databaseError);
    }

}
