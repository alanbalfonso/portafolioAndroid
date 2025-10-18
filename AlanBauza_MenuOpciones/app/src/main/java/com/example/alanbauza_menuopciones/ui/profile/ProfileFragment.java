package com.example.alanbauza_menuopciones.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.alanbauza_menuopciones.R;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    private TextInputEditText etName, etEmail;
    private RadioGroup rgGender;
    private CheckBox cbNotifications;
    private Spinner spinnerExperience;
    private Button btnSaveProfile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        etName = root.findViewById(R.id.etName);
        etEmail = root.findViewById(R.id.etEmail);
        rgGender = root.findViewById(R.id.rgGender);
        cbNotifications = root.findViewById(R.id.cbNotifications);
        spinnerExperience = root.findViewById(R.id.spinnerExperience);
        btnSaveProfile = root.findViewById(R.id.btnSaveProfile);

        // Configurar spinner de experiencia
        String[] experienceLevels = {"Principiante", "Intermedio", "Avanzado", "Experto"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, experienceLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExperience.setAdapter(adapter);

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        return root;
    }

    private void saveProfile() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener género seleccionado
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            RadioButton selectedRadio = getView().findViewById(selectedGenderId);
            gender = selectedRadio.getText().toString();
        }

        // Obtener nivel de experiencia
        String experience = spinnerExperience.getSelectedItem().toString();

        // Obtener estado de notificaciones
        boolean notifications = cbNotifications.isChecked();

        // Simular guardado del perfil
        String message = "Perfil guardado:\n" +
                "Nombre: " + name + "\n" +
                "Email: " + email + "\n" +
                "Género: " + gender + "\n" +
                "Experiencia: " + experience + "\n" +
                "Notificaciones: " + (notifications ? "Activadas" : "Desactivadas");

        Toast.makeText(getContext(), getString(R.string.profile_saved), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
