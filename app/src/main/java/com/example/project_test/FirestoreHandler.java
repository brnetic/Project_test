package com.example.project_test;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirestoreHandler {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<StudyGroup> studyGroups;

    private ArrayList<StudySession> studySessions;
    private ArrayList<ChatMessage> chatMessages;

    FirestoreHandler(){

    }

    public ArrayList<StudyGroup> getStudyGroups(User user){
        db.collection("studygroups").whereArrayContains("members",user.getId())
                .get()
                .addOnCompleteListener(task->{


                        if (task.isSuccessful()) {
                            // Document exists, retrieve data

                            QuerySnapshot querySnapshot = task.getResult();
                            if(querySnapshot != null){
                                for(QueryDocumentSnapshot document: querySnapshot){
                                    studyGroups.add(document.toObject(StudyGroup.class));
                                }
                            }

                        } else {
                            // Document does not exist
                            Log.d("Firestore", "No such document");
                            studyGroups = null;
                        }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error getting document", e);
                    }
                });

        return studyGroups;
    }


    public ArrayList<StudySession> getStudySessions(StudyGroup studyGroup){
        db.collection("studysessions").whereEqualTo("studyGroup",studyGroup)
                .get()
                .addOnCompleteListener(task->{


                    if (task.isSuccessful()) {
                        // Document exists, retrieve data

                        QuerySnapshot querySnapshot = task.getResult();
                        if(querySnapshot != null){
                            for(QueryDocumentSnapshot document: querySnapshot){

                                studySessions.add(document.toObject(StudySession.class));
                            }
                        }

                    } else {
                        // Document does not exist
                        Log.d("Firestore", "No such document");
                        studySessions = null;
                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error getting document", e);
                    }
                });
        return studySessions;
    }
    public void createNewStudyGroup(StudyGroup studyGroup) {
        db.collection("studygroups").add(studyGroup).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("FirestoreHandler", "Succesfully saved a Study Group");
            } else {
                Log.d("FirestoreHandler", "Error with saving a Study Group");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Falire with saving a Study Group", e);
            }
        });
    }
    public void createNewStudySession(StudySession studySession){
        db.collection("studysessions").add(studySession).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("FirestoreHandler", "Succesfully saved a Study Group");
            } else {
                Log.d("FirestoreHandler", "Error with saving a Study Group");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Falire with saving a Study Group", e);
            }
        });
    }
    public void saveChatMessage(ChatMessage chatMessage){
        db.collection("chatmessages").add(chatMessage).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("FirestoreHandler", "Succesfully saved a Chat Message");
            } else {
                Log.d("FirestoreHandler", "Error with saving a Chat Message");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Failure with saving a Chat Message", e);
            }
        });
    }
    public ArrayList<ChatMessage> loadChatMessage(String studyGroupId){
        db.collection("chatmessages").whereEqualTo("groupId",studyGroupId)
                .get()
                .addOnCompleteListener(task->{


                    if (task.isSuccessful()) {
                        // Document exists, retrieve data

                        QuerySnapshot querySnapshot = task.getResult();
                        if(querySnapshot != null){
                            for(QueryDocumentSnapshot document: querySnapshot){

                                chatMessages.add(document.toObject(ChatMessage.class));
                            }
                        }

                    } else {
                        // Document does not exist
                        Log.d("Firestore", "No such document");
                        chatMessages = null;
                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error getting document", e);
                    }
                });
        return chatMessages;

    }


}
