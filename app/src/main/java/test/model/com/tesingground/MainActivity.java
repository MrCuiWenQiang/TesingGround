package test.model.com.tesingground;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.wtl.right.activity.BaseToolBarActivity;
import com.wtl.right.widget.builderdialog.DialogAction;
import com.wtl.right.widget.builderdialog.MyDialog;

public class MainActivity extends BaseToolBarActivity {


    @Override
    protected void initView() {
        setTitle("测试");
        setSubTitle("副标题");
        setBackIcon(R.mipmap.ic_launcher);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        showSelectList();
    }

    private void showCheckBoxSelect() {
        new MyDialog.CheckBoxMessageDialogBuilder(this)
                .setMessage("确定要删除吗")
                .addAction("确定", null)
                .addAction("取消", null)
                .show(getSupportFragmentManager(), "s");
    }

    private void showMessage() {
        new MyDialog.MessageDialogBuilder(this).setTitle("标题").setMessage("内容啊啊啊啊啊啊啊啊啊啊啊啊啊").addAction("qued", new DialogAction.ActionListener() {
            @Override
            public void onClick(MyDialog dialog, int index) {
                dialog.dismiss();
            }
        }).addAction("1231", DialogAction.ACTION_TYPE_BLOCK, new DialogAction.ActionListener() {
            @Override
            public void onClick(MyDialog dialog, int index) {
                dialog.dismiss();
            }
        }).show(getSupportFragmentManager(), "d");
    }

    private void showSelectList() {
        final String[] items = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6"};
        new MyDialog.MultipleChoiceDialogBuilder(this).setTitle("测试").setCheckedItems(new int[]{1, 3}).addItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).addAction("确定", new DialogAction.ActionListener() {
            @Override
            public void onClick(MyDialog dialog, int index) {
                Toast.makeText(MainActivity.this, "select" + index, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }).show(getSupportFragmentManager(), "da");
    }

    private void showMenuDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3"};
        new MyDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show(getSupportFragmentManager(), "da");
    }


    private void showCheckDialog() {
        int index = 0;
        final String[] items = new String[]{"选项1", "选项2", "选项3"};
        new MyDialog.CheckableDialogBuilder(this).setCheckedIndex(index)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show(getSupportFragmentManager(), "da");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
