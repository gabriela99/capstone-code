//package com.example.capstone_code.utils;
//
//import android.content.Intent;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.capstone_code.R;
//import com.example.capstone_code.viewmodel.LoginActivity;
//import com.example.capstone_code.viewmodel.UserListActivity;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class Toolbar extends ViewGroup {
//
//    @Override
//    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
//    }
//
//    /**
//     * Takes user to previous page
//     * @return true if back button is pressed
//     */
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//
//    /**
//     * Fill the toolbar with the menu from the XML file and the search view
//     * @param menu
//     * @return
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_profile_menu, menu);
//        return true;
//    }
//
//    /**
//     * When profile is selected, user sent to profile
//     * When logout is selected, user is logged out
//     * @param item selected field from menu list
//     * @return
//     */
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.users_icon:
//                Intent intent = new Intent(this, UserListActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.logout_icon:
//                FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
//                mFirebaseAuth.signOut();
//                Intent intent_logout = new Intent(this, LoginActivity.class);
//                intent_logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
//                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent_logout);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
