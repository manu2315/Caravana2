package calculatuesfuerzo.finsol.com.mx.calcula.util;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.util.Property;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserModel;

public class Util {
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        if(image!=null){
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        return null;
    }

    public static int switchFragment(FragmentManager fragmentManager, int viewportId, Fragment fragment, String fragmentTag, String backStackId){

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.setCustomAnimations(
                R.anim.fragment_slide_right_center,
                R.anim.fragment_slide_center_left,
                R.anim.fragment_slide_left_center,
                R.anim.fragment_slide_center_right
        );

        if(fragment!=null && fragmentTag!=null){
            fragmentTransaction.replace(
                    viewportId,
                    fragment,
                    fragmentTag
            );
        }

        if (backStackId != null) {
            fragmentTransaction.addToBackStack(backStackId);
        }

        return  fragmentTransaction.commit();

    }

    public static String formamtCurrency(Float amount){
        if(amount!=null){
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String s = formatter.format(new BigDecimal(amount));
            return s;
        }else{
            return "Monto nulo";
        }
    }

    public static String formamtCurrency(Double amount){
        if(amount!=null){
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String s = formatter.format(new BigDecimal(amount));
            return s;
        }else{
            return "Monto nulo";
        }
    }

    private static final Property<AnimatedColorSpan, Float> ANIMATED_COLOR_SPAN_FLOAT_PROPERTY = new Property<AnimatedColorSpan, Float>(Float.class, "ANIMATED_COLOR_SPAN_FLOAT_PROPERTY") {
        @Override
        public void set(AnimatedColorSpan span, Float value) {
            span.setTranslateXPercentage(value);
        }
        @Override
        public Float get(AnimatedColorSpan span) {
            return span.getTranslateXPercentage();
        }
    };

    public static String animarTexto(String texto, Context contexto, final TextView textView)
    {


        AnimatedColorSpan span = new AnimatedColorSpan(contexto);
        final SpannableString spannableString = new SpannableString(texto);
        spannableString.setSpan(span, 0, spannableString.length(), 0);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(span, ANIMATED_COLOR_SPAN_FLOAT_PROPERTY, 0, 100);
        objectAnimator.setEvaluator(new FloatEvaluator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(spannableString);
            }
        });
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(DateUtils.MINUTE_IN_MILLIS * 3);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
        return "" ;
    }

    public static AlertDialog infoDialog(final Context context, int messageId, int positiveButtonId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageId)
                .setPositiveButton(positiveButtonId, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        //FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                        //switchFragment(fragmentManager, R.id.fragment_container, SliderFragment.newInstance("",""), SliderFragment.class.getName(), null);
                        //((Activity)(context)).onBackPressed();
                    }
                });
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
        return builder.create();
    }

    public static String getTimeStamp() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + "";
    }

    public static String convertToHex(String n) {
        return String.format("%x", new BigInteger(1, n.getBytes()));
    }

    private static String obtenerHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String getSHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();
        return obtenerHex(sha1hash);
    }

    public static String cleanDivision(String divicionKey){
        String[] comp = divicionKey.split("_");
        return comp[1];
    }

    public static String cleanRegion(String regionKey){
        String[] comp = regionKey.split("_");
        return comp[1];
    }

    public static String formatoFecha(String fecha){
        if(fecha!=null){
            fecha = fecha.replace(" 00:00:00.0","");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = null;
            try {
                newDate = format.parse(fecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            format = new SimpleDateFormat("dd-MM-yyyy");
            String date = format.format(newDate);
            return date;
        }else
            return "---";

    }

    public static List<String> ordenarKeySet(Set<String> keySet){
        List<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);
        Collections.sort(keyList);
        return keyList ;
    }

    public static String getTipoPerfil(String puesto) {
        switch (puesto) {
            case UserModel.USER_NACIONAL:
                return Constantes.PUESTO_NACIONAL;
            case UserModel.USER_DIVICIONAL:
                return Constantes.PUESTO_DIVISIONAL;
            case UserModel.USER_REGIONAL:
                return Constantes.PUESTO_REGIONAL;
            case UserModel.USER_SUCURSAL:
                return Constantes.PUESTO_SUCURSAL;
            case UserModel.USER_CORDINADOR:
                return Constantes.PUESTO_COORDINADOR;
            case UserModel.USER_ASESOR:
                return Constantes.PUESTO_ASESOR;
            case UserModel.USER_SUCURSAL_45:
                return Constantes.PUESTO_ASESOR;
            default:
                return "";
        }
    }

    public static String getValorConUnidades(String titulo, Double valor){
        titulo=titulo.trim() ;
        if(titulo.equals("Cierre de Fichas") || titulo.equals("Cartera Bajo Riesgo") || titulo.equals("Cartera 0 Días Vencidos")){
            return truncar2Decimales(valor*100)+" %" ;
        }
        else if (titulo.equals("Intereses Cobrados") || titulo.equals("Cartera")){
            return Util.formamtCurrency(valor) ;
        }
        else
            return String.valueOf(valor.intValue()) ;
    }

    public static Double truncar2Decimales(Double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}
