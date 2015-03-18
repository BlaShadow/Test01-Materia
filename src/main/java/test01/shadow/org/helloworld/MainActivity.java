package test01.shadow.org.helloworld;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView)findViewById(R.id.test_txt);

        if(getValue().isEmpty() == false) {
            Html.ImageGetter imageGetter = new Html.ImageGetter(){
                @Override
                public Drawable getDrawable(String source) {
                    Drawable drawFromPath = MainActivity.this.getResources().getDrawable(R.drawable.ic_launcher);

                    drawFromPath.setBounds(0, 0, 200,200);

                    return drawFromPath;
                }
            };

            txt.setText(Html.fromHtml("<a>Hola</a><img src='https://node-os.com/images/nodejs.png'>", imageGetter, null));
        }

        setValue();
    }

    public String getValue(){
        return getSharedPreferences("appPrivate", MODE_PRIVATE).getString("app","");
    }

    public void setValue(){
        SharedPreferences.Editor editor = getSharedPreferences("appPrivate", MODE_PRIVATE).edit();
        editor.putString("app", "Hola profe");
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
