package asc.test.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tasks.bolts.Continuation;
import com.tasks.bolts.Task;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initeData();
    }

    private void initeData() {
        Log.i(TAG, "initeData");
        Task.call(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "test";
            }
        },Task.BACKGROUND_EXECUTOR).onSuccess(new Continuation<String, Object>() {
            @Override
            public Object then(Task<String> task) throws Exception {
                String result = task.getResult();
                Log.i(TAG, "then--task=="+result);
                return null;
            }
        },Task.UI_THREAD_EXECUTOR);
    }
}
