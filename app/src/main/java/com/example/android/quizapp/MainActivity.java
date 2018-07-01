package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submitQuiz(View view) {
        //Get the student name
        EditText userNameEditText = (EditText) findViewById(R.id.user_name_field);
        String userName = userNameEditText.getText().toString().toUpperCase();

        // Score the quiz
        double score = markQuizScript();

        // Display the order summary on the screen
        String scoreMessage = createScoreSummary(userName, score);

        // Show score summary as a toast
        Toast.makeText(this, scoreMessage, Toast.LENGTH_LONG).show();
    }

    /**
     * This method creates score summary - displays student name, score in % and the correct answers for all questions
     * @ param name of the student
     * @ param quiz score
     * @ return text summary
     */
    public String createScoreSummary(String userName, double score) {
        String scoreMessage = getString(R.string.student_name, userName);
        scoreMessage += "\n" + getString(R.string.quiz_score,
                NumberFormat.getPercentInstance().format(score));
        scoreMessage += "\n" + "\n" + getString(R.string.corr_answer);
        scoreMessage += "\n" + getString(R.string.answer_i);
        scoreMessage += "\n" + getString(R.string.answer_ii);
        scoreMessage += "\n" + getString(R.string.answer_iii);
        scoreMessage += "\n" + getString(R.string.answer_iv);
        scoreMessage += "\n" + getString(R.string.answer_v);
        return scoreMessage;
    }

    /**
     * This method checks the responses of the student for all the questions and score the quiz.
     * @ return quiz score
     */
    public double markQuizScript() {
        //define and initialize the score to 0
        double quizScore = 0;

        //Assign the correct answers to the quiz to variables
        String questionIAnswerCorr = getString(R.string.question_i_option_a);
        String questionIIAnswerCorr = getString(R.string.question_ii_option_b);
        String questionIVAnswerCorr = getString(R.string.i_circuit);
        String questionVAnswerCorrA = getString(R.string.silicon);
        String questionVAnswerCorrB = getString(R.string.semiconductor);

        //Find the id of the radio button selected for question i and get the text
        RadioGroup questionIRadioGroup = (RadioGroup) findViewById(R.id.question_i_radio_group);
        int questionIRadioButtonId = questionIRadioGroup.getCheckedRadioButtonId();
        if( questionIRadioButtonId != -1 ) {
            RadioButton questionIRadioButtonChecked = (RadioButton) findViewById(questionIRadioButtonId);
            String questionIResponse = questionIRadioButtonChecked.getText().toString();

            // check whether or not the response is correct. If it is correct add 1 to the score
            if(questionIResponse.equalsIgnoreCase(questionIAnswerCorr)) {
                quizScore += 1;
            }
        }

        //Find the id of the radio button selected for question ii and get the text
        RadioGroup questionIIRadioGroup = (RadioGroup) findViewById(R.id.question_ii_radio_group);
        int questionIIRadioButtonId = questionIIRadioGroup.getCheckedRadioButtonId();
        if( questionIIRadioButtonId != -1 ) {
            RadioButton questionIIRadioButtonChecked = (RadioButton) findViewById(questionIIRadioButtonId);
            String questionIIResponse = questionIIRadioButtonChecked.getText().toString();

            // check whether or not the response is correct. If it is correct add 1 to the score
            if(questionIIResponse.equalsIgnoreCase(questionIIAnswerCorr)) {
                quizScore += 1;
            }
        }

        // Figure out if the student checked option a for question iii
        CheckBox optionACheckBox = (CheckBox) findViewById(R.id.question_iii_option_a_checkbox);
        boolean hasOptionA = optionACheckBox.isChecked();
        //Add 0.5 if the student checked option a
        if (hasOptionA) {
            quizScore += 0.5;
        }

        // Figure out if the student checked option b for question iii
        CheckBox optionBCheckBox = (CheckBox) findViewById(R.id.question_iii_option_b_checkbox);
        boolean hasOptionB = optionBCheckBox.isChecked();
        //Subtract 0.5 if the student checked option b
        if (hasOptionB) {
            quizScore -= 0.5;
        }

        // Figure out if the student checked option c for question iii
        CheckBox optionCCheckBox = (CheckBox) findViewById(R.id.question_iii_option_c_checkbox);
        boolean hasOptionC = optionCCheckBox.isChecked();
        //Add 0.5 if the student checked option c
        if (hasOptionC) {
            quizScore += 0.5;
        }

        // Figure out if the student checked option d for question iii
        CheckBox optionDCheckBox = (CheckBox) findViewById(R.id.question_iii_option_d_checkbox);
        boolean hasOptionD = optionDCheckBox.isChecked();
        //Subtract 0.5 if the student checked option d
        if (hasOptionD) {
            quizScore -= 0.5;
        }

        //Get question iv response
        EditText questionIVEditText = (EditText) findViewById(R.id.question_iv_answer_field);
        String questionIVResponse = questionIVEditText.getText().toString();

        // check whether or not the response text contains the correct answer. If it is correct add 1 to the score
        if(questionIVResponse.toLowerCase().contains(questionIVAnswerCorr.toLowerCase())) {
            quizScore += 1;
        }

        //Get question v response
        EditText questionVEditText = (EditText) findViewById(R.id.question_v_answer_field);
        String questionVResponse = questionVEditText.getText().toString();

        // check whether or not the response text contains any of the possible correct answers. If it is correct add 1 to the score
        if(questionVResponse.toLowerCase().contains(questionVAnswerCorrA.toLowerCase()) ||
                questionVResponse.toLowerCase().contains(questionVAnswerCorrB.toLowerCase())) {
            quizScore += 1;
        }

        return quizScore/5;

    }

    /**
     * This method resets the layout content / view i.e. reset text fields, radio groups and checkboxes, when the reset button is clicked.
     */
    public void resetQuiz(View view) {
        setContentView(R.layout.activity_main);
    }

}
