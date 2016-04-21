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
	 * 1.������һ��ͼƬ 2.�����ʵ�ֶ�����ʧ 3.�����������ط����� �������һ�������ַ��
	 * 
	 **/
	int index;
	ImageButton[] btns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {// ���������߳���
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ������ͼƬ
		// �Ȼ�ȡ��ַ
		btns = new ImageButton[6];
		btns[0] = (ImageButton) findViewById(R.id.imageButton1);
		btns[1] = (ImageButton) findViewById(R.id.imageButton2);
		btns[2] = (ImageButton) findViewById(R.id.imageButton3);
		btns[3] = (ImageButton) findViewById(R.id.imageButton4);
		btns[4] = (ImageButton) findViewById(R.id.imageButton5);
		btns[5] = (ImageButton) findViewById(R.id.imageButton6);

		// ���Ч��
		// �������߳�,�Ѻ�ʱ�Ĳ����ŵ�����������û�����
		/**
		 * ʵ���Զ��˳���Ϸ
		 */

		new Thread(new Runnable() {
			// �������ʱע�ⷶΧ�߳��൱��һ��view,��ͬ����
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
							// ��׿�涨�ѿ��Ƹ��Ľ���Ĳ����ŵ����߳���
							// �Ѻ�ʱ�Ĳ����ŵ����߳�������Ϊ������û�����
							// ������ì��ʱ���������߳��е������߳�

							btns[index]
									.setImageResource(R.drawable.ic_launcher);
							// Ϊ�ؼ��ӱ�ǩ
							btns[index].setTag("xing");

						}
					});

				}
			}
		}).start();

	}

	/**
	 * ��ӿؼ�
	 **/

	int score;

	public void fun(View view) {
		ImageButton btns = (ImageButton) view;
		// ʵ�ּƷ�
		String tag = (String) btns.getTag();
		if ("xing".equals(tag)) {
			score++;
			btns.setImageResource(R.drawable.bg);
			btns.setTag("bingjing");
		}
		Toast.makeText(this, "" + score, 0).show();
		// ""+���������������ֵ
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
