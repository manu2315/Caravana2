package calculatuesfuerzo.finsol.com.mx.calcula.adapters.user;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import calculatuesfuerzo.finsol.com.mx.calcula.providers.Provider;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;

public class UserSingleton {
    public static UserModel USER_MODEL;
    public static void getUserModel(final Callback callcack){

        if(USER_MODEL==null){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            String sha1="";
            try {
                sha1 = Util.getSHA1(auth.getCurrentUser().getEmail());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Provider<UserModel> provider = new Provider<>(UserModel.class);
            final DatabaseReference databaseReference=provider.addGenericValueEventListener("usuarios/"+sha1, new Provider.ModelValueEventListener<UserModel>() {
                @Override
                public void onDataChange(UserModel userModel) {

                    if(USER_MODEL==null) {
                        USER_MODEL = userModel;
                        callcack.modelReady(USER_MODEL);
                    }

                    USER_MODEL = userModel;
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("","");
                }
            });
        }else{
            callcack.modelReady(USER_MODEL);
        }

    }

    public interface Callback{
        public void modelReady(UserModel userModel);
    }
}

