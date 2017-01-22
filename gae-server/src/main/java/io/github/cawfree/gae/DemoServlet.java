package io.github.cawfree.gae;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.net.URISyntaxException;

import java.util.Properties;

import com.google.firebase.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;

import io.github.cawfree.core.*;

public class DemoServlet extends HttpServlet {
  	
	/** Static Declarations. **/
	private static final String PATH_WEBINF_FIREBASE_KEY = "/WEB-INF/android-worker-firebase-adminsdk-hrq68-7d62f618ce.json";
	
	/* Member Variables. */
	private final String[] mReturnData = new String[]{ "default" };
	
	/** Initializes the Server on startup. **/
	@Override public final void init() throws ServletException {
		// Allocate the InputStream to fetch the Firebase Key.
                final InputStream       lInputStream       = this.getServletContext().getResourceAsStream(DemoServlet.PATH_WEBINF_FIREBASE_KEY);
                // Fetch the FirebaseOptions.
                final FirebaseOptions   lFirebaseOptions   = new FirebaseOptions.Builder().setServiceAccount(lInputStream).setDatabaseUrl(CoreGlobal.URL_DATABASE).build();
		// Initialize the default FirebaseApp.
		final FirebaseApp       lFirebaseApp       = FirebaseApp.initializeApp(lFirebaseOptions);
		// Fetch the Database Instance.
		final FirebaseDatabase  lFirebaseDatabase  = FirebaseDatabase.getInstance(lFirebaseApp);		
		// Fetch the Authorization Tools.
		final FirebaseAuth      lFirebaseAuth      = FirebaseAuth.getInstance(lFirebaseApp);
		// Fetch the Database Reference. (Must enable GAE manual scaling!)
		final DatabaseReference lDatabaseReference = lFirebaseDatabase.getReference();
		// Force the database to go online.
		lDatabaseReference.goOnline();
		
		try { 
			lDatabaseReference.child("something").addValueEventListener(new ValueEventListener() {
    				@Override public void onDataChange(DataSnapshot dataSnapshot) {
        				String res = dataSnapshot.getValue().toString();
        				mReturnData[0] = res;
    				}
				@Override public void onCancelled(DatabaseError pDatabaseError) {
				
				}
			});
			
		} 
		catch(RuntimeException r) { 
			this.mReturnData[0] = r.toString(); 
		}
		

	}

	@Override public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
 		
	resp.setContentType("text/plain");
	resp.getWriter().println("{ \"name\": \" "+ CoreGlobal.X+" "+this.mReturnData[0]+" \" }");

  }
}
