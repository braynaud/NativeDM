
/**
 * This is a class that enables a user to login via facebook, twitter or directly to parse.com
 * @author Derrick Orare
 */
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
import android.os.Handler;
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
   * Facebook app consumer key 
   */
 
  protected String facebookConsumerSecret;
  
  /**
   * Facebook app consumer secret
   */
  
  protected String facebookConsumerKey;
  
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
    ((Button) findViewById(R.id.login)).getBackground().setColorFilter(Color.parseColor("#989898"), Mode.SRC_ATOP);
    ((Button) findViewById(R.id.facebook)).getBackground().setColorFilter(Color.parseColor("#424de2"), Mode.SRC_IN);
    ((Button) findViewById(R.id.twitter)).getBackground().setColorFilter(Color.parseColor("#5ca6ce"), Mode.SRC_ATOP);
    ((Button) findViewById(R.id.signupbutton)).getBackground().setColorFilter(Color.parseColor("#989898"), Mode.SRC_ATOP);
    
    
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
        } else if (user.isNew()) {
        	signedUpSuccessAlert();
        	//After someone has been signed up, link their accounts to parse using the 
        	//TwitterUtils.link to make sure they are linked to their account
        	linkTwitterUser();
        	
        } else {
        	
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
   //Get the users inputed USERNAME and PASSWORD from the login page 
    String username = getLoginUserName();
    String password = getLoginPwd();
    
    //Verify that the usernames and password are valid before you register the user
	  if (username.length() < 1) {
		  	invalidUsernameAlert();
		    dialog.dismiss();
		    return;
	  }
	  if (password.length() <1){
		  	invalidPasswordAlert();
		    dialog.dismiss();
		    return;
	  }
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
  /**
   * LOGIN directly without using facebook or twitter
   * @param View v
   */

  public void loginCallback(View v) {
    final ProgressDialog dialog = ProgressDialog.show(this, "",
        "Logging in ...", true);

    //Get the users inputed USERNAME and PASSWORD from the login page 
    String username = getLoginUserName();
    String password = getLoginPwd();
    
    //Verify that the user names and password are valid before you register the user
	  if (username.length() < 1) {
		  	invalidUsernameAlert();
		    dialog.dismiss();
		    return;
	  }
	  if (password.length() <1){
		  	invalidPasswordAlert();
		    dialog.dismiss();
		    return;
	  }
    
    ParseUser.logInInBackground(getLoginUserName(), getLoginPwd(), new LogInCallback() {


      public void done(ParseUser user, ParseException e) {
        dialog.dismiss();
        if (user != null ) {
          //listener.onSignin("native", user);
        	
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
          }
      }
    });
  }
 public void linkTwitterUser(){
	//Check and see if the user is linked to the account. If he or she is not linked, make the link
	    final ParseUser user = ParseUser.getCurrentUser();
	    if (user != null) {
	    if (!ParseTwitterUtils.isLinked(user)) {
	    	  ParseTwitterUtils.link(user, this, new SaveCallback() {
	    	    @Override
	    	    public void done(ParseException ex) {
	    	      if (ParseTwitterUtils.isLinked(user)) {
	    	        Log.d("MyApp", "Woohoo, user logged in with Twitter!");
	    	      }
	    	    }
	    	  });
	    	}
	    }
 }
 public void signupCallback(View v) {
	 final View view = v;
	  //Display a sign up alert to notify the user that they are being signed up
	  createSignUpAlert();
	 //Create a ProcessDialog to show the user that they are being signed up

	  final ProgressDialog signupDialog = ProgressDialog.show(this, "",
	        "Signing you up. Please wait ...", true);
	  //Make sure that the user doesn't input a null value as the user name
	  //if they do, return an alert and do not complete the signup
	  String username = getLoginUserName();
	  String password = getLoginPwd();
	  if (username.length() < 1) {
		  	invalidUsernameAlert();
		    signupDialog.dismiss();
          return;
	  }
	  if (password.length() <1){
		  	invalidPasswordAlert();
		    signupDialog.dismiss();
		   return;

	  }
	  ParseUser newUser = new ParseUser();
  	  newUser.setUsername(getLoginUserName());
  	  newUser.setPassword(getLoginPwd());
  	  newUser.signUpInBackground(new SignUpCallback() {
  		  public void done(ParseException e) {
  			  //Dismiss the signupDialog 
  			  signupDialog.dismiss();
  		    if (e == null) {
  		      // Signup successful!
  		  	  Log.d("Message","Signed Up successfully");
  			signedUpSuccessAlert();
  			loginProgress();
  	  	  	//Give the device sometime to update the info in parse.com. This may take a few seconds.
  	  	  	//Use countdowntimer to do this
  			final Handler handler = new Handler();
  			handler.postDelayed(new Runnable() {
  	        @Override
  	        public void run() {
  	            // Give the device 5 seconds to delay as we wait for the timer to update.
  	        	//Once that is done, prompt the device to log in the user. 
  	          loginCallback(view);


  	        }
  	    }, 10000);
  		    } else {
  		      // Sign up didn't succeed. 
  		    signupUnsuccessful();

  		    }
  		  }
  		  
  		});
  	  
  }
 public void loginProgress(){
	 ProgressDialog dialog = ProgressDialog.show(this, "",
 	        "Logging in ..." +
 	        "This may take a few seconds as we update your information", true);
 }
private void signupUnsuccessful(){
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setMessage("SignUp failed. Try again or choose a different username...");
	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
	    }
	});   
	// Create the AlertDialog object and return it
	builder.create();
	builder.show();

}
private void invalidPasswordAlert() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setMessage("Password cannot be blank. Choose a correct password");
	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
	    }
	});   
	// Create the AlertDialog object and return it
	builder.create();
	builder.show();
}

private void invalidUsernameAlert() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setMessage("Username cannot be blank. Choose a correct username");
	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
	    }
	});   
	// Create the AlertDialog object and return it
	builder.create();
	builder.show();
}

 private void signedUpSuccessAlert() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setMessage("Signed up successfully... ");
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
