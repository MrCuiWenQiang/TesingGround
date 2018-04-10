package test.model.com.tesingground;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.wtl.right.activity.BaseToolBarActivity;
import com.wtl.right.widget.FragmentPagerAdapter;

import java.util.ArrayList;

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
        ViewPager viewpager = findViewById(R.id.viewpager);
        ArrayList<Object> fragments = new ArrayList<>();
        fragments.add(new Fragmentdemo());
        fragments.add(new Fragmentdemo());
        fragments.add(new Fragmentdemo());
        fragments.add(new Fragmentdemo());
        new FragmentPagerAdapter.Builder(getSupportFragmentManager()).addDatas(fragments).create(viewpager);
        test = new DialogTest();
        test.init(this);
    }

    @Override
    protected void initListener() {
        getSupportFragmentManager();
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
