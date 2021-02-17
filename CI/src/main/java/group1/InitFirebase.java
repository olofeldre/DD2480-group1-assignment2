package group1;

import java.io.FileInputStream;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;

class InitFirebase {
    
    public static void initialize() throws Exception {
        // Initialize Firebase
        FileInputStream serviceAccount = new FileInputStream("firebase-adminsdk-serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();
        FirebaseApp.initializeApp(options);
    }
}