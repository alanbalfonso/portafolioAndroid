package com.example.alanbauza_menuopciones.ui.agecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.alanbauza_menuopciones.R;

import java.util.Calendar;

public class AgeCalculatorFragment extends Fragment {

    private CalendarView calendarView;
    private Button btnCalculateAge;
    private TextView tvAgeResult;
    private long selectedDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_age_calculator, container, false);

        calendarView = root.findViewById(R.id.calendarView);
        btnCalculateAge = root.findViewById(R.id.btnCalculateAge);
        tvAgeResult = root.findViewById(R.id.tvAgeResult);

        // Inicializar con la fecha actual
        selectedDate = System.currentTimeMillis();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selectedDate = calendar.getTimeInMillis();
            }
        });

        btnCalculateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });

        return root;
    }

    private void calculateAge() {
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTimeInMillis(selectedDate);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // Verificar si el cumpleaños ya pasó este año
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        // Si la fecha seleccionada es futura, mostrar 0
        if (selectedDate > System.currentTimeMillis()) {
            age = 0;
        }

        tvAgeResult.setText(getString(R.string.age_result, age));
        tvAgeResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
