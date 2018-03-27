package test.model.com.tesingground;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.wtl.right.widget.RecyclerItemClickListener;
import com.wtl.right.widget.dialog.BottomSeetDialog;
import com.wtl.right.widget.dialog.DialogAction;
import com.wtl.right.widget.dialog.MyDialog;
import com.wtl.right.widget.dialog.TipDialog;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/3/24 0024.
 */

public class DialogTest {
    private Context mContext;

    public void init(Context mContext) {
        this.mContext = mContext;
    }

    private void showCheckBoxSelect() {
        new MyDialog.CheckBoxMessageDialogBuilder(mContext)
                .setMessage("确定要删除吗")
                .addAction("确定", null)
                .addAction("取消", null)
                .show(((FragmentActivity) mContext).getSupportFragmentManager(), "s");
    }

    private void showMessage() {
        new MyDialog.MessageDialogBuilder(mContext).setTitle("标题").setMessage("内容啊啊啊啊啊啊啊啊啊啊啊啊啊").addAction("qued", new DialogAction.ActionListener() {
            @Override
            public void onClick(MyDialog dialog, int index) {
                dialog.dismiss();
            }
        }).addAction("1231", DialogAction.ACTION_TYPE_BLOCK, new DialogAction.ActionListener() {
            @Override
            public void onClick(MyDialog dialog, int index) {
                dialog.dismiss();
            }
        }).show(((FragmentActivity) mContext).getSupportFragmentManager(), "d");
    }

    private void showSelectList() {
        final String[] items = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6"};
        new MyDialog.MultipleChoiceDialogBuilder(mContext).setTitle("测试").setCheckedItems(new int[]{1, 3}).addItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).addAction("确定", new DialogAction.ActionListener() {
            @Override
            public void onClick(MyDialog dialog, int index) {
                Toast.makeText(mContext, "select" + index, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }).show(((FragmentActivity) mContext).getSupportFragmentManager(), "da");
    }

    private void showMenuDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3"};
        new MyDialog.MenuDialogBuilder(mContext)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show(((FragmentActivity) mContext).getSupportFragmentManager(), "da");
    }


    public void showCheckDialog() {
        int index = 0;
        final String[] items = new String[]{"选项1", "选项2", "选项3"};
        new MyDialog.CheckableDialogBuilder(mContext).setCheckedIndex(index)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你选择了 " + items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show(((FragmentActivity) mContext).getSupportFragmentManager(), "da");
    }

    public void showTipDialog(int style) {
        new TipDialog.Builder(mContext).setType(style).setTipWord("456").create().show(((FragmentActivity) mContext).getSupportFragmentManager(), "da");
        ;
    }

    public void showBottomSeetDialog() {
        BottomSeetDialog.Builder builder = new BottomSeetDialog.Builder(mContext).addItem("001").addItem("002");
        builder.setOnItemClickListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int posotion) {
                Toast.makeText(mContext,"position"+posotion,Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show(((FragmentActivity) mContext).getSupportFragmentManager(), "da");
    }
}
