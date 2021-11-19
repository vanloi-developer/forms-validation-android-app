package com.example.android_validate;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner elType;
    EditText elBedRoom;
    RadioGroup elFurniture;
    EditText elPriceByMonth;
    EditText elReporterName;
    EditText elCreatedAt;
    Button elBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elBtnSubmit = findViewById(R.id.button_submit);

        elBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrValidate = {};
                List<String> errors = new ArrayList<>(Arrays.asList((arrValidate)));

                String[] sampleTypes = {"Flat", "House", "Bungalow"};
                String[] sampleFurnitures = {"Furnished", "Unfurnished", "PartFurnished"};

                List<String> types = new ArrayList<>(Arrays.asList(sampleTypes));
                List<String> furnitures = new ArrayList<>(Arrays.asList(sampleFurnitures));

                elType = findViewById(R.id.type);
                String valType = elType.getSelectedItem().toString();

                elBedRoom = findViewById(R.id.bedRoom);
                String valBedRoom = elBedRoom.getText().toString();

                elFurniture = findViewById(R.id.furniture);
                String valFurniture;
                if (elFurniture.getCheckedRadioButtonId() != -1) {
                    valFurniture = ((RadioButton)findViewById(elFurniture.getCheckedRadioButtonId())).getText().toString();
                } else {
                    valFurniture = "";
                }

                elPriceByMonth = findViewById(R.id.priceByMonth);
                String valPriceByMonth = elPriceByMonth.getText().toString();

                elReporterName = findViewById(R.id.reporterName);
                String valReporterName = elReporterName.getText().toString();

                elCreatedAt = findViewById(R.id.createdAt);
                String valCreatedAt = elCreatedAt.getText().toString();

                if (!(types.contains(valType))) {
                    errors.add("Type");
                }

                if (!(isNumber(valBedRoom))) {
                    errors.add("Bed Room");
                }

                if (!(furnitures.contains(valFurniture))) {
                    errors.add("Furniture");
                }

                if (!(isNumber(valPriceByMonth))) {
                    errors.add("Price By Month");
                }

                if (isBlank(valReporterName)) {
                    errors.add("Reporter Name");
                }

                if (!(formatDate(valCreatedAt))) {
                    errors.add("Created At");
                }

                if (errors.size() > 0) {
                    Toast toast = Toast.makeText(MainActivity.this, sendErrorsMessage(errors), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "You have successfully created the form!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public final static String sendErrorsMessage(List errors) {
        String message = "";
        for (int error = 0; error < errors.size(); error++) {
            message += (errors.get(error) + ", ");
        }

        return "Invalid " + message + "fields.";
    }

    public final static boolean formatDate(String val)
    {
        return Pattern.compile("^\\d{1,2}[\\/.]\\d{1,2}[\\/.]\\d{4}$").matcher(val).matches();
    }

    public final static boolean isBlank(String val)
    {
        return Pattern.compile("^\\s*$").matcher(val).matches();
    }

    public final static boolean isNumber(String val)
    {
        return Pattern.compile("^\\d+$").matcher(val).matches();
    }
}