package com.example.towersadmin.utils

import android.app.AlertDialog
import android.content.DialogInterface

class msgSair {
    // Código botão Voltar
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            checkExit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void checkExit()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(“Deseja realmente sair?”)
        .setCancelable(false)
        .setPositiveButton(“Sim”, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            finish();
//Ação tomada caso o usuário escolha sim.
        }
    })
        .setNegativeButton(“Não”, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    });
        AlertDialog alert = builder.create();
        alert.show();
    }
}