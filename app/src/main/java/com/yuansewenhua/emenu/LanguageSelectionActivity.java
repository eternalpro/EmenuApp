package com.yuansewenhua.emenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class LanguageSelectionActivity extends Activity {
    private Button btnChinese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        LanguageSelection selectionListenerClass = new LanguageSelection();
        this.btnChinese = (Button) this.findViewById(R.id.btnChinese);
        this.btnChinese.setOnClickListener(selectionListenerClass);

        //检测数据库状态，如果数据库已存在就跳过数据库载入过程，如果没有则吧assets目录中的数据库文件加载进虚拟机或设备
        try {
            this.pullDataBaseIntoDevice(true);
        } catch (IOException e) {
            Log.e("DBPullError", "DBError", e.fillInStackTrace());
            e.printStackTrace();
        }

    }

    /**
     * 检测数据库状态，如果数据库已存在就跳过数据库载入过程，
     * 如果没有,则把assets目录中的数据库文件加载进虚拟机或设备
     *
     * @param need 是否必须拷贝
     * @throws IOException
     */
    private void pullDataBaseIntoDevice(boolean need) throws IOException {
        File dbFile = this.getDatabasePath("emenu-db");
        if (need || !dbFile.exists()) {
            if (!dbFile.getParentFile().exists()) {
                dbFile.getParentFile().mkdir();
            }
            InputStream is = this.getAssets().open("emenu-db");
            FileUtils.copyInputStreamToFile(is, dbFile);
        }
    }

    private class LanguageSelection implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            view.setAlpha(0.5f);
            switch (view.getId()) {
                case R.id.btnChinese:
                    Intent intent = new Intent(LanguageSelectionActivity.this, ShowActivity.class);
                    startActivity(intent);

//                    try {
//                        InputStream is = view.getContext().getAssets().open("douchiniangaopaigu.png");
//                        v.setImageDrawable(Drawable.createFromStream(is, "douchiniangaopaigu.png"));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    break;
                case R.id.btnFrancais:
                    break;
                case R.id.btnEnglish:
                    break;
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.btnChinese.setAlpha(1f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_language_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
