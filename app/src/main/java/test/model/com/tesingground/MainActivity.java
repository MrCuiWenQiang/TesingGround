package test.model.com.tesingground;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wtl.right.activity.BaseToolBarActivity;
import com.wtl.right.widget.dialog.DialogAction;
import com.wtl.right.widget.dialog.MyDialog;
import com.wtl.right.widget.dialog.TipDialog;

public class MainActivity extends BaseToolBarActivity {

    private Button button;
    private DialogTest test;

    @Override
    protected void initView() {
        setTitle("测试");
        setSubTitle("副标题");
        setBackIcon(R.mipmap.ic_launcher);

        button = findViewById(R.id.button);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        test = new DialogTest();
        test.init(this);
//        test.showTipDialog(TipDialog.Builder.ICON_TYPE_SUCCESS);
    }

    @Override
    protected void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.showBottomSeetDialog();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
