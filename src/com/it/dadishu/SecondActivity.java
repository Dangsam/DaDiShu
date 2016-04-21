package com.it.dadishu;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SecondActivity extends Activity {

	int index;
	ImageButton[] btns;

	protected void onCreate(Bundle savedInstanceState) {
		// 运行在主线程中
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		btns = new ImageButton[6];
		btns[0] = (ImageButton) findViewById(R.id.ImageButton01);
		btns[1] = (ImageButton) findViewById(R.id.ImageButton02);
		btns[2] = (ImageButton) findViewById(R.id.ImageButton03);
		btns[3] = (ImageButton) findViewById(R.id.ImageButton04);

		// 将复杂的操作放到子线程中，提高用户体验
		/**
		 * 项目打地鼠 效果简介： 定时连续随机出现图片 点击后消失 实现点击计分 实现定时关闭
		 */
		
		new Thread(new Runnable() {
			int time=0;
			int max=30*1000;
			public void run() {

				while (time<max) {
					time=time+2000;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {

						e.printStackTrace();
						index = new Random().nextInt(4);
					}
					runOnUiThread(new Runnable() {
						public void run() {
							btns[index]
									.setImageResource(R.drawable.ic_launcher);
							btns[index].setTag("xing");
						}
					});
				}

			}
		}).start();

	}

	/**
	 * 添加控件实现某种功能 点击后图片覆盖消失且弹出计分框
	 */
	int score=0;
	public void fun(View view) {
		ImageButton btns = (ImageButton) view;
        String tag=(String) btns.getTag();
        if("xing".equals(tag)){
        	score++;
        btns.setImageResource(R.drawable.bg);
        btns.setTag("beijing");
        Toast.makeText(this, ""+score, 0).show();
        }
	}

	// 跳转到上一页
	public void fan(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
