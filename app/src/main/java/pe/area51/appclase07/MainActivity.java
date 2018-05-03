package pe.area51.appclase07;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonLongtaskMainThread).setOnClickListener(this);
        findViewById(R.id.buttonLongTaskWorkerThread).setOnClickListener(this);

    }

    private void executeLongTask(){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void executeLongTaskOnWorkerThread(){
         final Dialog progressDialog = createProgessDialog();
          progressDialog.show();
          new Thread(new Runnable(){
              @Override
              public void run() {
                executeLongTask();
                progressDialog.dismiss();
              }
          }).start();

    }

    private void executeLongTaskOnMainThread(){
        final Dialog progressDialog = createProgessDialog();
        progressDialog.show();
        executeLongTask();
        progressDialog.dismiss();
    }

    private Dialog createProgessDialog(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.progress_message);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLongtaskMainThread:
            executeLongTaskOnMainThread();
            break;
            case R.id.buttonLongTaskWorkerThread:
            executeLongTaskOnWorkerThread();
            break;
        }
    }
}
