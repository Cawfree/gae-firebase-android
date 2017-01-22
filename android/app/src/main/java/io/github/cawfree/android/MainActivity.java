package io.github.cawfree.android;

/* Android Dependencies. */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/* Firebase Dependencies.*/
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.database.*;

/* Core Dependencies. */
import io.github.cawfree.core.*;

public class MainActivity extends AppCompatActivity {

    /* Static Declarations. */
    private static final String TAG = "MainActivity";

    @Override
    protected final void onCreate(final Bundle pSavedInstanceState) {
        // Interact with the parent.
	super.onCreate(pSavedInstanceState);
        // Load the ContentView.
        this.setContentView(R.layout.activity_main);
        // Inform the user that we've launched the app.
	Toast.makeText(MainActivity.this, "Listening for changes.", Toast.LENGTH_SHORT).show();
	// Fetch the DatabaseReference.
	DatabaseReference lDatabaseReference = FirebaseDatabase.getInstance().getReference();
	// Assign a value to a specific location.
	lDatabaseReference.child("something").setValue("changed by android");
	// Listen for field changes.
	lDatabaseReference.child("something").addValueEventListener(new ValueEventListener() { 
        /** Called when the data changes.**/
        @Override public void onDataChange(final DataSnapshot pDataSnapshot) {
            // Iterate the DataSnapshot.
            for (final DataSnapshot lDataSnapshot: pDataSnapshot.getChildren()) {
                /** TODO: Handle Snapshot Data. **/
            }
	    // Inform the user that we've detected a change.
	    Toast.makeText(MainActivity.this, "Detected a change!", Toast.LENGTH_SHORT).show();	
           }
        /** Called when a data transaction fails. **/
        @Override public final void onCancelled(final DatabaseError pDatabaseError) {
                // Log that the database failed.
                Log.w(TAG, "loadPost:onCancelled", pDatabaseError.toException());
            }
        });
        
        // Detemine if there's an Intent to Handle.
        if(this.getIntent().getExtras() != null) {
            // Iterate the Keys.
            for (final String lKey : this.getIntent().getExtras().keySet()) {
                // Fetch the value.
		Object lValue = this.getIntent().getExtras().get(lKey);
            }
        }
    }

}
