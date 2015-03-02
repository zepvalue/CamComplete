package it.complete.cam;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CamComplete extends Activity {
	private static final String TAG = "CamUseActivity";
	Camera camera;
	Preview preview;
	ImageView imgPreview;

	byte[] imageData;
	Button buttonClick, confirm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		preview = new Preview(this);
		((FrameLayout) findViewById(R.id.preview)).addView(preview);
		imgPreview = (ImageView) findViewById(R.id.lpreview);
		
	/*	finalprev = new Preview(this);
		((FrameLayout) findViewById(R.id.FinalPrev)).addView(preview);*/
		
		buttonClick = (Button) findViewById(R.id.bClick);
		confirm =(Button) findViewById(R.id.bConf);
		
		
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				preview.camera.takePicture(shutterCallback, rawCallback,
						jpegCallback);
			}
		});
		
		
		confirm.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(confirm.getContext(), Launcher.class);
				myIntent.putExtra("imageData", imageData);
			    setResult(RESULT_OK, myIntent);				
				finish();
				
			 	//onBackPressed ();
			}});
		
		
		

		Log.d(TAG, "onCreate'd");
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}
	};

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() {
		
		public void onPictureTaken(byte[] data, Camera camera) {
			imageData = data;
		//FileOutputStream outStream = null;
		//Bitmap bmp=BitmapFactory.decodeByteArray(data,0,data.length);
		//imgPreview.setImageBitmap(bmp);
		 Log.d(TAG, "onPictureTaken - jpeg");
			/*try {
				// write to local sandbox file system
				// outStream =
				// CameraDemo.this.openFileOutput(String.format("%d.jpg",
				// System.currentTimeMillis()), 0);
				// Or write to sdcard
				outStream = new FileOutputStream(String.format(
						"/sdcard/%d.jpg", System.currentTimeMillis()));
				outStream.write(data);
				outStream.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			
	*/	}
		
	
	
	};
	
	
	
	
}
