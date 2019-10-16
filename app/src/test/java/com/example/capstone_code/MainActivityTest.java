package com.example.capstone_code;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})

public class MainActivityTest {

    private DatabaseReference mockedDatabaseReference;

    @Before
    public void setUp() {

        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
    }

    @Test
    public void onCreateTest() {
        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);

//        onAuthStateChanged(new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];
//
//                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
//                //when(mockedDataSnapshot.getValue(User.class)).thenReturn(testOrMockedUser)
//
//                valueEventListener.onDataChange(mockedDataSnapshot);
//                //valueEventListener.onCancelled(...);
//
//                return null;
//            }
//        }).when(mockedDatabaseReference).addListenerForSingleValueEvent(any(ValueEventListener.class));
//
//        new LoginActivity().getSignedInUserProfile();
//
//        // check preferences are updated
    }
}