package com.syzo.txtreader;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReaderActivity extends AppCompatActivity {
    private TextView tvContent;
    private RelativeLayout readerLayout;
    private float fontSize = 18f;
    private float lineSpacing = 1.5f;
    private float letterSpacing = 0f;
    private int bgColor = R.color.bg_beige;
    private int textColor = R.color.text_black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        tvContent = findViewById(R.id.tvContent);
        readerLayout = findViewById(R.id.readerLayout);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnSettings = findViewById(R.id.btnSettings);

        btnBack.setOnClickListener(v -> finish());
        btnSettings.setOnClickListener(v -> showSettingsDialog());

        loadFile();
    }

    private void loadFile() {
        Uri fileUri = getIntent().getData();
        if (fileUri != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(fileUri);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line).append("\n");
                }
                reader.close();
                tvContent.setText(text.toString());
                applySettings();
            } catch (Exception e) {
                e.printStackTrace();
                tvContent.setText("加载文件失败: " + e.getMessage());
            }
        }
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("阅读设置");
        builder.setView(R.layout.dialog_settings);

        AlertDialog dialog = builder.create();
        dialog.show();

        SeekBar seekFontSize = dialog.findViewById(R.id.seekFontSize);
        SeekBar seekLineSpacing = dialog.findViewById(R.id.seekLineSpacing);
        SeekBar seekLetterSpacing = dialog.findViewById(R.id.seekLetterSpacing);
        RadioGroup radioGroupBg = dialog.findViewById(R.id.radioGroupBg);

        seekFontSize.setProgress((int) fontSize);
        seekLineSpacing.setProgress((int) (lineSpacing * 10));
        seekLetterSpacing.setProgress((int) (letterSpacing * 10));

        setBgRadioCheck(radioGroupBg);

        seekFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fontSize = progress;
                applySettings();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekLineSpacing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lineSpacing = progress / 10f;
                applySettings();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekLetterSpacing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                letterSpacing = progress / 10f;
                applySettings();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        radioGroupBg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbBeige) {
                bgColor = R.color.bg_beige;
                textColor = R.color.text_black;
            } else if (checkedId == R.id.rbGreen) {
                bgColor = R.color.bg_green;
                textColor = R.color.text_black;
            } else if (checkedId == R.id.rbBlue) {
                bgColor = R.color.bg_blue;
                textColor = R.color.text_black;
            } else if (checkedId == R.id.rbBlack) {
                bgColor = R.color.bg_black;
                textColor = R.color.text_white;
            }
            applySettings();
        });
    }

    private void setBgRadioCheck(RadioGroup group) {
        if (bgColor == R.color.bg_beige) {
            group.check(R.id.rbBeige);
        } else if (bgColor == R.color.bg_green) {
            group.check(R.id.rbGreen);
        } else if (bgColor == R.color.bg_blue) {
            group.check(R.id.rbBlue);
        } else if (bgColor == R.color.bg_black) {
            group.check(R.id.rbBlack);
        }
    }

    private void applySettings() {
        tvContent.setTextSize(fontSize);
        tvContent.setLineSpacing(0, lineSpacing);
        tvContent.setLetterSpacing(letterSpacing);
        tvContent.setTextColor(ContextCompat.getColor(this, textColor));
        readerLayout.setBackgroundColor(ContextCompat.getColor(this, bgColor));
    }
}
