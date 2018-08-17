package calculatuesfuerzo.finsol.com.mx.calcula.util;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public  class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public String time;

    private EditText editText;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        time=setTime(hourOfDay,minute);
        editText.setText(time);
    }
    public String setTime(int hourOfDay, int minute){
        return (getCero(hourOfDay)+":"+getCero(minute));
    }

    private String getCero(int number){
        return (number<10) ? "0"+String.valueOf(number):String.valueOf(number);
    }

    public void bindEditText(EditText  editText){
        this.editText=editText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}