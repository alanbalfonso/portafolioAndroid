package com.example.alanbauza_menuopciones.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.alanbauza_menuopciones.R;
import com.google.android.material.textfield.TextInputEditText;

public class CalculatorFragment extends Fragment {

    private TextInputEditText etNumber1, etNumber2;
    private RadioGroup rgOperations;
    private Button btnCalculate;
    private TextView tvResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);

        etNumber1 = root.findViewById(R.id.etNumber1);
        etNumber2 = root.findViewById(R.id.etNumber2);
        rgOperations = root.findViewById(R.id.rgOperations);
        btnCalculate = root.findViewById(R.id.btnCalculate);
        tvResult = root.findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
            }
        });

        return root;
    }

    private void performCalculation() {
        String num1Str = etNumber1.getText().toString().trim();
        String num2Str = etNumber2.getText().toString().trim();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(getContext(), "Por favor ingresa ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;
            String operation = "";

            int selectedId = rgOperations.getCheckedRadioButtonId();

            if (selectedId == R.id.rbAdd) {
                result = num1 + num2;
                operation = "+";
            } else if (selectedId == R.id.rbSubtract) {
                result = num1 - num2;
                operation = "-";
            } else if (selectedId == R.id.rbMultiply) {
                result = num1 * num2;
                operation = "×";
            } else if (selectedId == R.id.rbDivide) {
                if (num2 == 0) {
                    Toast.makeText(getContext(), "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1 / num2;
                operation = "÷";
            }

            String resultText = String.format("%.2f %s %.2f = %.2f", num1, operation, num2, result);
            tvResult.setText(getString(R.string.result_text, resultText));
            tvResult.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Por favor ingresa números válidos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
