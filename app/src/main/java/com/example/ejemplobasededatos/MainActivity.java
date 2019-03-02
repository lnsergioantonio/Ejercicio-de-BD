package com.example.ejemplobasededatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ejemplobasededatos.POJO.Plant;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String LOGTAG="LOGTAG";
    Button btnAll,btnMin, btnDel;
    EditText editText;
    ListView listView;
    PlantsDataSource dataSource;
    List<Plant> plants ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= findViewById(R.id.editText);
        btnAll = findViewById(R.id.getAll);
        btnMin = findViewById(R.id.getMin);
        btnDel = findViewById(R.id.delete);

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plants = dataSource.findAll();
                refreshDisplay();
            }
        });
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plants = dataSource.findBy("price < 6","price ASC");
                refreshDisplay();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantId = editText.getText().toString();

                if(!plantId.equals("")){
                    dataSource.deleteById(plantId);
                    plants = dataSource.findAll();
                    refreshDisplay();
                }
            }
        });

        listView = findViewById(R.id.listView);
        dataSource = new PlantsDataSource(this);
        dataSource.open();

        plants = dataSource.findAll();

        if(plants.size()==0){
            createData();
            plants = dataSource.findAll();
        }
        refreshDisplay();
    }

    private void refreshDisplay() {
        ArrayAdapter<Plant> adapter =new ArrayAdapter<Plant>(this,android.R.layout.simple_list_item_1,plants);
        listView.setAdapter(adapter);
    }

    public void createData(){
        Plant plant = new Plant();
        plant.setBotanical("Sanguinaria canadensis");
        plant.setPrice(2.4);
        dataSource.create(plant);
        Log.i(LOGTAG,"ID"+plant.getId());

        plant = new Plant();
        plant.setBotanical("Aquilegia canadensis");
        plant.setPrice(9.37);
        dataSource.create(plant);
        Log.i(LOGTAG,"ID"+plant.getId());

        plant = new Plant();
        plant.setBotanical("Caltha palustris");
        plant.setPrice(6.81);
        dataSource.create(plant);
        Log.i(LOGTAG,"ID"+plant.getId());

        plant = new Plant();
        plant.setBotanical("Caltha palustris");
        plant.setPrice(9.9);
        dataSource.create(plant);
        Log.i(LOGTAG,"ID"+plant.getId());

        plant = new Plant();
        plant.setBotanical("Dicentra cucullaria");
        plant.setPrice(6.44);
        dataSource.create(plant);
        Log.i(LOGTAG,"ID"+plant.getId());
    }
}
