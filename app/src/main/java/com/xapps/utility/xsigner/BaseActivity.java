package com.xapps.utility.xsigner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.view.*;
import android.widget.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import eightbitlab.com.blurview.BlurView;
import com.xapps.utility.xsigner.BlurUtils;
import androidx.transition.TransitionManager;

public abstract class BaseActivity extends AppCompatActivity {
    
    public void ShowSingleButtonDialog(Context context, String title, String message, String buttonText, int uniqueId) {
        final AlertDialog FinishDialog = new AlertDialog.Builder(context).create();
		LayoutInflater FinishDialogLI = getLayoutInflater();
		View FinishDialogCV = FinishDialogLI.inflate(R.layout.material_dialog_layout_custom, null);
		FinishDialog.setView(FinishDialogCV);
		FinishDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final TextView DTitle = (TextView)
		FinishDialogCV.findViewById(R.id.Title);
		final TextView DMessage = (TextView)
		FinishDialogCV.findViewById(R.id.Message);
		final TextView RightText = (TextView)
		FinishDialogCV.findViewById(R.id.RightText);
		final LinearLayout LeftButton = (LinearLayout)
		FinishDialogCV.findViewById(R.id.LeftButton);
		final LinearLayout RightButton = (LinearLayout)
	    FinishDialogCV.findViewById(R.id.RightButton);
        final LinearLayout main = FinishDialogCV.findViewById(R.id.dialogContianer);
		DTitle.setText(title);
		DMessage.setText(message);
		RightText.setText(buttonText);
		LeftButton.setVisibility(View.GONE);
		RightButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View _view) {
				singleClickAction(FinishDialog, uniqueId);
			}
		});
		FinishDialog.setCancelable(false);
		FinishDialog.show();
    }
    
    public abstract void singleClickAction(AlertDialog dialog, int eventId);
}
