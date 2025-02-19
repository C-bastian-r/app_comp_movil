package com.example.app_comp_movil.Utils.Navigation;


import android.content.Context;
import android.content.Intent;

//Uso general para ejecutar las navegaciones sin tener que definir un Intent
//para cada caso

public class IntentNavigator {
    private Context context;

    public IntentNavigator(Context context) {this.context = context;}

    public void setIntentNav(Class<?> destinyClass){
        Intent intent = new Intent(context, destinyClass);
        context.startActivity(intent);
    }

}
