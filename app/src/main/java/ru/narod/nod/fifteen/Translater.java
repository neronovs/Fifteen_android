package ru.narod.nod.fifteen;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by User on 19.11.2016.
 */

class Translater {

    private Activity activity;
    private Context context;
    String language = Locale.getDefault().getDisplayLanguage();

    public Translater(Context context, Activity activity) {
        this.activity = activity;
        this.context = context;

        switch (activity.getComponentName().getClassName()) {
            case "ru.narod.nod.fifteen.HighScoresActivity" :
                activity_high_scores_translate();
                break;
            case "ru.narod.nod.fifteen.MainActivity" :
                activity_main_translate();
                break;
            case "ru.narod.nod.fifteen.RulesActivity" :
                activity_rules_translate();
                break;
            case "ru.narod.nod.fifteen.FieldActivity" :
                activity_field_translate();
                break;
            default:
                break;
        }
    }

    private void activity_high_scores_translate() {
        TextView tvHS = (TextView) activity.findViewById(R.id.tvHS);
        TextView tvBest = (TextView) activity.findViewById(R.id.tvBest);
        TextView tvIs = (TextView) activity.findViewById(R.id.tvIs);
        TextView tvOnThe = (TextView) activity.findViewById(R.id.tvOnThe);

        if (language.equalsIgnoreCase("русский")) {
            tvHS.setText(activity.getResources().getString(R.string.high_scores_head_rus).toString());
            tvBest.setText(activity.getResources().getString(R.string.best_rus).toString());
            tvIs.setText(activity.getResources().getString(R.string.is_rus).toString());
            tvOnThe.setText(activity.getResources().getString(R.string.on_the_rus).toString());
        }
    }

    private void activity_main_translate() {
        TextView tvHeader = (TextView) activity.findViewById(R.id.tvHeader);
        Button btnContinue = (Button) activity.findViewById(R.id.btnContinue);
        Button btnStart = (Button) activity.findViewById(R.id.btnStart);
        Button btnHS = (Button) activity.findViewById(R.id.btnHS);
        Button btnRules = (Button) activity.findViewById(R.id.btnRules);
        Button btnExit = (Button) activity.findViewById(R.id.btnExit);


        if (language.equalsIgnoreCase("русский")) {
            tvHeader.setText(activity.getResources().getString(R.string.the_fifteen_rus));
            btnContinue.setText(activity.getResources().getString(R.string.cont_rus));
            btnStart.setText(activity.getResources().getString(R.string.new_game_rus));
            btnHS.setText(activity.getResources().getString(R.string.high_scores_rus));
            btnRules.setText(activity.getResources().getString(R.string.rules_rus));
            btnExit.setText(activity.getResources().getString(R.string.exit_rus));
        }
    }

    private void activity_rules_translate() {
        TextView multiAutoCompleteTextView = (TextView) activity.findViewById(R.id.multiAutoCompleteTextView);
        TextView rulesTV = (TextView) activity.findViewById(R.id.rulesTV);
        //use specified language for description
        if (language.equalsIgnoreCase("русский")){
            multiAutoCompleteTextView.setText(activity.getResources().getString(R.string.rules_description_rus).toString());
            rulesTV.setText(activity.getResources().getString(R.string.rules_rus).toString());
        }
    }

    private void activity_field_translate() {
        TextView tvFieldTime = (TextView) activity.findViewById(R.id.tvFieldTime);
        //use specified language to fill info
        if (language.equalsIgnoreCase("русский")){
            tvFieldTime.setText(activity.getResources().getString(R.string.time_rus).toString());
        }
    }
}
