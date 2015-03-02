package it.complete.cam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Launcher extends Activity {
    /** Called when the activity is first created. */
	
	Button launch, send;
	Preview preview;
	ImageView imgPreview;
	EditText name, desc;
	TextView res;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lmain);
       
      launch = (Button) findViewById(R.id.bLaunch);
      imgPreview = (ImageView) findViewById(R.id.lpreview);
      name = (EditText) findViewById(R.id.etNome);
      desc = (EditText) findViewById(R.id.etDesc);
      send = (Button) findViewById(R.id.etDesc);
      res = (TextView) findViewById(R.id.res);
      
      
      launch.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent myIntent = new Intent(launch.getContext(), CamComplete.class);
				final int result=1;
				startActivityForResult(myIntent, result);				
			
			}});  
    
        
        send.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				res.setText("Invio dati in corso... \n");
				// TODO Auto-generated method stub
				String nameET = name.getText().toString();
				String descET = desc.getText().toString();
				
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost post = new HttpPost("http://www.terryvenus.it/booking/public/index.php/test/android");
				
				List<NameValuePair> 
				arrayDati = new ArrayList<NameValuePair>(1);
				arrayDati.add(new BasicNameValuePair("nome", nameET));
				arrayDati.add(new BasicNameValuePair("descrizione", descET));			
				
				try {
					post.setEntity(new UrlEncodedFormEntity(arrayDati));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				
				try {
					String response = httpclient.execute(post, responseHandler);
					res.setText(response);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			 
				
				
					
			}});
    }
    
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
     super.onActivityResult(requestCode, resultCode, data);
     byte[] imageData = data.getByteArrayExtra("imageData");
	 Bitmap bmp=BitmapFactory.decodeByteArray(imageData,0,imageData.length);
	 imgPreview.setImageBitmap(bmp);      
    }    
}