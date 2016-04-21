package com.it.dadishu;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	/**
	 * 1.随机获得一个图片 2.点击是实现动画消失 3.并且在其他地方出现 （获得有一个随机地址）
	 * 
	 **/
	int index;
	ImageButton[] btns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {// 运行在主线程中
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 随机获得图片
		// 先获取地址
		btns = new ImageButton[6];
		btns[0] = (ImageButton) findViewById(R.id.imageButton1);
		btns[1] = (ImageButton) findViewById(R.id.imageButton2);
		btns[2] = (ImageButton) findViewById(R.id.imageButton3);
		btns[3] = (ImageButton) findViewById(R.id.imageButton4);
		btns[4] = (ImageButton) findViewById(R.id.imageButton5);
		btns[5] = (ImageButton) findViewById(R.id.imageButton6);

		// 点击效果
		// 创建子线程,把耗时的操作放到里面这提高用户体验
		/**
		 * 实现自动退出游戏
		 */

		new Thread(new Runnable() {
			// 定义变量时注意范围线程相当于一个view,不同于类
			int maxtime = 60 * 1000;
			int time = 0;

			public void run() {
				while (time < maxtime) {
					time = time + 2000;

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					index = new Random().nextInt(6);

					runOnUiThread(new Runnable() {
						public void run() {
							// 安卓规定把控制更改界面的操作放到主线程中
							// 把耗时的操作放到子线程中这是为了提高用户体验
							// 当发生矛盾时可以在子线程中调用主线程

							btns[index]
									.setImageResource(R.drawable.ic_launcher);
							// 为控件加标签
							btns[index].setTag("xing");

						}
					});

				}
			}
		}).start();

	}

	/**
	 * 添加控件
	 **/

	int score;

	public void fun(View view) {
		ImageButton btns = (ImageButton) view;
		// 实现计分
		String tag = (String) btns.getTag();
		if ("xing".equals(tag)) {
			score++;
			btns.setImageResource(R.drawable.bg);
			btns.setTag("bingjing");
		}
		Toast.makeText(this, "" + score, 0).show();
		// ""+变量名可以输出其值
		btns.setImageResource(R.drawable.bg);
	}

	public void xia(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
