package com.example.nativeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class LoginActivity extends Activity {

	/**
	  * Declare the PARSE CLIENT KEY and APPLICATION ID for the Parse Initialization
	  */
  protected String clientKey = "liTYaW6SCcVkoM9KFL03mQsQtv8nKq96umPv54DP";
  protected String applicationId = "WZDdgyCHIW9EB3qh8uOyhh0ZeKkJOMv3j7DJVcW6";

  /**
   * Twitter app consumer key
   */
  protected String twitterConsumerKey="qAFJzPXQlwAonT3aCSPgg";

  /**
   * Twitter app consumer secret
   */
  protected String twitterConsumerSecret = "M45UL6rX3KHYjZgcSupqREeMU5VFcYfnrHZl1mtGw";

  /**
   * This is the onCreate method
   * @param applicationId, clientID are the application id and client id for the parse initialization 
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Parse.initialize(this, applicationId, clientKey);
    ParseTwitterUtils.initialize(twitterConsumerKey, twitterConsumerSecret);
    //ParseFacebookUtils.initialize(applicationId);
    
    setContentView(R.layout.main);
    ((Button) findViewById(R.id.login)).getBackground().setColorFilter(Color.parseColor("#E0E0E0"), Mode.SRC_ATOP);
    ((Button) findViewById(R.id.facebook)).getBackground().setColorFilter(Color.parseColor("#E0E0E0"), Mode.SRC_IN);
    ((Button) findViewById(R.id.twitter)).getBackground().setColorFilter(Color.parseColor("#00CCFF"), Mode.SRC_ATOP);
    ((Button) findViewById(R.id.signupbutton)).getBackground().setColorFilter(Color.parseColor("#E0E0E0"), Mode.SRC_ATOP);
  }

  /**
   * This is the TWITTER LOGIN CALLBACK
   * @param twitterConsumerKey, twitterConsumerSecret 
   */

  public void twitterCallback(View v) {
	  final ProgressDialog loginDialog = ProgressDialog.show(this, "",
		        "Logging in. Please wait ...", true);
    ParseTwitterUtils.logIn(this, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException err) {
    	  loginDialog.dismiss();
        if (user == null) {
          //listener.onError("twitter", err);
        } else if (user.isNew()) {
        	signedUpSuccessAlert();
        	
        } else {
          //listener.onSignin("twitter", user);
        	
          loginSuccess();
 
        }
      }
      
    });
  }


  /**
   * This is the FACEBOOK LOGIN CALLBACK
   * @param facebookConsumerKey, facebookConsumerSecret
   */

 /* public void facebookCallback(View v) {
    ParseTwitterUtils.logIn(this, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException err) {
        if (user == null) {
          listener.onError("facebook", err);
        } else if (user.isNew()) {
          listener.onSignup("facebook", user);
        } else {
          listener.onSignin("facebook", user);
        }
      }
    });
  }*/
  
  
  /**
   * Use getters to retrieve the usernames and passwords
   * getLoginUserName gets the name of the user
   * getLoginPwd gets the user's password 
   */

  private String getLoginUserName() {
    return ((EditText) findViewById(R.id.username)).getText().toString();
  }

  private String getLoginPwd() {
    return ((EditText) findViewById(R.id.pwd)).getText().toString();
  }

  public void loginCallback(View v) {
    final ProgressDialog dialog = ProgressDialog.show(this, "",
        "Logging in ...", true);

    //Get the users inputed USERNAME and PASSWORD from the login page 
    ParseUser.logInInBackground(getLoginUserName(), getLoginPwd(), new LogInCallback() {
      public void done(ParseUser user, ParseException e) {
        dialog.dismiss();
        if (user != null ) {
          //listener.onSignin("native", user);
        	
          //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        	Log.d("Message","Login SUCCESS");
        	loginSuccessfulAlert();
        
        	loginSuccess();
        }
         //Otherwise if the user is new and doesnt have an account
         //Create an account directly to parse 
          else {
      		//Create a log in failed alert to show the user that login failed
        	  loginFailedAlert();

        	 Log.d("Message","Login unsuccessful");
          //listener.onError("native", e);
          }
      }
    });
  }
 public void signupCallback(View v) {
	  //Display a sign up alert to notify the user that they are being signed up
	  createSignUpAlert();
	 //Create a ProcessDialog to show the user that they are being signed up

	  final ProgressDialog signupDialog = ProgressDialog.show(this, "",
	        "Signing you up. Please wait ...", true);
	  ParseUser newUser = new ParseUser();
  	  newUser.setUsername(getLoginUserName());
  	  newUser.setPassword(getLoginPwd());
  	  newUser.signUpInBackground(new SignUpCallback() {
  		  public void done(ParseException e) {
  			  signupDialog.dismiss();
  		    if (e == null) {
  		      // Hooray! Let them use the app now.
  		    } else {
  		      // Sign up didn't succeed. Look at the ParseException
  		      // to figure out what went wrong
  		    }
  		  }
  		});
        //startActivity(new Intent(LoginActivity.this, MainActivity.class));
  	  Log.d("Message","Signed Up successfully");
		signedUpSuccessAlert();
  	  //Once signup, prompt the user to log in
  	  loginCallback(v);
  }

 private void signedUpSuccessAlert() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setMessage("Siged up successfully. " +
			"Click login to proceed!");
	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
	    }
	});   
	// Create the AlertDialog object and return it
	builder.create();
	builder.show();
}

 private void createSignUpAlert() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setMessage("Click ok and Sign you up!");
	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
	    }
	});   
	// Create the AlertDialog object and return it
	builder.create();
	builder.show();
}
/**
 * This is a method that loads a new activity after successfully login in to the system
 * 
 */
	public void loginSuccess(){

	Intent loginSuccess = new Intent(this, MainActivity.class);
	startActivity(loginSuccess);
	finish();
  }

  
  /**
   * This is a dialog builder that will be used to send alerts to the user
   * in case they have any issue during sign up or login
   */
  public Dialog onCreateDialog(Bundle savedInstanceState) {
      // Use the Builder class for convenient dialog construction
      final AlertDialog.Builder builder = new AlertDialog.Builder(this);
             builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                     // FIRE ZE MISSILES!
                 }
             });   
      // Create the AlertDialog object and return it
      return builder.create();
  }

	private void loginFailedAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Log in failed! Try facebook or twitter or signup if you haven't!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		    }
		});   
		// Create the AlertDialog object and return it
		builder.create();
		builder.show();
	}

	private void loginSuccessfulAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Log in successful!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		    }
		});   
		// Create the AlertDialog object and return it
		builder.create();
		builder.show();
	}

 
}

